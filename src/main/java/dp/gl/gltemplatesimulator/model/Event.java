package dp.gl.gltemplatesimulator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Table("events")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer code;
  private Integer f_parent;
  private String hierarchy_id;
  private String name;
  private String json_sample_business_agreed;
  private String voucher_description_formula;
  private String voucher_voucher_type_formula;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJson_sample_business_agreed() {
    return json_sample_business_agreed;
  }

  public void setJson_sample_business_agreed(String json_sample_business_agreed) {
    this.json_sample_business_agreed = json_sample_business_agreed;
  }

  public String getVoucher_description_formula() {
    return voucher_description_formula;
  }

  public void setVoucher_description_formula(String voucher_description_formula) {
    this.voucher_description_formula = voucher_description_formula;
  }

  public String getVoucher_voucher_type_formula() {
    return voucher_voucher_type_formula;
  }

  public void setVoucher_voucher_type_formula(String voucher_voucher_type_formula) {
    this.voucher_voucher_type_formula = voucher_voucher_type_formula;
  }
}
