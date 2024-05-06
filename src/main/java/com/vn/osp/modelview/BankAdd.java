package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

/**
 * Created by Admin on 31/5/2017.
 */
public class BankAdd {

    private String name;
    private Long entry_user_id;
    private String entry_user_name;
    private String code;
    private Long active;
    private String name_;
    private String code_;
    private Boolean success;

    public BankAdd() {
    }

    public BankAdd(String name, Long entry_user_id, String entry_user_name, String code, Long active, String name_, String code_, Boolean success) {
        this.name = name;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.code = code;
        this.active = active;
        this.name_ = name_;
        this.code_ = code_;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
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
            name_ = ( truong + SystemMessageProperties.getSystemProperty("v3_province_name")+ notEmpty);
            success= false;
        }
        if(code == null || code.equals("")){
            code_= (truong+ SystemMessageProperties.getSystemProperty("v3_province_code")+ notEmpty);
            success= false;
        }

    }
    public String getOrderString(){


        String orderString2 = "";


        if(code!= null && !code.equals("")){

            orderString2 = "where code ='"+code+"'";
        }

        String query =orderString2 ;
        return query;
    }
    public String generateAddJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setEntry_user_id(CommonContext.user.getEntry_user_id());
        this.setEntry_user_name(CommonContext.user.getEntry_user_name());
        try {
            System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
