package dp.gl.gltemplatesimulator.service;

import dp.gl.gltemplatesimulator.model.Account;
import dp.gl.gltemplatesimulator.model.Config;
import dp.gl.gltemplatesimulator.model.Costcenter;
import dp.gl.gltemplatesimulator.model.ProfileMapping;
import dp.gl.gltemplatesimulator.repository.AccountRepository;
import dp.gl.gltemplatesimulator.repository.ConfigRepository;
import dp.gl.gltemplatesimulator.repository.CostcenterRepository;
import dp.gl.gltemplatesimulator.util.NVL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("DBLookupService")
public class DBLookupService implements LookupService{
    static Logger logger = LoggerFactory.getLogger(DBLookupService.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ConfigRepository configRepository;

    @Autowired
    CostcenterRepository costcenterRepository;

    private String sqlFindOneByProfileCodeAndBizid="select m.*,p.code profile_code,p.f_next_profile from biz_mapping m" +
            " join biz_profile p on p.id=m.f_profile" +
            " where m.biz_id=:biz_id and p.code=:profileCode";

    private String sqlFindAccountByParentAndAccountno="select id from account" +
            " where f_parent=:f_parent and account_no=:account_no";

    private String sqlInsertNewAccount="insert into account (f_parent, hierarchy_id, account_no, full_account_no, account_name, account_type, account_origin, is_cutoff,f_profile,f_profile_mapping,version)\n" +
            " select\n" +
            "     id f_parent\n" +
            "     ,concat(hierarchy_id,'_',id) hierarchy_id\n" +
            "     ,:account_no as account_no\n" +
            "     ,concat(full_account_no,lpad(:account_no,:level_length,'0')) full_account_no\n" +
            "     ,:account_name account_name\n" +
            "     ,account_type\n" +
            "     ,account_origin\n" +
            "     ,is_cutoff\n" +
            "     ,:f_profile as f_profile" +
            "     ,:f_profile_mapping as f_profile_mapping" +
            "     ,0 as version" +
            " from account p where id=:f_parent\n";

    private String sqlInsertNewBizMapping="insert into biz_mapping (f_profile,biz_id,biz_name,account_no,f_request)\n" +
            " values (\n" +
            "     (select if from biz_profile where code=:profileCode) \n" +
            "     ,:biz_id\n" +
            "     ,:biz_name \n" +
            "     ,(select ifnull(max(account_no),0)+1 as account_no from biz_mapping m join biz_profile p on p.id=m.f_profile where p.code=:profileCode) account_no\n" +
            "     ,null f_request\n" +
            "     )\n";

    private String sqlFindCutoffYearLeafAccountRemain="select (debit-credit) as remain from cutoff_year cy" +
            " where cy.year=:year and cy.f_account=:f_account_leaf";


    private ProfileMapping find(String profileCode, Integer biz_id,String biz_name){
        logger.debug(String.format("find(%s,%d)",profileCode,  biz_id));

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("biz_id",biz_id);
        paramSource.addValue("profileCode",profileCode);
        ProfileMapping result = jdbcTemplate.queryForObject(sqlFindOneByProfileCodeAndBizid, paramSource, new BeanPropertyRowMapper<ProfileMapping>(ProfileMapping.class)  );

        if(result==null && biz_name!=null){
            paramSource.addValue("biz_name",biz_name);
            jdbcTemplate.update(sqlInsertNewBizMapping, paramSource);

            result = jdbcTemplate.queryForObject(sqlFindOneByProfileCodeAndBizid, paramSource, new BeanPropertyRowMapper<ProfileMapping>(ProfileMapping.class)  );
        }

        return result;
    }

    public String account(Integer f_account_base, String profileCode, Integer biz_id) {
        return account(f_account_base, profileCode, biz_id,null);
    }
    public String account(Integer f_account_base, String profileCode, Integer biz_id, String biz_name) {
        logger.debug(String.format("account(%d,%s,%d,%s)",f_account_base,profileCode,  biz_id,biz_name));

        Account accountBase = accountRepository.findById(f_account_base).get();

        ProfileMapping pm = find(profileCode,biz_id,biz_name);
        if(pm==null){
            throw new RuntimeException(String.format("پروفایلی با مشخصات {کد پروفایل:٪s , شناسه مپینگ بیزنس:٪d یافت نشد!}",profileCode,biz_id));
        }

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("f_parent",accountBase.getId());
        paramSource.addValue("account_no",pm.getAccount_no());
        List<Map<String,Object>> mapResult = jdbcTemplate.queryForList(sqlFindAccountByParentAndAccountno
                , paramSource);

        if(mapResult.size()>0) {
            return mapResult.get(0).get("id").toString();
        }else{
            logger.debug("account not Found then should create it");

            if(accountBase.getF_profile()!=null
            && accountBase.getF_profile()!=pm.getF_profile()){
                String msg = String.format("امکان اضافه کردن پروفایل %s زیر حساب ٪s وجود ندارد!",pm.getProfile_code(),accountBase.getFullAccountNo());
                throw new RuntimeException(msg);
            }

            long level = "".equals(NVL.getString(accountBase.getHierarchy_id())) ? 2 :  NVL.getString(accountBase.getHierarchy_id()).chars().filter(ch -> ch == '_').count()+3;
            Config config = configRepository.findByKey("account_code_count_level_"+level);

            paramSource.addValue("level_length",config.getValue());
            paramSource.addValue("account_name",pm.getBiz_name());
            paramSource.addValue("f_profile",pm.getF_next_profile());
            paramSource.addValue("f_profile_mapping",pm.getId());
            KeyHolder keyHolder=new GeneratedKeyHolder();
            jdbcTemplate.update(sqlInsertNewAccount
                    , paramSource
                    , keyHolder );
            return keyHolder.getKey().toString();
        }
    }

    @Override
    public String costCenter(String profileCode, Integer biz_id) {
        return costCenter(profileCode,  biz_id,null);
    }
    public String costCenter(String profileCode, Integer biz_id,String biz_name) {
        logger.debug(String.format("costCenter(%s,%d,%s)",profileCode,  biz_id,biz_name));

        ProfileMapping pm = find(profileCode,biz_id,biz_name);
        if(pm==null)throw new RuntimeException(String.format("پروفایلی با مشخصات {کد پروفایل:٪s , شناسه مپینگ بیزنس:٪d یافت نشد!}",profileCode,biz_id));

        Costcenter costcenter = costcenterRepository.findByCode(pm.getId());
        if(costcenter==null){
            logger.debug("costCenter not Found then should create it");

            costcenter = new Costcenter();
            costcenter.setCode(pm.getId().toString());
            costcenter.setHierarchy_id(null);
            costcenter.setName(pm.getBiz_name());
            costcenter.setStatus(1);
            costcenter.setVersion(0);
            costcenterRepository.save(costcenter);
        }
        return costcenter.getId().toString();
    }

    @Override
    public String profileMappingName(String profileCode, Integer biz_id) {
        return profileMappingName(profileCode,  biz_id,null);
    }
    public String profileMappingName(String profileCode, Integer biz_id,String biz_name) {
        logger.debug(String.format("profileMappingName(%s,%d,%s)",profileCode,  biz_id,biz_name));

        ProfileMapping pm = find(profileCode,biz_id,biz_name);
        if(pm!=null)return pm.getBiz_name();
        return null;
    }

    @Override
    public String account(String full_account_no) {
        logger.debug(String.format("account(%s)",full_account_no));

        Account accountBase = accountRepository.findByFullAccountNo(full_account_no);
        return accountBase.getId().toString();
    }

    public String costCenter(String code) {
        logger.debug(String.format("costCenter(%s)",code));
        Costcenter costcenterBase = costcenterRepository.findByCode(NVL.getInt(code));
        return costcenterBase.getId().toString();
    }

    @Override
    public String costCenter(Integer f_costcenter_base, String profileCode, Integer biz_id) {
        return costCenter(f_costcenter_base,  profileCode,  biz_id,null);
    }
    public String costCenter(Integer f_costcenter_base, String profileCode, Integer biz_id, String biz_name) {
        logger.debug(String.format("costCenter(%d,%s,%d,%s)",f_costcenter_base,profileCode,  biz_id,biz_name));

        Costcenter costcenterBase = costcenterRepository.findById(f_costcenter_base).get();

        ProfileMapping pm = find(profileCode,biz_id,biz_name);
        if(pm==null)throw new RuntimeException(String.format("پروفایلی با مشخصات {کد پروفایل:٪s , شناسه مپینگ بیزنس:٪d یافت نشد!}",profileCode,biz_id));

        Costcenter costcenter = costcenterRepository.findByCode(pm.getId());
        if(costcenter==null){
            logger.debug("costCenter not Found then should create it");

            costcenter = new Costcenter();
            costcenter.setCode(pm.getId().toString());
            costcenter.setHierarchy_id(null);
            costcenter.setName(pm.getBiz_name());
            costcenter.setStatus(1);
            costcenter.setVersion(0);
            costcenter.setF_parent(f_costcenter_base);
            if(costcenterBase.getHierarchy_id()==null){
                costcenter.setHierarchy_id(""+f_costcenter_base);
            }else{
                costcenter.setHierarchy_id(costcenterBase.getHierarchy_id()+"_"+f_costcenter_base);
            }
            costcenterRepository.save(costcenter);
        }
        return costcenter.getId().toString();

    }

    @Override
    public String remain(Integer f_account_leaf) {
        logger.debug(String.format("remain(%d)",f_account_leaf));

        Config config = configRepository.findByKey("current_financial_year");

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("year",NVL.getInt(config.getValue()));
        paramSource.addValue("f_account_leaf",f_account_leaf);
        String result = jdbcTemplate.queryForObject(sqlFindCutoffYearLeafAccountRemain, paramSource,String.class);
        return result;
    }


}
