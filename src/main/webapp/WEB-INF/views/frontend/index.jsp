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
<script type="text/javascript">
	//khởi tạo array chứa danh sách ảnh
	var slide = new Array();
	slide[0] = "${classpath}/frontend/images/slide1.jpg";
	slide[1] = "${classpath}/frontend/images/slide2.jpg";
	//tạo biến lưu vị trí ảnh
	var n = 0;
	setInterval(function() {
		$(".slide-img").fadeOut(500, function() {
			n++;
			//thay đổi giá trị của thuộc tính src thành ảnh kế tiếp
			$(".slide-img").attr("src", slide[n]);
			$(".slide-img").fadeIn(500);
			//nếu n ở vị trí ảnh cuối cùng thì reset n
			if (n == slide.length - 1)
				n = -1;
		});
	}, 4000);
</script>
</head>
<body>
	<div class="container">
		
		<!-- header -->
		<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

		<!-- Banner chính -->
		<div class="slide">
			<img src="${classpath}/frontend/images/slide1.jpg" class="slide-img">
		</div>
		<!-- /Banner chính -->


		<!-- main -->
		<div class="main">
			<!-- san-pham-ban-chay -->
			<div class="san-pham-ban-chay">
				<h2 class="tittle">SẢN PHẨM BÁN CHẠY</h2>
				<div class="gach-chan">
					<img style="height: 35px;"
						src="${classpath}/frontend/images/gach-chan.png">
				</div>
				<div class="list-san-pham">
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot1.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Tay nắm bánh hình mây, cầu vồng
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">45.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot2.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Bộ trang trí chủ đề Puppy Girl cho bé gái
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">290.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot3.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Tag cắm bóng bay xinh xắn, màu hồng
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">20.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot4.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Mô hình Chibi bé gái công chúa
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">95.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot5.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Bộ trang trí chủ đề khủng long, bé trai
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">499.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>
					<div class="card">
						<div class="card-image">
							<img src="${classpath}/frontend/images/hot6.jpg">
						</div>
						<div class="card-content">
							<h3 class="card-name">
								</i>Bóng bay kim loại Đỏ Đô màu đẹp
							</h3>
							<p style="color: #e04c78; font-size: 20px; margin-top: -12px;"
								class="card-price">35.000₫</p>
							<p class="card-add">Thêm vào giỏ hàng</p>
						</div>
					</div>

				</div>
			</div>
			<!-- /san-pham-ban-chay -->

			<!-- Danh sách sản phẩm -->
			<form class="form-inline" action="${classpath }/index" method="get">
				<div class="box">
					<div class="link">
						<h2 href="">Danh sách sản phẩm</h2>
						<a href="#">Bóng bay sinh nhật</a> <a href="#">Mô hình Chibi</a> <a
							href="#">Phụ kiện trang trí</a>
						<p><a
							href="${classpath }/list">Xem tất cả</a></p>
						</p>
					</div>
					<div class="main-bot-right">
						<c:forEach items="${products }" var="product" varStatus="loop">
							<c:if test="${loop.index < 10}">
								<div class="card">

									<a href="${classpath }/detail/${product.id }"
										class="image-card"> <img
										src="${classpath }/FileUploads/${product.avatar }"
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


			<!-- Các sản phẩm -->


			<!-- Bóng bay sinh nhật -->
			<c:forEach items="${categories}" var="category">
				<div class="box">
					<div class="link">
						<h2 href="">${category.name}</h2>
						<a href="#">Trang trí sinh nhật cho bé gái</a> <a href="#">Trang
							trí sinh nhật cho bé trai</a> <a href="#">Trang trí sinh nhật cho
							người lớn</a>
						<p><a
							href="${classpath }/shop/${category.id}">Xem tất cả</a></p>
					</div>
					<div class="main-bot-right">
						<c:forEach items="${category.products}" var="product"
							varStatus="loop">
							<c:if test="${loop.index < 10}">

								<div class="card">
									<a href="${classpath }/detail/${product.id }"
										class="image-card"> <img
										src="${classpath}/FileUploads/${product.avatar}">
									</a>
									<div class="content-card">${product.name}</div>
									<div class="gia">${product.price}₫</div>
									<a style="cursor: pointer;"
										onclick="addToCart(${product.id}, 1, '${product.name }')">
										<div class="add-card">Thêm vào giỏ hàng</div>
									</a>
								</div>

							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
			<!-- Bóng bay sinh nhật -->

			<!-- /Các sản phẩm -->

			<!-- Tin tức -->
			<div class="tin-tuc">
				<h2 class="tittle">TIN TỨC - BÀI VIẾT</h2>
				<div class="gach-chan">
					<img style="height: 35px;"
						src="${classpath}/frontend/images/gach-chan.png">
				</div>
				<div class="noi-dung">
					<div class="noi-dung-left">
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/1.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/2.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/3.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/4.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/5.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/6.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
							<div class="text">Nguyên tắc ăn uống “vào con không vào mẹ”
								Giai đoạn 1 (3 tháng đầu) Ở</div>
						</div>
					</div>
					<div class="noi-dung-right">
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/1.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/2.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/3.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/4.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/5.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>
						<div class="card">
							<div class="image">
								<img src="${classpath}/frontend/images/tintuc/6.jpg">
							</div>
							<div class="content">Bà bầu cứ nắm nguyên tắc này, đảm bảo
								ăn nhiều bao</div>
						</div>

					</div>
				</div>
			</div>
			<!-- Tin tức -->

			<!-- Hình ảnh thực tế -->
			<div class="thuc-te">
				<h2 class="tittle">HÌNH ẢNH TRANG TRÍ THỰC TẾ</h2>
				<div class="gach-chan">
					<img style="height: 35px;"
						src="${classpath}/frontend/images/gach-chan.png">
				</div>
				<div style="text-align: center;" class="text">Dưới đây là một
					số hình ảnh thi công thực tế tại nhà khách hàng mà nhân viên chúng
					tôi ghi nhận được, mời quý vị đón xem:</div>
				<div class="list-image">
					<li><img src="${classpath}/frontend/images/thucte/1.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/2.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/3.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/4.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/5.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/6.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/7.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/8.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/9.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/10.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/11.jpg">
					</li>
					<li><img src="${classpath}/frontend/images/thucte/12.jpg">
					</li>
				</div>
			</div>
			<!-- /Hình ảnh thực tế -->




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
