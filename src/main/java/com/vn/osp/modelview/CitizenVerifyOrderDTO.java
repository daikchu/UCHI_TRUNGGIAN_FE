package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.json.JSONObject;

import java.util.List;

@Data
public class CitizenVerifyOrderDTO {
    private String order_id;
    private String transaction_status;
    private String status;
    private String note;
    private String files_id_remove;
    private List<String> file_names;
    private List<String> file_paths;
//    private List attach_files;
    private String update_user_name;

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
