<%@ tag language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="user" required="true" rtexprvalue="true"
	type="com.epam.preprod.veremiev.task11.model.entities.User"%>

<c:choose>
	<c:when test="${user != null }">
		<div class="d-flex">
			<div class="mr-2">
				<c:choose>
					<c:when test="${user.photo != null}">
						<img class="img-fluid header-user-photo" alt="user icon"
							src="${applicationScope['user.icon.path']}${user.photo}">
					</c:when>
					<c:otherwise>
						<img class="img-fluid header-user-photo" alt="user icon"
							src="${applicationScope['user.icon.default']}">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="header-user-login">
				<span class="text align-middle">${user.login}</span>
			</div>
			<div>
				<form action="logout" method="post" class="billing-form" novalidate>
					<button class="btn btn-primary py-1 px-4 mt-1 ml-2">Logout</button>
				</form>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="d-flex mt-1">
			<form action="login" method="post" class="billing-form"
				id="login-form" novalidate>
				<label for="login" class="text-white">Login</label> <input
					type="text" class="col-xs-2" name="login" placeholder=""
					value="${user_info.login}"> <label for="password"
					class="text-white">Password</label> <input type="password"
					name="password" placeholder="">
				<button class="btn btn-primary py-1 px-4">Login</button>
			</form>
		</div>
	</c:otherwise>
</c:choose>


