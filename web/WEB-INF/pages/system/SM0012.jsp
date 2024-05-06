<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/checkbox.js"></script>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Thêm mói nhóm quyền
--%>
<spring:url value="/system/create-grouprole" var="addUrl"/>
<spring:url value="/system/grouprole-list" var="backUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới nhóm quyền</span>
</div>
<div class="truong-form-chinhpq">
    <form class="form-horizontal" action="${addUrl}" method="post" id="form-padding">
        <div class="col-md-10 ">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default" id="panel1">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse">
                                THÔNG TIN NHÓM QUYỀN
                            </a>
                        </h4>

                    </div>
                    <div class="panel-body">

                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label required label-bam-trai">Tên nhóm quyền</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="grouprolename">
                            </div>

                        </div>
                        <div class="form-group truong-margin-fg">
                            <label class="label-bam-trai control-label col-md-3">Mô tả</label>
                            <div class="col-md-9">
                                <textarea name="description" rows="4" class="form-control" ></textarea>
                            </div>
                        </div>


                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label label-bam-trai">Trạng thái hoạt động</label>
                            <div class="col-md-9">
                                <label class="radio-inline prevent-type">
                                    <input type="radio" checked name="active_flg" value="1">Đang hoạt động
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="active_flg" value="0">Ngừng hoạt động
                                </label>
                            </div>
                        </div>
                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label  label-bam-trai">Người tạo</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" disabled>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai">Ngày tạo</label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control" disabled>
                            </div>
                        </div>


                        <div class="form-group truong-margin-fg">
                            <label class="col-md-3 control-label  label-bam-trai">Người cập nhật</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control" disabled>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai">Ngày cập nhật</label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control" disabled>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


        </div>
        <div class="col-md-4 ">
            <input type="checkbox" class="selectAll " id="grouprole-check-all" ><span class="truong-font-chu" style="vertical-align: 1px;">Tất cả các quyền</span>
        </div>

        <div class="col-md-12">
            <div class="panel-group">


                <ul class="nav nav-tabs">
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role1">Quản trị hệ thống</a></li>
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role2">Chức năng</a></li>
                    <li class="col-md-2 123"><a data-toggle="tab" href="#role3" >Báo cáo</a></li>
                </ul>

                <div class="tab-content">
                    <div id="role1" class="tab-pane fade in active">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox" id="cb_1"></th>
                                <th class=" ann-title border-table table-giua col-md-4 ">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>
                            </tr>
                            <c:forEach items="${createGroupRoleForm.systemManagerList}" var="item">
                                <tr>
                                    <td class="border-table align-giua  "><input type="checkbox" name="cb01" value="${item.code}_0" onchange="checkAllInRow(this)"></td>
                                    <td class="border-table " style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_64"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_32"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_16"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_8"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_4"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_2"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb01" value="${item.code}_1"></td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                    <div id="role2" class="tab-pane fade in">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox" id="cb_2"></th>
                                <th class=" ann-title border-table table-giua col-md-4">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <%--<th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>--%>
                            </tr>
                            <c:forEach items="${createGroupRoleForm.functionList}" var="item">
                                <tr>
                                    <td class="border-table  align-giua "><input type="checkbox" name="cb02" value="${item.code}_0" onchange="checkAllInRow(this)"></td>
                                    <td class="border-table" style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_64"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_32"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_16"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_8"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_4"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_2"></td>
                                        <%--<td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb02" value="${item.code}_1"></td>--%>

                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="role3" class="tab-pane fade in">
                        <table class="table" style="margin-bottom:0%">
                            <tr class="border-table">
                                <th class=" ann-title border-table table-giua col-md-1"><input type="checkbox" id="cb_3"></th>
                                <th class=" ann-title border-table table-giua col-md-4">Chức năng</th>
                                <th class=" ann-title border-table table-giua">Xem</th>
                                <%--<th class=" ann-title border-table table-giua">Thêm</th>
                                <th class=" ann-title border-table table-giua">Sửa</th>
                                <th class=" ann-title border-table table-giua">Xóa</th>--%>
                                <th class=" ann-title border-table table-giua">Tìm kiếm</th>
                                <th class=" ann-title border-table table-giua">In</th>
                                <%--<th class=" ann-title border-table table-giua">Sao lưu/phục hồi</th>--%>
                            </tr>
                            <c:forEach items="${createGroupRoleForm.reportList}" var="item">
                                <tr>
                                    <td class="border-table align-giua  "><input type="checkbox" name="cb03" value="${item.code}_64" onchange="checkAllInRow(this)"></td>
                                    <td class="border-table" style="color: black;">${item.name}</td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_64"></td>
                                        <%--<td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_32"></td>
                                        <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_16"></td>
                                        <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_8"></td>--%>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_4"></td>
                                    <td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_2"></td>
                                        <%--<td class="border-table align-giua" style="color: black;"><input type="checkbox" name="cb03" value="${item.code}_1"></td>--%>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>

                </div>


            </div>
        </div>
        <div class="truong-prevent-btb col-md-12">
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
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1150){
            $('.123').removeClass('col-md-2');
            $('.123').addClass('col-md-3');
        }
        else{
            $('.123').removeClass('col-md-3');
            $('.123').addClass('col-md-2');
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
    $(document).ready(function() {
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
    function checkAllInRow(e){
        if($(e).is(':checked')) $(e).parent().parent().find("input:checkbox").each(function() {
            $(this).prop('checked', true);
        });
        else $(e).parent().parent().find("input:checkbox").each(function() {
            $(this).prop('checked', false);
        });
    }

    function activaTab(tab) {
        $('.nav-tabs a[href="#' + tab + '"]').tab('show');
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