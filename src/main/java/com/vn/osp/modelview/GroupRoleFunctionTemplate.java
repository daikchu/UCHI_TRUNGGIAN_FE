package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupRoleFunctionTemplate {
    private Long id;
    private String group_role_code;
    private String group_role_name;
    private String function_code;

    @JsonCreator
    public GroupRoleFunctionTemplate(@JsonProperty(value = "id", required = false) final Long id,
                     @JsonProperty(value = "group_role_code", required = false) final String group_role_code,
                     @JsonProperty(value = "group_role_name", required = false) final String group_role_name,
                     @JsonProperty(value = "function_code", required = false) final String function_code){
        this.id = id;
        this.group_role_code = group_role_code;
        this.group_role_name = group_role_name;
        this.function_code = function_code;
    }
    public GroupRoleFunctionTemplate(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup_role_code() {
        return group_role_code;
    }

    public void setGroup_role_code(String group_role_code) {
        this.group_role_code = group_role_code;
    }

    public String getGroup_role_name() {
        return group_role_name;
    }

    public void setGroup_role_name(String group_role_name) {
        this.group_role_name = group_role_name;
    }

    public String getFunction_code() {
        return function_code;
    }

    public void setFunction_code(String function_code) {
        this.function_code = function_code;
    }
}
