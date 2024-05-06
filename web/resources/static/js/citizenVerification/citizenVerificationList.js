var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('citizenVerificationController',['$scope','$http','$window','$sce', 'fileUpload' ,function ($scope,$http,$window,$sce, fileUpload) {
    //declare

    $scope.searchConditionOrigin = {
        verify_id:$("#verify_id").val(),
        citizen_verify_fullname: $("#citizen_verify_fullname").val(),
        citizen_verify_cccd: $("#citizen_verify_cccd").val(),
        province_code: $("#search_province_code").val(),
        notary_office_id: $("#search_notary_office_id").val(),
        verify_by: $("#search_verify_by").val(),
        verify_status:$("#search_verify_status").val(),
        verify_date_from: $("#verify_date_from").val(),
        verify_date_to: $("#verify_date_to").val()
    };

    $scope.searchCondition = {
        verify_id:"",
        citizen_verify_fullname: "",
        citizen_verify_cccd: "",
        province_code: $("#search_province_code").val(),
        notary_office_id: $("#search_notary_office_id").val(),
        verify_by: $("#search_verify_by").val(),
        verify_status:$("#search_verify_status").val(),
        verify_date_from: "",
        verify_date_to: ""
    };

    $scope.searchConditionMessage = {
        verify_date_from: "",
        verify_date_to: ""
    };
    $scope.citizenVerification = {
        verify_id: "",
        transaction_id: "",
        notary_office_id: "",
        province_code: "",
        verify_date: null,
        verify_by: "",
        verify_status: "",
        citizen_verify_cccd: "",
        citizen_verify_fullname: "",
        citizen_info: "",
        notary_office_name: "",
        province_name: "",
        verify_status_name: "",
        verify_by_name: ""
    };


    $scope.paymentTransactionMessage = {
        status: "",
        transaction_status: "",
        note: "",
        attach_files: ""
    };

    $scope.provinces = [];
    $scope.offices = [];
    $scope.verifyExcutors = [];
    $scope.transactionStatuses = [];
    $scope.additionalStatuses = [];

    //init
    function getAllProvince(){
        $http.get(ospApiUrl + "/province")
            .then(function (response) {
                if(response.status === 200){
                    $scope.provinces = response.data;
                }
            });
    }

    $scope.getNotaryOfficeByProvince = function() {
        $http.get(ospApiUrl + "/customer?province_code="+$scope.searchCondition.province_code)
            .then(function (response) {
                if(response.status === 200){
                    $scope.offices = response.data;
                }
            });
    };

    $scope.getVerifyExcutors = function() {
        $http.get(ospApiUrl + "/citizen-verifications/retrieve-accounts?province_code="+$scope.searchCondition.province_code
            +"&notary_office_code="+$scope.searchCondition.notary_office_id)
            .then(function (response) {
                if(response.status === 200){
                    $scope.verifyExcutors = response.data;
                }
            });
    };


    getAllProvince();
    $scope.getNotaryOfficeByProvince();
    $scope.getVerifyExcutors();
    //todo get can bo thuc hien


    //END init

    $scope.onSearch = function() {
        $("#search_province_code").val($scope.searchCondition.province_code);
        $("#search_notary_office_id").val($scope.searchCondition.notary_office_id);
        $("#search_verify_status").val($scope.searchCondition.verify_status);
        $("#search_verify_by").val($scope.searchCondition.verify_by);
        if(!validateSearchCondition()) return;
        $("#page").val("1");
        $("#search-frm").submit();
    };

    function validateSearchCondition() {
        $scope.searchConditionMessage.verify_date_from = "";
        $scope.searchConditionMessage.verify_date_to = "";
        var isValid = true;
        var dateFromObj = getDateObjectFromDateStr($("#verify_date_from").val());
        var dateToObj = getDateObjectFromDateStr($("#verify_date_to").val());
        if(dateFromObj > dateToObj) {
            $scope.searchConditionMessage.verify_date_from = "Từ ngày không được lớn hơn đến ngày";
            $scope.searchConditionMessage.verify_date_to = "Từ ngày không được lớn hơn đến ngày";
            isValid = false;
        }
        return isValid;
    }

    $scope.clearSearchCondition = function() {
        $("#verify_id").val("");
        $("#citizen_verify_fullname").val("");
        $("#citizen_verify_cccd").val("");
        $("#verify_date_from").val("");
        $("#verify_date_to").val("");
        $("#verify_by_name").val("");
        $scope.searchCondition.notary_office_id = "";
        $scope.searchCondition.province_code = "";
        $scope.searchCondition.verify_status = "";
        $scope.searchCondition.verify_by = "";
        $scope.searchCondition.verify_by_name = "";
    };

    $scope.viewDetail =  function(order_id) {
        callGetDetail(order_id);
        // $("#viewCitizenVerifyOrderDetail").modal('show');
    };
    function viewDetail2(order_id) {
        callGetDetail(order_id);
    }

    function callGetDetail(id){
        $("#viewCitizenInfoDetail").modal('show');
        $http.get(ospApiUrl + "/citizen-verifications/"+id)
            .then(function (response) {
                if(response.status === 200){
                    $scope.citizenVerification = response.data;
                    $scope.citizenVerification.citizen_info = JSON.parse($scope.citizenVerification.citizen_info);
                    $("#viewCitizenInfoDetail").modal('show');
                    /*setHeightPopup();*/
                }
            });
    }

    $scope.export = function() {
        window.open(ospApiUrl + "/citizen-verifications/export" + buildUrlSearch());
    };

    function buildUrlSearch(){
        var searchCondition = $scope.searchConditionOrigin;

        var urlSearch = "";
        if(searchCondition.verify_id) {
            urlSearch += (urlSearch ? "&" : "?") + "verify_id="+searchCondition.verify_id;
        }
        if(searchCondition.citizen_verify_fullname) {
            urlSearch += (urlSearch ? "&" : "?") + "citizen_verify_fullname="+searchCondition.citizen_verify_fullname;
        }
        if(searchCondition.citizen_verify_cccd) {
            urlSearch += (urlSearch ? "&" : "?") + "citizen_verify_cccd="+searchCondition.citizen_verify_cccd;
        }
        if(searchCondition.province_code) {
            urlSearch += (urlSearch ? "&" : "?") + "province_code="+searchCondition.province_code;
        }
        if(searchCondition.notary_office_id) {
            urlSearch += (urlSearch ? "&" : "?") + "notary_office_id="+searchCondition.notary_office_id;
        }
        if(searchCondition.verify_by) {
            urlSearch += (urlSearch ? "&" : "?") + "verify_by="+searchCondition.verify_by;
        }
        if(searchCondition.verify_status) {
            urlSearch += (urlSearch ? "&" : "?") + "verify_status="+searchCondition.verify_status;
        }
        if(searchCondition.verify_date_from) {
            urlSearch += (urlSearch ? "&" : "?") + "verify_date_from="+searchCondition.verify_date_from;
        }
        if(searchCondition.verify_date_to) {
            urlSearch += (urlSearch ? "&" : "?") + "verify_date_to="+searchCondition.verify_date_to;
        }
        return urlSearch;
    }

    $scope.showBtnEditOrder = function() {
        var transactionPending = $scope.citizenVerifyOrder.transaction_hists.find(function(el){ return el.transaction_status === 'pending'});
        return !!transactionPending;
    };

    $scope.closePopup = function(idPopup) {
        $("#"+idPopup).modal('hide');
    };

    //reset modal height
    function setHeightPopup(){
        //body.modal-open
        $("div.modal-medium").height($("body.modal-open").height()+50);
    }

    //type are success, error
    function showNotifyMessage(message, idTag, type) {
        // Hiển thị phần tử
        $("#"+idTag).html(message);
        $("#"+idTag).removeClass("status-error status-success").addClass("status-"+type);
        $("#"+idTag).fadeIn(1000); // Thời gian hiển thị 1 giây

        // Sau 5 giây, ẩn phần tử
        setTimeout(function() {
            $("#"+idTag).fadeOut(1000); // Thời gian ẩn 1 giây
        }, 5000); // 5000ms = 5 giây
    }

    $scope.convertIntToDateTime = function(number) {
        return convertIntToDateTime(number);
    };

    $scope.setCssByStatus = function(status) {
        return setCssByStatus(status);
    };

    $scope.setCssClassForPaymentAdditionStatus = function() {


    };

    function setCssByStatus(status) {
        switch (status) {
            case "pending":
                return "status-box-pending";
            case "fail":
                return "status-box-fail";
            case "success":
                return "status-box-success";
            default:
                return ""
        }
    }
    /*$http.get(ospApiUrl + "/question-help/page", {params: {page: 1, search:"", type:typeQASTP}})
        .then(function (response) {
            $scope.listQA = response.data;
            /!*if(response.status == 200){
                $("#errorDelete").modal('show');
            }*!/
        });*/

   /* $http.get(ospApiUrl + "/question-help/count", {params: {search:"", type:typeQASTP}})
        .then(function (response) {
            $scope.countListQA = response.data;
        });*/

    /*$scope.viewAsDoc=function () {
        $("#viewHtmlAsWord").html($("#editor").html());
        $("#viewContentAsWord").modal('show');
    }

    $scope.downloadWord=function () {
        $("#editor").wordExport();
    }*/
}]);

myApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
        return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })

    }

}]);
