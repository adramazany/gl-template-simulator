package dp.gl.gltemplatesimulator.service;

import com.google.gson.Gson;
import dp.gl.gltemplatesimulator.dto.ArticleDTO;
import dp.gl.gltemplatesimulator.dto.TemplateDTO;
import dp.gl.gltemplatesimulator.model.*;
import dp.gl.gltemplatesimulator.repository.EventRepository;
import dp.gl.gltemplatesimulator.repository.TemplateRepository;
import dp.gl.gltemplatesimulator.util.NVL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.script.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    DBLookupService dbLookupService;

    @Autowired
    ArticleService articleService;

    @Value("${gl.scriptEngineName:JavaScript}")
    private String scriptEngineName;

    private String sqlFindAllDTO="select t.* ,e.name event_name,e.json_sample_business_agreed,e.voucher_voucher_type_formula,e.voucher_description_formula\n" +
            "from templates t\n" +
            "join events e on e.id=t.f_event\n";

    private String sqlFindAllByf_eventid="select t.* ,e.name event_name,e.json_sample_business_agreed,e.voucher_voucher_type_formula,e.voucher_description_formula\n" +
            " from templates t\n" +
            " join events e on e.id=t.f_event\n " +
            " where f_event=:f_event";

    Gson gson = new Gson();

    public List<TemplateDTO> findAll(){
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
//        paramSource.addValue("biz_id",biz_id);
//        paramSource.addValue("profileCode",profileCode);
        List<TemplateDTO> result = jdbcTemplate.query(sqlFindAllDTO, paramSource, new BeanPropertyRowMapper<TemplateDTO>(TemplateDTO.class)  );
        return result;
    }

    public List<TemplateDTO> findAllByf_event(Integer f_event){
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("f_event",f_event);
        List<TemplateDTO> result = jdbcTemplate.query(sqlFindAllByf_eventid, paramSource, new BeanPropertyRowMapper<TemplateDTO>(TemplateDTO.class)  );
        return result;
    }

    public void saveOrUpdate(TemplateDTO templateDTO){
        Template template;
        if(templateDTO.getId()!=null){
            template=templateRepository.findById(templateDTO.getId()).get();
            templateDTO.setVersion(NVL.getInt(templateDTO.getVersion())+1);
            templateDTO.setLast_modify_date(new Timestamp(System.currentTimeMillis()));
        }else{
            template=new Template();
            templateDTO.setVersion(1);
            templateDTO.setCreate_date(new Timestamp(System.currentTimeMillis()));
        }
        if(templateDTO.getCreate_date()==null){
            templateDTO.setCreate_date(new Timestamp(System.currentTimeMillis()));
        }

        BeanUtils.copyProperties(templateDTO,template);
        templateRepository.save(template);
        templateDTO.setId(template.getId());


        Event event = eventRepository.findById(template.getF_event()).get();
        event.setJson_sample_business_agreed(templateDTO.getJson_sample_business_agreed());
        event.setVoucher_description_formula(templateDTO.getVoucher_description_formula());
        event.setVoucher_voucher_type_formula(templateDTO.getVoucher_voucher_type_formula());
        eventRepository.save(event);

    }

    public Article calculateTemplate(TransactionRequest req, Template template) throws ScriptException {
        System.out.println("TemplateService.calculateTemplate");

        ScriptEngine engine = new ScriptEngineManager().getEngineByName(scriptEngineName);
        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("req",req);
        bindings.put("baseAccount",template.getF_account_base());
        bindings.put("lookup",dbLookupService);

        boolean templateStatus = NVL.getBool( calculateTemplateItem(engine , template.getStatus_formula() ) );
        if(!templateStatus){
            System.out.println("templateStatus=false for:"+template);
            return null;
        }

        Article result = new Article();

        result.setF_account_leaf(NVL.getInteger( calculateTemplateItem(engine , template.getAccount_no_formula() ) ));
        result.setDebit(NVL.getInteger( calculateTemplateItem(engine , template.getDebit_formula() ) ));
        result.setCredit(NVL.getInteger( calculateTemplateItem(engine , template.getCredit_formula() ) ));
        result.setF_costcenter1(NVL.getInteger( calculateTemplateItem(engine , template.getCostcenter1_formula() ) ));
        result.setF_costcenter2(NVL.getInteger( calculateTemplateItem(engine , template.getCostcenter2_formula() ) ));
        result.setF_costcenter3(NVL.getInteger( calculateTemplateItem(engine , template.getCostcenter3_formula() ) ));
        result.setDescription(NVL.getString( calculateTemplateItem(engine , template.getDescription_formula() ) ));

        result.setCreate_date(new Date(System.currentTimeMillis()));
        result.setF_template(template.getId());
        result.setOrderno(template.getOrder_no());

        System.out.println("result = " + gson.toJson(result));

        return result;
    }

    private Object calculateTemplateItem(ScriptEngine engine,String script) throws ScriptException {
        Object result=null;
        if(script!=null && !"".equals(script.trim())){
            result = engine.eval("var reqJson=JSON.parse(req.json);\n"+script);
        }
        System.out.println("TemplateService.calculateTemplateItem:"+String.format("result:%s   <==  script:%s",result,script));
        return result;
    }

    public ArticleDTO calculateTemplate(TemplateDTO templateDTO) throws ScriptException {
        System.out.println("TemplateService.calculateTemplate");
        TransactionRequest req = new TransactionRequest();
        req.setJson(templateDTO.getJson_sample_business_agreed());

        Article article = calculateTemplate(req,templateDTO);
        if(article==null){
            return null;
        }

        ArticleDTO result = articleService.convertArticle2DTO(article);

        System.out.println("result = " + gson.toJson(result));

        return result;
    }

    public void deleteTemplate(Integer templateId){
        templateRepository.deleteById(templateId);
    }

    public List<ArticleDTO> calculateAllTemplates(Integer eventid,String json) throws ScriptException {
        System.out.println("TemplateService.calculateAllTemplates");

        List<ArticleDTO> result = new ArrayList<ArticleDTO>();

        Iterable<TemplateDTO> templates = findAllByf_event(eventid);
        for (Template template:templates) {
            TransactionRequest req = new TransactionRequest();
            req.setJson(json);

            Article article = calculateTemplate(req,template);
            if(article!=null){
                ArticleDTO dto = articleService.convertArticle2DTO(article);
                result.add(dto);
            }
        }

        System.out.println("result = " + gson.toJson(result));

        return result;

    }

}
