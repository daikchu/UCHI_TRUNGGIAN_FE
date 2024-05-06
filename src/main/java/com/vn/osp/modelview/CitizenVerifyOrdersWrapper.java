package com.vn.osp.modelview;

import com.vn.osp.common.util.PagingResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CitizenVerifyOrdersWrapper {
    private List<CitizenVerifyOrders> citizenVerifyOrders;
    private int total;
    private int page=1;
    private int totalPage;
//    private PagingResult page;

    private String order_id;
    private String province_code;
    private String notary_office_code;
    private String transaction_status;
    private String status;
    private String update_by;
    private String order_time_from;
    private String order_time_to;
    private String update_user_name;

    private List<Province> provinces;
    private List<Customer> notary_offices;
    private List<Status> transaction_statuses;
    private List<Status> statuses;
    private List user_updates;

    private boolean isOpenDetail;
    private String idOpenDetail;

}
