package dp.gl.gltemplatesimulator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import java.sql.Timestamp;

@Table
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer f_parent;
    private String  hierarchy_id;
    private String  account_no;
//    private String  full_account_no;
    private String  fullAccountNo;
    private String  account_name;
//    private Integer inherited;
    private Boolean inherited;
    private Integer status;
    private Integer account_type;
    private Integer account_origin;
//    private Integer is_cutoff;
    private Boolean is_cutoff;
    private Integer version;
    private Timestamp create_date;
    private Timestamp last_modify_date;

    private Integer f_profile;
    private Integer f_profile_mapping;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getF_parent() {
        return f_parent;
    }

    public void setF_parent(Integer f_parent) {
        this.f_parent = f_parent;
    }

    public String getHierarchy_id() {
        return hierarchy_id;
    }

    public void setHierarchy_id(String hierarchy_id) {
        this.hierarchy_id = hierarchy_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

//    public String getFull_account_no() {
//        return full_account_no;
//    }
//
//    public void setFull_account_no(String full_account_no) {
//        this.full_account_no = full_account_no;
//    }


    public String getFullAccountNo() {
        return fullAccountNo;
    }

    public void setFullAccountNo(String fullAccountNo) {
        this.fullAccountNo = fullAccountNo;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Boolean getInherited() {
        return inherited;
    }

    public void setInherited(Boolean inherited) {
        this.inherited = inherited;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAccount_type() {
        return account_type;
    }

    public void setAccount_type(Integer account_type) {
        this.account_type = account_type;
    }

    public Integer getAccount_origin() {
        return account_origin;
    }

    public void setAccount_origin(Integer account_origin) {
        this.account_origin = account_origin;
    }

    public Boolean getIs_cutoff() {
        return is_cutoff;
    }

    public void setIs_cutoff(Boolean is_cutoff) {
        this.is_cutoff = is_cutoff;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getLast_modify_date() {
        return last_modify_date;
    }

    public void setLast_modify_date(Timestamp last_modify_date) {
        this.last_modify_date = last_modify_date;
    }

    public Integer getF_profile() {
        return f_profile;
    }

    public void setF_profile(Integer f_profile) {
        this.f_profile = f_profile;
    }

    public Integer getF_profile_mapping() {
        return f_profile_mapping;
    }

    public void setF_profile_mapping(Integer f_profile_mapping) {
        this.f_profile_mapping = f_profile_mapping;
    }
}
