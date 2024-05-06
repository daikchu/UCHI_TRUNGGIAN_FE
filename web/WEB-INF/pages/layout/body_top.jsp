<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
	<%@ page import="com.vn.osp.common.util.SystemProperties" %>
	<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/logout" var="logoutUrl"/>
<spring:url value="/home" var="homeUrl"/>

	<script type="text/javascript">
		var ospApiUrl = '<%=SystemProperties.getProperty("url_config_server_api_public")%>';

		function toggleNotificationList() {
			var notificationList = document.getElementById("notification-list");
			notificationList.style.display = notificationList.style.display === "none" ? "block" : "none";
			getNotifications();
		}
		document.addEventListener('click', function(event) {
			var notificationIcon = document.getElementById('notification-icon');
			var notificationList = document.getElementById('notification-list');
			var targetElement = event.target;
			if (!notificationIcon.contains(targetElement) && !notificationList.contains(targetElement)) {
			notificationList.style.display = 'none';
			}
		});

		var notification = {
			total: 0,
			totalNoSeen: 0,
			page: 1,
			items: []
		}
		var totalNotificationNoSeen = 0;

		getNotificationTotal();


	</script>

	<header>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid" style="background-color: #2ca9e0">
			<div class="navbar-header" style="display:flex;align-items: center">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="${homeUrl}"><img src="<%=request.getContextPath()%>/static/image/logo-1.png" class="navbar-brand"/></a>
				<span id="office-department"><%=CommonContext.agency%></span>
				<div id="notification-icon" onclick="toggleNotificationList()"><img id="bell" src="<%=request.getContextPath()%>/static/image/bell.png">
					<span id="notification-count">0</span>
					<p id="bell-notice"></p>
				</div>
				<a href="${logoutUrl}"><img id="logout" src="<%=request.getContextPath()%>/static/image/logout.png"></a>
			</div>
		</div>
	<div id="notification-list" style="display: none;">
	<div id="notification-items">
	<ul class="notification-list">
	<li class="notification-item">
	<div class="title">Mã đơn hàng DH12345678 đã thanh toán dư 1000 VNĐ</div>
	<div class="details">
	<div class="time">9:00 AM</div>
	<div class="status">Chưa xem</div>
	</div>
	</li>
	</ul>
	</div>

	<a onclick="loadMoreNotifications()" id="load-more-button" style="display: flex; justify-content: center">Tải thêm</a>
	</div>

	</div>


</header>

<body>
<div id="wrapper">
	<!-- Sidebar -->
	<div id="sidebar-wrapper" style="background-color: #2a2a2a">
		<ul class="sidebar-nav">
			<li class="sidebar-brand" style="background-color: #313131">

				<div class="profile">
					<div class="profile_pic">
						<img src="<%=request.getContextPath()%>/static/image/user.png" alt="..." class="img-circle profile_img">
					</div>
					<div class="profile_info">
						<span style="color :white">  Xin chào </span>
						<img class="edit-profile" src="<%=request.getContextPath()%>/static/image/icon menu/edit-profile.png">
						<h2 style="color: white"><%=CommonContext.user.getAccount()%></h2>
					</div>
				</div>
			</li>

			<li>
				<a href="<%=request.getContextPath()%>/home" id="trang-chu"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/1.png">&nbsp&nbsp Trang chủ </a>

			</li>
			<li>
				<a href="<%=request.getContextPath()%>/contracttemplate/list"  id="mau-hop-dong"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Tên hợp đồng</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/province/list"  id="tinh-thanh"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Tỉnh thành</a>
			</li>

			<li>
				<a href="<%=request.getContextPath()%>/contractkind/list"  id="kieu-hop-dong"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Nhóm hợp đồng</a>
			</li>
			<li>
				<a href="#" class="dropdown" id="quan-tri-khach-hang"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Quản trị khách hàng<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">
					<li class="subitem2">
						<a href="<%=request.getContextPath()%>/customer/list" id="quan-ly-khach-hang">
							<img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png"><span>Quản lý khách hàng</span>
						</a>
					</li>
					<li class="subitem2">
						<a href="<%=request.getContextPath()%>/package/package-list" id="quan-ly-goi-dich-vu">
							<img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png"><span>Quản lý gói dịch vụ</span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" class="dropdown" id="quan-ly-xac-thuc-danh-tinh"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/4.png">&nbsp &nbsp Quản lý xác thực danh tính<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
					<ul style="display: none">
						<li class="subitem2">
							<a href="<%=request.getContextPath()%>/xac-thuc-danh-tinh/danh-sach" id="danh-sach-luot-xac-thuc">
								<img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png"><span>Danh sách lượt xác thực</span>
							</a>
						</li>
						<li class="subitem2">
							<a href="<%=request.getContextPath()%>/giao-dich-phi-xac-thuc/danh-sach" id="danh-sach-giao-dich-phi-xac-thuc">
								<img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png"><span>Giao dịch phí xác thực</span>
							</a>
						</li>
					</ul>
			</li>
			<li>
				<a href="#" class="dropdown" id="quan-tri-he-thong"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/5.png" >&nbsp &nbsp Quản trị hệ thống<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
                <ul style="display: none">
				<li class="subitem2"><a href="<%=request.getContextPath()%>/system/user-list" id="ds-can-bo-stp"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách người dùng</a></li>

					<%--<li class="subitem2"><a href="<%=request.getContextPath()%>/system/office-list" id="ds-to-chuc"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Danh sách tổ chức công chứng / phường xã</a></li>--%>
			</ul>
			</li>
			<li>
				<a href="#" class="dropdown" id="mau-giao-dien"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/5.png" >&nbsp &nbsp Mẫu hiển thị<img class="ar2" src="<%=request.getContextPath()%>/static/image/icon menu/ar2.png"></a>
				<ul style="display: none">
					<li class="subitem2"><a href="<%=request.getContextPath()%>/template/privy/list" id="duongsu"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Đương sự</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/template/property/list" id="taisan"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Tài sản</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/fee/list" id="phihopdong"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Phí hợp đồng</a></li>
					<li class="subitem2"><a href="<%=request.getContextPath()%>/attestation-template/list" id="ds-mau-loi-chung"><img class="menu-dot" src="<%=request.getContextPath()%>/static/image/dot.png">Lời chứng</a></li>

				</ul>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/bank/list" id="ngan-hang"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/6.png">&nbsp &nbsp Ngân hàng</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/question-help/list" id="tro-giup"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/2.png">&nbsp &nbsp Quản lý câu hỏi trợ giúp</a>
			</li>
			<li>
				<a href="/contact-us" id="lien-he"><img class="menu-logo"  src="<%=request.getContextPath()%>/static/image/icon menu/7.png">&nbsp &nbsp Liên hệ</a>
			</li>
		</ul>
		<div>Uchi Sở Tư Pháp v3.0</div>
	</div>
	<!-- /#sidebar-wrapper -->

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12" id="uchi-content">
