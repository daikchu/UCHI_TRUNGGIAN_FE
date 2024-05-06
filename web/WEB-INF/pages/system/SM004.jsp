<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Phân quyền cán bộ STP
--%>
<spring:url value="/system/user-permission" var="permissionUrl"/>
<spring:url value="/system/user-list" var="backUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Phân quyền cán bộ Sở Tư Pháp</span>
</div>
<div class="truong-form-chinhpq">
    <div class="panel-group canh-le-trai" id="accordion">
        <form class="form-horizontal" action="${permissionUrl}" method="post">
            <input type="hidden" name="userId" value="${userPermissionForm.user.userId}">
            <div class="panel-body">

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Họ tên</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.family_name} ${userPermissionForm.user.first_name}</strong>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Tài khoản đăng nhập</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.account}</strong>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Chức vụ</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.role}</strong>
                        </div>
                    </div>

                </div>


                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Trạng thái hoạt động</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <c:if test="${userPermissionForm.user.active_flg == 1}">
                                <strong>Đang hoạt động</strong>
                            </c:if>
                            <c:if test="${userPermissionForm.user.active_flg == 2}">
                                <strong>Ngừng hoạt động</strong>
                            </c:if>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Phân quyền</label>
                        <div class="col-md-6 control-label label-bam-trai truong-font-chu">
                            <input type="checkbox" class="selectAll truong-check-fix">
                            Tất cả nhóm quyền

                            <ul style="list-style:none">
                                <c:forEach items="${userPermissionForm.groupRoles}" var="item">
                                    <c:if test="${item.activeByPreventUser == 1}">
                                        <li><input class="truong-check-fix" type="checkbox" name="cb01" value="${item.groupRoleId}" checked>&nbsp&nbsp&nbsp ${item.grouprolename}</li>
                                    </c:if>
                                    <c:if test="${item.activeByPreventUser != 1}">
                                        <li><input class="truong-check-fix" type="checkbox" name="cb01" value="${item.groupRoleId}">&nbsp&nbsp&nbsp ${item.grouprolename}</li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
            <div class="truong-form-chinhpq">

                <div class="col-md-7">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-2 truong-rs-bt2os">
                            <input type="submit" class="form-control luu" name="" value="Lưu">
                        </div>
                        <div class="col-md-2 truong-rs-bt2">
                            <a href="${backUrl}"><button class="form-control huybo">Huỷ bỏ</button></a>
                        </div>
                    </div>

                </div>
            </div>
            <%--
            <div class="prevent-type-box">
                <div class="form-group">
                    <div class="col-md-3 col-md-offset-2">
                    <div class="row">
                    <div class="col-md-4">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <div class="col-md-4">
                        <input type="button" class="form-control huybo" name="" value="Hủy bỏ">
                    </div>
                    </div>
                    </div>
                </div>
            </div>
            --%>
        </form>
    </div>
</div>

<script>
    $(window).on('resize',function(){
        var win = $(this);
        if (win.width() < 1200){
            $('.truong-rs-bt2os').removeClass('col-md-2');
            $('.truong-rs-bt2os').addClass('col-md-3 ');
            $('.truong-rs-bt2').removeClass('col-md-2');
            $('.truong-rs-bt2').addClass('col-md-3');
        }else {
            $('.truong-rs-bt2os').removeClass('col-md-3 ');
            $('.truong-rs-bt2os').addClass('col-md-2 ');
            $('.truong-rs-bt2').removeClass('col-md-3');
            $('.truong-rs-bt2').addClass('col-md-2');
        }
    });
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
