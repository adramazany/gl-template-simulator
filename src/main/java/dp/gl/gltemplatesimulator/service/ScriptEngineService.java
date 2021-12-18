package dp.gl.gltemplatesimulator.service;

import dp.gl.gltemplatesimulator.dto.TemplateDTO;
import dp.gl.gltemplatesimulator.model.Article;
import dp.gl.gltemplatesimulator.model.ProfileMapping;
import dp.gl.gltemplatesimulator.model.Template;
import dp.gl.gltemplatesimulator.model.TransactionRequest;
import dp.gl.gltemplatesimulator.util.NVL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.script.*;
import java.sql.Date;
import java.util.List;

@Service
public class ScriptEngineService {

    @Autowired
    MemoryLookupService memoryLookupService;

    //ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
    //ScriptEngine ee = new ScriptEngineManager().getEngineByName("GraalJS");
//    ScriptEngine ee = new ScriptEngineManager().getEngineByName("js");
//    ScriptEngine ee = new ScriptEngineManager().getEngineByName("GraalVM:js");
//    ScriptEngine ee = new ScriptEngineManager().getEngineByName("GraalVM:python");

    public Object evalByProfiles(TransactionRequest req, String script, List<ProfileMapping> profileMappings) throws ScriptException {
        ((MemoryLookupService)memoryLookupService).setProfileMappings(profileMappings);

        ScriptEngine ee = new ScriptEngineManager().getEngineByName("JavaScript");
        Bindings bindings = ee.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("req",req);
        bindings.put("lookup",memoryLookupService);
        return ee.eval("var reqJson=JSON.parse(req.json);\n"+script);
    }


}
