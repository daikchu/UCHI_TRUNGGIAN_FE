<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Mẫu đương sự</span>
</div>
<div class="truong-form-chinhds panel-group">

    <form class="form-horizontal" action="<%=request.getContextPath()%>/template/privy/list" id="search-frm" method="get">

        <%--<div class="panel-body">--%>
            <%--<div class="form-group">--%>
                <%--<label class="col-md-2 control-label required label-bam-trai">Tên hợp đồng</label>--%>
                <%--<div class="col-md-4">--%>
                    <%--<input type="text" class="form-control" name="name" id="name1" value="${page.name}">--%>

                <%--</div>--%>

                <%--<label class="col-md-2 control-label required label-bam-phai">Mã hợp đồng</label>--%>
                <%--<div class="col-md-4">--%>
                    <%--<select name="code" class="form-control truong-selectbox-padding">--%>
                        <%--<option value="">Tất cả</option>--%>
                        <%--<c:forEach items="${page.listContractKind}" var="item">--%>
                            <%--<option value="${item.contract_kind_code}" ${page.code==item.contract_kind_code?"selected":""}>${item.contract_kind_code}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<label class="col-md-2 control-label label-bam-trai">Trạng thái </label>--%>
                <%--<div class="col-md-4 control-label label-bam-trai">--%>
                    <%--<select name="active_flg" class="form-control truong-selectbox-padding">--%>
                        <%--<option value="">Tất cả</option>--%>
                        <%--<option value="1" ${page.active_flg==1?"selected":""}>Đang hoạt động</option>--%>
                        <%--<option value="0" ${page.active_flg==0?"selected":""}>Ngừng hoạt động</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>



                <%--<div class="col-md-12">--%>
                    <%--<div class="truong-button-fixwidth-right">--%>
                        <%--<input type="button" class="form-control huybo" name="" value="Xóa điều kiện" onclick="clearText()">--%>
                    <%--</div>--%>
                    <%--<div class="truong-button-fixwidth-right">--%>

                        <%--<input type="submit" class="form-control luu" name="" value="Tìm kiếm">--%>
                    <%--</div>--%>

                <%--</div>--%>
            <%--</div>--%>


        <%--</div>--%>

        <div class="col-md-12 truong-margin-footer0px">
            <div class="truong-re-bt col-md-12 export-button truong-image-fix">
                <a class="truong-small-linkbt" href="<%=request.getContextPath()%>/template/privy/add"> <input type="button" class="form-control luu truong-image-bt "  name="" value="Thêm mới" ></a>

            </div>
            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua">Mã mẫu</th>
                    <th class=" ann-title border-table table-giua">Tên mẫu</th>
                    <th class=" ann-title border-table table-giua">Trạng thái</th>
                    <th class=" ann-title border-table table-giua">Ngày tạo</th>
                    <th class=" ann-title border-table table-giua">Ngày cập nhập</th>
                    <th class=" ann-title border-table table-giua" >Hành động</th>


                </tr>
                <c:if test="${page.rowCount > 0}">
                    <c:forEach items="${page.items}" var="item">
                        <tr>
                            <td class="border-table truong-rstable-widthper8" style="color: black;"><a href="<%=request.getContextPath()%>/template/privy/edit/${item.id}">${item.id}</a></td>
                            <td class="border-table truong-rstable-widthper8" style="color: black;"><a href="<%=request.getContextPath()%>/template/privy/edit/${item.id}">${item.name}</a></td>
                            <td class="border-table truong-rstable-widthper8" style="color: black;">
                                <c:choose>
                                    <c:when test="${item.disable}">
                                        Ngừng hoạt động
                                    </c:when>
                                    <c:otherwise>
                                        Hoạt động
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="border-table truong-rstable-widthper8" style="color: black;"><fmt:formatDate value="${item.entry_time}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
                            <td class="border-table truong-rstable-widthper8" style="color: black;"><fmt:formatDate value="${item.update_time}" pattern="dd-MM-yyyy HH:mm:ss" /> </td>
                            <td class="border-table truong-rstable-widthper8">
                                <div class="btn-group">
                                    <button class="btn btn-default btn-md dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="<%=request.getContextPath()%>/template/privy/edit/${item.id}"><i class="fa fa-pencil-square-o"></i>Chỉnh sửa</a></li>
                                        <%--<li><a href="<%=request.getContextPath()%>/template/privy/delete/${item.id}"><i class="fa fa-pencil-square-o"></i>Xóa</a></li>--%>
                                        <li><a class="deleteItem"
                                               data-toggle="modal" data-target="#deleteItem"
                                               data-privy.id="${item.id}"><i class="fa fa-pencil-square-o"></i>Xóa</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${page.rowCount}</span> dữ
                            liệu</div>
                            <div class="align-phai">

                            </div>
                        </th>
                    </tr>
                </c:if>
                <c:if test="${page.rowCount == 0}">
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
        <form action="<%=request.getContextPath()%>/template/privy/delete" id="delete-form" method="POST">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Xóa mẫu hiển thị</h4>
                </div>
                <div class="modal-body">
                    <p>Bạn chắc chắn xóa mẫu hiển thị này chứ, điều này có thể ảnh hưởng các đơn vị đã đồng bộ mẫu này? </p>
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

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        loadMenu();
        $(".deleteItem").click(function(){
            var id=$(this).data('privy.id');
            $(".delete_id").val(id);
        });
    });
    function loadMenu() {
        var parentItem = $("#mau-giao-dien");
        $(parentItem).click();
        $("#duongsu").addClass("child-menu");
    }
</script>


