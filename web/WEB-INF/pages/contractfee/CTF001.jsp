<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mói thông tin phí hợp đồng
--%>
<spring:url value="/fee/add" var="addUrl" />
<spring:url value="/fee/list" var="backUrl" />
<spring:url value="/fee/loadContractTemplate" var="loadContractTemplate"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới tính phí</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${contractFee.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông tin gợi ý phí hợp đồng không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <%--<input type="hidden" name="contractKindList" value="${contractFee.contractKindList}">
            <input type="hidden" name="contractTempList" value="${contractFee.contractTempList}">--%>

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN PHÍ HỢP ĐỒNG
                        </a>
                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Mã mẫu hợp đồng</label>
                            <div class="col-md-3">
                                <select name="nhomHD" id="nhomHD" class="form-control truong-selectbox-padding" onchange="loadContractTemplate()" >
                                    <option value="">Tất cả</option>
                                    <c:forEach items="${contractFee.contractKindList}" var="item" >
                                        <option value="${item.contract_kind_code}" ${contractFee.nhomHD == item.contract_kind_code?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai required">Mã hợp đồng</label>
                            <div class="col-md-3">
                                <select name="ct_template_code" id="tenHD" class="form-control truong-selectbox-padding" >
                                    <option value="">Tất cả</option>
                                    <c:forEach items="${contractFee.contractTempList}" var="item" >
                                        <option value="${item.code_template}" ${contractFee.ct_template_code == item.code_template?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Khoảng tiền từ</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.from_fee_!= null?"error_input":""} ${contractFee.space_!=null?"error_input":""}" name="from_fee" value="${contractFee.from_fee}">
                                <div class="error_tooltip">${contractFee.from_fee_}</div>
                                <div class="error_tooltip">${contractFee.space_}</div>
                            </div>

                            <label class="col-md-2 control-label label-bam-phai">Khoảng tiền đến</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.to_fee_!= null?"error_input":""}" name="to_fee" value="${contractFee.to_fee}">
                                <div class="error_tooltip">${contractFee.to_fee_}</div>
                                <%--<div class="error_tooltip">${provinceAdd.code_}</div>--%>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai required">Công thức tính</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.formula_fee_!=null?"error_input":""}" name="formula_fee" id="formula_fee" value="${contractFee.formula_fee}">
                                <div class="error_tooltip">${contractFee.formula_fee_}</div>
                                <div>Nhập số tiền hoặc nhập công thức tính với biến a là giá trị tài sản công chứng VD:  800000 + 0.06/100*(a - 1000000000)</div>

                            </div>
                            <label class="col-md-2 control-label label-bam-phai">Thông tư</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="circulars_fee" value="${contractFee.to_fee}">

                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <div class="col-md-2 truong-rs-bt2">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>

<script>
    $(window).on('resize',function(){
        var win = $(this);
        if (win.width() < 1200){

            $('.truong-rs-bt2os').removeClass('col-md-2 col-md-offset-4');
            $('.truong-rs-bt2os').addClass('col-md-3 col-md-offset-3');
            $('.truong-rs-bt2').removeClass('col-md-2');
            $('.truong-rs-bt2').addClass('col-md-3');
        }else {
            $('.truong-rs-bt2os').removeClass('col-md-3 col-md-offset-3');
            $('.truong-rs-bt2os').addClass('col-md-2 col-md-offset-4');
            $('.truong-rs-bt2').removeClass('col-md-3');
            $('.truong-rs-bt2').addClass('col-md-2');
        }
    });
    $("#formula_fee").keypress(function (key) {
        if ((key.charCode < 45 || key.charCode > 57) && key.charCode != 97 && key.charCode != 126 && (key.charCode < 40 || key.charCode > 43)) return false;
    });
    $("#formula_fee").bind("cut copy paste", function (e) {
        e.preventDefault();

    });

    function loadContractTemplate() {
        var loadContractTemplate=  document.getElementById("nhomHD").value;
        $.ajax({
            type: "GET",
            url: '${loadContractTemplate}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                contractKind: loadContractTemplate,
            },
            success: function (response) {
                var stringVpcc = response.result;
                var htmlContent1 = '<select name="ct_template_code" class="form-control truong-selectbox-padding"><option value="">Tất cả</option>';
                var listVpcc = stringVpcc.split(";");
                for( var i =0;i<listVpcc.length-1;i++){
                    var nameVPCC = listVpcc[i];
                    var arr = nameVPCC.split("o0o");
                    var idVPCC = arr[0];
                    var tenVPCC = arr[1];

                    htmlContent1 += '<option value ="'+idVPCC+'">'+tenVPCC+'</option>';
                }
                htmlContent1+='</select>';
                $('#tenHD').html(htmlContent1);


            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })

    }


</script>





<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        var parentItem = $("#mau-giao-dien");
        $(parentItem).click();
        $("#phihopdong").addClass("child-menu");
    }
</script>
