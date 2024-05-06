<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="layout/header.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/login.css" type="text/css" />
<%--<script src="./static/plugin/jquery.min-1.9.1.js" type="text/javascript"></script>--%>
<%--<script src="./static/plugin/jquery.cookie.js" type="text/javascript"></script>
<script src="./static/js/login.js" type="text/javascript"></script>--%>

<%--<jsp:include page="layout/body_top.jsp" />--%>
<spring:url value="/dangnhap" var="dangnhapUrl" />
<spring:url value="/send-email" var="mailUrl" />

<form name="loginform" method="post" action="${mailUrl }" id="loginform">
  <div id="content" class="col-md-12">
      <div class="col-md-1"></div>
    <div id="form" class="col-md-10">
      <div id="focus">
          <div class="row">
              <div class="col-md-4"></div>
              <div id="uchi-logo" class="col-md-4">
                  <img id="img-uchi-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon.png"  alt="Uchi Logo"/>
                  <p id="uchi-slogan">Ngôi nhà chung của các tổ chức công chứng</p>
              </div>
              <div class="col-md-4"></div>
          </div>
        <ul>
          <li class="col-md-5" id="uchi-info">
              <div class="row" >
                  <div class="col-md-9"></div>
                  <div class="col-md-3">
                    <img id="img-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon2.png"  alt="Uchi Logo"/>
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
                <div id="login-system">Quên mật khẩu</div>
            </div>
              <div class="row">
                  <input type="text" name="account" id="username" placeholder="Tài khoản..."/>
                  <div class="error_tooltip">${forgetPasswordForm.account_}</div>
              </div>
              <div class="row">
                  <input type="email" name="email" id="password" placeholder="Email..."/>
                  <div class="error_tooltip">${forgetPasswordForm.email_}</div>
              </div>
              <div class="row" id="login-consol">
                  <button type="submit" class="col-md-5" id="login" >ĐỒNG Ý</button>
                  <div class="col-md-1"></div>
                  <div class="col-md-6" id="forget-pass"><a href="${dangnhapUrl}"><img id="forget-img" src="<%=request.getContextPath()%>/static/image/login/dangnhap.png">Đăng nhập</a></div>
              </div>
          </li>
        </ul>

      </div>
    </div>
      <div class="col-md-1"></div>
      <div class="row" id="footer-info">
          <div class="col-md-3">Uchi Sở Tư Pháp v3.0 <img src="<%=request.getContextPath()%>/static/image/login/uchi-icon-mini.png"></div>
          <div class="col-md-5"></div>
          <div class="col-md-4" id="company">Công ty cổ phần công nghệ phần mềm và nội dung số OSP</div>
      </div>
  </div>

</form>
<%--
<jsp:include page="layout/footer.jsp" />--%>
