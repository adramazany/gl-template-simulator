package dp.gl.gltemplatesimulator.service;

import dp.gl.gltemplatesimulator.model.Test;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public Test findOne(){
        return new Test(1,"ali");
    }
}
