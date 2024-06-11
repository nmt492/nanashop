<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>party - Trang chủ</title>
<link rel="shortcut icon" type="image/png"
	href="${classpath}/frontend/images/logo-nana.jpg" />

<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style type="text/css">
.slide {
	width: 891px;
	height: 336px;
	padding-left: 330px;
	overflow: hidden;
}

.slide img {
	height: 320px;
	margin-left: 10px;
	margin-top: 10px;
	border-radius: 10px;
}
</style>

</head>
<body>
	<div class="container">
		<!-- header -->
		<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

		<!-- main -->
		<div class="main">
			
			<!-- Danh sách sản phẩm -->
			<form class="form-inline" action="${classpath }/search" method="get">
				<div class="box">
					<div class="link">
						<h2 href="">Kết quả tìm kiếm cho '${q}'</h2>
						
						<p>
							${totalProducts } kết quả
						</p>
					</div>
					<div class="main-bot-right">
						<c:forEach items="${products }" var="product" varStatus="loop">
						<c:if test="${loop.index < 10}">
							<div class="card">

								<a href="${classpath }/detail/${product.id }" class="image-card">
									<img src="${classpath }/FileUploads/${product.avatar }"
									class="fit-img zoom-img">
								</a>
								<div class="content-card">${product.name }</div>
								<div class="gia">${product.price }₫</div>
								<a onclick="addToCart(${product.id}, 1, '${product.name }')">
									<div class="add-card">Thêm vào giỏ hàng</div>
								</a>
							</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</form>
			<!-- Danh sách sản phẩm -->







		</div>
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
