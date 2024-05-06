package com.vn.osp.modelview;

import lombok.Data;

import java.util.Date;

@Data
public class Attestation {
    private Long id;
    private String name;
    private String code;
    private String kind_html;
    private String description;
    private Integer type;
    private Integer active_flg;
    private Integer type_org;
    private String parent_code;
    private Long entry_user_id;
    private String entry_user_name;
    private Date entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private Date update_date_time;
}
