<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Cập nhật gói dịch vụ
--%>
<spring:url value="/package/update-package" var="updateUrl"/>
<spring:url value="/package/delete-package" var="deleteUrl"/>
<spring:url value="/package/package-list" var="backUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa gói dịch vụ</span>
</div>
<div class="truong-form-chinhpq">
    <div id="uchi-status">
        <c:if test="${packageForm.success == false}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin
                gói dịch vụ không thành công
            </div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
        <c:if test="${msg != null}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">${msg}
            </div>
        </c:if>
    </div>
    <form class="form-horizontal" action="${updateUrl}" method="post" id="form-padding">
        <input type="hidden" name="id" value="${packageForm.id}">
        <div class="col-md-10 ">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default" id="panel1">
                    <div class="panel-heading">
                        <h4 class="panel-title">

                            THÔNG TIN GÓI DỊCH VỤ

                        </h4>

                    </div>
                    <div class="panel-body">

                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label required label-bam-trai">Mã gói dịch vụ</label>
                            <div class="col-md-9">
                                <input type="text"
                                       class="form-control ${packageForm.code_!= null ? "error_input":""}" ${packageForm.code_!= null ? "autofocus":""}
                                       name="code" value="${packageForm.code}" readonly>
                                <div class="error_tooltip">${packageForm.code_}</div>
                            </div>
                        </div>

                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label required label-bam-trai">Tên gói dịch vụ</label>
                            <div class="col-md-9">
                                <input type="text"
                                       class="form-control ${packageForm.name_!= null ? "error_input":""}" ${packageForm.name_!= null ? "autofocus":""}
                                       name="name" value="${packageForm.name}">
                                <div class="error_tooltip">${packageForm.name_}</div>
                            </div>
                        </div>

                        <div class="form-group truong-margin-fg">
                            <label class="label-bam-trai control-label col-md-3">Mô tả</label>
                            <div class="col-md-9">
                                <textarea name="description" rows="4"
                                          class="form-control ${packageForm.description_!=null? "error_input":""}" ${packageForm.description_!= null ? "autofocus":""}
                                          value="${packageForm.description}">${packageForm.description}</textarea>
                                <div class="error_tooltip">${packageForm.description_}</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


        </div>
        <div class="col-md-4">
            <input type="checkbox" class="selectAll" id="grouprole-check-all"> <span class="truong-font-chu"
                                                                                     style="vertical-align: 1px">Tất cả các quyền</span>
        </div>

        <div class="col-md-12">
            <div class="panel-group">


                <ul class="nav nav-tabs">
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role1">Quản trị hệ thống</a></li>
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role2">Chức năng</a></li>
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role3">Báo cáo</a></li>
                </ul>

                <div class="tab-content">
                    <div id="role1" class="tab-pane fade in active">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox"
                                                                                               id="cb_1"></th>
                                <th class=" ann-title border-table table-giua col-md-3">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>
                            </tr>
                            <c:forEach items="${packageForm.notarySystemManagerList}" var="item">
                                <tr>
                                    <td class="border-table align-giua  "><input type="checkbox" name="cb01"
                                                                                 value="${item.code}_0"
                                                                                 onchange="checkAllInRow(this)"></td>
                                    <td class="border-table" style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_64"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_32"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_16"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_8"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_4"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_2"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb01"
                                                                                                     value="${item.code}_1"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                    <div id="role2" class="tab-pane fade in">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox"
                                                                                               id="cb_2"></th>
                                <th class=" ann-title border-table table-giua col-md-3">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <th class=" ann-title border-table table-giua">Trình duyệt</th>
                                <th class=" ann-title border-table table-giua">Duyệt</th>
                                <th class=" ann-title border-table table-giua">Giải tỏa</th>
                                <%--<th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>--%>
                            </tr>
                            <c:forEach items="${packageForm.notaryfunctionList}" var="item">
                                <tr>
                                    <td class="border-table align-giua  "><input type="checkbox" name="cb02"
                                                                                 value="${item.code}_0"
                                                                                 onchange="checkAllInRow(this)"></td>
                                    <td class="border-table" style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_64"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_32"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_16"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_8"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_4"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_2"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_128"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_256"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb02"
                                                                                                     value="${item.code}_512"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                        <%--<td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_1"></td>--%>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="role3" class="tab-pane fade in">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox"
                                                                                               id="cb_3"></th>
                                <th class=" ann-title border-table table-giua col-md-3">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <%--<th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>--%>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <%--<th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>--%>
                            </tr>
                            <c:forEach items="${packageForm.notaryreportList}" var="item">
                                <tr>
                                    <td class="border-table align-giua  "><input type="checkbox" name="cb03"
                                                                                 value="${item.code}_0"
                                                                                 onchange="checkAllInRow(this)"></td>
                                    <td class="border-table" style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb03"
                                                                                                     value="${item.code}_64"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb03"
                                                                                                     value="${item.code}_4"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox"
                                                                                                     name="cb03"
                                                                                                     value="${item.code}_2"
                                                                                                     onchange="checkChiren(this)">
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
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

<!-- Modal -->
<div class="modal fade" id="xoa" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">

            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite">
                    Xóa thông tin gói dịch vụ

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
            <div class="modal-footer">
                <div class="col-md-3 col-md-offset-3">
                    <a href="${deleteUrl}/${packageForm.id}" class="truong-small-linkbt"><input
                            type="button" class="form-control luu" name="" value="Xóa"></a>
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

<script>
    $(window).on('load resize', function () {
        var win = $(this);
        if (win.width() < 1150) {
            $('.123').removeClass('col-md-2');
            $('.123').addClass('col-md-3');
        } else {
            $('.123').removeClass('col-md-3');
            $('.123').addClass('col-md-2');
        }
    });
</script>
<script>

    $(function () {
        $('#birthday').datepicker({
            format: "dd/mm/yyyy",
            forceParse: false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
    $(document).ready(function () {
        var systemManagerList = "${packageForm.cb01}".split(",");
        var functionList = "${packageForm.cb02}".split(",");
        var reportList = "${packageForm.cb03}".split(",");
        $("input[name='cb01']").each(function () {
            for (var i = 0; i < systemManagerList.length; i++) {
                if (systemManagerList[i] == this.value) {
                    $(this).prop('checked', true);
                    //$(this).parent().children("#cb01").prop('checked', true);
                }
            }
        });
        $("input[name='cb02']").each(function () {
            for (var i = 0; i < functionList.length; i++) {
                if (functionList[i] == this.value) {
                    $(this).prop('checked', true);
                }
            }
        });
        $("input[name='cb03']").each(function () {
            for (var i = 0; i < reportList.length; i++) {
                if (reportList[i] == this.value) {
                    $(this).prop('checked', true);
                }

            }
        });

        //kiem tra cac checkbox trong bang dc check toan bo chua.
        function checkValuecb_1() {
            var result = true;
            $("input[name='cb01']").each(function () {
                if (!$(this).is(':checked')) {
                    result = false;
                }

            });
            return result;
        }

        function checkValuecb_2() {
            var result = true;
            $("input[name='cb02']").each(function () {
                if (!$(this).is(':checked')) {
                    result = false;
                }

            });
            return result;
        }

        function checkValuecb_3() {
            var result = true;
            $("input[name='cb03']").each(function () {
                if (!$(this).is(':checked')) {
                    result = false;
                }

            });
            return result;
        }

        var checkcb1 = checkValuecb_1();
        var checkcb2 = checkValuecb_2();
        var checkcb3 = checkValuecb_3();
        if (checkcb1) {
            $('#cb_1').prop('checked', true);
        }
        if (checkcb2) {
            $('#cb_2').prop('checked', true);
        }
        if (checkcb3) {
            $('#cb_3').prop('checked', true);
        }
        if (checkcb1 && checkcb2 && checkcb3) {
            $('#grouprole-check-all').prop('checked', true);
        }

        //WHEN change value of checkbox group
        $("#cb_1").change(function () {
            if (this.checked) {

                if ($('#cb_1').is(':checked') && $('#cb_2').is(':checked') && $('#cb_3').is(':checked')) {
                    $('#grouprole-check-all').prop('checked', true);
                }
            } else {
                $('#grouprole-check-all').prop('checked', false);
            }
        });
        $("#cb_2").change(function () {
            if (this.checked) {
                if ($('#cb_1').is(':checked') && $('#cb_2').is(':checked') && $('#cb_3').is(':checked')) {
                    $('#grouprole-check-all').prop('checked', true);
                }
            } else {
                $('#grouprole-check-all').prop('checked', false);
            }
        });
        $("#cb_3").change(function () {
            if (this.checked) {
                if ($('#cb_1').is(':checked') && $('#cb_2').is(':checked') && $('#cb_3').is(':checked')) {
                    $('#grouprole-check-all').prop('checked', true);
                }
            } else {
                $('#grouprole-check-all').prop('checked', false);
            }
        });
        $("#grouprole-check-all").change(function () {
            if (this.checked) {
                $('#cb_1').prop('checked', true);
                $('#cb_2').prop('checked', true);
                $('#cb_3').prop('checked', true);
            } else {
                $('#cb_1').prop('checked', false);
                $('#cb_2').prop('checked', false);
                $('#cb_3').prop('checked', false);
            }
        });

    });
    $(document).ready(function () {
        $('.selectAll').checkboxes({
            itemChild: 'cb'
        });
        $('#cb_1').checkboxes({
            itemChild: 'cb01'
        });
        $('#cb_2').checkboxes({
            itemChild: 'cb02'
        });
        $('#cb_3').checkboxes({
            itemChild: 'cb03'
        });
        activaTab('role1');
    });

    function activaTab(tab) {
        $('.nav-tabs a[href="#' + tab + '"]').tab('show');
    }

    function checkAllInRow(e) {
        if ($(e).is(':checked')) $(e).parent().parent().find("input:checkbox").each(function () {
            $(this).prop('checked', true);
        });
        else $(e).parent().parent().find("input:checkbox").each(function () {
            $(this).prop('checked', false);
        });
    }

    function checkChiren(e) {
        if ($(e).is(':checked')) {
            var check = checkAllValue();
            if (check) {
                $(e).parent().parent().find("input:checkbox").each(function (i) {
                    if (i == 0) {
                        $(this).prop('checked', true);
                    }
                });
            }
        } else {
            $(e).parent().parent().find("input:checkbox").each(function (i) {
                if (i == 0) {
                    $(this).prop('checked', false);
                }
            });
        }

        function checkAllValue() {
            var result = true;
            $(e).parent().parent().find("input[type='checkbox']").each(function (i) {
                //ko tinh doi tuong dau tien: item_0
                if (!$(this).is(':checked')) {
                    if (i > 0) {
                        result = false;
                    }

                }

            });
            return result;
        }
    }
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-nhom-quyen").addClass("child-menu");
    });
</script>
