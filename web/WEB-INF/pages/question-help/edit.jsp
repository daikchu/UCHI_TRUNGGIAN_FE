<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thêm mói thông tin câu hỏi trợ giúp
--%>
<spring:url value="/question-help/update" var="addUrl" />
<spring:url value="/question-help/list" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa thông tin câu hỏi trợ giúp</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${contractFee.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Cập nhật thông tin câu hỏi trợ giúp không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <%--<input type="hidden" name="contractKindList" value="${contractFee.contractKindList}">
            <input type="hidden" name="contractTempList" value="${contractFee.contractTempList}">--%>
                <input type="hidden" name="id" value="${item.id}">
                <input type="hidden" name="entry_user_id" value="${item.entry_user_id}">
                <input type="hidden" name="entry_date_time" value="${item.entry_date_time}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN CÂU HỎI TRỢ GIÚP
                        </a>
                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">

                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai required">Câu hỏi dành cho</label>
                            <div class="col-md-9 control-label label-bam-trai">
                                <input class="truong-check-fix" type="radio" name="type" value="${Constants.type_QASTP}" ${item.type==Constants.type_QASTP?"checked":""}> Sở Tư Pháp
                                <input class="truong-check-fix" type="radio" name="type" value="${Constants.type_QATCCC}" ${item.type==Constants.type_QATCCC?"checked":""}> Tổ chức công chứng
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai required">Tiêu đề</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control ${item.question_!= null?"error_input":""}" name="question" value="${item.question}">
                                <div class="error_tooltip">${item.question_}</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai required">Câu trả lời</label>
                            <div class="col-md-9">
                                <textarea name="answer" value="${item.answer}" rows="4" class="form-control">${item.answer}</textarea>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-2 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <div class="col-md-2 truong-rs-bt2">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>

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
    $("#formula_fee").keypress(function (key) {
        if ((key.charCode < 45 || key.charCode > 57) && key.charCode != 97 && key.charCode != 126 && (key.charCode < 40 || key.charCode > 43)) return false;
    });
    $("#formula_fee").bind("cut copy paste", function (e) {
        e.preventDefault();

    });


</script>

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
