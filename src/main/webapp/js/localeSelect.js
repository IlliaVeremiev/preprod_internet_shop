$('#localeSelect').change(function(v) {
	var lang = $(v.target).val();
	document.location = addParameter("lang", lang);
});

function addParameter(parameter, value) {
	if (document.location.search.includes("lang")) {
		return document.location.href.replace(
				new RegExp(parameter + '=[^#^&]*'), parameter + "=" + value);
	} else {
		return document.location.href
				+ (document.location.href.includes('?') ? "&" : "?")
				+ parameter + "=" + value;
	}
}