<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="layout/header.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/login.css" type="text/css"/>
<%--<script src="./static/plugin/jquery.min-1.9.1.js" type="text/javascript"></script>--%>
<%--<script src="./static/plugin/jquery.cookie.js" type="text/javascript"></script>
<script src="./static/js/login.js" type="text/javascript"></script>--%>

<%--<jsp:include page="layout/body_top.jsp" />--%>
<spring:url value="/forgot-password" var="quenmatkhauUrl"/>
<spring:url value="/preventmanager" var="preventManagerUrl"/>
<spring:url value="/login" var="loginUrl"/>

<form name="loginform" method="post" action="<%=request.getContextPath()%>/login" id="loginform">
    <div id="content" class="col-md-12">
        <div class="col-md-1"></div>
        <div id="form" class="col-md-10">
            <div id="focus">
                <div class="row">
                    <div class="col-md-4"></div>
                    <div id="uchi-logo" class="col-md-4">
                        <img id="img-uchi-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon.png" alt="Uchi Logo"/>
                        <p id="uchi-slogan">Ngôi nhà chung của các tổ chức công chứng</p>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <ul>
                    <li class="col-md-5" id="uchi-info">
                        <div class="row">
                            <div class="col-md-9"></div>
                            <div class="col-md-3">
                                <img id="img-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon2.png" alt="Uchi Logo"/>
                            </div>
                        </div>
                        <div class="row" class="uchi-info">
                            <div class="col-md-6"></div>
                            <div class="col-md-6" id="about-us">
                                <p>Hotline: 0435 682 502 - 0986 083 003</p>
                                <p>2016 Bản quyền thuộc về osp.com.vn</p>
                            </div>
                        </div>
                        <div class="row"></div>
                    </li>
                    <li class="col-md-3" id="login-form">
                            <div class="row">
                                <div id="login-system">Đăng nhập hệ thống</div>
                            </div>
                            <div class="row">
                                <input type="text" name="username" id="username" placeholder="Tài khoản..."/>
                                <div class="error_tooltip">${loginForm.username_}</div>
                            </div>
                            <div class="row">
                                <input type="password" name="password" id="password" placeholder="Mật khẩu..."/>
                                <div class="error_tooltip">${loginForm.password_}</div>
                            </div>
                            <div class="row" id="login-consol">
                                <button type="submit" class="col-md-5" id="login">ĐĂNG NHẬP</button>
                                <div class="col-md-1"></div>
                                <div class="col-md-6" id="forget-pass"><a href="<%=request.getContextPath()%>/forgot-password"><img id="forget-img"
                                                                                                        src="<%=request.getContextPath()%>/static/image/login/quenmatkhau.png">Quên
                                    mật khẩu ?</a></div>
                            </div>
                    </li>
                </ul>

            </div>
        </div>
        <div class="col-md-1"></div>
        <div class="row" id="footer-info">
            <div class="col-md-3">Uchi v3.0 <img src="<%=request.getContextPath()%>/static/image/login/uchi-icon-mini.png"></div>
            <div class="col-md-5"></div>
            <div class="col-md-4" id="company">Công ty cổ phần công nghệ phần mềm và nội dung số OSP</div>
        </div>
    </div>

</form>
<%--
<jsp:include page="layout/footer.jsp" />--%>
