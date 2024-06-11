<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
.menu-top {
	position: sticky;
	top: -10px;
	z-index: 500px;
}
</style>
<script>
        function searchAndRedirect() {
            // Lấy giá trị từ ô input tìm kiếm
            var searchQuery = document.getElementById('searchInput').value;

            // Kiểm tra xem người dùng đã nhập thông tin tìm kiếm hay chưa
            if (searchQuery.trim() !== "") {
                // Chuyển hướng đến trang kết quả tìm kiếm, thay đổi 'result.html' thành URL của trang kết quả tìm kiếm thực tế
                window.location.href = 'search?q=' + encodeURIComponent(searchQuery);
            }
        }
    </script>

<header>
	<div class="header-left">
		<nav>
			<ul>
				<li><a href="#"><i class="fa-solid fa-location-dot"></i></i>
						Địa chỉ</a></li>
				<li><a href="#"><i class="fa-solid fa-phone"></i>
						0915101520</a></li>
			</ul>
		</nav>
	</div>
	<div class="header-right">
		<nav>
			<ul>
				<li><a href="#"> Thanh toán</a></li>
				<li><a href="#">Địa chỉ</a></li>
				<li><a href="#"><i class="fa-brands fa-facebook-f"></i></a></li>
				<li><a href="#"><i class="fa-regular fa-envelope"></i></a></li>
			</ul>
		</nav>
	</div>
</header>
<!-- /header -->
<!-- Banner -->
<div class="banner">
	<div class="banner-left">
		<img
			style="width: 120px; margin: 12px; padding-left: 20px; cursor: pointer;"
			src="${classpath}/frontend/images/logo-nana.jpg">
	</div>
	<div class="search-container">
		<select>
			<option value="0">All</option>
			<c:forEach items="${categories }" var="category">
				<option value="${category.id }">${category.name }</option>
			</c:forEach>
		</select> <input type="text" id="searchInput" name="searchInput"
			placeholder="Tìm kiếm sản phẩm...">
		<button onclick="searchAndRedirect()" type="submit">
			<i class="fa-solid fa-magnifying-glass"></i>
		</button>
	</div>
	<div class="banner-right">
		<img style="width: 200px; margin-top: 20px;"
			src="${classpath}/frontend/images/hotline-1.jpg">
	</div>
</div>
<!-- /Banner -->
<div class="line1"></div>
<!-- Menu ngang -->
<div class="menu-top">
	<div class="box1"></div>
	<div onclick="showHideList()" class="box2">
		<i class="fa-solid fa-list"></i> Danh mục sản phẩm
		<ul class="myList">
			<li><a style="color: #ff78b5;" href="#">Sản phẩm bán chạy</a><i
				style="right: 30px; color: yellow;"
				class="fa-solid fa-fire-flame-curved"></i></li>
			<li><a href="${classpath }/shop/4">Trang trí sinh nhật</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Trang trí thôi nôi</a></li>
					<li><a href="#">Trang trí sinh nhật cho bé gái</a></li>
					<li><a href="#">Trang trí sinh nhật cho bé trai</a></li>
					<li><a href="#">Trang trí sinh nhật cho người lớn</a></li>
					<li><a href="#">Bộ full (Packgae box)</a></li>
				</ul></li>
			<li><a href="${classpath }/shop/1">Bóng bay sinh nhật</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Bóng cười</a></li>
					<li><a href="#">Bóng cao su</a></li>
					<li><a href="#">Bóng nhôm</a></li>
					<li><a href="#">Combo bóng</a></li>
					<li><a href="#">Phụ kiện bóng</a></li>
				</ul></li>
			<li><a href="#">Bảng thông tin, bảng tên, cây welcome</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Bảng tên</a></li>
					<li><a href="#">Bảng thông tin</a></li>
					<li><a href="#">Cây welcome</a></li>
				</ul></li>
			<li><a href="#">Backdrop - phông màn</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Trang trí thôi nôi</a></li>
					<li><a href="#">Trang trí sinh nhật cho bé gái</a></li>
					<li><a href="#">Trang trí sinh nhật cho bé trai</a></li>
					<li><a href="#">Trang trí sinh nhật cho người lớn</a></li>
					<li><a href="#">Bộ full (Packgae box)</a></li>
				</ul></li>
			<li><a href="#">Phụ kiện trang trí sinh nhật</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Backdrop theo chủ đề</a></li>
					<li><a href="#">Dây Garland</a></li>
					<li><a href="#">Đèn trang trí</a></li>
					<li><a href="#">Màn giấy xoắn</a></li>
					<li><a href="#">Màn kim tuyến</a></li>
					<li><a href="#">Phông quạt giấy</a></li>
					<li><a href="#">Phông tú cầu</a></li>
				</ul></li>
			<li><a href="${classpath }/shop/3">Mô hình Chibi</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Chibi bé gái</a></li>
					<li><a href="#">Chi bi bé trai</a></li>
				</ul></li>
			<li><a href="#">Phụ kiện trang trí đám cưới, ngày đặc biệt</a><i
				class="fa-solid fa-caret-right"></i>
				<ul class="myList1">
					<li><a href="#">Bong bóng</a></li>
					<li><a href="#">Phụ kiện trang trí khác</a></li></li>
		</ul>
	</div>

	<nav>
		<ul>
			<li><a href="${classpath}/index">Trang chủ</a></li>
			<li><a href="#">Giới thiệu</a></li>
			<li><a href="#">Hướng dẫn mua hàng</a></li>
			<li><a href="#">Đăng ký mua buôn</a></li>
			<li><a href="#">Địa chỉ</a></li>
			<li><a style="margin-left: 50px;" href="${classpath}/cart">Giỏ
					hàng <i class="fa-solid fa-basket-shopping"></i>
			</a></li>
			<span style="margin-left: -15px; font-weight: bold"
				id="totalCartProductsId" class="header__bottom-num">(${totalCartProducts})</span>
			<c:choose>
				<c:when test="${isLogined }">
					<a href="${classpath }/logout"><i
					class="fa-regular fa-address-card"></i></i>
					</a>
					
				</c:when>
				<c:otherwise>
					<a href="${classpath }/login"><i
					class="fa-regular fa-address-card"></i>
					</a>
				</c:otherwise>
			</c:choose>
			<li>${loginedUser.name }</li>
		</ul>
	</nav>
</div>
<!-- /Menu ngang -->