<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Name</th>
				<th scope="col">Email</th>
				<th scope="col">Mobile</th>
				<th scope="col">Address</th>
				<th scope="col">Message</th>
				<th scope="col">Filename</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th scope="row">1</th>
				<td>${contact.txtName }</td>
				<td>${contact.txtEmail }</td>
				<td>${contact.txtMobile }</td>
				<td>${contact.txtAddress }</td>
				<td>${contact.txtMessage }</td>
				<td>${filename }</td>
				
			</tr>
			
		</tbody>
	</table>
</body>
</html>