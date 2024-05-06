<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mói thông tin cán bộ
--%>
<spring:url value="/system/add-user" var="addUrl" />
<spring:url value="/system/user-list" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới thông tin cán bộ</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">
        <c:if test="${user.success == true}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">Tạo mới thông tin cán bộ Sở Tư pháp thành công</div>
        </c:if>
        <c:if test="${user.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông tin cán bộ Sở Tư pháp không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <input type="hidden" name="authorId" value="${user.authorId}" id="authorId">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN CÁN BỘ
                        </a>
                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Họ đệm</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.family_name_ != null? "error_input":""}" name="family_name" value="${user.family_name}">
                                <div class="error_tooltip">${user.family_name_}</div>
                            </div>
                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label required label-bam-phai">Tên</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.first_name_ != null? "error_input":""}" name="first_name" value="${user.first_name}">
                                <div class="error_tooltip">${user.first_name_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Tài khoản đăng nhập</label>
                            <div class="col-md-1">
                                <label class="col-md-3 control-label label-bam-phai" id="authorValue" name="authorId"></label>
                            </div>
                            <div class="col-md-2">
                                <input type="text" class="form-control ${user.account_ != null? "error_input":""}" name="account" value="${user.account}">
                                <div class="error_tooltip">${user.account_}</div>
                            </div>
                            <div class="col-md-6 control-label truong-bo-pl" style="text-align: left!important">
                                <i>
                                    (Tài khoản đăng nhập chỉ được dùng chữ cái, số, dấu gạch dưới và dấu gạch ngang)
                                </i>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Mật khẩu</label>
                            <div class="col-md-3">
                                <input type="password" class="form-control ${user.password_ != null? "error_input":""}" name="password" value="${user.password}">
                                <div class="error_tooltip">${user.password_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Xác nhận mật khẩu</label>
                            <div class="col-md-3">
                                <input type="password" class="form-control ${user.rePassword_ != null? "error_input":""}" name="rePassword">
                                <div class="error_tooltip">${user.rePassword_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Ngày sinh</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.birthday_ != null? "error_input":""}" id="birthday" name="birthday" value="${user.birthday}">
                                <div class="error_tooltip">${user.birthday_}</div>
                            </div>
                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label  label-bam-phai">Giới tính</label>
                            <div class="col-md-3    ">
                                <select class=" form-control" name="sex">
                                    <option value="1">Nam</option>
                                    <option value="0">Nữ</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Địa chỉ</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control ${user.address_ != null? "error_input":""}" name="address" value="${user.address}">
                                <div class="error_tooltip">${user.address_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Email</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.email_ != null? "error_input":""}" name="email" value="${user.email}">
                                <div class="error_tooltip">${user.email_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Điện thoại cố định</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.telephone_ != null? "error_input":""}" name="telephone" value="${user.telephone}">
                                <div class="error_tooltip">${user.telephone_}</div>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai">Điện thoại di động</label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control ${user.mobile_ != null? "error_input":""}" name="mobile" value="${user.mobile}">
                                <div class="error_tooltip">${user.mobile_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Chức vụ</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.role_ != null? "error_input":""}" name="role" value="${user.role}">
                                <div class="error_tooltip">${user.role_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Trạng thái hoạt động</label>
                            <div class="col-md-9">
                                <label class="radio-inline prevent-type">
                                    <input type="radio" name="active_flg" value="1" checked >Đang hoạt động
                                </label>
                                <label class="radio-inline ">
                                    <input type="radio" name="active_flg" value="0">Ngừng hoạt động
                                </label>
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
                        <a href="${backUrl}"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
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
<script>

    $(function () {
        $('#birthday').datepicker({
            format: "dd/mm/yyyy",
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
    function changeOffice(e){
        var element = $(e).find('option:selected');
        var authorCode = element.attr("authorCode");
        $('#authorValue').text(authorCode);
        $('#authorId').val(authorCode);
    }
    $(document).ready(function () {
        var e = $('#officeAuthor');
        changeOffice(e);
    });
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />


