<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title }</title>

<!-- variables -->
<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
<!-- css -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<style type="text/css">
html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,dl,dt,dd,ol,nav ul,nav li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inherit;vertical-align:baseline;}
article, aside, details, figcaption, figure,footer, header, hgroup, menu, nav, section {display: block;}
ol,ul{list-style:none;margin:0px;padding:0px;}
blockquote,q{quotes:none;}
blockquote:before,blockquote:after,q:before,q:after{content:'';content:none;}
table{border-collapse:collapse;border-spacing:0;}
/* start editing from here */
a{text-decoration:none;}
.txt-rt{text-align:right;}/* text align right */
.txt-lt{text-align:left;}/* text align left */
.txt-center{text-align:center;}/* text align center */
.float-rt{float:right;}/* float right */
.float-lt{float:left;}/* float left */
.clear{clear:both;}/* clear float */
.pos-relative{position:relative;}/* Position Relative */
.pos-absolute{position:absolute;}/* Position Absolute */
.vertical-base{	vertical-align:baseline;}/* vertical align baseline */
.vertical-top{	vertical-align:top;}/* vertical align top */
nav.vertical ul li{	display:block;}/* vertical menu */
nav.horizontal ul li{	display: inline-block;}/* horizontal menu */
img{max-width:100%;}
/*end reset*/
html, body{
    font-size: 100%;
	font-family: 'Montserrat', sans-serif;
	background:url(${classpath}/frontend/images/backgr.jpg) no-repeat 0px 0px;
	background-size:cover;
	background-attachment:fixed;
	-webkit-background-size:cover;
	-moz-background-size:cover;
	-o-background-size:cover;
	-ms-background-size:cover;
}
body {
		padding-bottom:30px;
}
h1,h2,h3,h4,h5,h6,input,p,a,select,button,textarea{
	font-family: 'Montserrat', sans-serif;
	margin:0;
}
ul,label{
	margin:0;
	padding:0;
}
body a:hover,body a{
	text-decoration:none;
}



/* login Title */
.login-title {
	padding: 50px 0;
	text-align: center;
	letter-spacing: 2px;
}
.login-title h1 {
	margin: 0 0 0px;
    font-size: 45px;
    letter-spacing: 5px;
    text-transform: uppercase;
    font-weight: 400;
    color: white;
}
.login-title span {
	font-size: 12px;
}
.login-title span .fa {
	color: #33b5e5;
}
.login-title span a {
	color: #33b5e5;
	font-weight: 600;
	text-decoration: none;
}



.login {
	width: 400px;
	margin: 16px auto;
	font-size: 16px;
}

/* Reset top and bottom margins from certain elements */
.login-header,
.login p {
	margin-top: 0;
	margin-bottom: 0;
}

/* The triangle form is achieved by a CSS hack */
.login-triangle {
	width: 0;
	margin-right: auto;
	margin-left: auto;
	border: 12px solid transparent;
	border-bottom-color: #28d;
}

.login-header {
  background: #33b5e5;
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
}

.w3l_grid {
  background: #fff;
  padding:25px;
}

/* Every row inside .login-container is defined with p tags */



.login input[type="text"],
.login input[type="password"] {
  background: #fff;
  margin: 25px 0px;
  color:#ccc6c6;
  box-sizing: border-box;
  display: block;
  width: 100%;
  border-width: 1px;
  border-style: solid;
  padding: 13px;
  outline:none;
  font-family: inherit;
  font-size: 0.95em;
}
.login-container input[type="text"] {
	color:#ccc6c6;
}

.login-container input[type="text"]:hover {
    border-color: #28d;
}

/* Text fields' focus effect */
.login input[type="text"]:focus,
.login input[type="password"]:focus {
  border-color: #33b5e5;
}
.login input[type="text"]:hover 
.login input[type="password"]:hover
 border-color:#28d;
  
}
.login input[type="submit"] {
  background:#33b5e5 ;
  border-color: transparent;
  color: #fff;
  cursor: pointer;
}

.login input[type="submit"]:hover {
  background:#28d;
}

/* Buttons' focus effect */
.login input[type="submit"]:focus {
  border-color: #05a;
}
.form .social-icons {
	font-size:20px;
}
.form .social-icons { 
}
.second-section{
	

}

.social-links {

      margin: 0px 0px 20px 0px;
  	  text-align: center;
}
.w3_bottom {
   margin: 20px 0;
}
.bottom-header h3 {
    color: #33b5e5;
    text-align: center;
    font-size:20px;
	margin-top: 0px;
}
.bottom-header p {
	padding:12px;
}
/* buttons */
.social-links ul li {
    display: inline-block;
}

.social-links a {
	width: 40px;
  	height: 40px;
  	display: inline-block;
  	border-radius: 50%;
  	font-size: 24px;
  	color: #fff;
	opacity: 0.75;
	transition: opacity 0.15s linear;
}

.social-links a:hover {
	opacity: 1;
}

/* icons */

.social-links i {
    line-height: 43px;
    font-size: 18px;
    
}

/* colors */

.facebook {
 	background: #3b5998;
}

.twitter {
  	background: #55acee;
}

.googleplus {
  	background: #dd4b39;
}

.linkedin {
  	background: #0077b5;
}

.pinterest {
  	background: #cb2027;
}

.login-container input[type="submit"] {
    width: 100%;
    background-color:#ef4d4d;
    color: white;
    padding: 12px 20px;
   
    border: none;
	font-size:14px;
	letter-spacing:1px;
    cursor: pointer;
	outline:none;
	transition: .5s ease-in;
    -webkit-transition: .5s ease-in;
    -ms-transition: .5s ease-in;
    -o-transition: .5s ease-in;
    -moz-transition:.5s ease-in;
}

input[type=submit]:hover {
    background-color:rgba(51, 181, 229, 0.62);
}


 .bottom-text {

  

  box-sizing: border-box;
  color: #666666;
  font-size: 12px;
  text-align: center;

}
.bottom-text p  {
	font-size:14px;
}
 .bottom-text a {
  color: #33b5e5;
  text-decoration: none;
  font-size:14px;
	}

.bottom-text p a{
	color:#33b5e5;
	margin-left: 5px;
    transition: .5s ease-in;
    -webkit-transition: .5s ease-in;
    -ms-transition: .5s ease-in;
    -o-transition: .5s ease-in;
    -moz-transition: .5s ease-in;
}
.bottom-text p a:hover {
	color:#ef4d4d;
	
}
.bottom-text h4 {
	font-size:10px;
	color:#33b5e5;
	margin-top: 10px;
    color:#ef4d4d;
	transition: .5s ease-in;
    -webkit-transition: .5s ease-in;
    -ms-transition: .5s ease-in;
    -o-transition: .5s ease-in;
    -moz-transition: .5s ease-in;
}
.bottom-text h4 a:hover {
	color:#ef4d4d;
	
}
.footer-w3l {
	
}
.footer-w3l p{
	font-size:14px;
	letter-spacing:0.5px;
	color:#fff;
	text-align:center;
	margin-top:50px;
}
.footer-w3l p a{
	color:#fff;
}
.footer-w3l p a:hover{
	text-decoration:underline;
	color:#ef4d4d;
	color:#33b5e5;
	transition: .5s ease-in;
    -webkit-transition: .5s ease-in;
    -ms-transition: .5s ease-in;
    -o-transition: .5s ease-in;
    -moz-transition:.5s ease-in;
}
/* responsive design */
@media only screen and (max-width:1080px) {
.login-title {
    padding: 30px 0;
}
.login-title h1 {
    font-size:35px;
    letter-spacing: 4px;
}
.login {
    width: 370px;
    margin: 14px auto;
    font-size: 15px;
}
.login-header {
    padding: 13px;
    font-size: 1.2em;
}
.w3l_grid {
  padding:20px;
}

.w3_bottom {
    margin: 18px 0;
}
.bottom-header h3 {
    font-size: 16px;

}
.bottom-text p {
    font-size: 12px;
}


.bottom-text p a {

    margin-left: 4px;
}
.bottom-text a {
    color: #33b5e5;
    text-decoration: none;
    font-size: 12px;
}
.login input[type="text"], .login input[type="password"] {

    margin: 20px 0px;
    padding: 10px;
}

.login input[type="text"], .login input[type="password"] {
    margin: 20px 0px;
    width: 100%;
    border-width: 1px;
    border-style: solid;
    padding: 13px;
    outline: none;
    font-family: inherit;
    font-size: 0.9em;
}
.social-links {
    margin: 0px 0px 18px 0px;
}
.social-links a {
    width: 37px;
    height: 37px;
}
.footer-w3l p {
    font-size: 12px;
    letter-spacing: 0.5px;
    color: #fff;
    text-align: center;
    margin-top:35px;
}

@media only screen and (max-width:991px) {
	.login-title {
    padding:28px 0;
	}
	.login-title h1 {
    font-size:34px;
    letter-spacing: 4px;
}
.login {
    width: 340px;
    margin: 9px auto;
    font-size: 11px;
}
.login-header {
   
    padding: 12px;
    font-size: 1.2em;
}
.w3l_grid {
    padding:19px;
}

  .w3_bottom {
    margin: 18px 0;
}
.bottom-header h3 {
    font-size: 16px;

}
.bottom-text p {
    font-size: 11px;
}


.bottom-text p a {

    margin-left: 4px;
}
.bottom-text a {
    
    font-size: 11px;
}
.login input[type="text"], .login input[type="password"] {

    margin: 20px 0px;
    padding: 10px;
}

.login input[type="text"], .login input[type="password"] {
    margin: 20px 0px;
    width: 100%;
    border-width: 1px;
    border-style: solid;
    padding: 13px;
    outline: none;
    font-family: inherit;
    font-size: 0.9em;
}
.social-links {
    margin: 0px 0px 18px 0px;
}
.social-links a {
    width: 37px;
    height: 37px;
}
.footer-w3l p {
    font-size: 11px;
    letter-spacing: 0.5px;
    color: #fff;
    text-align: center;
    margin-top:20px;
}
}
@media only screen and (max-width:800px) {
.login-title h1 {
    font-size: 30px;
	}
.login-title {
    padding: 26px 0;
}
.login-header {
    padding:8px;
}
.login {
    width: 360px;
    margin: 11px auto;
    font-size: 13px;
}
.w3l_grid {
    padding: 15px;
}
.login input[type="text"], .login input[type="password"] {
  margin: 14px 0px;
    padding: 9px;
    font-size: 0.9em;
}
.login-container input[type="submit"] {
      padding: 9px 0px;
   font-size: 13px;
}
.bottom-header h3 {
    font-size: 12px;
}
.w3_bottom {
    margin: 16px 0;
}
.social-links a {
    width: 34px;
    height: 34px;
}
.social-links {
    margin: 0px 0px 16px 0px;
}


@media only screen and (max-width:414px) {
.login-title h1 {
    font-size: 25px;
}
.login {
    width: 290px;
    margin: 8px auto;
    font-size: 12px;
}
.w3l_grid {
    padding: 13px;
}
.footer-w3l p {
    font-size: 10px;
    letter-spacing: 0.4px;
}
.w3_bottom {
    margin: 14px 0;
}
.social-links {
    margin: 0px 0px 14px 0px;
}
.social-links a {
    width: 30px;
    height: 30px;
}
.social-links i {
    line-height: 28px;
    font-size: 16px;
}

@media only screen and (max-width:375px) {
.login-title h1 {
    font-size: 23px;
}
.login-header {
    padding: 5px;
}
.login input[type="text"], .login input[type="password"] {
    margin: 12px 0px;
    padding: 7px;
    font-size: 0.9em;
}
.login-container input[type="submit"] {
    padding: 7px 0px;
    font-size: 12px;
}
.social-links i {
    line-height: 26px;
    font-size: 15px;
}
.social-links a {
    width: 30px;
    height: 30px;
}
.footer-w3l p {
    font-size: 9px;
}
@media only screen and (max-width:320px) {
.login-title h1 {
    font-size: 20px;
}

/* responsive design */
</style>
<body>
	<div class="container">
		
		<!-- content -->
	<main>
    <!-- Section: Design Block -->
    <section>
		<div style="margin-top: -100px; margin-bottom: 110px " class="login-form w3_form">
			<!--  Title-->
				<div class="login-title w3_title">
					 <h1>Elegant login Form</h1>
				</div>
					 <div class="login w3_login">
						  <h2 class="login-header w3_header">Log in</h2>
							  <div class="w3l_grid">
								  <form class="login-container" action="${classpath }/login_processing_url" method="post">
								<%-- 
                        			<input type="hidden" name="${_csrf.parameterName }" value="${csrf.token }" />
                        		--%>   
								<c:if test="${not empty param.login_error }">
                            		<div role="alert">Login attempt was not successful, try again!!!</div>
                        		</c:if>
									<input type="text" placeholder="username" id="username" name="username" >
									<input type="password" id="password" name="password" placeholder="password">
									<input type="submit" value="Login">
								  </form>
		  <div class="second-section w3_section">
			   <div class="bottom-header w3_bottom">
					<h3>OR</h3>
			   </div>
			   <div class="social-links w3_social">
				   <ul>
				   <!-- facebook -->
					   <li> <a class="facebook" href="#" target="blank"><i style="color: white;" class="fa-brands fa-facebook-f"></i></a></li>
		  
				   <!-- twitter -->
					   <li> <a class="twitter" href="#" target="blank"><i style="color: white;" class="fa-brands fa-twitter"></i></a></li>
		  
				   <!-- google plus -->
					   <li> <a class="googleplus" href="#" target="blank"><i style="color: white;" class="fa-brands fa-google-plus"></i></a></li>
				 </ul>
			 </div>
		  </div>
						   
		  <div class="bottom-text w3_bottom_text">
				<p>No account yet?<a href="${classpath }/signup">Signup</a></p>
				<h4> <a href="#">Forgot your password?</a></h4>
		  </div>
		  
							</div>
				 </div>
			
		  </div>
    </section>
    <!-- Section: Design Block -->
</main>

		
		<div class="scroll__top">
			<i class='bx bx-up-arrow-alt'></i>
		</div>

		<!-- mobile menu -->

	</div>

</body>

</html>