package dp.gl.gltemplatesimulator.service;

import dp.gl.gltemplatesimulator.model.ProfileMapping;
import dp.gl.gltemplatesimulator.util.NVL;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MemoryLookupService")
public class MemoryLookupService implements LookupService{

    List<ProfileMapping> profileMappings=new ArrayList<>();

    public void setProfileMappings(List<ProfileMapping> profileMappings) {
        this.profileMappings = profileMappings;
    }

    private ProfileMapping find(String profileCode,Integer biz_id){
        for (ProfileMapping pm:profileMappings) {
            if(pm.getBiz_id()==biz_id
                    && pm.getProfile_code().equalsIgnoreCase(profileCode)){
                return pm;
            }
        }
        return null;
    }

    @Override
    public String account(Integer f_account_base,String profileCode, Integer biz_id) {
        ProfileMapping pm = find(profileCode,biz_id);
        if(pm!=null)return pm.getAccount_no();
        return null;
    }

    @Override
    public String costCenter(String profileCode, Integer biz_id) {
        ProfileMapping pm = find(profileCode,biz_id);
        if(pm!=null)return NVL.getString( pm.getId() );
        return null;
    }

    @Override
    public String profileMappingName(String profileCode, Integer biz_id) {
        ProfileMapping pm = find(profileCode,biz_id);
        if(pm!=null)return pm.getBiz_name();
        return null;
    }

    @Override
    public String account(String full_account_no) {
        throw new RuntimeException("not implemented yet!");
    }

    @Override
    public String costCenter(Integer f_costcenter_base, String profileCode, Integer biz_id) {
        throw new RuntimeException("Not Implemented yet!");
    }

    @Override
    public String costCenter(String code) {
        throw new RuntimeException("Not Implemented yet!");
    }

    @Override
    public String remain(Integer f_account) {
        throw new RuntimeException("Not Implemented yet!");
    }

    @Override
    public String account(Integer f_account_base, String profileCode, Integer biz_id, String biz_name) {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public String costCenter(Integer f_costcenter_base, String profileCode, Integer biz_id, String biz_name) {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public String profileMappingName(String profileCode, Integer biz_id, String biz_name) {
        throw new RuntimeException("Not implemented yet!");
    }

}
