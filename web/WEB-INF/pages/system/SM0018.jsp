<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách dữ liệu sao lưu
--%>
<spring:url value="/system/backup-list" var="backupListUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách dữ liệu sao lưu</span>
</div>
<div class="truong-form-chinhds panel-group">

    <form class="form-horizontal" action="${backupListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${backupList.page}">
        <input type="hidden" name="totalPage" id="userTotalPage" value="${backupList.totalPage}">
        <div class="panel-body">

            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tên file dữ liệu</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="filename" value="${backupList.filename}">
                </div>

                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-8 truong-rs-bttk">
                            <input type="submit" class="form-control luu truong-rs-bt1 " value="Tìm kiếm">
                        </div>
                    </div>
                </div>
            </div>




        </div>

        <div class="panel-body truong-margin-footer0px">
            <div class=" col-md-12 export-button truong-image-fixxoa">
                <a class="truong-small-linkbt" href=""> <input type="button" class="form-control huybo truong-image-bttrash "  name="" value="Xóa" ></a>

            </div>
            <table class="table border-table">
                <tr class="border-table">
                    <th class="border-table table-giua col-md-1"><input type="checkbox" class="selectAll"></th>
                    <th class="ann-title border-table table-giua col-md-9">Tên file, thư mục</th>
                    <th class="ann-title border-table table-giua col-md-2">Thời gian cập nhật</th>
                </tr>
                <c:forEach items="${backupList.list}" var="item">
                    <tr>
                        <td class="border-table align-giua col-md-1"><input type="checkbox" name="cb11" class="cb11" value="${item.fileName}"></td>
                        <td class="border-table "><div class="truong-image-table"></div>${item.fileName}</td>
                        <td class="border-table truong-text-center">${item.dateFile}</td>
                    </tr>
                </c:forEach>
                <tr class="table-tr-xam">
                    <th colspan="7">Tổng số <span style="color:red">${backupList.list.size()}</span> dữ
                        liệu
                        <div class="align-phai">
                            <img onclick="firstPage()"
                                 class="pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                            <img onclick="previewPage()"
                                 class="pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                            ${backupList.page}
                            trên ${backupList.totalPage}
                            <img onclick="nextPage()"
                                 class="pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                            <img onclick="lastPage()"
                                 class="pagging-icon"
                                 src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                        </div>
                    </th>
                </tr>
            </table>

        </div>
    </form>
</div>

<script>
    $('.selectAll').click(function(){
        $('input:checkbox').not(this).prop('checked', this.checked);
    });
</script>
<script>
    $(window).on('resize load', function() {
        var win = $(this);
        if (win.width() < 1437) {
            $('.truong-rs-bttk').removeClass('col-md-8');
            $('.truong-rs-bttk').addClass('col-md-10');

        } else {
            $('.truong-rs-bttk').removeClass('col-md-10');
            $('.truong-rs-bttk').addClass('col-md-8');
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
        $("#ds-backup").addClass("child-menu");
    });
</script>

