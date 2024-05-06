<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách Tỉnh
--%>
<spring:url value="/contracttemplate/add-view" var="addUrl" />
<spring:url value="/contracttemplate/list" var="contractTempListUrl" />
<spring:url value="/contracttemplate/update-view" var="updateUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách hợp đồng</span>
</div>
<div class="truong-form-chinhds panel-group">
    <c:if test="${contractTempList.action_status != null}">
        <div class="padding-default" id="uchi-status">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${contractTempList.action_status} </div>
        </div>
    </c:if>

    <form class="form-horizontal" action="${contractTempListUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${contractTempList.page}">
        <input type="hidden" name="totalPage" id="totalPage" value="${contractTempList.totalPage}">


        <div class="panel-body">
            <div class="form-group">
                <label class="col-md-2 control-label required label-bam-trai">Tên hợp đồng</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" name="name" id="name1" value="${contractTempList.name}">

                </div>

                <label class="col-md-2 control-label required label-bam-phai">Mã hợp đồng</label>
                <div class="col-md-4">
                    <select name="code" class="form-control truong-selectbox-padding">
                        <option value="">Tất cả</option>
                        <c:forEach items="${contractTempList.listContractKind}" var="item">
                            <option value="${item.contract_kind_code}" ${contractTempList.code==item.contract_kind_code?"selected":""}>${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label label-bam-trai">Trạng thái </label>
                <div class="col-md-4  label-bam-trai">
                    <select name="active_flg" class="form-control truong-selectbox-padding">
                        <option value="">Tất cả</option>
                        <option value="1" ${contractTempList.active_flg==1?"selected":""}>Đang hoạt động</option>
                        <option value="0" ${contractTempList.active_flg==0?"selected":""}>Ngừng hoạt động</option>
                    </select>
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
                    <th class=" ann-title border-table table-giua">Tên hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Mã hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>



                </tr>
                <c:if test="${contractTempList.totalContractTemp > 0}">
                    <c:forEach items="${contractTempList.listContractTemp}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel"><a href="${updateUrl}/${item.sid}">${item.name}</a></td>
                            <td class="border-table truong-rstable-widthper15 truong-text-verticel" style="color: black;">${item.contractKindName}</td>

                            <td class="border-table truong-rstable-widthper8" style="color: black;">
                                <c:if test="${item.active_flg==1}"><div class="truong-online-fix"><div class="truong-creat-circlegr" ></div>Đang hoạt động</div></c:if>
                                <c:if test="${item.active_flg==0}"><div class="truong-offline-fix"><div class="truong-creat-circlesv" ></div>Ngừng hoạt động</div></c:if>
                            </td>


                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${contractTempList.totalContractTemp}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <c:if test="${contractTempList.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${contractTempList.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${contractTempList.page}
                                trên ${contractTempList.totalPage}
                                <c:if test="${contractTempList.page == contractTempList.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${contractTempList.page != contractTempList.totalPage}">
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
                <c:if test="${contractTempList.totalContractTemp == 0}">
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
        var totalPage = $('#totalPage').val();
        $('#page').val(totalPage);
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
        $(".sidebar-nav > li > #mau-hop-dong").addClass("father-menu");
    }
</script>


