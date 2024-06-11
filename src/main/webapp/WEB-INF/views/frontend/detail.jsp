<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sản phẩm</title>
<link rel="shortcut icon" type="image/png"
	href="${classpath}/frontend/images/chibi/chibi1.jpg" />
<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript">
		function changeImage(imgId) {
			//---
			//reset đường viền
			document.getElementById('img1').removeAttribute("style");
			document.getElementById('img2').removeAttribute("style");
			document.getElementById('img3').removeAttribute("style");
			document.getElementById('img4').removeAttribute("style");
			//set đường viền
			document.getElementById(imgId).setAttribute("style", "border: 1px solid red;")
			//----
			//Lấy giá trị thuộc tính src của id = imgId
			var image_src = document.getElementById(imgId).getAttribute("src");
			//set giá trị vào id = show-image
			document.getElementById("show-image").setAttribute("src", image_src)
		}
		
	</script>
</head>
<body>
	<div class="container">
		<!-- header -->

		<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>

		<!-- main -->
		<div class="main">
			<div class="top">
				<div class="top-left" id="box">
					<img style="height: 350px; width: 350px"
						src="${classpath }/FileUploads/${product.avatar }" id="show-image">
					<ul>
						<li><img id="img1" onclick="changeImage('img1');"
							onauxclick="opacity('img1')"
							src="${classpath }/FileUploads/${product.avatar }"></li>
						<li><img id="img2" onclick="changeImage('img2');"
							onauxclick="opacity('img1')"
							src="${classpath }/FileUploads/${productImage.path }"></li>
						<li><img id="img3" onclick="changeImage('img3');"
							onauxclick="opacity('img1')"
							src="${classpath }/FileUploads/${productImage.path }"></li>
						<li><img id="img4" onclick="changeImage('img4');"
							onauxclick="opacity('img1')"
							src="${classpath }/FileUploads/${productImage.path }"></li>

					</ul>
					<div class="lien-he">
						<div class="text">
							<div class="icon">
								<img src="${classpath}/frontend/images/headset.png">
							</div>
							<div class="content">Hãy để lại SĐT, chuyên viên tư vấn của
								chúng tôi sẽ gọi ngay cho bạn miễn phí!</div>
						</div>
						<input type="text" placeholder="Nhập số điện thoại...">
						<button type="submit">
							Gửi</i>
					</div>
				</div>
				<div class="top-center">
					<div class="link">
						<a href="${classpath }/index">Trang chủ</a><i> / </i> <a
							href="${classpath }/shop/${product.category.id}">${product.category.name }</a>
					</div>
					<div class="tittle">${product.name }</div>
					<div class="gia">${product.price }₫</div>
					<div class="info">${product.shortDescription }</div>
					<div class="so-luong">
						<input style="text-align: center;" type="number" id="quantity"
							name="quantity" value="1">
						<div>
							<a onclick="addToCart(${product.id}, quantity, '${product.name }')"
								class="add-cart-link">THÊM VÀO GIỎ HÀNG</a>
						</div>
					</div>

					<div class="lien-he">
						<div class="fb">Chat Facebook</div>
						<div class="zalo">Chat Zalo</div>
						<div class="si">Mua sỉ: 086.835.7939</div>
						<div class="le">Mua lẻ: 097.822.5678</div>
					</div>
					<div class="mua-ngay">
						<div class="text">MUA NGAY</div>
						<div class="content">Gọi điện xác nhận và giao hàng tận nơi</div>
					</div>
					<div class="danh-muc">
						<p>Danh mục:</p>
						<p class="a">Chibi bé trai,</p>
						<p class="a">Chibi bé gái</p>
					</div>
					<div class="mxh">
						<div class="fb">
							<i class="fa-brands fa-facebook-f"></i>
						</div>
						<div class="twit">
							<i class="fa-brands fa-twitter"></i>
						</div>
						<div class="mail">
							<i class="fa-regular fa-envelope"></i>
						</div>
						<div class="pin">
							<i class="fa-brands fa-pinterest"></i>
						</div>
						<div class="linkin">
							<i class="fa-brands fa-linkedin"></i>
						</div>
					</div>
				</div>
				<div class="top-right">
					<div class="box">
						<div class="bgr-text-spm">
							<div class="text">Bài viết mới</div>
						</div>
						<div class="bgr-spm">
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp1.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Bà bầu cứ nắm nguyên tắc này,
										đảm bảo ăn nhiều bao nhiêu cũng “vào con không vào mẹ”</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp2.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Tìm cách bổ sung lợi khuẩn cho
										hệ tiêu hóa của trẻ</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp3.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Cách khắc phục khi uống sữa bầu
										bị tiêu chảy</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp4.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Con em thoát khỏi táo bón một
										cách thần kỳ nhờ bào tử lợi khuẩn Preg-Mom</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm">
									<img src="${classpath}/frontend/images/shop/sp5.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Top 5 loại sữa tốt nhất cho bà
										bầu và thai nhi</div>
								</div>
							</div>
						</div>
					</div>


					<div class="box">
						<div class="bgr-text-spm">
							<div class="text">Sản phẩm mới</div>
						</div>
						<div class="bgr-spm">
							<div class="spm">
								<div class="image-spm1">
									<img src="${classpath}/frontend/images/shop/sp1.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái làm ca sĩ</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm1">
									<img src="${classpath}/frontend/images/shop/sp2.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé trai khoác túi
										đi học</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm1">
									<img src="${classpath}/frontend/images/shop/sp3.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé trai công nhân
										58</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm1">
									<img src="${classpath}/frontend/images/shop/sp4.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái dễ thương
										01</div>
								</div>
							</div>
							<div class="spm">
								<div class="image-spm1">
									<img src="${classpath}/frontend/images/shop/sp5.jpg">
								</div>
								<div class="info">
									<div class="content-spm">Mô hình Chibi bé gái màu hồng mã
										51</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="mo-ta"></div>
			<div class="sptt">Sản phẩm tương tự</div>
			<div class="bot">
				<div class="main-bot-right">
					<div class="card">
						<div class="image-card">
							<img src="${classpath}/frontend/images/chibi/chibi1.jpg">
						</div>
						<div class="content-card">Mô hình chibi bé gái màu hồng mã
							51</div>
						<div class="gia">560.000₫</div>
						<div class="add-card">Thêm vào giỏ hàng</div>
					</div>
					<div class="card">
						<div class="image-card">
							<img src="${classpath}/frontend/images/chibi/chibi2.jpg">
						</div>
						<div class="content-card">Mô hình chibi bé gái làm ca sĩ</div>
						<div class="gia">560.000₫</div>
						<div class="add-card">Thêm vào giỏ hàng</div>
					</div>
					<div class="card">
						<div class="image-card">
							<img src="${classpath}/frontend/images/chibi/chibi3.jpg">
						</div>
						<div class="content-card">Mô hình chibi bé trai khoác túi đi
							học</div>
						<div class="gia">560.000₫</div>
						<div class="add-card">Thêm vào giỏ hàng</div>
					</div>
					<div class="card">
						<div class="image-card">
							<img src="${classpath}/frontend/images/chibi/chibi4.jpg">
						</div>
						<div class="content-card">Mô hình chibi bé gái dễ thương 01</div>
						<div class="gia">560.000₫</div>
						<div class="add-card">
							<button>Thêm vào giỏ hàng</button>
						</div>

					</div>
				</div>
			</div>
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
			alert("Thêm "  +jQuery("#quantity").val() + " sản phẩm '" + _productName + "' vào giỏ hàng ");
			let data = {
				productId: _productId, //lay theo id
				quantity: jQuery("#quantity").val(),
				
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
					alert(jsonResult.code + ': Đã có lỗi xay ra...!')
				},
			});
		}
	</script>
</html>
