<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thông tin cấu hình 1
--%>
<spring:url value="/system/backup-list" var="listUrl" />
<spring:url value="/system/backup-update-view" var="backInitUrl" />
<spring:url value="/system/backup-now" var="backupNowUrl" />
<spring:url value="/system/restore-view" var="restoreUrl" />


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cấu hình tham số sao lưu</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status" >
        <c:if test="${cfBackup.success == true}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${cfBackup.action_status}</div>
        </c:if>
        <c:if test="${cfBackup.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${cfBackup.action_status}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN CẤU HÌNH LƯU TRỮ DỮ LIỆU SAO LƯU
                        </a>
                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">

                        <table class="table " >
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title" >Thư mục lưu trữ dữ liệu sao lưu</th>
                                <td class="border-table">${cfBackup.pathBackUp}</td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Lịch sao lưu</th>
                                <td class="border-table">
                                  ${cfBackup.mon == true?'Thứ 2 - ':''}
                                    ${cfBackup.tue == true?'Thứ 3 - ':''}
                                    ${cfBackup.wed == true?'Thứ 4 - ':''}
                                    ${cfBackup.thu == true?'Thứ 5 - ':''}
                                    ${cfBackup.fri == true?'Thứ 6 - ':''}
                                    ${cfBackup.sat == true?'Thứ 7 - ':''}
                                    ${cfBackup.sun == true?'Chủ nhật':''}
                                </td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Thời gian sao lưu</th>
                                <td class="border-table">${cfBackup.backupTime}</td>
                            </tr>

                        </table>
                    </div>
                </div>
                <div class="truong-panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="row">
                                    <label class="col-md-5 control-label label-bam-trai">Trạng thái sao lưu</label>


                                    <label class="control-label  truong-text-radior col-md-6"><span class="truong-text-coloryl">Cho phép sao lưu dữ liệu</span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



            </div>
        </form>
        <div class="truong-prevent-btb">
            <div class="truong-rs truong-rs-cssbt4-25">
                <div class="col-md-2 ">
                    <a href="${backInitUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu" name="" value="Chỉnh sửa"></a>
                </div>
                <div class="col-md-4 ">
                    <a href="${backupNowUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu" name="" value="Sao lưu dữ liệu đột xuất "></a>
                </div>
                <div class="row">
                    <div class="col-md-6 truong-rs-bt4t3">
                        <div class="col-md-6 ">
                            <a href="${restoreUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu" name="" value="Phục hồi dữ liệu "></a>
                        </div>
                        <div class="col-md-6 ">
                            <a href="${listUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu" name="" value="Xem danh sách"></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


</div>



<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width() < 1578 && win.width() > 1320){

            $('.truong-rs').addClass('truong-rs-cssbt4-20');
            $('.truong-rs').removeClass('truong-rs-cssbt4-10 truong-rs-cssbt4-6 truong-rs-cssbt4-25');
        }
        if(win.width()>1578){

            $('.truong-rs').addClass('truong-rs-cssbt4-25');
            $('.truong-rs').removeClass('truong-rs-cssbt4-20 truong-rs-cssbt4-10 truong-rs-cssbt4-6');
        }
        if(win.width()<1320 && win.width()>1050){

            $('.truong-rs').addClass('truong-rs-cssbt4-10');
            $('.truong-rs').removeClass('truong-rs-cssbt4-20 truong-rs-cssbt4-25 truong-rs-cssbt4-6');
        }
        if(win.width()<1050){

            $('.truong-rs').addClass('truong-rs-cssbt4-6');
            $('.truong-rs').removeClass('truong-rs-cssbt4-20 truong-rs-cssbt4-25 truong-rs-cssbt4-10');
        }
    });
</script>


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
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>
