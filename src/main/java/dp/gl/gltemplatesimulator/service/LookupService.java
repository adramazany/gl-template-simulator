package dp.gl.gltemplatesimulator.service;

public interface LookupService {
    String account(Integer f_account_base,String profileCode,Integer biz_id);
    String costCenter(String profileCode,Integer biz_id);
    String profileMappingName(String profileCode,Integer biz_id);
    String account(String full_account_no);

    String costCenter(Integer f_costcenter_base, String profileCode,Integer biz_id);
    String costCenter(String code);

    String remain(Integer f_account_leaf);

    String account(Integer f_account_base,String profileCode,Integer biz_id,String biz_name);
    String costCenter(Integer f_costcenter_base, String profileCode,Integer biz_id,String biz_name);
    String profileMappingName(String profileCode,Integer biz_id,String biz_name);

}
