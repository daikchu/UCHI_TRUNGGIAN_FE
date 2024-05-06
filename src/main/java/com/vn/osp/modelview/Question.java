package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.Date;

public class Question {
    private Long id;
    private String question;
    private String answer;
    private Integer type;
    private String entry_date_time;
    private String update_date_time;
    private Long entry_user_id;
    private Long update_user_id;

    @JsonIgnore
    private String question_;
    @JsonIgnore
    private String answer_;
    @JsonIgnore
    private String type_;
    @JsonIgnore
    private Boolean success;

    public Question() {
    }

    public Question(@JsonProperty(value = "id") final Long id,
                    @JsonProperty(value = "question") final String question,
                    @JsonProperty(value = "answer") final String answer,
                    @JsonProperty(value = "type")final Integer type,
                    @JsonProperty(value = "entry_date_time") final String entry_date_time,
                    @JsonProperty(value = "entry_user_id") final Long entry_user_id,
                    @JsonProperty(value = "update_user_id") final Long update_user_id,
                    @JsonProperty(value = "update_date_time") final String update_date_time) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.entry_date_time = entry_date_time;
        this.update_date_time = update_date_time;
        this.entry_user_id = entry_user_id;
        this.update_user_id = update_user_id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(String entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getQuestion_() {
        return question_;
    }

    public void setQuestion_(String question_) {
        this.question_ = question_;
    }

    public String getAnswer_() {
        return answer_;
    }

    public void setAnswer_(String answer_) {
        this.answer_ = answer_;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void validate() {

        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");


        if (StringUtils.isBlank(question)) {
            question_ = (truong + SystemMessageProperties.getSystemProperty("v3_contract_template_name") + notEmpty);
            success = false;
        }
        if (StringUtils.isBlank(answer)) {
            answer_ = (truong + SystemMessageProperties.getSystemProperty("v3_contract_template_name") + notEmpty);
            success = false;
        }
        /*if(code == null || code.equals("")){
            code_= (truong+ SystemMessageProperties.getSystemProperty("v3_contract_template_code")+ notEmpty);
            success= false;
        }*/


    }

    public String generateAddJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setEntry_user_id(CommonContext.user.getEntry_user_id());
        this.setUpdate_user_id(CommonContext.user.getEntry_user_id());
        try {
            System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUpdate_user_id(CommonContext.user.getUpdate_user_id());

//        if(this.kind_html==null){
//            this.setKind_html(" ");
//        }
//        if(this.office_code_==null){
//            this.setOffice_code(" ");
//        }
        try {
//            System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
