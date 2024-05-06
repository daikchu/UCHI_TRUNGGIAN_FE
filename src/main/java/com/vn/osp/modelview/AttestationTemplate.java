package com.vn.osp.modelview;

import lombok.Data;

import java.util.Date;

@Data
public class AttestationTemplate {
    private Long id;
    private String name;
    private String kind_html;
    private String description;
    private int type;
    private int active_flg;
    private int type_org;
    private Long parent_id;
    private Long entry_user_id;
    private String entry_user_name;
    private Date entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private Date update_date_time;
}
