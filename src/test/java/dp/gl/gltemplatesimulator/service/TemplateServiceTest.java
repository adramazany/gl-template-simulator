package dp.gl.gltemplatesimulator.service;

import com.google.gson.Gson;
import dp.gl.gltemplatesimulator.dto.TemplateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TemplateServiceTest {

    @Autowired
    TemplateService templateService;

    Gson gson=new Gson();

    @Test
    void testFindAll() {
        List<TemplateDTO> result = templateService.findAll();
        System.out.println("result = " + gson.toJson( result ));
    }

    @Test
    void saveOrUpdate() {
        TemplateDTO dto = new TemplateDTO();
        //dto.setId(5);
        dto.setName("الگوی تست 10");
        dto.setF_event(1000);
        dto.setOrder_no(10);
        dto.setF_account_base(1);
        dto.setStatus(1);

        templateService.saveOrUpdate(dto);
    }
}