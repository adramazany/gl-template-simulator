package dp.gl.gltemplatesimulator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;

import java.sql.Timestamp;

@Table("cutoff_year")
public class CutoffYear {

    @Id
    @GeneratedValue
    Integer     id;
    Integer     credit;
    Integer     debit;
    Timestamp   last_modify_date;
    Integer     version;
    Integer     year;
    Integer     f_account;
    Integer     f_user_modify;
    Timestamp   create_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Timestamp getLast_modify_date() {
        return last_modify_date;
    }

    public void setLast_modify_date(Timestamp last_modify_date) {
        this.last_modify_date = last_modify_date;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getF_account() {
        return f_account;
    }

    public void setF_account(Integer f_account) {
        this.f_account = f_account;
    }

    public Integer getF_user_modify() {
        return f_user_modify;
    }

    public void setF_user_modify(Integer f_user_modify) {
        this.f_user_modify = f_user_modify;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
}
