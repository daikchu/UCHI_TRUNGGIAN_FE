package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Admin on 2/6/2017.
 */
public class ContractTempAdd {
    private String name;
    private String code;
    private String description;
    private Long active_flg;
    private Long relate_object_number;
    private String relate_object_A_display;
    private String relate_object_B_display;
    private String relate_object_C_display;
    private Long period_flag;
    private Long mortage_cancel_func;
    private Long entry_user_id;
    private String entry_user_name;
    private String kind_html;
    private String office_code;
    private Long code_template;
    private Long kind_id;//sonnv them
    @JsonIgnore
    private List<ContractKind> listContractKind;
    @JsonIgnore
    private List<ContractTemp> listContractTemp;
    @JsonIgnore
    private String name_;
    @JsonIgnore
    private String code_;
    @JsonIgnore
    private String description_;
    @JsonIgnore
    private String relate_object_number_;
    @JsonIgnore
    private String office_code_;
    @JsonIgnore
    private Boolean success;

    public ContractTempAdd() {
    }

    public ContractTempAdd(String name, String code, String description, Long active_flg, Long relate_object_number, Long period_flag, Long mortage_cancel_func, Long entry_user_id, String entry_user_name, String kind_html, String office_code,Long code_template,Long kind_id, List<ContractKind> listContractKind, String name_, String code_, String description_, String relate_object_number_, String office_code_, Boolean success) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.active_flg = active_flg;
        this.relate_object_number = relate_object_number;
        this.period_flag = period_flag;
        this.mortage_cancel_func = mortage_cancel_func;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.kind_html = kind_html;
        this.office_code = office_code;
        this.code_template=code_template;
        this.kind_id=kind_id;
        this.listContractKind = listContractKind;
        this.name_ = name_;
        this.code_ = code_;
        this.description_ = description_;
        this.relate_object_number_ = relate_object_number_;
        this.office_code_ = office_code_;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getRelate_object_number() {
        return relate_object_number;
    }

    public void setRelate_object_number(Long relate_object_number) {
        this.relate_object_number = relate_object_number;
    }

    public Long getPeriod_flag() {
        return period_flag;
    }

    public void setPeriod_flag(Long period_flag) {
        this.period_flag = period_flag;
    }

    public Long getMortage_cancel_func() {
        return mortage_cancel_func;
    }

    public void setMortage_cancel_func(Long mortage_cancel_func) {
        this.mortage_cancel_func = mortage_cancel_func;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }

    public String getKind_html() {
        return kind_html;
    }

    public void setKind_html(String kind_html) {
        this.kind_html = kind_html;
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public Long getCode_template() {
        return code_template;
    }

    public void setCode_template(Long code_template) {
        this.code_template = code_template;
    }

    public List<ContractKind> getListContractKind() {
        return listContractKind;
    }

    public void setListContractKind(List<ContractKind> listContractKind) {
        this.listContractKind = listContractKind;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getRelate_object_number_() {
        return relate_object_number_;
    }

    public void setRelate_object_number_(String relate_object_number_) {
        this.relate_object_number_ = relate_object_number_;
    }

    public String getOffice_code_() {
        return office_code_;
    }

    public void setOffice_code_(String office_code_) {
        this.office_code_ = office_code_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public void validate(){

        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");


        if(name == null || name.equals("")){
            name_ = ( truong + SystemMessageProperties.getSystemProperty("v3_contract_template_name")+ notEmpty);
            success= false;
        }
        if(code == null || code.equals("")){
            code_= (truong+ SystemMessageProperties.getSystemProperty("v3_contract_template_code")+ notEmpty);
            success= false;
        }



    }

    public String generateAddJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setEntry_user_id(CommonContext.user.getEntry_user_id());
        this.setEntry_user_name(CommonContext.user.getEntry_user_name());
        if(this.relate_object_number == null)
        {
            this.setRelate_object_number(Long.valueOf(2));
        }
//        if(this.kind_html==null){
//            this.setKind_html("");
//        }
//        if(this.office_code_==null){
//            this.setOffice_code("");
//        }
        try {
//            System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRelate_object_A_display() {
        return relate_object_A_display;
    }

    public void setRelate_object_A_display(String relate_object_A_display) {
        this.relate_object_A_display = relate_object_A_display;
    }

    public String getRelate_object_B_display() {
        return relate_object_B_display;
    }

    public void setRelate_object_B_display(String relate_object_B_display) {
        this.relate_object_B_display = relate_object_B_display;
    }

    public String getRelate_object_C_display() {
        return relate_object_C_display;
    }

    public void setRelate_object_C_display(String relate_object_C_display) {
        this.relate_object_C_display = relate_object_C_display;
    }

    public List<ContractTemp> getListContractTemp() {
        return listContractTemp;
    }

    public void setListContractTemp(List<ContractTemp> listContractTemp) {
        this.listContractTemp = listContractTemp;
    }

    public Long getKind_id() {
        return kind_id;
    }

    public void setKind_id(Long kind_id) {
        this.kind_id = kind_id;
    }
}
