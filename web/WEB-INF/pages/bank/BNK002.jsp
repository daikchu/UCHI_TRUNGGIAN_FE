<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách Tỉnh
--%>
<spring:url value="/bank/add-view" var="addUrl" />
<spring:url value="/bank/list" var="bankListUrl" />
<spring:url value="/bank/update-view" var="updateUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách ngân hàng</span>
</div>
<div class="truong-form-chinhds panel-group">
    <c:if test="${bankList.action_status != null}">
        <div class="padding-default" id="uchi-status">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${bankList.action_status} </div>
        </div>
    </c:if>

    <form class="form-horizontal" action="${bankListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${bankList.page}">
        <input type="hidden" name="bankTotalPage" id="bankTotalPage" value="${bankList.totalPage}">


        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Tên ngân hàng</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="name" id="name1" value="${bankList.name}">
                </div>



            </div>
            <div class="form-group">


                <label class="col-md-2 control-label label-bam-trai">Mã ngân hàng</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="code" id="code" value="${bankList.code}">
                </div>

                    <div class="truong-button-fixwidth-right" style="margin-right: 15px !important;">
                        <input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">
                    </div>
                    <div class="truong-button-fixwidth-right">

                        <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                    </div>


            </div>




        </div>

        <div class="col-md-12 truong-margin-footer0px">
            <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                <a class="truong-small-linkbt" href="${addUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>

            </div>
            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua col-md-3">Tên ngân hàng</th>
                    <th class=" ann-title border-table table-giua col-md-2">Mã mã ngân hàng</th>
                    <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>



                </tr>
                <c:if test="${bankList.totalBank > 0}">
                    <c:forEach items="${bankList.listBank}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel"><a href="${updateUrl}/${item.sid}">${item.name}</a></td>
                            <td class="border-table truong-rstable-widthper15 truong-text-verticel" style="color: black;">${item.code}</td>

                            <td class="border-table truong-rstable-widthper8" style="color: black;">
                                <c:if test="${item.active==1}"><div class="truong-online-fix"><div class="truong-creat-circlegr" ></div>Đang hoạt động</div></c:if>
                                <c:if test="${item.active==0}"><div class="truong-offline-fix"><div class="truong-creat-circlesv" ></div>Ngừng hoạt động</div></c:if>
                            </td>


                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${bankList.totalBank}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <c:if test="${bankList.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${bankList.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${bankList.page}
                                trên ${bankList.totalPage}
                                <c:if test="${bankList.page == bankList.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${bankList.page != bankList.totalPage}">
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
                <c:if test="${bankList.totalBank == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
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
        var provinceTotalPage = $('#bankTotalPage').val();
        $('#page').val(provinceTotalPage);
        $("#search-frm").submit();
    }
    function clearText(){
        $('#name1').val("");
        $('#code').val("");

    }
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #ngan-hang").addClass("father-menu");
    }
</script>


