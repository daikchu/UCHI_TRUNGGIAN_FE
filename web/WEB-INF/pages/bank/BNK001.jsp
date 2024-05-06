<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mói thông tin tỉnh thành
--%>
<spring:url value="/bank/add" var="addUrl" />
<spring:url value="/bank/list" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới ngân hàng</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${bankAdd.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông tin ngân hàng không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN NGÂN HÀNG
                        </a>
                    </h4>

                </div>
                <div class="panel-body">

                        <div class="form-group">
                            <label class="col-md-2 control-label required label-bam-trai">Tên ngân hàng</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${bankAdd.name_!=null ? "error_input":""}" name="name" value="${bankAdd.name}">
                                <div class="error_tooltip">${bankAdd.name_}</div>
                            </div>

                            <label class="col-md-2 control-label required label-bam-phai">Mã ngân hàng</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${bankAdd.code_!=null ?"error_input":""}" name="code" value="${bankAdd.code}">
                                <div class="error_tooltip">${bankAdd.code_}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Trạng thái </label>
                            <div class="col-md-5 control-label label-bam-trai">
                                <input class="truong-check-fix" type="radio" name="active" value="1" checked> Đang hoạt động &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                                <input class="truong-check-fix" type="radio" name="active" value="0"> Ngừng hoạt động
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
</script>



<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #ngan-hang").addClass("father-menu");
    }
</script>


