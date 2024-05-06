<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách cán bộ
--%>
<spring:url value="/system/add-user-view" var="addUrl" />
<spring:url value="/system/user-list" var="userListUrl" />
<spring:url value="/system/user-permission-view" var="userPermissionViewUrl" />
<spring:url value="/system/user-update-view" var="updateUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách cán bộ</span>
</div>
<div class="truong-form-chinhds panel-group">
    <div id="uchi-status" class="padding-default">
        <c:if test="${users.action_status != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${users.action_status}</div>
        </c:if>
    </div>
    <form class="form-horizontal" action="${userListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${users.page}">
        <input type="hidden" name="userTotalPage" id="userTotalPage" value="${users.userTotalPage}">
        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-1 control-label label-bam-trai">Họ đệm</label>
                <div class="col-md-2">
                    <input type="text" class="form-control" name="family_name" id="family_name" value="${users.family_name}">
                </div>

                <label class="col-md-1 control-label label-bam-phai">Tên</label>
                <div class="col-md-2">
                    <input type="text" class="form-control" name="first_name" id="first_name" value="${users.first_name}">
                </div>

            </div>


            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tài khoản đăng nhập</label>
                <div class="col-md-2">
                    <input type="text" class="form-control" name="account" id="account" value="${users.account}">
                </div>

                <label class="col-md-2 control-label label-bam-phai">Trạng thái hoạt động</label>
                <div class="col-md-2">
                    <select class=" form-control" name="active_flg" id ="active_flg">
                        <option value="0" ${users.active_flg == 0 ?"selected":""}>[Tất cả]</option>
                        <option value="1" ${users.active_flg == 1 ?"selected":""}>Đang hoạt động</option>
                        <option value="2" ${users.active_flg == 2 ?"selected":""}>Ngừng hoạt động</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-4 123">

                            <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                        </div>
                        <div class="col-md-5 123">
                            <input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="panel-body">
            <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${addUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>

            </div>
            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua col-md-3">Họ tên</th>
                    <th class=" ann-title border-table table-giua">Tài khoản đăng nhập</th>
                    <th class=" ann-title border-table table-giua">Ngày sinh</th>
                    <th class=" ann-title border-table table-giua col-md-2">Chức vụ</th>
                    <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>
                    <th class=" ann-title border-table table-giua">Phân quyền</th>

                </tr>
                <c:if test="${users.userListNumber > 0}">
                    <c:forEach items="${users.userList}" var="item">
                        <tr>
                            <td class="border-table   "><a href="${updateUrl}/${item.userId}">${item.family_name} ${item.first_name}</a></td>
                            <td class="border-table" style="color: black;">${item.account}</td>
                            <td class="border-table truong-text-center" style="color: black;">${item.birthday}</td>
                            <td class="border-table" style="color: black;">${item.role}</td>

                            <td class="border-table truong-text-center" style="color: black;">
                                <c:if test="${item.active_flg==1}"><span class="truong-circle-green">Đang hoạt động</span></c:if>
                                <c:if test="${item.active_flg==0}"><span class="truong-circle-silver"> Ngừng hoạt động</span></c:if>
                            </td>
                            <td class="border-table truong-text-center" style="color: black;"><a href="${userPermissionViewUrl}/${item.userId}"><img class="truong-image-tb"src="<%=request.getContextPath()%>/static/image/phanquyen.png"></a></td>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7">Tổng số<span style="color:red">${users.userListNumber}</span> dữ
                            liệu
                            <div class="align-phai">
                                <img onclick="firstPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                <img onclick="previewPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                    ${users.page}
                                trên ${users.userTotalPage}
                                <img onclick="nextPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                <img onclick="lastPage()"
                                     class="pagging-icon"
                                     src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                            </div>
                        </th>
                    </tr>
                </c:if>
                <c:if test="${users.userListNumber == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 365%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </form>
</div>
<script>
    $(window).on('resize', function() {
        var win = $(this);
        if (win.width() < 1371) {
            $('.123').removeClass('col-md-4');
            $('.123').addClass('col-md-6');

        } else {
            $('.123').removeClass('col-md-6');
            $('.123').addClass('col-md-4');
        }
    });
</script>
<%--
<script>
    $(window).on('resize', function() {
        var win = $(this);
        if (win.width() < 1600) {
            $('.truong-re-bt').removeClass('col-md-1');
            $('.truong-re-bt').addClass('col-md-2');
            $('.truong-re-bt').removeClass('col-md-offset-11');
            $('.truong-re-bt').addClass('col-md-offset-10');

        } else {
            $('.truong-re-bt').removeClass('col-md-2');
            $('.truong-re-bt').addClass('col-md-1');
            $('.truong-re-bt').removeClass('col-md-offset-10');
            $('.truong-re-bt').addClass('col-md-offset-11');
        }
    });
</script>
--%>

<script>
    function firstPage() {
        $('#page').val(1);
        $("#search-frm").submit();
    }
    function previewPage() {
        var page = $('#page').val();
        $('#page').val(parseInt(page) - 1);
        $("#search-frm").submit();
    }
    function nextPage() {
        var page = $('#page').val();
        $('#page').val(parseInt(page) + 1);
        $("#search-frm").submit();
    }
    function lastPage() {
        var userTotalPage = $('#userTotalPage').val();
        $('#page').val(userTotalPage);
        $("#search-frm").submit();
    }
    function clearText(){
        $('#family_name').val("");
        $('#first_name').val("");
        $('#active_flg').val(0);
        $('#account').val("");
    }
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-can-bo-stp").addClass("child-menu");
    });
</script>


