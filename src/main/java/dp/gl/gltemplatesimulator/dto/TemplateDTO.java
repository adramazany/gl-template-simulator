package dp.gl.gltemplatesimulator.dto;

import dp.gl.gltemplatesimulator.model.Template;

public class TemplateDTO extends Template {
    String event_name;
    String json_sample_business_agreed;
    String voucher_voucher_type_formula;
    String voucher_description_formula;


    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getJson_sample_business_agreed() {
        return json_sample_business_agreed;
    }

    public void setJson_sample_business_agreed(String json_sample_business_agreed) {
        this.json_sample_business_agreed = json_sample_business_agreed;
    }

    public String getVoucher_voucher_type_formula() {
        return voucher_voucher_type_formula;
    }

    public void setVoucher_voucher_type_formula(String voucher_voucher_type_formula) {
        this.voucher_voucher_type_formula = voucher_voucher_type_formula;
    }

    public String getVoucher_description_formula() {
        return voucher_description_formula;
    }

    public void setVoucher_description_formula(String voucher_description_formula) {
        this.voucher_description_formula = voucher_description_formula;
    }
}
