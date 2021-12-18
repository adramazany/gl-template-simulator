package dp.gl.gltemplatesimulator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Integer f_voucher;
    Integer f_template;
    Integer orderno;
    Integer f_account_leaf;
    Integer debit;
    Integer credit;
    String  description;
    Integer f_costcenter1;
    Integer f_costcenter2;
    Integer f_costcenter3;
    Integer version;
    Date    create_date;
    Date    last_modify_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getF_voucher() {
        return f_voucher;
    }

    public void setF_voucher(Integer f_voucher) {
        this.f_voucher = f_voucher;
    }

    public Integer getF_template() {
        return f_template;
    }

    public void setF_template(Integer f_template) {
        this.f_template = f_template;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getF_account_leaf() {
        return f_account_leaf;
    }

    public void setF_account_leaf(Integer f_account_leaf) {
        this.f_account_leaf = f_account_leaf;
    }

    public Integer getDebit() {
        return debit;
    }

    public void setDebit(Integer debit) {
        this.debit = debit;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getF_costcenter1() {
        return f_costcenter1;
    }

    public void setF_costcenter1(Integer f_costcenter1) {
        this.f_costcenter1 = f_costcenter1;
    }

    public Integer getF_costcenter2() {
        return f_costcenter2;
    }

    public void setF_costcenter2(Integer f_costcenter2) {
        this.f_costcenter2 = f_costcenter2;
    }

    public Integer getF_costcenter3() {
        return f_costcenter3;
    }

    public void setF_costcenter3(Integer f_costcenter3) {
        this.f_costcenter3 = f_costcenter3;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_modify_date() {
        return last_modify_date;
    }

    public void setLast_modify_date(Date last_modify_date) {
        this.last_modify_date = last_modify_date;
    }
}
