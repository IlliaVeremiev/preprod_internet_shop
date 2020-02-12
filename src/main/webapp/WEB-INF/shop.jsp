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
					<h1 class="mb-0 bread">
						<fmt:message key="shop.shop" />
					</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-lg-10 order-md-last">
					<c:choose>
						<c:when test="${fn:length(products_list) gt 0}">
							<div class="row">
								<c:if test="${ errors != null }">
									<div class="col-md-12">
										<c:forEach items="${errors.iterator()}" var="error">
											<div class="form-group">
												<div class="alert alert-danger">
													<strong>${error.header}</strong> ${error.message}
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<c:forEach items="${ products_list }" var="product"
									varStatus="loop">
									<div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex">
										<div class="product d-flex flex-column">
											<a href="#${ product.id }" class="img-prod"><img
												class="img-fluid"
												src="${ applicationScope['products.photo.path'] }${ product.photo }"
												alt="Colorlib Template">
												<div class="overlay"></div> </a>
											<div class="text py-3 pb-4 px-3">
												<div class="d-flex">
													<div class="cat">
														<span>${ product.category.name }</span>
													</div>
													<div class="rating">
														<p class="text-right mb-0">
															<a href="#"><span class="ion-ios-star-outline"></span></a>
															<a href="#"><span class="ion-ios-star-outline"></span></a>
															<a href="#"><span class="ion-ios-star-outline"></span></a>
															<a href="#"><span class="ion-ios-star-outline"></span></a>
															<a href="#"><span class="ion-ios-star-outline"></span></a>
														</p>
													</div>
												</div>
												<h3>
													<a class="name-link" href="#" value="${product.id}">${ product.manufacturer.name }
														${ product.title }</a>
												</h3>
												<div class="pricing">
													<p class="price">
														<span>${ product.price }</span>
													</p>
												</div>
												<p class="bottom-area d-flex px-3">
													<a href="#"
														class="add-to-cart text-center py-2 mr-1 add-to-cart-button"
														value="${product.id}"><span><fmt:message key="shop.add_to_cart" /> <i
															class="ion-ios-add ml-1"></i>
													</span></a> <a href="#" class="buy-now text-center py-2"><fmt:message key="shop.buy_now" /><span><i
															class="ion-ios-cart ml-1"></i></span></a>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<pd:pagination current="${page_number}" max="${pages_count}" />
						</c:when>
						<c:otherwise>
							<div class="col-md-12">

								<div class="form-group">
									<div class="alert alert-primary" role="alert"><fmt:message key="shop.no_items_found" /></div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-4 col-lg-2">
					<div class="sidebar">

						<form class="sorting-form" action="">
							<input type="hidden" name="items_on_page"
								value="${empty param.items_on_page ? 10 : param.items_on_page}">
							<input type="hidden" name="manufacturer"
								value="${param.manufacturer}"> <input type="hidden"
								name="page_number" value="${param.page_number}"> <input
								type="hidden" name="price_min" value="${param.price_min}">
							<input type="hidden" name="price_max" value="${param.price_max}">
							<input type="hidden" name="order_dir" value="${param.order_dir}">
							<input type="hidden" name="category" value="${param.category}">
							<input type="hidden" name="order_by" value="${param.order_by}">
							<input type="hidden" name="name" value="${param.name}">
						</form>


						<div class="sidebar-box-2">
							<h2 class="heading"><fmt:message key="shop.settings" /></h2>
							<div class="row">

								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.items_on_page" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <input
												class="items-on-page-input form-control" type="number"
												name="items_on_page"
												value="${empty param.items_on_page ? 10 : param.items_on_page}">
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="sidebar-box-2">
							<h2 class="heading"><fmt:message key="shop.ordering" /></h2>
							<div class="row">

								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.by" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <a class="order-button"
												href="#" value="name"><fmt:message key="shop.name" /></a>
										</div>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <a class="order-button"
												href="#" value="price"><fmt:message key="shop.price" /></a>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="sidebar-box-2">
							<h2 class="heading"><fmt:message key="shop.select_by" /></h2>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.name_list" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <input
												class="name-input form-control" type="text" name="name"
												value="${param.name}">
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.brand_list" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <select
												class="form-control manufacturer-input">
												<option value="">All</option>
												<c:forEach items="${manufacturers}" var="manuf">
													<option value="${manuf.id}"
														${param.manufacturer eq manuf.id ? "selected" : ""}>
														${manuf.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.min_price_list" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <input
												class="price-min-input form-control" type="number"
												name="price_min" value="${param.price_min}">
										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="guests"><fmt:message key="shop.max_price_list" /></label>
										<div class="form-field">
											<i class="icon icon-arrow-down3"></i> <input
												class="price-max-input form-control" type="number"
												name="price_max" value="${param.price_max}">
										</div>
									</div>
								</div>

							</div>
						</div>




						<div class="sidebar-box-2">
							<h2 class="heading"><fmt:message key="shop.categories" /></h2>
							<div class="fancy-collapse-panel">
								<div class="panel-group" id="accordion" role="tablist"
									aria-multiselectable="true">

									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingOne">
											<h4 class="panel-title">
												<!-- <button >All</button> -->
												<a href="#" class="category-button" value=""><fmt:message key="shop.all" /></a>
											</h4>
										</div>

									</div>

									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingOne">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapseOne" aria-expanded="true"
													aria-controls="collapseOne"><fmt:message key="shop.mouses" /> </a>
											</h4>
										</div>
										<div id="collapseOne" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingOne">
											<div class="panel-body">
												<ul>
													<li><a href="#" class="category-button" value="mouse"><fmt:message key="shop.all" /></a>
													</li>
												</ul>
											</div>
										</div>
									</div>

									<div class="panel panel-default">
										<div class="panel-heading" role="tab" id="headingTwo">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapseTwo" aria-expanded="false"
													aria-controls="collapseTwo"><fmt:message key="shop.keyboards" /></a>
											</h4>
										</div>
										<div id="collapseTwo" class="panel-collapse collapse"
											role="tabpanel" aria-labelledby="headingTwo">
											<div class="panel-body">
												<ul>
													<li><a href="#" class="category-button"
														value="keyboard"><fmt:message key="shop.all" /></a></li>
													<li><a href="#" class="category-button"
														value="mechanical keyboard"><fmt:message key="shop.mechanical" /></a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="shop.product_added" /></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<span class="modal-product-name"></span> x <span
						class="modal-product-count"></span> <span><fmt:message key="shop.now_in_your_cart" /></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal"><fmt:message key="shop.ok" /></button>
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
	<script type="text/javascript" src="js/shop-search.js"></script>
	<script type="text/javascript" src="js/add-to-cart.js"></script>

</body>

</html>