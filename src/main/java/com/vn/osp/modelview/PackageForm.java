package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.List;

public class PackageForm {
    private Long id;
    private String code;
    private String name;
    private Long user_num;
    private String description;
    private String note;
    private List<Function> notarySystemManagerList;
    private List<Function> notaryfunctionList;
    private List<Function> notaryreportList;

    private String cb01;
    private String cb02;
    private String cb03;
    private String code_;
    private String name_;
    private String description_;
    private boolean success = true;
    private boolean successDelete = true;

    //    @JsonCreator
//    public PackageForm(@JsonProperty(value = "id",required = false) Long id,
//                   @JsonProperty(value = "code",required = false) String code,
//                   @JsonProperty(value = "name",required = false) String name,
//                   @JsonProperty(value = "user_num",required = false) Long user_num,
//                   @JsonProperty(value = "description",required = false) String description,
//                   @JsonProperty(value = "note",required = false) String note){
//        this.id = id;
//        this.code = code;
//        this.name = name;
//        this.user_num = user_num;
//        this.description = description;
//        this.note = note;
//
//    }
    public PackageForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_num() {
        return user_num;
    }

    public void setUser_num(Long user_num) {
        this.user_num = user_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Function> getNotarySystemManagerList() {
        return notarySystemManagerList;
    }

    public void setNotarySystemManagerList(List<Function> notarySystemManagerList) {
        this.notarySystemManagerList = notarySystemManagerList;
    }

    public List<Function> getNotaryfunctionList() {
        return notaryfunctionList;
    }

    public void setNotaryfunctionList(List<Function> notaryfunctionList) {
        this.notaryfunctionList = notaryfunctionList;
    }

    public List<Function> getNotaryreportList() {
        return notaryreportList;
    }

    public void setNotaryreportList(List<Function> notaryreportList) {
        this.notaryreportList = notaryreportList;
    }

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }

    public String getCb02() {
        return cb02;
    }

    public void setCb02(String cb02) {
        this.cb02 = cb02;
    }

    public String getCb03() {
        return cb03;
    }

    public void setCb03(String cb03) {
        this.cb03 = cb03;
    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccessDelete() {
        return successDelete;
    }

    public void setSuccessDelete(boolean successDelete) {
        this.successDelete = successDelete;
    }

    public void validate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");

        boolean code = StringUtil.checkSpecialCharacter(this.code);
        boolean name = StringUtil.checkSpecialCharacter(this.name);
        if (StringUtils.isBlank(this.code) || this.code == null || this.code.equals("")) {
            code_ = "Trường mã gói dịch vụ không được phép để trống";
            success = false;
        }
        if (!code) {
            code_ = "Mã gói dịch vụ không được chứa kí tự đặc biệt !";
            success = false;
        }
        if (StringUtils.isBlank(this.name)|| this.name == null || this.name.equals("")) {
            name_ = "Trường tên gói dịch vụ không được phép để trống";
            success = false;
        }
        if (!name) {
            name_ = "Tên gói dịch vụ không được chứa kí tự đặc biệt !";
            success = false;
        }
        if (this.code.length() > 200) {
            code_ = (truong + SystemMessageProperties.getSystemProperty("v3_package_code") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }
        if (this.name.length() > 200) {
            name_ = (truong + SystemMessageProperties.getSystemProperty("v3_package_name") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }
        if (description != null && description.length() > 1000) {
            description_ = (truong + SystemMessageProperties.getSystemProperty("v3_description") + max_length + EditString.SPACE + 1000 + ky_tu);
            success = false;
        }
    }

    public String generateAddJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
