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
					<h1 class="mb-0 bread">Login</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-10 ftco-animate">


					<form action="login" method="post" class="billing-form"
						id="login-form" novalidate>
						<input type="hidden" name="redirect" value="${param.redirect}">
						<h3 class="mb-4 billing-heading">Enter your user data</h3>

						<div class="row align-items-start">
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
							<div class="col-md-6">
								<div class="form-group">
									<label for="login">Login</label> <input type="text"
										class="form-control" id="login" name="login" placeholder=""
										value="${user_info.login}">

								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" id="password" name="password"
										placeholder="">

								</div>
							</div>
							<div class="w-100"></div>
							<div class="col-md-12">
								<div class="form-group mt-4">
									<div class="radio">
										<label class="mr-3">
											<button class="btn btn-primary py-3 px-4" id="confirm">Login</button>
										</label>
									</div>
								</div>
							</div>
						</div>
					</form>
					<!-- END -->

				</div>
				<!-- .col-md-8 -->
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>

</html>