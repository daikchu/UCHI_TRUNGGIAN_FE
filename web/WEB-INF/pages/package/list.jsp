<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Danh sách gói dịch vụ
--%>
<spring:url value="/package/create-package-view" var="createPackageUrl"/>
<spring:url value="/package/update-package-view" var="updatePackageUrl"/>
<spring:url value="/package/package-list" var="packageUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách gói dịch vụ</span>
</div>
<div class="truong-form-chinhds panel-group">
    <c:if test="${packageListForm.action_status != null}">
        <div class="padding-default" id="uchi-status">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${packageListForm.action_status} </div>
        </div>
    </c:if>

    <form class="form-horizontal" action="${packageUrl}" method="get" id="search-frm">
        <input type="hidden" name="listNumber" value="${packageListForm.listNumber}">
        <input type="hidden" name="totalPage" id="totalPage" value="${packageListForm.totalPage}">
        <input type="hidden" name="page" id="page" value="${packageListForm.page}">
        <div class="panel-body">

            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai text-nowrap">Tên gói dịch vụ</label>
                <div class="col-md-2">
                    <input type="text" class="form-control" name="titleFilter" value="${packageListForm.titleFilter}">
                </div>

                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-4 truong-rs-bttk">
                            <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                        </div>
                    </div>

                </div>
            </div>

        </div>

        <div class="panel-body truong-margin-footer0px">
            <div class="col-md-12 truong-image-fix export-button">
                <a class="truong-small-linkbt" href="${createPackageUrl}"><input type="button"
                                                                                   class="form-control luu truong-image-bt "
                                                                                   name="" value="Thêm mới"></a>
            </div>
            <table class="table" style="margin-bottom:0%">
                <tr class="border-table">
                    <th class=" ann-title border-table table-giua truong-rstable-widthper5">STT</th>
                    <th class=" ann-title border-table table-giua truong-rstable-widthper15">Tên gói dịch vụ</th>
                    <th class=" ann-title border-table table-giua ">Mô tả</th>
                </tr>
                <c:if test="${packageListForm.listNumber ==0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Không có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${packageListForm.listNumber > 0}">
                    <c:forEach items="${packageListForm.packages}" var="item" varStatus="index">
                        <tr>
                            <td class="border-table align-giua  ">${firstRecord+index.index}</td>
<%--                            <c:if test="${item.grouprolename.length() <= 200}">--%>
                                <td class="border-table truong-text-verticel"
                                    style="color: black;word-break: break-all;">
                                    <a href="${updatePackageUrl}/${item.id}">${item.name}</a>
                                </td>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.grouprolename.length() > 200}">--%>
<%--                                <td class="border-table truong-text-verticel"--%>
<%--                                    style="color: black;word-break: break-all;">--%>
<%--                                    <a href="${updateGroupRoleUrl}/${item.groupRoleId}">${item.grouprolename.substring(0,200)}</a>--%>
<%--                                    <img data-html="true" data-toggle="popover" data-trigger="hover"--%>
<%--                                         data-placement="top"--%>
<%--                                         data-content="<div class='title-green'>Chi tiết tên nhóm quyền:</div>${item.grouprolename}"--%>
<%--                                         src="<%=request.getContextPath()%>/static/image/xemthem.png">--%>
<%--                                </td>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.description.length() <= 200}">--%>
<%--                                <td class="border-table truong-text-verticel"--%>
<%--                                    style="color: black;word-break: break-all;">--%>
<%--                                        ${item.description}--%>
<%--                                </td>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${item.description.length() > 200}">--%>
<%--                                <td class="border-table truong-text-verticel"--%>
<%--                                    style="color: black;word-break: break-all;">--%>
<%--                                        ${item.description.substring(0,200)} <img data-html="true" data-toggle="popover"--%>
<%--                                                                                  data-trigger="hover"--%>
<%--                                                                                  data-placement="top"--%>
<%--                                                                                  data-content="<div class='title-green'>Chi tiết mô tả:</div>${item.description}"--%>
<%--                                                                                  src="<%=request.getContextPath()%>/static/image/xemthem.png">--%>
<%--                                </td>--%>
<%--                            </c:if>--%>
                            <td class="border-table truong-text-verticel"
                                style="color: black;word-break: break-all;">
                                    ${item.description}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7">
                            <div class="truong-padding-foot-table"> Tổng số <span
                                    class="truong-text-colorred">${packageListForm.listNumber}</span> dữ
                                liệu
                            </div>
                            <div class="align-phai">
                                <c:if test="${packageListForm.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${groupRoleListForm.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${packageListForm.page}
                                trên ${packageListForm.totalPage}
                                <c:if test="${packageListForm.page==packageListForm.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${packageListForm.page!=packageListForm.totalPage}">
                                    <img onclick="nextPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img onclick="lastPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                            </div>
                        </th>
                    </tr>
                </c:if>
            </table>
        </div>
    </form>
</div>
<script>
    $(window).on('load resize', function () {
        var win = $(this);
        if (win.width() < 1371) {
            $('.truong-rs-bttk').removeClass('col-md-4');
            $('.truong-rs-bttk').addClass('col-md-6');

        } else {
            $('.truong-rs-bttk').removeClass('col-md-6');
            $('.truong-rs-bttk').addClass('col-md-4');
        }
    });
</script>
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
        var totalPage = $('#totalPage').val();
        $('#page').val(totalPage);
        $("#search-frm").submit();
    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-khach-hang");
        $(parentItem).click();
        $("#quan-ly-goi-dich-vu").addClass("child-menu");
        $('[data-toggle="popover"]').popover();
    });
</script>


