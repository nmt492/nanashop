<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${classpath }/backend/assets/images/favicon.png">
    <title>${title }</title>
    
    <!-- variables -->
    <jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
    
    <!-- Custome css resource file -->
    <jsp:include page="/WEB-INF/views/backend/layout/css.jsp"></jsp:include>
    
</head>

<body>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- Topbar header - style you can find in pages.scss -->
        <jsp:include page="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
        <!-- End Topbar header -->
       
        
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
        	<!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <h2 class="page-title text-truncate text-dark font-weight-medium mb-1">List Sale Order</h2>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <!-- basic table -->
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                        	
                            <div class="card-body">
	                        	<form action="${classpath }/admin/order" method="get">
		                            <div class="table-responsive">
	                                		
										<!-- Tìm kiếm -->
										<div class="row">
											<div class="col-md-2">
												<div class="form-group mb-4">
													<!-- 
													<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<input type="checkbox" class="form-check-input" id="status" name="status" checked="checked" />
			                                        <label for="status">Active</label>
			                                         -->
			                                        <select class="form-control"
														id="status" name="status">
															<option value="2">All</option>
															<option value="1">Đã giao hàng</option>
															<option value="0">Chưa giao hàng</option>
													</select>
												</div>
											</div>
											
											
											<div class="col-md-2">
												<input class="form-control" type="date" 
													id="beginDate" name="beginDate"/>		
											</div>
											<div class="col-md-2">
												<input class="form-control"
																type="date" id="endDate" name="endDate" />		
											</div>
											
											<div class="col-md-3">
												<input type="text" class="form-control" id="keyword"
														name="keyword" placeholder="Search keyword" />		
											</div>
											
											<div class="col-md-1">
												<button type="submit" id="btnSearch" name="btnSearch" class="btn btn-primary">Search</button>
											</div>
										</div>
										<!-- Hết tìm kiếm -->
	                                    <table id="zero_config" class="table table-striped table-bordered no-wrap">
	                                        <thead>
	                                            <tr align="center">
	                                            	<th scope="col">No.</th>
	                                                <th scope="col">Code</th>
	                                                <th scope="col">Customer</th>
	                                                <th scope="col">Mobile</th>
	                                                <th scope="col">Address</th>
	                                                <th scope="col">Payment</th>
	                                                <th scope="col">Create by</th>
	                                                <th scope="col">Create date</th>
	                                                <th scope="col">Delivery date</th>
	                                                <th scope="col">Status</th>
	                                                <th scope="col">Actions</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="saleOrder" items="${saleOrders }" varStatus="loop">
	                                            <tr>
	                                            	<th scope="row">${loop.index + 1 }</th>
	                                                <td>${saleOrder.code }</td>
	                                                <td>${saleOrder.customerName }</td>
	                                                <td>${saleOrder.customerMobile }</td>
	                                                <td >
	                                                	${saleOrder.customerAddress }
	                                                </td>
	                                                <td>${saleOrder.total }</td>	                                                
	                                                <td>${saleOrder.userCreateSaleOrder.username }</td>
	                                                
	                                                <td>
	                                                	<fmt:formatDate value="${saleOrder.createDate }" pattern="dd-MM-yyyy"/>
	                                                </td>
	                                                <td></td>
	                                                
	                                                <td>
	                                                	<span id="_product_status_${saleOrder.id }">
	                                                		<c:choose>
	                                                			<c:when test="${saleOrder.status }">
	                                                				<span>Đã giao hàng</span>
	                                                			</c:when>
	                                                			<c:otherwise>
	                                                				<span>Chưa giao hàng</span>
	                                                			</c:otherwise>
	                                                		</c:choose>
	                                                	</span>
	                                                	
	                                                </td>
	                                               
	                                                <td>
	                                                	<a href="${classpath }/admin/product-edit/${product.id }" role="button" 
	                                                							class="btn btn-primary">Edit</a>
	                                                	<a href="${classpath }/admin/product-delete/${product.id }" role="button" 
	                                                							class="btn btn-secondary">Delete</a>
	                                                </td>
	                                            </tr>
	                                            </c:forEach>
	                                        </tbody>
	                                        <tfoot>
	                                            <tr align="center">
	                                                <th scope="col">No.</th>
	                                                <th scope="col">Code</th>
	                                                <th scope="col">Customer</th>
	                                                <th scope="col">Mobile</th>
	                                                <th scope="col">Address</th>
	                                                <th scope="col">Payment</th>
	                                                <th scope="col">Create by</th>
	                                                <th scope="col">Create date</th>
	                                                <th scope="col">Delivery date</th>
	                                                <th scope="col">Status</th>
	                                                <th scope="col">Actions</th>
	                                            </tr>
	                                        </tfoot>
	                                    </table>
	                                   
											
		                                    <div class="col-md-6">
			                                    <div class="pagination float-right">
			                                    	<div id="paging"></div>
			                                    </div>
		                                    </div>
		                                  </div>
		                            </div>	                           
	                        	</form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    
	<!-- Slider js: All Jquery-->
    <jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
    
 	<!-- pagination -->
	<script type="text/javascript">
		$( document ).ready(function() {
			//Dat gia tri cua status ung voi dieu kien search truoc do
			$("#status").val(${productSearch.status});
			$("#categoryId").val(${productSearch.categoryId});
			
			$("#paging").pagination({
				currentPage: ${productSearch.currentPage}, //Trang hien tai
				items: ${productSearch.totalItems}, //Tong so san pham (total products)
				itemsOnPage: ${productSearch.sizeOfPage},
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					$('#page').val(pageNumber);
					$('#btnSearch').trigger('click');
				},
			});
		});
	</script>
</body>

</html>