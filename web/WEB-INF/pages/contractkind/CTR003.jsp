<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Chỉnh sửa kiểu hợp đồng
--%>
<spring:url value="/contractkind/update" var="updateUrl" />
<spring:url value="/contractkind/delete" var="removeUrl" />
<spring:url value="/contractkind/list" var="backUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa kiểu hợp đồng</span>
</div>


<div class="truong-form-chinhbtt">


    <c:if test="${contractKind.success== false}">
        <div class="padding-default" id="uchi-status">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin kiểu hợp đồng không thành công </div>
        </div>
    </c:if>

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="ckId" value="${contractKind.ckId}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN KIỂU HỢP ĐỒNG
                        </a>
                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-2 control-label required label-bam-trai">Tên kiểu hợp đồng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${contractKind.name_!= null ?"error_input":""}" name="name" value="${contractKind.name}">

                            <div class="error_tooltip">${contractKind.name_}</div>

                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-1 control-label required label-bam-phai">Mã kiểu hợp đồng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${contractKind.code_!= null ?"error_input":""}" name="contract_kind_code" value="${contractKind.contract_kind_code}" disabled>

                            <div class="error_tooltip">${contractKind.code_}</div>

                        </div>
                    </div>




                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 col-md-offset-3 truong-rs-bt3os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
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
            <input type="hidden" name="ckId" value="${contractKind.ckId}">
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
                    <a href="${removeUrl}/${contractKind.ckId}" class="truong-small-linkbt"><input type="button" class="form-control luu" name="" value="Xóa"></a>
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
        $(".sidebar-nav > li > #kieu-hop-dong").addClass("father-menu");
    }
</script>


