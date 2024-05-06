<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Cập nhật tổ chức công chứng
--%>
<spring:url value="/customer/update" var="updateUrl"/>
<spring:url value="/customer/list" var="backUrl"/>
<spring:url value="/customer/delete" var="deleteUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cập nhật Tổ chức công chứng</span>
</div>
<div class="truong-form-chinhbtt">

    <div id="uchi-status">
        <c:if test="${customerUpdate.success == false}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin
                tổ chức công chứng không thành công
            </div>
        </c:if>
    </div>

    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="noid" value="${customerUpdate.noid}">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                        CẬP NHẬT THÔNG TIN ĐƠN VỊ

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Tên tổ chức </label>
                            <div class="col-md-9">
                                <input type="text"
                                       class="form-control ${customerUpdate.name_!= null ? "error_input":""}"
                                       name="name" id="customer_name"
                                       value="${customerUpdate.name}">
                                <div class="error_tooltip">${customerUpdate.name_}</div>
                                <div class="error_tooltip" id="name_"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Mã tổ chức </label>
                            <div class="col-md-3">
                                <input class=" form-control" type="text" id="code"
                                       value="${customerUpdate.code}" name="code" readonly>
                            </div>

                            <label class="col-md-3 control-label required label-bam-phai">Tỉnh/thành phố</label>
                            <div class="col-md-3">
                                <select class="form-control select2" name="province_code" id="province_code">
                                    <option value="">--- Chọn tỉnh/thành phố ---</option>
                                    <c:forEach items="${customerUpdate.provinceList}" var="item">
                                        <option value="${item.code}" ${item.code==customerUpdate.province_code ?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                                <div class="error_tooltip">${customerUpdate.province_code_}</div>
                                <div class="error_tooltip" id="province_id_"></div>
                            </div>
                        </div>

                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Gói dịch vụ</label>
                            <div class="col-md-3">
<%--                                name="pakage_code" id="pakage_code"--%>
                                <select class="form-control select2" name="pakage_code" id="pakage_code">
                                    <option value="">-- Chọn gói dịch vụ --</option>
                                    <c:forEach items="${customerUpdate.packages}" var="item">
                                        <option value="${item.code}" ${item.code==customerUpdate.pakage_code ?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                                <div class="error_tooltip">${customerUpdate.pakage_code_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Gói dịch vụ xác thực công dân</label>
                            <div class="col-md-3">
                                <%--                                name="pakage_code" id="pakage_code"--%>
                                <select class="form-control select2" name="citizen_verify_package_code" id="citizen_verify_package_code">
                                    <option value="">-- Chọn gói dịch vụ --</option>
                                    <c:forEach items="${customerUpdate.citizenVerifyPackages}" var="item">
                                        <option value="${item.code}" ${item.code==customerUpdate.citizen_verify_package_code ?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Địa chỉ </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control " id="address" name="address"
                                       value="${customerUpdate.address}">
                                <div class="error_tooltip">${customerUpdate.address_}</div>
                            </div>
                        </div>
                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Điện thoại </label>
                            <div class="col-md-3">
                                <input class=" form-control ${customerUpdate.phone_!=null?"error_input":""}" type="text"
                                       id="phone"
                                       value="${customerUpdate.phone}" name="phone">
                                <div class="error_tooltip">${customerUpdate.phone_}</div>
                                <div class="error_tooltip" id="phone_"></div>
                            </div>

                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label  label-bam-phai">Email </label>
                            <div class="col-md-3    ">
                                <input class=" form-control ${customerUpdate.email_!=null?"error_input":""}" type="text"
                                       id="email"
                                       value="${customerUpdate.email}" name="email">
                                <div class="error_tooltip">${customerUpdate.email_}</div>
                                <div class="error_tooltip" id="email_"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Ngày bắt đầu ký hợp
                                đồng</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="date_start"
                                       value="${customerUpdate.date_start}" minlength="10" maxlength="10"
                                       onkeypress="return restrictCharacters(this, event, forDate);" id="fromDate" readonly>
                                <span class="truong-text-colorred">${customerUpdate.date_start_}</span>
                            </div>

                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label  label-bam-phai">Website </label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control " id="website"
                                       value="${customerUpdate.website}" name="website">
                            </div>
                        </div>
                    </div>

                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Ngày hết hạn</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" name="date_exp"
                                       value="${customerUpdate.date_exp}" minlength="10" maxlength="10"
                                       onkeypress="return restrictCharacters(this, event, forDate);" id="toDate">
                                <span class="truong-text-colorred">${customerUpdate.date_exp_}</span>
                            </div>

                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label  label-bam-phai">Ghi chú </label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control " id="note"
                                       value="${customerUpdate.note}" name="note">
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-md-12 truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 truong-rs-bt3os col-md-offset-3">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <div class="col-md-2 truong-rs-bt3 truong-small-linkbt">
                        <input type="button" class="form-control luu" data-toggle="modal" data-target="#xoa"
                               name="" value="Xóa">
                    </div>
                    <div class="col-md-2 truong-rs-bt3">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo"
                                                                                name="" value="Hủy bỏ"></a>
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
            <input type="hidden" name="noid" value="${customerUpdate.noid}">
            <div class="panel panel-default">

                <div class="panel-heading" style="background-color: #2e9ad0 ">
                    <h5 class="panel-title truong-text-colorwhite">
                        Xóa Tổ chức công chứng

                        <button type="button" class="close truong-button-xoa" data-dismiss="modal"
                                style="margin-bottom: 5px"><img
                                src="<%=request.getContextPath()%>/static/image/close.png"
                                class="control-label truong-imagexoa-fix"></button>


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
                    <a href="${deleteUrl}/${customerUpdate.noid}" class="truong-small-linkbt"><input type="button"
                                                                                                     class="form-control luu"
                                                                                                     name=""
                                                                                                     value="Xóa"></a>
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
    $(window).on('load resize', function () {
        var win = $(this);
        if (win.width() < 1300) {
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        } else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

        }
    });
</script>

<script type="text/javascript">
    $(function () {
        document.getElementById("fromDate").readOnly = true;
        // $('#fromDate').datepicker({
        //     format: "dd/mm/yyyy",
        //     startDate: "01/01/1900",
        //     endDate: "31/12/2099",
        //     forceParse: false,
        //     language: 'vi'
        // }).on('changeDate', function (ev) {
        //     $(this).datepicker('hide');
        // });
        $('#toDate').datepicker({
            format: "dd/mm/yyyy",
            startDate: "01/01/1900",
            endDate: "31/12/2099",
            forceParse: false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
</script>

<script>
    /*lay ngay hien tại để gán max cho datepicker*/
    var now = new Date();
    var endDate = ("0" + now.getDate()).slice(-2) + '/' + ("0" + (now.getMonth() + 1)).slice(-2) + '/' + now.getFullYear();
    /*khu vực viet câm' nhập các ký tự trên bàn phím*/
    var digitsOnly = /[1234567890]/g;
    var forDate = /[1234567890/]/g;
    var NoOnly = /[~]/g;
    var integerOnly = /[0-9\.]/g;
    var alphaOnly = /[A-Za-z]/g;

    function restrictCharacters(myfield, e, restrictionType) {
        if (!e) var e = window.event
        if (e.keyCode) code = e.keyCode;
        else if (e.which) code = e.which;
        var character = String.fromCharCode(code);

        // if they pressed esc... remove focus from field...
        if (code == 27) {
            this.blur();
            return false;
        }

        // ignore if they are press other keys
        // strange because code: 39 is the down key AND ' key...
        // and DEL also equals .
        if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && code != 38 && (code != 39 || (code == 39 && character == "'")) && code != 40) {
            if (character.match(restrictionType)) {
                return true;
            } else {
                return false;
            }

        }
    }

</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-khach-hang");
        $(parentItem).click();
        $("#quan-ly-khach-hang").addClass("child-menu");
    });
</script>


