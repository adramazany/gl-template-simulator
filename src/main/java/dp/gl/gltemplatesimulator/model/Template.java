package dp.gl.gltemplatesimulator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Table("templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id ;

    String name;
    Integer f_event;
    Integer order_no;
    Integer f_account_base;
    String account_no_formula;
    String debit_formula;
    String credit_formula;
    String description_formula;
    String costcenter1_formula;
    String costcenter2_formula;
    String costcenter3_formula;
    Integer status;
    Integer version;
    Timestamp create_date;
    Timestamp last_modify_date;

    String status_formula;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getF_event() {
        return f_event;
    }

    public void setF_event(Integer f_event) {
        this.f_event = f_event;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public Integer getF_account_base() {
        return f_account_base;
    }

    public void setF_account_base(Integer f_account_base) {
        this.f_account_base = f_account_base;
    }

    public String getAccount_no_formula() {
        return account_no_formula;
    }

    public void setAccount_no_formula(String account_no_formula) {
        this.account_no_formula = account_no_formula;
    }

    public String getDebit_formula() {
        return debit_formula;
    }

    public void setDebit_formula(String debit_formula) {
        this.debit_formula = debit_formula;
    }

    public String getCredit_formula() {
        return credit_formula;
    }

    public void setCredit_formula(String credit_formula) {
        this.credit_formula = credit_formula;
    }

    public String getDescription_formula() {
        return description_formula;
    }

    public void setDescription_formula(String description_formula) {
        this.description_formula = description_formula;
    }

    public String getCostcenter1_formula() {
        return costcenter1_formula;
    }

    public void setCostcenter1_formula(String costcenter1_formula) {
        this.costcenter1_formula = costcenter1_formula;
    }

    public String getCostcenter2_formula() {
        return costcenter2_formula;
    }

    public void setCostcenter2_formula(String costcenter2_formula) {
        this.costcenter2_formula = costcenter2_formula;
    }

    public String getCostcenter3_formula() {
        return costcenter3_formula;
    }

    public void setCostcenter3_formula(String costcenter3_formula) {
        this.costcenter3_formula = costcenter3_formula;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getStatus_formula() {
        return status_formula;
    }

    public void setStatus_formula(String status_formula) {
        this.status_formula = status_formula;
    }
}
