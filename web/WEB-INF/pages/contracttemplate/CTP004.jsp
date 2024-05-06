<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mói thông tin mẫu hợp đồng
--%>
<spring:url value="/contracttemplate/add" var="addUrl" />
<spring:url value="/contracttemplate/list" var="backUrl" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/uchi.css" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới mẫu hợp đồng</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${contractTempAdd.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông tin không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" id="formSubmit" method="post" >

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN MẪU HỢP ĐỒNG
                        </a>
                    </h4>

                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <label class="col-md-2 control-label required label-bam-trai">Tên hợp đồng</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${contractTempAdd.name_!=null ? "error_input":""}" name="name" value="${contractTempAdd.name}">
                            <div class="error_tooltip">${contractTempAdd.name_}</div>
                        </div>

                        <label class="col-md-2 control-label required label-bam-phai">Nhóm hợp đồng</label>
                        <div class="col-md-5">
                            <select name="code" class="form-control truong-selectbox-padding ${contractTempAdd.code_!=null?"error_input":""}">
                                <option value="">--Chọn--</option>
                                <c:forEach items="${contractTempAdd.listContractKind}" var="item">
                                    <option value="${item.contract_kind_code}" ${contractTempAdd.code==item.contract_kind_code?"selected":""}>${item.name}</option>
                                </c:forEach>
                            </select>
                            <div class="error_tooltip">${contractTempAdd.code_}</div>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-md-2 control-label label-bam-trai">Mã mẫu HĐ</label>--%>
                        <%--<div class="col-md-3">--%>
                            <%--<input type="text" name="code_template" id="codeTemplate" class="form-control "  value="${contractTempAdd.code_template}">--%>
                            <%--<small style="font-size:14px;"><a id="showthongbao" class="text-primary"></a><code id="errorCode"></code></small>--%>
                            <%--<div class="error_tooltip"></div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Trạng thái </label>
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
                            <textarea name="description" rows="4" class="form-control" value="${contractTempAdd.description}" ></textarea>
                            <div class="error_tooltip"></div>
                        </div>

                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-2 control-label label-bam-trai">Số bên liên quan</label>--%>
                    <%--<div class="col-md-3">--%>
                    <%--<input type="text" class="form-control " name="relate_object_number" value="${contractTempAdd.relate_object_number}">--%>
                    <%--<div class="error_tooltip"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <div class="col-md-offset-2">
                            <input type="checkbox" class="truong-margin-left15" name="mortage_cancel_func" value="1" ><span class="truong-font-chu" style="vertical-align: 2px;">Giải chấp</span>
                            <input type="hidden" name="mortage_cancel_func" value="0" >

                            <input type="checkbox" class="truong-margin-left15" name="period_flag" value="1"><span class="truong-font-chu" style="vertical-align: 2px;">Thời hạn hợp đồng</span>
                            <input type="hidden" name="period_flag" value="0" >
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-2 control-label label-bam-trai">Mã tổ chức</label>--%>
                    <%--<div class="col-md-3">--%>
                    <%--<input type="text" name="office_code" class="form-control "  value="${contractTempAdd.office_code}">--%>
                    <%--<div class="error_tooltip"></div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">HTML</label>

                        <div class="col-md-10">
                            <div class="btn-toolbar m-b-sm btn-editor" data-role="editor-toolbar"
                                 data-target="#editor">
                                <div class="btn btn-success btn-sm " id="cke_inputcontract" title="Thêm text để gán đối tượng" ><i class="fa fa-plus"></i>Text Item</div>
                                <div class="btn btn-success btn-sm" id="viewsource" title="Thêm text để gán đối tượng" ><i class="fa fa-plus"></i>ViewSource</div>
                                <div class="btn btn-primary btn-sm " id="addTextBoxSimple" title="Thêm text trống không liên kết đối tượng" ><i class="fa fa-plus"></i>Add text</div>
                                <div class="btn btn-success btn-sm " id="addDuongSu" title="Khu vực hiển thị đương sự trên văn bản" ><i class="fa fa-plus"></i>Đương sự</div>
                                <div class="btn btn-success btn-sm " id="addTaiSan" title="Khu vực hiển thị tài sản trên văn bản" ><i class="fa fa-plus"></i>Tài sản</div>
                                <%--<br><br>--%>
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

                                <br><br>
                                <div class="form-group">
                                    <div id="textboxofp"></div>
                                </div>
                                <div id="editor" contenteditable="true" class="form-control" style="font-size:14pt!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;">

                                </div>
                            </div>
                            <%--<textarea hidden="true" name="contentText" id="contentText"></textarea>--%>
                            <div id="sourcecontract" contenteditable="true" style="display:none;font-size:14px!important;width: 742px!important;height:842px;overflow:scroll;font-family: 'Times New Roman';padding:20px 20px!important;"></div>

                        </div>

                        <textarea hidden  class="col-md-12"  id="giatriKindHtml"  name="kind_html"  rows="4" value="${contractTempAdd.kind_html}" ></textarea>

                    </div>

                </div>


            </div>

            <div class="form-group" style="text-align: center;margin:20px 0px;">
                <a class="btn btn-primary" style="min-width:130px;" id="submitform" onclick="return BeforeSubmit();">Lưu</a>
                <a class="btn btn-default"  style="min-width:130px;" href="${backUrl}">Hủy bỏ</a>
            </div>


        </form>
    </div>


</div>
<script src="<%=request.getContextPath()%>/static/js/wysiwyg/jquery.hotkeys.js"></script>
<script src="<%=request.getContextPath()%>/static/js/wysiwyg/bootstrap-wysiwyg.js"></script>
<script src="<%=request.getContextPath()%>/static/js/wysiwyg/demo.js"></script>
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
    $(document).ready(function () {
        loadMenu();

    });
    function loadMenu() {
        $(".sidebar-nav > li > #mau-hop-dong").addClass("father-menu");
    }
    function BeforeSubmit() {
        $('.text-duongsu').html('');
        $('.text-taisan').html('');
        $("#giatriKindHtml").html($("#editor").html());
//        document.getElementById("giatriKindHtml").value = document.getElementById("editor").value;
        $( "#formSubmit" ).submit();
    }
    <%--var url='<%=SystemProperties.getProperty("url_config_server_api_hopdong")%>';--%>
    <%--$('#codeTemplate').change(function() {--%>
        <%--var code=$('#codeTemplate').val();--%>

        <%--$.getJSON(url+"/ContractTemplate/checkByCode?code=" + code,function (data) {--%>
            <%--if(data==true){--%>
                <%--$('#errorCode').text('Lưu ý: Mã HĐ này đã tồn tại trên hệ thống!');--%>
                <%--$('#showthongbao').text('');--%>
            <%--}else{--%>
                <%--$('#errorCode').text('');--%>
                <%--$('#showthongbao').text('Mã HĐ khả dụng!');--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
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
    function pasteHtmlAtCaret(html) {
        var sel, range;
        if (window.getSelection) {
            // IE9 and non-IE
            sel = window.getSelection();
            if (sel.getRangeAt && sel.rangeCount) {
                range = sel.getRangeAt(0);
                range.deleteContents();

                // Range.createContextualFragment() would be useful here but is
                // only relatively recently standardized and is not supported in
                // some browsers (IE9, for one)
                var el = document.createElement("div");
                el.innerHTML = html;
                var frag = document.createDocumentFragment(), node, lastNode;
                while ( (node = el.firstChild) ) {
                    lastNode = frag.appendChild(node);
                }
                var firstNode = frag.firstChild;
                range.insertNode(frag);

                // Preserve the selection
                if (lastNode) {
                    range = range.cloneRange();
                    range.setStartAfter(lastNode);
                    range.collapse(true);
                    sel.removeAllRanges();
                    sel.addRange(range);
                }
            }
        } else if ( (sel = document.selection) && sel.type != "Control") {
            // IE < 9
            var originalRange = sel.createRange();
            originalRange.collapse(true);
            sel.createRange().pasteHTML(html);

        }
    }
    //add div duong su
    $("#addDuongSu").click(function(){
        var sel;
        sel = window.getSelection();
        if(typeof(sel.focusNode.data) !='undefined'){
//            $("#spantext").text(sel.focusNode.data);
            pasteHtmlAtCaret('<div dynamic="duongsu" class="text-duongsu" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-success">Khu vực hiển thị đương sự</a></div>');
        }
        else
            pasteHtmlAtCaret('<div dynamic="duongsu" class="text-duongsu" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-success">>>Khu vực hiển thị đương sự<<</a></div>');
    });
    //add div tai san
    $("#addTaiSan").click(function(){
        pasteHtmlAtCaret('<div dynamic="taisan" class="text-taisan" style="text-indent: 1cm;font-family:Times New Roman;font-size: 14pt;margin-top:6.0pt;margin-right:0cm;margin-bottom:6.0pt; margin-left:0cm;line-height:150%"><a class="btn btn-primary">>>Khu vực hiển thị tài sản<<</a></div>');

    });

    //Add span chỉ để hiển thị mà không liên quan editable
    $("#addTextBoxSimple").click(function(){
        var sel;
        sel = window.getSelection();
        if(typeof(sel.focusNode.data) !='undefined'){
//            $("#spantext").text(sel.focusNode.data);
            pasteHtmlAtCaret("<span class='simple' contenteditable='true'></span> ");
        }
        else
            pasteHtmlAtCaret("<span class='simple' placeholder='' contenteditable='true'></span> ");

    });

    //Add span để mapping với editable
    $("#cke_inputcontract").click(function(){
        var sel;
        sel = window.getSelection();
        if(typeof(sel.focusNode.data) !='undefined'){
//            $("#spantext").text(sel.focusNode.data);
//            pasteHtmlAtCaret("<span class='inputcontract' editable-text='newtextbox' placeholder='"+ sel.focusNode.data +"' contenteditable='true'></span> ");
            pasteHtmlAtCaret("<span class='inputcontract' editable-text='newtextbox' placeholder='.....' contenteditable='true'></span> ");
        }
        else
            pasteHtmlAtCaret("<span class='inputcontract' editable-text='newtextbox' placeholder='.....' contenteditable='true'></span> ");

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
                $("#textboxofp").append("editable-text : <input class='idoftextbox' value='"+$(e.target).attr("editable-text")+"'></input>    Mặc định :<input class='placeholdertext' value='"+$(e.target).attr("placeholder")+"'></input> <a class='deleteiteminput btn btn-danger btn-xs' style='float:none;' href='javascript:void(0)'>Xóa</a>");
                $('.idoftextbox').keyup(function(){

                    $(e.target).attr("editable-text",$(this).val());
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


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />