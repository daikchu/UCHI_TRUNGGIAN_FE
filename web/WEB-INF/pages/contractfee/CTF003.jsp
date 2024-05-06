<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Chỉnh sửa gợi ý phí hợp đồng
--%>
<spring:url value="/fee/update" var="updateUrl" />
<spring:url value="/fee/delete" var="removeUrl" />
<spring:url value="/fee/list" var="backUrl" />
<spring:url value="/fee/loadContractTemplate" var="loadContractTemplate"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa kiểu hợp đồng</span>
</div>


<div class="truong-form-chinhbtt">


   <%-- <c:if test="${contractKind.success== false}">
        <div class="padding-default" id="uchi-status">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin kiểu hợp đồng không thành công </div>
        </div>
    </c:if>--%>

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="id" value="${contractFee.id}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN KIỂU HỢP ĐỒNG
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
                            <label class="col-md-2 control-label label-bam-trai">Mã hợp đồng</label>
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
                            <label class="col-md-2 control-label required label-bam-trai">Khoảng tiền từ</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.from_fee_ != null?"error_input":""} ${contractFee.space_!= null?"error_input":""}" name="from_fee" value="${contractFee.from_fee}">
                                <div class="error_tooltip">${contractFee.from_fee_}</div>
                                <div class="error_tooltip">${contractFee.space_}</div>
                                <%--<div class="error_tooltip">${provinceAdd.name_}</div>--%>
                            </div>

                            <label class="col-md-2 control-label required label-bam-phai">Khoảng tiền đến</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.to_fee_!=null?"error_input":""}" name="to_fee" value="${contractFee.to_fee}">
                                <div class="error_tooltip">${contractFee.to_fee_}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Công thức tính</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${contractFee.formula_fee_!=null?"error_input":""}" name="formula_fee" id="formula_fee" value="${contractFee.formula_fee}">
                                <div class="error_tooltip">${contractFee.formula_fee_}</div>
                                <div>Nhập số tiền hoặc nhập công thức tính với biến a là giá trị tài sản công chứng VD:  800000 + 0.06/100*(a - 1000000000)</div>

                            </div>

                            <label class="col-md-2 control-label label-bam-phai">Thông tư</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="circulars_fee" value="${contractFee.circulars_fee}">

                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 col-md-offset-3 truong-rs-bt3os">
                        <input type="submit" class="form-control luu" name="" value="Cập Nhật">
                    </div>
                    <div class="col-md-2 truong-rs-bt3">
                        <input type="button" class="form-control luu" data-toggle="modal" data-target="#xoa"
                               name="" value="Xóa">
                    </div>

                    <div class="col-md-2 truong-rs-bt3">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>


<!-- Modal -->
<div class="modal fade" id="xoa" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <input type="hidden" name="id" value="${contractFee.id}">
            <div class="panel panel-default">

                <div class="panel-heading" style="background-color: #2e9ad0 ">
                    <h5 class="panel-title truong-text-colorwhite">
                        Xóa thông tin kiểu hợp đồng

                        <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                                src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>


                    </h5>

                </div>
                <div class="panel-body">
                    <div class="truong-modal-padding" style="padding-bottom: 7%;">
                        <label class="col-md-12 control-label align-giua">Bạn có thực sự muốn xóa dữ liệu ?</label>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-bottom: 2%;">
                <div class="col-md-3 col-md-offset-3">
                    <a href="${removeUrl}/${contractFee.id}" class="truong-small-linkbt"><input type="button" class="form-control luu" name="" value="Xóa"></a>
                </div>
                <div class="col-md-3 ">
                    <input type="button" class="form-control huybo" data-dismiss="modal" name="" value="Hủy bỏ">
                </div>

            </div>
        </div>
    </div>
</div>

<%--End Modal--%>
<script>
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1300){
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        }else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

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

<script>

    $(function () {
        $('#birthday').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
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


