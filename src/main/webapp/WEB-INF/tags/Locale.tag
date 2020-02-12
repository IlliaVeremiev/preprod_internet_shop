<%@ tag language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="locales" required="true" rtexprvalue="true"
	type="java.util.List"%>
<%@ attribute name="current" required="true" rtexprvalue="true"
	type="java.lang.String"%>


<div class="d-flex mt-1">
	<select class="custom-select custom-select-sm" id="localeSelect">
		<c:forEach items="${locales}" var="locale">
			<option ${current eq locale ? "selected" : "" }>${locale}</option>
		</c:forEach>
	</select>
	<script type="text/javascript" src="js/localeSelect.js"></script>
</div>
