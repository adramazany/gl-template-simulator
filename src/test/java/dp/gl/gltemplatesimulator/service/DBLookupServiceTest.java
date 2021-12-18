package dp.gl.gltemplatesimulator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ContextConfiguration(loader = HeadlessSpringBootContextLoader.class)
class DBLookupServiceTest {

    @Autowired
    DBLookupService dbLookupService;

    @Test
    void findAccount() {
//        String profileCode="seller";
        String profileCode="loantype";
        Integer biz_id=1;
        String result = dbLookupService.account(1,profileCode,biz_id);
        System.out.println("result = " + result);
    }

    @Test
    void findCostcenter() {
    }

    @Test
    void findName() {
    }
}