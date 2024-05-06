package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by minh on 3/28/2017.
 */
public class UpdateManual {
    private Long id;
    private String title;
    private String content;
    private String file_name;
    private String file_path;
    private Long update_user_id;
    private String update_user_name;
    @JsonIgnore
    private MultipartFile manualFile;

    @JsonCreator
    public UpdateManual(
            @JsonProperty(value = "id", required = true) Long id,
            @JsonProperty(value = "title", required = true) String title,
            @JsonProperty(value = "content", required = true) String content,
            @JsonProperty(value = "file_name", required = true) String file_name,
            @JsonProperty(value = "file_path", required = true) String file_path,
            @JsonProperty(value = "update_user_id", required = true) Long update_user_id,
            @JsonProperty(value = "update_user_name", required = true) String update_user_name){
        this.id=id;
        this.title = title;
        this.content = content;
        this.file_name = file_name;
        this.file_path = file_path;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
    }


    public UpdateManual() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }


    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public MultipartFile getManualfile() {
        return manualFile;
    }

    public void setManualfile(MultipartFile manualFile) {
        this.manualFile = manualFile;
    }

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        AddUnitRequest addUnitRequest =new AddUnitRequest();

        try {
            obj.put("id", id);
            obj.put("title", title);
            obj.put("content", content);
            obj.put("file_name", file_name);
            obj.put("file_path", file_path);
            obj.put("update_user_id", "1");
            obj.put("update_user_name", "minhbq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
