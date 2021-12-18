package dp.gl.gltemplatesimulator.model;

public class ProfileMapping {
    Integer id;
    String profile_code;
    Integer biz_id;
    String biz_name;
    String account_no;
    Integer f_profile;
    Integer f_request;
    Integer f_next_profile;

    public ProfileMapping() {
    }

    public ProfileMapping(String profile_code, Integer biz_id, String biz_name, String account_no) {
        this.profile_code = profile_code;
        this.biz_id = biz_id;
        this.biz_name = biz_name;
        this.account_no = account_no;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile_code() {
        return profile_code;
    }

    public void setProfile_code(String profile_code) {
        this.profile_code = profile_code;
    }

    public Integer getBiz_id() {
        return biz_id;
    }

    public void setBiz_id(Integer biz_id) {
        this.biz_id = biz_id;
    }

    public String getBiz_name() {
        return biz_name;
    }

    public void setBiz_name(String biz_name) {
        this.biz_name = biz_name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public Integer getF_next_profile() {
        return f_next_profile;
    }

    public void setF_next_profile(Integer f_next_profile) {
        this.f_next_profile = f_next_profile;
    }

    public Integer getF_profile() {
        return f_profile;
    }

    public void setF_profile(Integer f_profile) {
        this.f_profile = f_profile;
    }

    public Integer getF_request() {
        return f_request;
    }

    public void setF_request(Integer f_request) {
        this.f_request = f_request;
    }
}
