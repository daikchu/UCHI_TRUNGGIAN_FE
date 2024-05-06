<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách câu hỏi trợ giúp
--%>
<spring:url value="/question-help/add-view" var="addUrl" />
<spring:url value="/question-help/list" var="listUrl" />
<spring:url value="/question-help/update-view" var="updateUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách câu hỏi trợ giúp</span>
</div>
<div class="truong-form-chinhds panel-group">
<%--    <c:if test="${provinceList.action_status != null}">
        <div class="padding-default" id="uchi-status">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${qaList.action_status} </div>
        </div>
    </c:if>--%>

    <form class="form-horizontal" action="${listUrl}" id="search-frm" method="get">
        <input type="hidden" name="page" id="page" value="${qaList.page}">
        <input type="hidden" name="questionTotalPage" id="questionTotalPage" value="${qaList.totalPage}">
        <div class="panel-body">
            <div class="form-group">
                <div class="col-md-6">
                    <label class="col-md-2 control-label label-bam-trai">Tiêu đề</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="question" value="${qaList.question}">
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="col-md-4 control-label label-bam-trai">Câu hỏi dành cho</label>
                    <div class="col-md-4">
                        <select class=" form-control" name="type">
                            <option value="" ${qaList.type == null || qaList.type == "" ?"selected":""}>[Tất cả]</option>
                            <option value="${Constants.type_QASTP}"} ${qaList.type == Constants.type_QASTP ?"selected":""}>Sở tư pháp</option>
                            <option value="${Constants.type_QATCCC}" ${qaList.type == Constants.type_QATCCC ?"selected":""}>Tổ chức công chứng</option>
                        </select>
                    </div>
                </div>


            </div>
            <div class="form-group">


               <%-- <label class="col-md-2 control-label label-bam-trai">Mã tỉnh</label>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="code" id="code" value="${qaList.code}">
                </div>--%>

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
                    <th class=" ann-title border-table table-giua">Tiêu đề</th>
                    <th class=" ann-title border-table table-giua">Câu trả lời</th>
                    <th class=" ann-title border-table table-giua">Câu hỏi dành cho</th>
                    <th class=" ann-title border-table table-giua" >Hành động</th>


                </tr>
                <c:if test="${qaList.totalQuestion > 0}">
                    <c:forEach items="${qaList.listQuestion}" var="item">
                        <tr>
                            <td class="border-table truong-text-verticel"><a href="${updateUrl}/${item.id}">${item.question}</a></td>
                            <td class="border-table <%--truong-rstable-widthper15--%> truong-text-verticel" style="color: black;">${item.answer}</td>
                            <td class="border-table <%--align-giua truong-rstable-widthper8--%> truong-text-verticel" style="color: black;">${item.type == Constants.type_QASTP?'Sở tư pháp':'Tổ chức công chứng'}</td>
                            <td class="border-table <%--truong-rstable-widthper8--%> truong-text-verticel">
                                <div class="btn-group">
                                    <button class="btn btn-default btn-md dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="${updateUrl}/${item.id}"><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</a></li>
                                            <%--<li><a href="<%=request.getContextPath()%>/template/privy/delete/${item.id}"><i class="fa fa-pencil-square-o"></i>Xóa</a></li>--%>
                                        <li><a class="deleteItem"
                                               data-toggle="modal" data-target="#deleteItem"
                                               data-question.id="${item.id}"><i class="fa fa-pencil-square-o"></i>Xóa</a></li>
                                    </ul>
                                </div>
                            </td>

                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${qaList.totalQuestion}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <c:if test="${qaList.page==1}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                <c:if test="${qaList.page!=1}">
                                    <img onclick="firstPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                    <img onclick="previewPage()"
                                         class="pagging-icon"
                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                </c:if>
                                    ${qaList.page}
                                trên ${qaList.totalPage}
                                <c:if test="${qaList.page == qaList.totalPage}">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                    <img
                                            class="truong-pagging-icon"
                                            src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                </c:if>
                                <c:if test="${qaList.page != qaList.totalPage}">
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
                <c:if test="${qaList.totalQuestion == 0}">
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

<div class="modal fade" id="deleteItem" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <form action="<%=request.getContextPath()%>/question-help/delete" id="delete-form" method="POST">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Xóa thông tin câu hỏi trợ giúp</h4>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn xóa mẫu thông tin câu hỏi trợ giúp này chứ? </p>
                    <input  type="hidden" name="id" class="form-control delete_id" />
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" >Xóa</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>
        </form>
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
        var questionTotalPage = $('#questionTotalPage').val();
        $('#page').val(questionTotalPage);
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
        $(".deleteItem").click(function(){
            var id=$(this).data('question.id');
            $(".delete_id").val(id);
        });
    });
    function loadMenu() {
        $(".sidebar-nav > li > #tinh-thanh").addClass("father-menu");
    }
</script>


