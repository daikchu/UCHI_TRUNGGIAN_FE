package com.vn.osp.modelview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CitizenVerifyOrders {
    private String order_id;

    private LocalDateTime order_time;

    private String verify_number;

    private String verify_fee;

    private String verify_fee_received;

    private String notary_office_code;

    private String notary_office_name;

    private String province_code;

    private String province_name;

    private String transaction_status;

    private String status_name;

    private String transaction_status_name;

    private String notary_office_excutor;

    private String notary_office_excutor_fullname;

    private String note;

    private String attach_files;

    private String update_by;

    private String update_by_name;

    private String payment_content;

    private String order_time_formatted;

    private String update_user_fullname;

    private List transaction_hists;
}
