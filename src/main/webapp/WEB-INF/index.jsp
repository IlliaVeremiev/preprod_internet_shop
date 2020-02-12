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

	<section id="home-section" class="hero">
		<div class="home-slider owl-carousel">
			<div class="slider-item js-fullheight">
				<div class="overlay"></div>
				<div class="container-fluid p-0">
					<div class="row d-md-flex no-gutters slider-text align-items-center justify-content-end"
						data-scrollax-parent="true">
						<img class="one-third order-md-last img-fluid" src="images/bg_1.png" alt="">
						<div class="one-forth d-flex align-items-center ftco-animate"
							data-scrollax=" properties: { translateY: '70%' }">
							<div class="text">
								<span class="subheading">#New Arrival</span>
								<div class="horizontal">
									<h1 class="mb-4 mt-3">New mouse</h1>
									<p class="mb-4">There’s a new Razer Mamba in town.
										Actually, make that two new Razer Mambas. Razer’s big new gear
										for E3 2015 is a revamped Chroma Mamba, with the original’s
										wireless/wired functionality, plus a wired Tournament Edition
										that also fits into the Chroma line of RGB LED peripherals.</p>
									<p>
										<a href="#" class="btn-custom">Discover Now</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="slider-item js-fullheight">
				<div class="overlay"></div>
				<div class="container-fluid p-0">
					<div class="row d-flex no-gutters slider-text align-items-center justify-content-end"
						data-scrollax-parent="true">
						<img class="one-third order-md-last img-fluid" src="images/bg_2.png" alt="">
						<div class="one-forth d-flex align-items-center ftco-animate"
							data-scrollax=" properties: { translateY: '70%' }">
							<div class="text">
								<span class="subheading">#New Arrival</span>
								<div class="horizontal">
									<h1 class="mb-4 mt-3">New keyboard</h1>
									<p class="mb-4">Apex 150 delivers everything you need for
										competitive gaming at a competitive price. SteelSeries Quick
										Tension switches, 5-zone RGB, Discord notifications and splash
										resistance combine for the best value in gaming.</p>
									<p>
										<a href="#" class="btn-custom">Discover Now</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section ftco-no-pt ftco-no-pb">
		<div class="container">
			<div class="row no-gutters ftco-services">
				<div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services p-4 py-md-5">
						<div class="icon d-flex justify-content-center align-items-center mb-4">
							<span class="flaticon-bag"></span>
						</div>
						<div class="media-body">
							<h3 class="heading">Free Delivery</h3>
							<p>No need to pay for delivery when purchasing more than 100</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services p-4 py-md-5">
						<div class="icon d-flex justify-content-center align-items-center mb-4">
							<span class="flaticon-customer-service"></span>
						</div>
						<div class="media-body">
							<h3 class="heading">Support Customer</h3>
							<p>Support is always in touch</p>
						</div>
					</div>
				</div>
				<div class="col-lg-4 text-center d-flex align-self-stretch ftco-animate">
					<div class="media block-6 services p-4 py-md-5">
						<div class="icon d-flex justify-content-center align-items-center mb-4">
							<span class="flaticon-payment-security"></span>
						</div>
						<div class="media-body">
							<h3 class="heading">Secure Payments</h3>
							<p>Pay by any means</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>