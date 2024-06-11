<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Trang trí sinh nhật</title>
<link rel="shortcut icon" type="image/png"
	href="${classpath}/frontend/images/logo.png" />
<link rel="stylesheet" type="text/css"
	href="${classpath}/frontend/css/css.css">
<link rel="stylesheet" type="text/css"
	href="${classpath}/frontend/css/shop.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<div class="container">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>




		<!-- main -->
		<form action="${classpath }/shop/${category.id }" method="get">
		<div class="main">
			<div class="main-top">
				<div class="main-top-left">
					<div class="tieu-de">${category.name }</div>
					<div class="chi-tiet">
						<a style="color: #949494;" href="${classpath }/index">TRANG CHỦ /</a> <a
							style="color: black;" href="${classpath }/shop/${category.id }">${category.name }</a>
					</div>
				</div>
				<div class="main-top-right">
					<div class="text">Hiển thị tất cả <fmt:formatNumber value="${totalProducts }"
										minFractionDigits="0"></fmt:formatNumber> kết quả</div>
					<select>
						<option>Thứ tự mặc định</option>
						<option>Thứ tự theo mức độ phổ biến</option>
						<option>Thứ tự theo độ đánh giá</option>
						<option>Mới nhất</option>
						<option>Thứ tự theo giá: Từ thấp đến cao</option>
						<option>Thứ tự theo giá: Từ cao xuống thấp</option>
					</select>
				</div>
			</div>

			<div class="main-bot">
				<div class="main-bot-left">
					<div class="box">
						<div class="bgr-text-spm">
							<div class="text">Sản phẩm mới</div>
						</div>
						<div class="bgr-spm">
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp1.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái làm ca sĩ</div>
									<div class="gia">580.000₫</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp2.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé trai khoác túi
										đi học</div>
									<div class="gia">462.000₫</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp3.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé trai công nhân
										58</div>
									<div class="gia">95.000₫</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp4.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái dễ thương
										01</div>
									<div class="gia">450.000₫</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp5.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái màu hồng mã
										51</div>
									<div class="gia">485.000₫</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				
				<div class="main-bot-right">
					<c:forEach items="${category.products}" var="product">
						<div style="height: 300px" class="card">
							<div class="image-card">
								<img src="${classpath }/FileUploads/${product.avatar }">
							</div>
							<div class="content-card">${product.name}</div>
							<div class="gia">${product.price}₫</div>
							<a onclick="addToCart(${product.id}, 1, '${product.name }')">
							<div  class="add-card">Thêm vào giỏ hàng</div>
							</a>
						</div>
					</c:forEach>
				</div>
				
				
			</div>
		</div>
		</form>
		<!-- /main -->


		<!-- footer -->

		<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
		<!-- /footer -->
	</div>
	<!-- /container -->
</body>
<script type="text/javascript">
		addToCart = function(_productId, _quantity, _productName) {		
			alert("Thêm "  + _quantity + " sản phẩm '" + _productName + "' vào giỏ hàng ");
			let data = {
				productId: _productId, //lay theo id
				quantity: _quantity,
				
			};
				
			//$ === jQuery
			jQuery.ajax({
				url : "/add-to-cart",
				type : "POST",
				contentType: "application/json",
				data : JSON.stringify(data),
				dataType : "json", //Kieu du lieu tra ve tu controller la json
				
				success : function(jsonResult) {
					/* alert(jsonResult.code + ": " + jsonResult.message); */
					let totalProducts = jsonResult.totalCartProducts;
					$("#totalCartProductsId").html(totalProducts);
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert(jsonResult.code + ': Đã có lỗi xảy ra...!')
				},
			});
		}
	</script>
</html>
