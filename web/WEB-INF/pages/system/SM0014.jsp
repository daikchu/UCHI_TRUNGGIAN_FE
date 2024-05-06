<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mới cấu hình dữ liệu sao lưu
--%>
<spring:url value="/system/backup-init" var="backupInitUrl" />
<spring:url value="/system/backup-view" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cấu hình tham số sao lưu</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${cfBackup.action_status != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${cfBackup.action_status}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${backupInitUrl}" method="post">
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
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Thư mục lưu trữ</label>
                            <div class="col-md-10">
                                <input type="text" class="form-control " name="pathBackUp" value="${cfBackup.pathBackUp}" >
                                <div class="error_tooltip"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Thời gian sao lưu</label>
                            <div class="col-md-2">
                                <input type="text" class="form-control" name="backupTime" value="${cfBackup.backupTime}" >
                                <div class="error_tooltip"></div>
                            </div>

                        </div>
                    </div>
                    <div class="form-group">

                            <label class="col-md-2 control-label  label-bam-trai">Lịch sao lưu</label>
                            <div class="col-md-6 control-label label-bam-trai truong-font-chu">
                                <input type="checkbox" class="selectAll truong-check-fix">
                                Tất cả các ngày trong tuần

                                <ul style="list-style:none">
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="mon" ${cfBackup.mon == true?"checked":""}>Thứ 2</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="tue" ${cfBackup.tue == true?"checked":""}>Thứ 3</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="wed" ${cfBackup.wed == true?"checked":""}>Thứ 4</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="thu" ${cfBackup.thu == true?"checked":""}>Thứ 5</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="fri" ${cfBackup.fri == true?"checked":""}>Thứ 6</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="sat" ${cfBackup.sat == true?"checked":""}>Thứ 7</li>
                                    <li><input class="truong-check-fix truong-check-fixmg" type="checkbox" name="sun" ${cfBackup.sun == true?"checked":""}>Chủ nhật</li>


                                </ul>
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
                        <a href="${backUrl}" class="a-no-decoration"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>
<script>
$('.selectAll').click(function(){
$('input:checkbox').not(this).prop('checked', this.checked);
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
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>