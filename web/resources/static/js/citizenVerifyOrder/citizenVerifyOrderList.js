var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('citizenVerifyOrderController',['$scope','$http','$window','$sce', 'fileUpload' ,function ($scope,$http,$window,$sce, fileUpload) {
    //declare

    $scope.searchCondition = {
        province_code: $("#search_province_code").val(),
        notary_office_code: $("#search_notary_office_code").val(),
        order_id:"",
        transaction_status:$("#search_transaction_status").val(),
        status:$("#search_status").val(),
        order_time_from:"",
        order_time_to:"",
        update_user_name: ""
    };
    $scope.searchConditionMessage = {
        order_time_from:"",
        order_time_to:""
    };
    $scope.citizenVerifyOrder = {
        order_id: "",
        order_time: null,
        verify_number: 0,
        verify_fee: "",
        verify_fee_received: "",
        notary_office_code: "",
        notary_office_name: "",
        province_code: "",
        province_name: "",
        transaction_status: "",
        transaction_status_name: "",
        status: "",
        status_name: "",
        notary_office_excutor_fullname: "",
        note: "",
        attach_files: "",
        update_by: "",
        payment_content: "",
        update_user_fullname: "",
        transaction_hists: []
    };

    $scope.citizenVerifyOrderEdit = {
        order_id: "",
        order_time: null,
        verify_number: 0,
        verify_fee: "",
        verify_fee_received: "",
        notary_office_code: "",
        notary_office_name: "",
        province_code: "",
        province_name: "",
        transaction_status: "",
        transaction_status_name: "",
        status: "",
        status_name: "",
        notary_office_excutor_fullname: "",
        note: "",
        attach_files: "",
        update_by: "",
        payment_content: "",
        update_user_fullname: "",
        transaction_hists: []
    };

    $scope.paymentTransaction = {
        id: "",
        transaction_id: "",
        order_id: "",
        transaction_time: "",
        reference_number: "",
        amount: "",
        content: "",
        bank_account: "",
        trans_type: "",
        error: 0,
        error_reason: "",
        toast_message: "",
        ref_transaction_id: "",
        status: "",
        status_name: "",
        transaction_status: "",
        transaction_status_name: "",
        file_names: "",
        file_paths: "",
        note: "",
        notary_office_name: "",
        province_name: "",
        notary_office_excutor_fullname: ""
    };

    $scope.cssClassAndDisable ={
        required_transaction_status: "",
        required_status: "",
        required_note: "",
        required_attach_files: "",
        disabled_transaction_status: false,
        disabled_status: false,
        disabled_note: false,
        disabled_attach_files: false
    };

    $scope.citizenVerifyOrderMessage = {
        status: "",
        transaction_status: "",
        note: "",
        attach_files: ""
    };

    $scope.provinces = [];
    $scope.offices = [];
    $scope.transactionStatuses = [];
    $scope.additionalStatuses = [];
    $scope.transactionStatusesToChange = [];
    $scope.additionalStatusesToChange = [];
    var files_id_remove = [];
    var FILE_MAX_SIZE = 5 * 1024 * 1024; // 5MB


    var isOpenDetail = $("#isOpenDetail").val();
    var idOpenDetail = $("#idOpenDetail").val();
    var updatedStatus = $("#updatedStatus").val();

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

    function getTransactionStatuses(){
        $http.get(ospApiUrl + "/statuses?type=citizen_verify_transaction")
            .then(function (response) {
                if(response.status === 200){
                    $scope.transactionStatuses = response.data;
                }
            });
    }

    function getAdditionalStatuses() {
        $http.get(ospApiUrl + "/statuses?type=citizen_verify_transaction_additional")
            .then(function (response) {
                if(response.status === 200){
                    $scope.additionalStatuses = response.data;
                }
            });
    }


    getAllProvince();
    $scope.getNotaryOfficeByProvince();
    getTransactionStatuses();
    getAdditionalStatuses();
    initOpenPopupDetail();


    //END init

    function initOpenPopupDetail() {
        if($("#idOpenDetailWrapper").val()) {
            isOpenDetail = "true";
            idOpenDetail = $("#idOpenDetailWrapper").val();
        }
        if(isOpenDetail === "true" && idOpenDetail) {
            callGetDetail(idOpenDetail);
            //todo set notify message
            onDisplayNotify();

        }
    }

    $scope.onSearchOrders = function() {
        $("#search_province_code").val($scope.searchCondition.province_code);
        $("#search_notary_office_code").val($scope.searchCondition.notary_office_code);
        $("#search_transaction_status").val($scope.searchCondition.transaction_status);
        $("#search_status").val($scope.searchCondition.status);
        if(!validateSearchCondition()) return;
        $("#page").val("1");
        $("#search-frm").submit();
    };

    function validateSearchCondition() {
        $scope.searchConditionMessage.order_time_from = "";
        $scope.searchConditionMessage.order_time_to = "";

        var isValid = true;
        var order_time_from = $("#order_time_from").val();
        var order_time_to = $("#order_time_to").val();

        var dateFromObj = getDateObjectFromDateStr(order_time_from);
        var dateToObj = getDateObjectFromDateStr(order_time_to);
        if(dateFromObj > dateToObj) {
            $scope.searchConditionMessage.order_time_from = "Từ ngày không được lớn hơn đến ngày";
            $scope.searchConditionMessage.order_time_to = "Từ ngày không được lớn hơn đến ngày";
            isValid = false;
        }
        return isValid;
    }

    $scope.clearSearchCondition = function() {
        $("#order_id").val("");
        $scope.searchCondition.transaction_status = "";
        $scope.searchCondition.notary_office_code = "";
        $scope.searchCondition.province_code = "";
        $scope.searchCondition.status = "";
        $("#order_time_from").val("");
        $("#order_time_to").val("");
        resetSelectOption("update_user_name");
    };

    $scope.viewDetail =  function(order_id) {
        callGetDetail(order_id);
        // $("#viewCitizenVerifyOrderDetail").modal('show');
    };
    function viewDetail2(order_id) {
        callGetDetail(order_id);
    }
``
    function onDisplayNotify(){
        // Sau 5 giây, ẩn phần tử
        setTimeout(function() {
            $(".notifyMessage").fadeOut(1000); // Thời gian ẩn 1 giây
        }, 5000); // 5000ms = 5 giây
    }

    function callGetDetail(order_id){
        $http.get(ospApiUrl + "/citizen-verify-orders/"+order_id)
            .then(function (response) {
                if(response.status === 200){
                    $scope.citizenVerifyOrder = response.data;
                    $("#viewCitizenVerifyOrderDetail").modal('show');
                    setHeightPopup();
                }
            });
    }

    $scope.editOrder = function(order_id) {
        if(!order_id) {
            console.error("order id is null");
            return;
        };
        $http.get(ospApiUrl + "/citizen-verify-orders/"+order_id)
            .then(function (response) {
                if(response.status === 200){
                    $scope.citizenVerifyOrder = response.data;
                    $scope.citizenVerifyOrderEdit = JSON.parse(JSON.stringify($scope.citizenVerifyOrder));
                    $("#viewOrderEdit").modal('show');
                    setCssARequireAndDisable();
                    $scope.setTransactionStatusesByChange();
                }
            });

    };

    $scope.onclickSaveOrder = function() {
        //validate
    };

    $scope.actionSaveOrder = function() {
        $scope.closePopup("viewOrderEditConfirm");
        if(validateEditOrder() === false) {
            return;
        }
        // save4();
        var queryStringOfListScreen = window.location.search;// Lấy chuỗi query parameters từ URL hiện tại
        $("#edit_order_id").val($scope.citizenVerifyOrderEdit.order_id);
        $("#edit_transaction_status").val($scope.citizenVerifyOrderEdit.transaction_status);
        $("#edit_status").val($scope.citizenVerifyOrderEdit.status);
        $("#urlScreenList").val(queryStringOfListScreen);
        $("#edit_attach_file_remove").val(files_id_remove.join(','));
        $("#edit_note").val($scope.citizenVerifyOrderEdit.note);

        $("#formSaveTransaction").submit();
    };

    $scope.changeTransactionStatus = function(){
        setCssARequireAndDisable();
        $scope.setTransactionStatusesByChange();
    };

    $scope.changeAdditionStatus = function(){
        setCssARequireAndDisable();
    };

    $scope.setTransactionStatusesByChange = function() {
        $scope.transactionStatusesToChange = $scope.transactionStatuses;
        $scope.additionalStatusesToChange = $scope.additionalStatuses;

        if($scope.citizenVerifyOrder.transaction_status === "pending") {
            $scope.transactionStatusesToChange = $scope.transactionStatuses.filter(function (el) {return el.code === "pending" || el.code === "success"}) || [];

            if($scope.citizenVerifyOrderEdit.transaction_status === "success") {
                $scope.additionalStatusesToChange = $scope.additionalStatuses.filter(function (el) {return el.code === "payment_over"}) || [];
            }
        }
        else if($scope.citizenVerifyOrder.transaction_status === "fail"){
            $scope.transactionStatusesToChange = $scope.transactionStatuses.filter(function (el) {return el.code === "fail" || el.code === "success"}) || [];
            if($scope.citizenVerifyOrderEdit.transaction_status === "success") {
                $scope.additionalStatusesToChange = $scope.additionalStatuses.filter(function (el) {return el.code === "payment_over"}) || [];
            }
        }

    };

    function setCssARequireAndDisable() {
        $scope.cssClassAndDisable.required_transaction_status = "";
        $scope.cssClassAndDisable.required_status= "";
        $scope.cssClassAndDisable.required_note= "";
        $scope.cssClassAndDisable.required_attach_files= "";
        $scope.cssClassAndDisable.disabled_transaction_status= false;
        $scope.cssClassAndDisable.disabled_status= false;
        $scope.cssClassAndDisable.disabled_note= false;
        $scope.cssClassAndDisable.disabled_attach_files= false;

        //giao dich co trang thai cho thanh toan
        if($scope.citizenVerifyOrder.transaction_status === "pending") {
            $scope.cssClassAndDisable.transaction_status = "required";
            //khi chon trang thai giao dich = pending
            if($scope.citizenVerifyOrderEdit.transaction_status === "pending") {
                //không cho phép chọn trạng thái bổ sung
                $scope.cssClassAndDisable.disabled_status = true;
                $scope.citizenVerifyOrderEdit.status = $scope.citizenVerifyOrder.status;
            }
            else if($scope.citizenVerifyOrderEdit.transaction_status === "success") {
                //cho phép và bắt buộc tải file
                $scope.cssClassAndDisable.required_attach_files = "required";
            }
        }
        //giao dich co trang thai thanh toan that bai
        else if($scope.citizenVerifyOrder.transaction_status === "fail") {
            $scope.cssClassAndDisable.required_transaction_status = "required";
            //Chon Trang thai giao dich = Thanh toan that bai
            if($scope.citizenVerifyOrderEdit.transaction_status === "fail"){
                //khong cho phep nhap trang thai bo sung
                $scope.cssClassAndDisable.disabled_status = true;
                $scope.citizenVerifyOrderEdit.status = $scope.citizenVerifyOrder.status;
            }
            //Chon Trang thai giao dich = Thanh toan thanh cong
            else if($scope.citizenVerifyOrderEdit.transaction_status === "success"){
                $scope.cssClassAndDisable.required_attach_files = true;
            }
        }
        //giao dich co trang thai thanh toan thanh cong
        else if($scope.citizenVerifyOrder.transaction_status === "success") {
            //khong cho phep chinh sua trang thai giao dich, trang thai bo sung
            $scope.cssClassAndDisable.disabled_transaction_status = true;
            $scope.cssClassAndDisable.disabled_status = true;
        }
    }

    $scope.downloadFile = function(attachFileId, payment_id){
        window.open(ospApiUrl + "/attach-files/download/" + attachFileId + "/" + payment_id + "/" + "PAYMENT_TRANSACTION", '_blank');
    };

    $scope.removeFile = function(id){
        if(id) files_id_remove.push(id);
        removeArrayElement($scope.citizenVerifyOrderEdit.attach_files, id, "id");
    };
    function removeArrayElement(array, fieldValue, fieldName){
        for (var i = 0; i < array.length; i++) {
            if (array[i][fieldName] === fieldValue) {
                array.splice(i, 1);
                break;
            }
        }
    }

    function validateInputFile() {
        var isValid = true;
        var fileInput = $("#attach_files");
        if(!fileInput.val()) return true;
        /*if($scope.citizenVerifyOrderEdit.attach_files.length > 0) {
            $scope.citizenVerifyOrderMessage.attach_files = "Không được phép tải lên nhiều file!";
            return false;
        }*/
        var file = fileInput[0];
        if (file) {
            if (file.size > FILE_MAX_SIZE) {
                $scope.citizenVerifyOrderMessage.attach_files = "File tải lên có định dạng jpg, jpeg, png và dung lương tối đa 5MB";
                // event.target.value = null; // Xóa giá trị đã chọn
                isValid = false;
            } else {
                var fileName = $("#attach_files").val().split("\\").pop();
                var fileExtension = fileName.split('.').pop().toLowerCase();
                var allowedExtensions = ['jpg', 'jpeg', 'png'];

                if (!allowedExtensions.includes(fileExtension)) {
                    $scope.citizenVerifyOrderMessage.attach_files = "File tải lên có định dạng jpg, jpeg, png và dung lương tối đa 5MB";
                    // alert("Vui lòng chỉ chọn tệp tin có định dạng JPG, JPEG hoặc PNG.");
                    // event.target.value = null; // Xóa giá trị đã chọn
                    isValid = false;
                } else {
                    // Tệp tin hợp lệ, xử lý tiếp
                    // ...
                }
            }
        }
        return isValid;
    }

    function validateEditOrder(){
        $scope.citizenVerifyOrderMessage.status = "";
        $scope.citizenVerifyOrderMessage.transaction_status = "";
        $scope.citizenVerifyOrderMessage.note = "";
        $scope.citizenVerifyOrderMessage.attach_files = "";
        var isValid = true;

        isValid = validateInputFile();

        //giao dich co trang thai cho thanh toan
        if($scope.citizenVerifyOrder.transaction_status === "pending") {
            //cho phep chọn cho thanh toan va thanh toan thanh cong--> validate not null
            if($scope.citizenVerifyOrderEdit.transaction_status !== "pending" && $scope.citizenVerifyOrderEdit.transaction_status !== "success") {
                $scope.citizenVerifyOrderMessage.transaction_status  = "Chỉ được chọn Trạng thái giao dịch Chờ thanh toán hoặc Thành công";
                isValid = false;
            }
            //khi chon trang thai giao dich = pending
            if($scope.citizenVerifyOrderEdit.transaction_status === "pending") {
                //không cho phép chọn trạng thái bổ sung
                //
                //
            }
            else if($scope.citizenVerifyOrderEdit.transaction_status === "success") {
                //cho phép và không bắt buộc chọn trạng thái bổ sung
                //cho phép và bắt buộc tải file
                var fileInput = $("#attach_files");
                if((!$scope.citizenVerifyOrderEdit.attach_files || $scope.citizenVerifyOrderEdit.attach_files.length <= 0) && fileInput[0].files.length === 0) {
                    $scope.citizenVerifyOrderMessage.attach_files = "Tải file đính kèm";
                    isValid = false;
                }
                //dropdown trạng thái bổ sung chỉ nằm trong 3 items
                if($scope.citizenVerifyOrderEdit.status
                    && $scope.citizenVerifyOrderEdit.status !== "payment_over"
                    && $scope.citizenVerifyOrderEdit.status !== "wrong_content"
                    && $scope.citizenVerifyOrderEdit.status !== "over_wrong_content")
                {
                    $scope.citizenVerifyOrderMessage.status  = "Không chọn hoặc chỉ được chọn các trạng thái Thanh toán dư, Sai nội dung, Thanh toán dư và sai nội dung";
                    isValid = false;
                }
            }
        }
        //giao dich co trang thai thanh toan that bai
        else if($scope.citizenVerifyOrder.transaction_status === "fail") {
            //cho phep chọn cho thanh toan va thanh toan thanh cong--> validate not null
            if($scope.citizenVerifyOrderEdit.transaction_status !== "success" && $scope.citizenVerifyOrderEdit.transaction_status !== "fail") {
                $scope.citizenVerifyOrderMessage.transaction_status  = "Chỉ được chọn Trạng thái giao dịch Thành công hoặc Thất bại";
                isValid = false;
            }

            //Trang thai giao dich = Thanh toan that bai
            if($scope.citizenVerifyOrderEdit.transaction_status === "fail"){
                //khong cho phep nhap trang thai bo sung
                $scope.citizenVerifyOrderEdit.status = $scope.citizenVerifyOrder.status;
            }
        }
        //giao dich co trang thai thanh toan thanh cong
        else if($scope.citizenVerifyOrder.transaction_status === "success") {
            //khong cho phep chinh sua trang thai giao dich, trang thai bo sung
            $scope.citizenVerifyOrderEdit.transaction_status = $scope.citizenVerifyOrder.transaction_status;
            $scope.citizenVerifyOrderEdit.status = $scope.citizenVerifyOrder.status;
            //cho phep va bat buoc nhap ghi chu
            /*if(!$scope.citizenVerifyOrderEdit.note){
                $scope.citizenVerifyOrderMessage.note = "Trường không được để trống";
                isValid = false;
            }*/
            //Cho phep va khong bat buoc tai len va xoa file dinh kem

        }

        return isValid;
    }

    $scope.export = function() {
        window.open(ospApiUrl + "/citizen-verify-orders/export" + buildUrlSearch());
    };

    function buildUrlSearch(){
        $scope.searchCondition.order_id = $("#order_id").val();
        $scope.searchCondition.order_time_from = $("#order_time_from").val();
        $scope.searchCondition.order_time_to = $("#order_time_to").val();
        $scope.searchCondition.update_user_name = $("#update_user_name").val();

        var urlSearch = "";
        if($scope.searchCondition.order_id) {
            urlSearch += (urlSearch ? "&" : "?") + "order_id="+$scope.searchCondition.order_id;
        }
        if($scope.searchCondition.province_code) {
            urlSearch += (urlSearch ? "&" : "?") + "province_code="+$scope.searchCondition.province_code;
        }
        if($scope.searchCondition.notary_office_code) {
            urlSearch += (urlSearch ? "&" : "?") + "notary_office_code="+$scope.searchCondition.notary_office_code;
        }
        if($scope.searchCondition.transaction_status) {
            urlSearch += (urlSearch ? "&" : "?") + "transaction_status="+$scope.searchCondition.transaction_status;
        }
        if($scope.searchCondition.status) {
            urlSearch += (urlSearch ? "&" : "?") + "status="+$scope.searchCondition.status;
        }
        if($scope.searchCondition.order_time_from) {
            urlSearch += (urlSearch ? "&" : "?") + "order_time_from="+$scope.searchCondition.order_time_from;
        }
        if($scope.searchCondition.order_time_to) {
            urlSearch += (urlSearch ? "&" : "?") + "order_time_to="+$scope.searchCondition.order_time_to;
        }
        if($scope.searchCondition.update_user_name) {
            urlSearch += (urlSearch ? "&" : "?") + "update_user_name="+$scope.searchCondition.update_user_name;
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
        $("div.modal-medium").height("580px");
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

    $scope.cssClassForAttachFile = function() {
        if($scope.citizenVerifyOrderEdit.transaction_status === "fail"){

        }
        return "";
    };

    $scope.cssClassForTransactionStatus = function() {
        if($scope.citizenVerifyOrder.transaction_status === "pending"){
            return "required";
        } else if($scope.citizenVerifyOrder.transaction_status === "fail"){
            return "required";
        } else return "";
    };

    $scope.disableTransactionStatus = function() {
      return $scope.citizenVerifyOrder.transaction_status === "success";
    };

    $scope.disableAdditionStatus = function() {
        return $scope.citizenVerifyOrder.transaction_status === "pending";
    };

    $scope.cssClassForPaymentAdditionStatus = function() {
        if($scope.citizenVerifyOrder.transaction_status === "pending"){

        }
        return $scope.citizenVerifyOrderEdit.transaction_status === "fail" ? "required" : "";
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

    $scope.formatNumber = function(number) {
        return formatNumberReturn(number);
    }

    $scope.showMultiTextByFormat = function(value1, value2, isBreakLineBetween) {
        return value1  + (isBreakLineBetween ? "<br/>" : '') + value2;
    }
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
