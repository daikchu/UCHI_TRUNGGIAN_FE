package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Package {
    private Long id;
    private String code;
    private String name;
    private Long user_num;
    private String description;
    private String note;

    @JsonCreator
    public Package(@JsonProperty(value = "sid",required = false) Long id,
                   @JsonProperty(value = "code",required = false) String code,
                   @JsonProperty(value = "name",required = false) String name,
                   @JsonProperty(value = "user_num",required = false) Long user_num,
                   @JsonProperty(value = "description",required = false) String description,
                   @JsonProperty(value = "note",required = false) String note){
        this.id = id;
        this.code = code;
        this.name = name;
        this.user_num = user_num;
        this.description = description;
        this.note = note;

    }
    public Package(){
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
}
