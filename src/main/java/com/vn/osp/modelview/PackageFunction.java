package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageFunction {
    private Long id;
    private String package_code;
    private String function_code;

    @JsonCreator
    public PackageFunction(@JsonProperty(value = "id", required = false) Long id,
                               @JsonProperty(value = "package_code", required = false) String package_code,
                               @JsonProperty(value = "function_code", required = false) String function_code){
        this.id = id;
        this.package_code = package_code;
        this.function_code = function_code;
    }

    public PackageFunction(){
    }

    public Long getSId() {
        return id;
    }

    public void setSId(Long id) {
        this.id = id;
    }

    public String getPackage_code() {
        return package_code;
    }

    public void setPackage_code(String package_code) {
        this.package_code = package_code;
    }

    public String getFunction_code() {
        return function_code;
    }

    public void setFunction_code(String function_code) {
        this.function_code = function_code;
    }
}
