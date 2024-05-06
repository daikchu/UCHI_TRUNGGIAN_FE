<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách Tổ chức công chứng
--%>
<spring:url value="/customer/list" var="listUrl"/>
<spring:url value="/customer/add-view" var="addViewUrl"/>
<spring:url value="/customer/update-view" var="updateUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách Tổ chức công chứng</span>
</div>
<div class="truong-form-chinhds">
    <div class="panel-group" class="padding-default">
        <c:if test="${customerListWrapper.action_status != null}">
            <div class="padding-default" id="uchi-status">
                <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${customerListWrapper.action_status} </div>
            </div>
        </c:if>
<%-- Form tìm kiếm tổ chức công chứng --%>
        <form class="form-horizontal" id="search-frm" action="${listUrl}" method="get">
            <div class="panel-body">
                <div class>
                    <input type="hidden" name="notaryDateFromFilter" id="notaryDateFromFilter"
                           value="${customerListWrapper.customerDateFromFilter}">
                    <input type="hidden" name="notaryDateToFilter" id="notaryDateToFilter"
                           value="${customerListWrapper.customerDateToFilter}">
                    <input type="hidden" name="listNumber" value="${customerListWrapper.total}">
                    <input type="hidden" name="totalPage" id="totalPage" value="${customerListWrapper.totalPage}">
                    <input type="hidden" name="page" id="page" value="${customerListWrapper.page}">
<%--                    <input type="hidden" name="province" id="province" value="${customerListWrapper.provinceList}">--%>
                    <div class="form-group">
                      <%--Tìm kiếm theo Tên tổ chức công chứng--%>
                        <label class="col-md-2 control-label label-bam-trai"> Tên tổ chức </label>
                        <div class="col-md-3 replace">
                            <input type="text" class="form-control" name="nameFilter" id="nameFilter"
                                   value="${customerListWrapper.nameFilter}">
                        </div>
                    </div>
                    <div class="form-group">
                        <%--Tìm kiếm theo Ngày hết hạn--%>
                        <label class="col-md-2 control-label label-bam-trai">Ngày hết hạn</label>
                        <div class="col-md-10">
                            <div class="row">
                                <div class="col-md-3 truong-padding-deleteright">
                                    <select class="form-control truong-selectbox-padding select2" name="timeType" id="timeType"
                                            onchange="timeTypeChange()">
                                        <option value="01" ${customerListWrapper.timeType.equals('01')?"selected":""}>Trong ngày
                                            hôm nay
                                        </option>
                                        <option value="02" ${customerListWrapper.timeType.equals('02')?"selected":""}>Trong tuần
                                            này
                                        </option>
                                        <option value="03" ${customerListWrapper.timeType.equals('03')?"selected":""}>Trong tháng
                                            này
                                        </option>
                                        <option value="04" ${customerListWrapper.timeType.equals('04')?"selected":""}>Trong năm
                                            nay
                                        </option>
                                        <option value="05" ${customerListWrapper.timeType.equals('05')?"selected":""}>Trong khoảng
                                            thời gian
                                        </option>
                                    </select>
                                </div>
                            <%-- Tìm kiếm theo khoảng thời gian --%>
                                <div class="col-md-9">
                                    <div class="row">
                                        <div ${customerListWrapper.timeType.equals('05')?"":"style='display: none;'"}
                                                id="dateTimeFilter">
                                            <div class="col-md-9" id="to-from">
                                                <div class="row">
                                                    <label class="col-md-3 control-label  label-bam-trai">Từ ngày</label>
                                                    <div class="col-md-3 truong-padding-delete">
                                                        <input type="text" value="${customerListWrapper.printFromDate}"
                                                               class="form-control date-time" name="printFromDate"
                                                               id="fromDate">
                                                        <div style="margin-top: 5px;color: red;"
                                                             class="error_tooltip">${validate_msg_from_date}</div>
                                                    </div>
                                                    <label class="col-md-3 control-label  label-bam-trai">Đến ngày</label>
                                                    <div class="col-md-3 truong-padding-delete">
                                                        <input type="text" value="${customerListWrapper.printToDate}"
                                                               class="form-control date-time" name="printToDate" id="toDate">
                                                        <div style="margin-top: 5px;color: red;"
                                                             class="error_tooltip">${validate_msg_to_date}</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                          <div class="col-md-7 styleSearch">
                              <div class="truong-button-fixwidth-right">
                                  <input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">
                              </div>
                              <div id="bt-search" class="truong-button-fixwidth-right">
                                  <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                              </div>
                          </div>
                    </div>
                </div>
            </div>
        </form>




        <div class="panel-body truong-margin-footer0px" id="notaryTable">
         <%-- Button thêm mói thông tin tổ chức công chức  --%>
             <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                 <a class="truong-small-linkbt" href="${addViewUrl}"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>
             </div>
         <%-- Bảng thống kê danh sách tổ chức công chứng --%>
            <table class="table" style="margin-bottom:0%">

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua truong-rstable-widthper5">STT</th>
                    <th class=" ann-title border-table table-giua ">Tên tổ chức</th>
                    <th class=" ann-title border-table table-giua ">Tỉnh/Thành phố</th>
                    <th class=" ann-title border-table table-giua ">Số điện thoại</th>
                    <th class=" ann-title border-table table-giua ">Email</th>
                    <th class=" ann-title border-table table-giua">Ngày bắt đầu</th>

                </tr>
                <c:if test="${customerListWrapper.total == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Không có dữ liệu
                        </td>
                    </tr>
                </c:if>
                <c:if test="${customerListWrapper.total != 0}">

                    <c:forEach items="${customerListWrapper.customers}" var="item" varStatus="index">

                        <tr>
                            <td class="border-table align-giua  ">${firstRecord+index.index}</td>

                            <td class="border-table truong-text-verticel" style="color: black;">
                                <a href="${updateUrl}/${item.noid}">${item.name}</a>
                            </td>

                            <td class="border-table truong-text-verticel" style="color: black;">
<%--                                    ${item.province_code}--%>
                                <c:forEach items="${customerListWrapper.provinceList}" var="province">
                                    <c:if test="${item.province_code.equals(province.code)}">
                                        ${province.name}
                                    </c:if>
                                </c:forEach>
                            </td>

                            <td class="border-table truong-text-verticel" style="color: black;">
                                    ${item.phone}
                            </td>
                            <td class="border-table truong-text-verticel" style="color: black;">
                                    ${item.email}
                            </td>
                            <td class="border-table" style="color: black;">
                                ${item.date_start}
                            </td>
                        </tr>
                    </c:forEach>


                    <tr class="table-tr-xam">
                        <th colspan="7">
                            <div class="truong-padding-foot-table"> Tổng số <span
                                    class="truong-text-colorred">${customerListWrapper.total}</span> dữ liệu
                            </div>
                            <div class="align-phai">
                                <c:if test="${customerListWrapper.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${customerListWrapper.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${customerListWrapper.page}
                                trên ${customerListWrapper.totalPage}
                                <c:if test="${customerListWrapper.page== customerListWrapper.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${customerListWrapper.page!= customerListWrapper.totalPage}">
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

    </div>
</div>


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
        var userTotalPage = $('#totalPage').val();
        $('#page').val(userTotalPage);
        $("#search-frm").submit();
    }
    function clearText(){
        $('#nameFilter').val("");
        $('#timeType').val("03");
        document.getElementById("dateTimeFilter").style.display = 'none';
    }

    function timeTypeChange() {
        var win = $(this);
        var timeType = $('#timeType').val();
        if (timeType == "05") {
            if (win.width() < 1114) {
                $('#to-from').removeClass('col-md-9');
                $('#to-from').addClass('col-md-12');
                $('#bt-search').css('display', 'block');
                $('#to-from').addClass('truong-padding-right30');
            } else {
                $('#to-from').removeClass('col-md-12');
                $('#to-from').addClass('col-md-9');
                $('#bt-search').css('display', 'block');
                $('#to-from').removeClass('truong-padding-right30');
            }
            $('.date-time').val('');
        }
        else {
            $('#to-from').removeClass('col-md-12');
            $('#to-from').addClass('col-md-9');
            $('#search').css('display', 'block');
            $('#to-from').removeClass('truong-padding-right30');
        }
    };


</script>
<script>
    $(window).on('load resize', function () {
        var win = $(this);
        var timeType = $('#timeType').val();
        if (timeType == "05") {
            if (win.width() < 1114) {
                $('#to-from').removeClass('col-md-9');
                $('#to-from').addClass('col-md-12');
                $('#bt-search').css('display', 'block');
                $('#to-from').addClass('truong-padding-right30');

            } else {
                $('#to-from').removeClass('col-md-12');
                $('#to-from').addClass('col-md-9');
                $('#bt-search').css('display', 'block');
                $('#to-from').removeClass('truong-padding-right30');

            }
        }

    });
</script>

<script>
    $(document).ready(function () {
        $("#timeType").change(function () {
            var timeType = $('#timeType option:selected').val();
            if (timeType == '05') {
                $('#dateTimeFilter').css("display", "block");
            }
            if (timeType != '05') {
                $('#dateTimeFilter').css("display", "none");
            }
        });
    });
    $(function () {
        $('#fromDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse: false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
        $('#toDate').datepicker({
            format: "dd/mm/yyyy",
            forceParse: false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-khach-hang");
        $(parentItem).click();
        $("#quan-ly-khach-hang").addClass("child-menu");
    });
</script>
<style>
    .replace{
        width: 335px;
    }
    .styleSearch{
        margin-top: 12px;
    }
</style>
