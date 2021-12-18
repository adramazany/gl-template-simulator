package dp.gl.gltemplatesimulator.dto;

import dp.gl.gltemplatesimulator.model.Article;

public class ArticleDTO extends Article {

    String account_no;
    String full_account_no;
    String account_name;
    String costcenter1;
    String costcenter2;
    String costcenter3;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getFull_account_no() {
        return full_account_no;
    }

    public void setFull_account_no(String full_account_no) {
        this.full_account_no = full_account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getCostcenter1() {
        return costcenter1;
    }

    public void setCostcenter1(String costcenter1) {
        this.costcenter1 = costcenter1;
    }

    public String getCostcenter2() {
        return costcenter2;
    }

    public void setCostcenter2(String costcenter2) {
        this.costcenter2 = costcenter2;
    }

    public String getCostcenter3() {
        return costcenter3;
    }

    public void setCostcenter3(String costcenter3) {
        this.costcenter3 = costcenter3;
    }
}
