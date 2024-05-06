<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header1.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Chỉnh sửa thông tin hợp đồng
--%>
<spring:url value="/contracttemplate/update" var="updateUrl" />
<spring:url value="/contracttemplate/delete" var="removeUrl" />
<spring:url value="/contracttemplate/list" var="backUrl" />
<spring:url value="/contracttemplate/loadContractTemplate" var="loadContractTemplate"/>

<script src="<%=request.getContextPath()%>/static/angularjs/angular.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/angularjs/angular-sanitize.min.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/static/js/exportWord/FileSaver.js"></script>
<script src="<%=request.getContextPath()%>/static/js/exportWord/jquery.wordexport.js"></script>

<script src="<%=request.getContextPath()%>/static/js/exportWord/template.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/js/print/jQuery.print.js"></script>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa thông tin hợp đồng</span>
</div>


<div class="truong-form-chinhbtt" ng-app="osp" ng-controller="templateDetailController">


    <c:if test="${contractTemp.success== false}">
        <div class="padding-default" id="uchi-status">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin hợp đồng không thành công </div>
        </div>
    </c:if>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="POST" id="formSubmit">
            <input type="hidden" name="sid" value="${contractTemp.sid}">


            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN HỢP ĐỒNG
                        </a>
                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <label class="col-md-2 control-label required label-bam-trai">Nhóm hợp đồng</label>
                        <div class="col-md-3">
                            <select name="code" id="nhomHD" class="form-control truong-selectbox-padding" onchange="loadContractTemplate()">
                                <option value="">--Chọn--</option>
                                <c:forEach items="${contractTemp.listContractKind}" var="item">
                                    <option value="${item.contract_kind_code}" ${contractTemp.code==item.contract_kind_code?"selected":""}>${item.name}</option>
                                </c:forEach>
                            </select>
                            <div class="error_tooltip">${contractTemp.code_}</div>
                        </div>

                        <label class="col-md-2 control-label label-bam-phai">Tên hợp đồng</label>
                        <div class="col-md-5">
                            <select name="kind_id" id="tenHD" class="form-control truong-selectbox-padding">
                                <option value="0">--Chọn--</option>
                                <c:forEach items="${contractTemp.contractTemps}" var="item">
                                    <option value="${item.code_template}" ${contractTemp.kind_id==item.code_template?"selected":""}>${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label required label-bam-trai">Tên mẫu hợp đồng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${contractTemp.name_!=null ? "error_input":""}" name="name" value="${contractTemp.name}">
                            <div class="error_tooltip">${contractTemp.name_}</div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Hiển thị bên A</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="relate_object_A_display" value="${contractTemp.relate_object_A_display}">
                        </div>

                        <label class="col-md-2 control-label label-bam-phai">Hiển thị bên B</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="relate_object_B_display" value="${contractTemp.relate_object_B_display}">
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-2 control-label label-bam-trai">Mã mẫu HĐ</label>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<input type="text" name="code_template" class="form-control "  value="${contractTemp.code_template}" disabled>--%>
                            <%--<div class="error_tooltip"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Hiển thị bên C</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="relate_object_C_display" value="${contractTemp.relate_object_C_display}">
                        </div>

                        <label class="col-md-2 control-label label-bam-phai">Trạng thái </label>
                        <div class="col-md-5 control-label label-bam-trai">
                            <label class="radio-inline prevent-type">
                                <input type="radio" name="active_flg" value="1" ${contractTemp.active_flg == 1 ?"checked":""}>Đang hoạt động
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="active_flg" value="0" ${contractTemp.active_flg == 0 ?"checked":""}>Ngừng hoạt động
                            </label>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Mô tả</label>

                        <div class="col-md-10">
                            <textarea name="description" rows="4" class="form-control" value="${contractTemp.description}" >${contractTemp.description}</textarea>
                            <div class="error_tooltip"></div>
                        </div>

                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-2 control-label label-bam-trai">Số bên liên quan</label>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<input type="text" class="form-control " name="relate_object_number" value="${contractTemp.relate_object_number}">--%>
                            <%--<div class="error_tooltip"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <div class="col-md-offset-2">

                            <c:choose>
                                <c:when test="${contractTemp.mortage_cancel_func== 1}">
                                    <input type="checkbox" class="truong-margin-left15" name="mortage_cancel_func" value="1" checked="checked"\><span class="truong-font-chu" style="vertical-align: 2px;">Giải chấp</span>
                                    <input type="hidden" name="mortage_cancel_func" value="0" >
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" class="truong-margin-left15" name="mortage_cancel_func" value="1" ><span class="truong-font-chu" style="vertical-align: 2px;">Giải chấp</span>
                                    <input type="hidden" name="mortage_cancel_func" value="0" >
                                </c:otherwise>
                            </c:choose>

                                <c:choose>
                                    <c:when test="${contractTemp.period_flag== 1}">
                                        <input type="checkbox" class="truong-margin-left15" name="period_flag" value="1" checked="checked"><span class="truong-font-chu" style="vertical-align: 2px;">Thời hạn hợp đồng</span>
                                        <input type="hidden" name="period_flag" value="0" >
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" class="truong-margin-left15" name="period_flag" value="1" ><span class="truong-font-chu" style="vertical-align: 2px;">Thời hạn hợp đồng</span>
                                        <input type="hidden" name="period_flag" value="0" >
                                    </c:otherwise>
                                </c:choose>

                            <%--<input type="checkbox" class="truong-margin-left15" name="period_flag" value="1"><span class="truong-font-chu" style="vertical-align: 2px;">Thời hạn hợp đồng</span>--%>
                            <%--<input type="hidden" name="period_flag" value="0" >--%>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-2 control-label label-bam-trai">Mã tổ chức</label>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<input type="text" name="office_code" class="form-control "  value="${contractTemp.office_code}">--%>
                            <%--<div class="error_tooltip"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">HTML</label>

                        <div class="col-md-10">
                            <div class="btn-toolbar m-b-sm btn-editor" data-role="editor-toolbar"
                                 data-target="#editor">
                                <%--<div class="btn btn-success btn-sm " id="cke_inputcontract" title="Thêm text để gán đối tượng" ><i class="fa fa-plus"></i>Text Item</div>--%>
                                <%--<div class="btn btn-success btn-sm" id="viewsource" title="Xem source code dạng html" ><i class="fa fa-plus"></i>ViewSource</div>--%>
                                <%--<div class="btn btn-primary btn-sm " id="addTextBoxSimple" title="Thêm text trống không liên kết đối tượng" ><i class="fa fa-plus"></i>Add text</div>--%>
                                <%--<div class="btn btn-success btn-sm " id="addDuongSu" title="Khu vực hiển thị đương sự trên văn bản" ><i class="fa fa-plus"></i>Đương sự</div>--%>
                                <%--<div class="btn btn-success btn-sm " id="addTaiSan" title="Khu vực hiển thị tài sản trên văn bản" ><i class="fa fa-plus"></i>Tài sản</div>--%>
                                <button type='button' id="cke_inputcontract"   class="btn btn-success btn-sm " title="Thêm text để gán đối tượng" ><i class="fa fa-plus"></i>Text Item</button>
                                <button type='button' id="viewsource"   class="btn btn-success btn-sm" title="Xem code dạng HTML" ><i class="fa fa-plus"></i>ViewSource</button>
                                <button type='button' id="addTextBoxSimple"  class="btn btn-primary btn-sm " title="Thêm text trống không liên kết đối tượng" ><i class="fa fa-plus"></i>Add text</button>
                                <button type='button' id="addDuongSu"  class="btn btn-success btn-sm " title="Khu vực hiển thị đương sự trên văn bản" ><i class="fa fa-plus"></i>Đương sự</button>
                                <button type='button' id="addDuongSuBenA"  class="btn btn-success btn-sm " title="Khu vực hiển thị đương sự bên A trên văn bản" ><i class="fa fa-plus">Đương sự bên A</i></button>
                                <button type='button' id="addDuongSuBenB"  class="btn btn-success btn-sm " title="Khu vực hiển thị đương sự bên B trên văn bản" ><i class="fa fa-plus">Đương sự bên B</i></button>
                                <button type='button' id="addTaiSan"  class="btn btn-success btn-sm " title="Khu vực hiển thị tài sản trên văn bản" ><i class="fa fa-plus"></i>Tài sản</button>
                                <br><br>
                                    <div class="btn-group">
                                        <a class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a data-edit="fontSize 5"><font size="5">18</font></a></li>
                                            <li><a data-edit="fontSize 4"><font size="4">14</font></a></li>
                                            <li><a data-edit="fontSize 3"><font size="3">12</font></a></li>
                                        </ul>
                                    </div>
                                <div class="btn-group"><a class="btn btn-default btn-sm" data-edit="bold" title="Bold (Ctrl/Cmd+B)">
                                    <i class="fa fa-bold"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="strikethrough" title="Strikethrough"> <i class="fa fa-strikethrough"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="underline" title="Underline (Ctrl/Cmd+U)"> <i class="fa fa-underline"></i></a>
                                </div>
                                <div class="btn-group">
                                    <a class="btn btn-default btn-sm"  data-edit="insertunorderedlist" title="Bullet list">
                                        <i class="fa fa-list-ul"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="insertorderedlist" title="Number list">
                                        <i class="fa fa-list-ol"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="outdent" title="Reduce indent (Shift+Tab)">
                                        <i class="fa fa-dedent"></i></a>
                                    <a class="btn btn-default btn-sm" data-edit="indent" title="Indent (Tab)">
                                        <i class="fa fa-indent"></i></a>
                                </div>
                                <div class="btn-group">
                                    <a class="btn btn-default btn-sm" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)">
                                        <i class="fa fa-align-left"></i>
                                    </a>
                                    <a class="btn btn-default btn-sm" data-edit="justifycenter"
                                       title="Center (Ctrl/Cmd+E)"><i
                                            class="fa fa-align-center"></i>
                                    </a>
                                    <a class="btn btn-default btn-sm" data-edit="justifyright"
                                       title="Align Right (Ctrl/Cmd+R)"><i
                                            class="fa fa-align-right"></i>
                                    </a>
                                    <a class="btn btn-default btn-sm" data-edit="justifyfull"
                                       title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i>
                                    </a>
                                </div>

                                <div class="btn-group"><a class="btn btn-default btn-sm"
                                                          data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
                                        class="fa fa-undo"></i></a> <a class="btn btn-default btn-sm"
                                                                       data-edit="redo"
                                                                       title="Redo (Ctrl/Cmd+Y)"><i
                                        class="fa fa-repeat"></i></a></div>
                                <div class="btn-group ">
                                    <a ng-click="viewAsDoc()" style="margin:0px 0px;" class="btn btn-sm btn-info">Xem online</a>
                                    <a ng-click="downloadWord()"style="margin:0px 2px;" class="btn btn-sm btn-info">Xuất file word</a>
                                </div>
                                <%--<div class="btn btn-primary btn-sm " id="addTextBoxSimple" title="Thêm text trống không liên kết đối tượng" ><i class="fa fa-plus"></i>Add text</div>--%>
                                <%--<div class="btn btn-success btn-sm " id="addDuongSu" title="Khu vực hiển thị đương sự trên văn bản" ><i class="fa fa-plus"></i>Đương sự</div>--%>
                                <%--<div class="btn btn-success btn-sm " id="addTaiSan" title="Khu vực hiển thị tài sản trên văn bản" ><i class="fa fa-plus"></i>Tài sản</div>--%>
                                <br><br>
                                <div class="form-group">
                                    <div id="textboxofp"></div>
                                </div>

                                <!-- <div id="editor" class="form-control" style="font-size:14px;width: 595px;height:842px;overflow:scroll;line-height: 2.5px;font-family: 'Times New Roman';"> -->
                                <div id="editor" contenteditable="true" class="form-control" style="font-size:14pt!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;">

                                </div>
                            </div>
                            <%--<textarea hidden="true" name="contentText" id="contentText"></textarea>--%>
                            <div id="sourcecontract" contenteditable="true" style="display:none;font-size:14px!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;"></div>

                        </div>

                        <textarea style="display:none;"  class="col-md-12"  id="giatriKindHtml"  name="kind_html"  rows="4" value="${contractTemp.kind_html}" ></textarea>
                    </div>

                    </div>

                </div>

            <div class="form-group" style="text-align: center;margin:20px 0px;">
                <a class="btn btn-primary" style="min-width:130px;" id="submitform" onclick="return BeforeSubmit();">Lưu</a>
                <a class="btn btn-danger" style="min-width:130px;" data-toggle="modal" data-target="#deleteContractTemp">Xóa</a>
                <a class="btn btn-default"  style="min-width:130px;" href="${backUrl}">Hủy bỏ</a>
            </div>
            <%--<div class="truong-prevent-btb">--%>
                <%--<div class="truong-can-button3">--%>
                    <%--<div class="col-md-2 col-md-offset-3 truong-rs-bt3os">--%>

                        <%--<input type="button" id="submitform" class="form-control luu" name="" value="Lưu" onclick="return BeforeSubmit();">--%>
                    <%--</div>--%>
                    <%--<div class="col-md-2 truong-rs-bt3">--%>
                        <%--<input type="button" class="form-control luu" data-toggle="modal" data-target="#xoa"--%>
                               <%--name="" value="Xóa">--%>
                    <%--</div>--%>

                    <%--<div class="col-md-2 truong-rs-bt3">--%>
                        <%--<a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>


        </form>
    </div>


</div>


<div class="modal fade" id="viewContentAsWord" role="dialog" style="width:auto;">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" style="margin:auto!important;align-content:center;width:810px;background: #fff;height:100%;min-height:500px;overflow: auto; float:left;font-size: 14pt;line-height:1.5;font-family: times new roman;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modal-body">
                <div style="float: right">
                    <div class="advanPrint nut-in"><img src="<%=request.getContextPath()%>/static/image/btn-print.gif"
                                                        alt="Print"></div>
                </div>

                <div id="viewHtmlAsWord" style="width: 595px;margin-left: 2.5cm;margin-right: 1.5cm;" >

                </div>
            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteContractTemp" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <input type="hidden" name="sid" value="${contractTemp.sid}">
            <div class="panel panel-default">

                <div class="panel-heading" style="background-color: #2e9ad0 ">
                    <h5 class="panel-title truong-text-colorwhite" style="color:#ffffff;">
                        Xóa tên hợp đồng

                        <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px"><img
                                src="<%=request.getContextPath()%>/static/image/close.png" class="control-label truong-imagexoa-fix"></button>


                    </h5>

                </div>
                <div class="panel-body">
                    <div class="truong-modal-padding" style="padding-bottom: 7%;">
                        <label class="col-md-12 control-label align-giua">Bạn có thực sự muốn xóa dữ liệu ?</label>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-bottom: 2%;">
                <div class="col-md-3 col-md-offset-3">
                    <a href="${removeUrl}/${contractTemp.sid}" class="truong-small-linkbt"><input type="button" class="form-control luu" name="" value="Xóa"></a>
                </div>
                <div class="col-md-3 ">
                    <input type="button" class="form-control huybo" data-dismiss="modal" name="" value="Hủy bỏ">
                </div>

            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/static/js/wysiwyg/jquery.hotkeys.js"></script>
<script src="<%=request.getContextPath()%>/static/js/wysiwyg/bootstrap-wysiwyg.js"></script>
<%--<script src="<%=request.getContextPath()%>/static/js/wysiwyg/demo.js"></script>--%>
<%--End Modal--%>
<script>
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1300){
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        }else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

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
</script>



<script>
    $(document).ready(function () {
        loadMenu();
        $("#editor").html('${contractTemp.kind_html}');
        $('.text-duongsu').html('<a class="btn btn-success">>>Khu vực hiển thị đương sự<<</a>');
        $('.text-duongsu-ben-a').html('<a class="btn btn-success">>>Khu vực hiển thị đương sự bên a<<</a>');
        $('.text-duongsu-ben-b').html('<a class="btn btn-success">>>Khu vực hiển thị đương sự bên b<<</a>');
        $('.text-taisan').html('<a class="btn btn-primary">>>Khu vực hiển thị tài sản<<</a>');
    });
    function loadMenu() {
        $(".sidebar-nav > li > #mau-hop-dong").addClass("father-menu");
    }
    function BeforeSubmit() {
        $('.text-duongsu').html('');//gan gia tri ve rong~
        $('.text-duongsu-ben-a').html('');
        $('.text-duongsu-ben-b').html('');
        $('.text-taisan').html('');
        $("#giatriKindHtml").html($("#editor").html());
        $( "#formSubmit" ).submit();
    }
</script>


<script type="text/javascript">
    $("#viewsource").click(function(){
        var clss=$("#viewsource").attr("class");
        if(clss=='btn btn-success btn-sm')
        {
            $("#editor").css("display","none");
            $("#sourcecontract").css("display","block");
            $("#sourcecontract").text($("#editor").html());
            $(this).addClass("active");
            $("#action_area").css("display","none");
        }
        if(clss=='btn btn-success btn-sm active')
        {
            $("#editor").css("display","block");
            $("#sourcecontract").css("display","none");
            $(this).removeClass("active");
            $("#action_area").css("display","none");
            $("#editor").html( $("#sourcecontract").text());
            $("#action_area").css("display","block");
        }
    });
//    function pasteHtmlAtCaret(html) {
//        var sel, range;
//        if (window.getSelection) {
//            // IE9 and non-IE
//            sel = window.getSelection();
//            if (sel.getRangeAt && sel.rangeCount) {
//                range = sel.getRangeAt(0);
//                range.deleteContents();
//
//                // Range.createContextualFragment() would be useful here but is
//                // only relatively recently standardized and is not supported in
//                // some browsers (IE9, for one)
//                var el = document.createElement("div");
//                el.innerHTML = html;
//                var frag = document.createDocumentFragment(), node, lastNode;
//                while ( (node = el.firstChild) ) {
//                    lastNode = frag.appendChild(node);
//                }
//                var firstNode = frag.firstChild;
//                range.insertNode(frag);
//
//                // Preserve the selection
//                if (lastNode) {
//                    range = range.cloneRange();
//                    range.setStartAfter(lastNode);
//                    range.collapse(true);
//                    sel.removeAllRanges();
//                    sel.addRange(range);
//                }
//            }
//        } else if ( (sel = document.selection) && sel.type != "Control") {
//            // IE < 9
//            var originalRange = sel.createRange();
//            originalRange.collapse(true);
//            sel.createRange().pasteHTML(html);
//
//        }
//    }

    function setEndOfContenteditable(contentEditableElement) {
        var range, selection;
        if (document.createRange) //Firefox, Chrome, Opera, Safari, IE 9+
        {
            range = document.createRange(); //Create a range (a range is a like the selection but invisible)
            range.selectNodeContents(contentEditableElement); //Select the entire contents of the element with the range
            range.collapse(false); //collapse the range to the end point. false means collapse to end rather than the start
            selection = window.getSelection(); //get the selection object (allows you to change selection)
            selection.removeAllRanges(); //remove any selections already made
            selection.addRange(range); //make the range you have just created the visible selection
        } else if (document.selection) //IE 8 and lower
        {
            range = document.body.createTextRange(); //Create a range (a range is a like the selection but invisible)
            range.moveToElementText(contentEditableElement); //Select the entire contents of the element with the range
            range.collapse(false); //collapse the range to the end point. false means collapse to end rather than the start
            range.select(); //Select the range (make it the visible selection
        }
    }

    function elementContainsSelection(el) {
        var sel;
        if (window.getSelection) {
            sel = window.getSelection();
            if (sel.rangeCount > 0) {
                for (var i = 0; i < sel.rangeCount; ++i) {
                    if (!isOrContains(sel.getRangeAt(i).commonAncestorContainer, el)) {
                        return false;
                    }
                }
                return true;
            }
        } else if ((sel = document.selection) && sel.type != "Control") {
            return isOrContains(sel.createRange().parentElement(), el);
        }
        return false;
    }

    function isOrContains(node, container) {
        while (node) {
            if (node === container) {
                return true;
            }
            node = node.parentNode;
        }
        return false;
    }

    function pasteHtmlAtCaret(html, el) {
        var sel, range;
        if (window.getSelection) {
            // IE9 and non-IE
            sel = window.getSelection();
            if (elementContainsSelection(el)) {
                if (sel.getRangeAt && sel.rangeCount) {
                    range = sel.getRangeAt(0);
                    range.deleteContents();

                    // Range.createContextualFragment() would be useful here but is
                    // non-standard and not supported in all browsers (IE9, for one)
                    var el = document.createElement("div");
                    el.innerHTML = html;
                    var frag = document.createDocumentFragment(),
                        node, lastNode;
                    while ((node = el.firstChild)) {
                        lastNode = frag.appendChild(node);
                    }
                    range.insertNode(frag);

                    // Preserve the selection
                    if (lastNode) {
                        range = range.cloneRange();
                        range.setStartAfter(lastNode);
                        range.collapse(true);
                        sel.removeAllRanges();
                        sel.addRange(range);
                    }
                } else if (document.selection && document.selection.type != "Control") {
                    // IE < 9
                    document.selection.createRange().pasteHTML(html);
                }
            } else {
                setEndOfContenteditable(el);
                pasteHtmlAtCaret(html, el);
            }
        }

    }
    var el = document.getElementById("editor");


    //add div duong su
    $("#addDuongSu").click(function(){
        pasteHtmlAtCaret('<div dynamic="duongsu" class="text-duongsu" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-success">>>Khu vực hiển thị đương sự<<</a></div>',el);
    });
    //add div duong su bên a
    $("#addDuongSuBenA").click(function(){
        pasteHtmlAtCaret('<div dynamic="duongsubena" class="text-duongsu-ben-a" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-success">>>Khu vực hiển thị đương sự bên a<<</a></div>',el);
    });
    //add div duong su bên b
    $("#addDuongSuBenB").click(function(){
        pasteHtmlAtCaret('<div dynamic="duongsubenb" class="text-duongsu-ben-b" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-success">>>Khu vực hiển thị đương sự bên b<<</a></div>',el);
    });
    //add div tai san
    $("#addTaiSan").click(function(){
        pasteHtmlAtCaret('<div dynamic="taisan" class="text-taisan" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-primary">>>Khu vực hiển thị tài sản<<</a></div>',el);
    });


    //Add span chỉ để hiển thị mà không liên quan editable
    $("#addTextBoxSimple").click(function(){
        var sel;

        sel = window.getSelection();
        if(typeof(sel.focusNode.data) !='undefined'){
//            $("#spantext").text(sel.focusNode.data);
            pasteHtmlAtCaret('<span class="simple" contenteditable="true"></span> ',el);
        }
        else
            pasteHtmlAtCaret('<span class="simple" contenteditable="true"></span> ',el);

    });

    //Add span để mapping với editable
    $("#cke_inputcontract").click(function(){
        var sel;

        sel = window.getSelection();
        if(typeof(sel.focusNode.data) !='undefined'){
//            $("#spantext").text(sel.focusNode.data);
//            pasteHtmlAtCaret("<span class='inputcontract' editable-text='newtextbox' placeholder='"+ sel.focusNode.data +"' contenteditable='true'></span> ");
            pasteHtmlAtCaret('<span class="inputcontract" editspan="newtextbox" ng-model="" placeholder="..." contenteditable="true"></span>',el);
        }
        else
            pasteHtmlAtCaret('<span class="inputcontract" editspan="newtextbox" ng-model="" placeholder="..." contenteditable="true"></span>',el);
    });



    $("#editor").click(function(e){

        //Nếu thẻ là SPAN
        if(e.target.nodeName=="SPAN"){
            if(e.target.className=="inputcontract" || e.target.className=="inputcontract textforcus")
            {
                $("#editor *").removeClass("spanactive");
                $("#editor *").removeClass("textforcus");
                $(e.target).addClass("textforcus");
                $("#editor").removeAttr("contenteditable");
                $("#textboxofp").html("");
                $("#textboxofp").css("display","block");
                $("#textboxofp").append("editspan : <input class='idoftextbox' value='"+$(e.target).attr("editspan")+"'></input>    Mặc định :<input class='placeholdertext' value='"+$(e.target).attr("placeholder")+"'></input> <a class='deleteiteminput btn btn-danger btn-xs' style='float:none;' href='javascript:void(0)'>Xóa</a>");
                $('.idoftextbox').keyup(function(){

                    $(e.target).attr("editspan",$(this).val());
                    $(e.target).attr("ng-model",$(this).val());
                });
                $('.placeholdertext').keyup(function(){
                    $(e.target).attr("placeholder",$(this).val());

                });
                $('.deleteiteminput').click(function(){
                    var r = confirm("Bạn có chắc chắn muốn xóa!");
                    if (r == true) {
                        $(e.target).remove();
                        $("#textboxofp").html("");
                    } else {
                        return false;
                    }

                });


            }
            //for span simple
            if(e.target.className=="simple" || e.target.className=="simple textforcus")
            {
                $("#editor *").removeClass("spanactive");
                $("#editor *").removeClass("textforcus");
                $(e.target).addClass("textforcus");
                $("#editor").removeAttr("contenteditable");
                $("#textboxofp").html("");

            }
        }
        else
        {
            $("#editor *").removeClass("spanactive");
            $(e.target).addClass("spanactive");
            $(this).attr("contenteditable","true");
            $("#editor *").removeClass("textforcus");

        }

    });
    function removeStuff(elem) {
        elem.children().each(function() {
            if($(this)[0].nodeName=="SPAN")
            {
                $(this).replaceWith( $(this)[0].textContent);
            }

            if ($(this).children().length > 0) {
                $(this).removeAttr('class');
                $(this).removeAttr('lang');
                $(this).css({'font-family':'Times New Roman','font-size':'14pt','text-indent':'','margin-left':'','text-align':'', 'margin': '0pt 0pt'});
                removeStuff($(this));
            } else {
                $(this).removeAttr('class');
                $(this).css({'font-family':'Times New Roman','font-size':'14pt','text-indent':'','margin-left':'','text-align':'', 'margin': '0pt 0pt'});
                $(this).removeAttr('lang');
            }
        });
    }



    //for wysiwyg
    $('#editor').wysiwyg();
    //$('#editor').html('<b>In ddam len</b> Ban nghi sao khi no');



</script>

<script>

    $(document).ready(function () {
        var parentItem = $("#quan-ly-hop-dong");
        $(parentItem).click();
        $("#ds-hd-online").addClass("child-menu");
    });
    $(function () {
        $(".nut-in").on('click', function () {

            $("#viewHtmlAsWord").print({

// Use Global styles
                globalStyles: false,

// Add link with attrbute media=print
                mediaPrint: false,

//Custom stylesheet
                stylesheet: "<%=request.getContextPath()%>/static/css/contract/print/print.css",

//Print in a hidden iframe
                iframe: false,

// Don't print this
                noPrintSelector: ".avoid-this",

// Add this on top
                append: "",

// Add this at bottom
                prepend: "",

// Manually add form values
                manuallyCopyFormValues: true,

// resolves after print and restructure the code for better maintainability
                deferred: $.Deferred(),

// timeout
                timeout: 250,

// Custom title
                title: null,

// Custom document type
                doctype: '<!doctype html>'

            });
        });
    });

</script>

<script>
    function loadContractTemplate() {
        var loadContractTemplate=  document.getElementById("nhomHD").value;
        $.ajax({
            type: "GET",
            url: '${loadContractTemplate}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                contractKind: loadContractTemplate,
            },
            success: function (response) {
                var stringVpcc = response.result;
                var htmlContent1 = '<select name="ct_template_code" class="form-control truong-selectbox-padding"><option value="">Chọn</option>';
                var listVpcc = stringVpcc.split(";");
                for( var i =0;i<listVpcc.length-1;i++){
                    var nameVPCC = listVpcc[i];
                    var arr = nameVPCC.split("o0o");
                    var idVPCC = arr[0];
                    var tenVPCC = arr[1];

                    htmlContent1 += '<option value ="'+idVPCC+'">'+tenVPCC+'</option>';
                }
                htmlContent1+='</select>';
                $('#tenHD').html(htmlContent1);


            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })

    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />