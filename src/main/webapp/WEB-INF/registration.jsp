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
					<h1 class="mb-0 bread">Registration</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-10 ftco-animate">


					<form action="registration" method="post" class="billing-form"
						enctype="multipart/form-data" id="registration-form" novalidate>
						<h3 class="mb-4 billing-heading">Enter your personal data</h3>

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

							<div class="w-100"></div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="firstname">Firt Name</label> <input type="text"
										class="form-control" id="name" placeholder="" name="name"
										value="${user_info.name}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="lastname">Last Name</label> <input type="text"
										class="form-control" id="surname" placeholder=""
										name="surname" value="${user_info.surname}">
								</div>
							</div>
							<div class="w-100"></div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="login">Login</label> <input type="text"
										class="form-control" id="login" name="login" placeholder=""
										value="${user_info.login}">
									<div class="invalid-feedback">Login may consist of 4
										Latin characters, numbers, '.' or '_'</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="email">Email Address</label> <input type="text"
										class="form-control" id="email" name="email" placeholder=""
										value="${user_info.email}">
									<div class="invalid-feedback">Enter valid email</div>
								</div>
							</div>
							<div class="w-100"></div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" id="password" name="password"
										placeholder="">
									<div class="invalid-feedback">Password must be at least 8
										characters, contain lowercase, uppercase characters and
										numbers</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="password">RePassword</label> <input type="password"
										class="form-control" id="repassword" name="repassword"
										placeholder="">
									<div class="invalid-feedback">Both password must be the
										same</div>
								</div>
							</div>
							<div class="w-100"></div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="country">State / Country</label>
									<div class="select-wrap">
										<div class="icon">
											<span class="ion-ios-arrow-down"></span>
										</div>
										<select name="" id="" class="form-control">
											<option value="">Ukraine</option>
											<option value="">Russia</option>
											<option value="">Belarus</option>
											<option value="">Romania</option>
										</select>
									</div>
								</div>
							</div>
							<div class="w-100"></div>
							<div class="col-md-12">
								<div class="form-group">
									<div class="input-group">
										<div class="custom-file">
											<input type="file" class="custom-file-input"
												accept="image/x-png,image/gif,image/jpeg" id="photo"
												name="photo"> <label class="custom-file-label"
												for="photo">Choose your photo</label>
										</div>
									</div>
									<label for="photo">Photo</label>
								</div>
							</div>


							<div class="w-100"></div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="captcha">Enter symbols what you see</label> <input
										type="text" class="form-control" placeholder="" name="captcha">
								</div>
							</div>
							<div class="col-md-6 align-self-end">
								<div class="form-group text-center">
									<div class="form-control">
										<ex:captchadisplay captcha="${ captcha }" id="${captcha_id}" />
									</div>
								</div>
							</div>

							<div class="w-100"></div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" id="terms"
									name="accept-terms" required> <label
									class="form-check-label" for="terms"> Agree to terms
									and conditions </label>
								<div class="invalid-feedback">You must agree before
									submitting.</div>
							</div>
							<div class="w-100"></div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox"
									id="send-email-new-products" value="send-email-new-products"
									name="mailings" required checked> <label
									class="form-check-label" for="terms"> Send me on email
									information about new products </label>
							</div>
							<div class="w-100"></div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox"
									id="send-email-sales" value="send-email-sales" name="mailings"
									required checked> <label class="form-check-label"
									for="terms"> Send me on email information about
									discounts and promotions</label>
							</div>
							<div class="w-100"></div>
							<div class="col-md-12">
								<div class="form-group mt-4">
									<div class="radio">
										<label class="mr-3">
											<button class="btn btn-primary py-3 px-4" id="confirm">Registrate</button>
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
	<script type="text/javascript"
		src="js/registration-validation/registration-form-validation.js"></script>
	<script type="text/javascript"
		src="js/registration-validation/registration-form-handlers.js"></script>
	<script type="text/javascript"
		src="js/registration-validation/registration-form.js"></script>

</body>

</html>