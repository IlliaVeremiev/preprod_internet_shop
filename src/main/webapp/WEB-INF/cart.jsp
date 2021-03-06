<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
</head>

<body class="goto-here">
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div class="hero-wrap hero-bread"
		style="background-image: url('images/bg_6.jpg');">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<h1 class="mb-0 bread">Shop</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-lg-10 order-md-last">
					<c:choose>
						<c:when test="${fn:length(cart_items) gt 0}">
							<div class="row">
								<div>
									<div class="border border-dark mb-3">
										<table class="table mb-0 cart-items-table">
											<thead class="thead-dark">
												<tr>
													<th scope="col">#</th>
													<th scope="col">Product title</th>
													<th scope="col">Count</th>
													<th scope="col">Price</th>
													<th scope="col">Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cart_items}" var="item" varStatus="i">
													<tr>
														<th scope="row">${i.count}</th>
														<td>${item.key.title}</td>
														<td class="count-label">${item.value}</td>
														<td><span class="total-product-price">${item.value*item.key.price}</span><em
															class="text-secondary"> (${item.key.price})</em></td>
														<td>
															<div class="d-flex justify-content-center data-container">
																<input class="data" type="hidden" name="id"
																	value="${item.key.id}">
																<div class="count-controll align-self-center mr-3">
																	<div class="d-flex flex-column">
																		<a href="#" class="increment-item">&uarr;</a> <a
																			href="#" class="decrement-item">&darr;</a>
																	</div>
																</div>
																<div class="remove-controll align-self-center">
																	<a href="#" class="remove-item">&#10005;</a>
																</div>
															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="d-flex">
										<div class="mr-auto">
											<button class="btn btn-primary py-1 px-4"
												id="clear-all-button">Clear all</button>
										</div>
										<div>
											Total price: <span class="total-price-value"> <fmt:formatNumber
													type="number" value="${total_price}" /></span>
											<button class="btn btn-primary py-1 px-4" id="buy-all-button">Purchase</button>
										</div>
									</div>

								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-md-12">

								<div class="form-group">
									<div class="alert alert-primary" role="alert">No items
										found</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</section>

	<div class="modal fade" id="errorModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">Oh, oh..</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Something went wrong, please try again
					later</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="unauthorized" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">You need to
						login to continue shopping</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Please sign in to your account</div>
				<div class="modal-footer">
					<a type="button" class="btn btn-secondary" href="login">Login</a> <a
						type="button" class="btn btn-primary" href="registration">SingUp</a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>


	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-migrate-3.0.1.min.js"></script>
	<script type="text/javascript" src="js/popper.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/jquery.waypoints.min.js"></script>
	<script type="text/javascript" src="js/jquery.stellar.min.js"></script>
	<script type="text/javascript" src="js/owl.carousel.min.js"></script>
	<script type="text/javascript" src="js/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript" src="js/aos.js"></script>
	<script type="text/javascript" src="js/jquery.animateNumber.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="js/scrollax.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script type="text/javascript" src="js/google-map.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/cart-operations.js"></script>

</body>

</html>