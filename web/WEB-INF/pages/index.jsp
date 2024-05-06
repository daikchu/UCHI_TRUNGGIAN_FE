<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/index.css">
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Trang chu
--%>
<spring:url value="/system/create-grouprole" var="addUrl"/>
<spring:url value="/announcement/update-view" var="announcementUrl"/>
<spring:url value="/announcement/list" var="announcementListUrl"/>
<spring:url value="/contract/search" var="searchUrl"/>
<spring:url value="/prevent/add-view" var="addUrl"/>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Trang chủ</span>
</div>
<div class="col-md-12" id="home-wapper">

</div>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>

<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #trang-chu").addClass("father-menu");
    }
</script>