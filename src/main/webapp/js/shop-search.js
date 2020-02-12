function setPage(num) {
	$("form.sorting-form > input[name*='page_number']").val(num);
}

$('.category-button').click(
		function(v) {
			setPage(1);
			$("form.sorting-form > input[name*='category']").val(
					$(v.target).attr("value"));
			$("form.sorting-form").submit();
			return false;
		});

$('.name-input').change(function(v) {
	setPage(1);
	$("form.sorting-form > input[name*='name']").val($(v.target).val());
	$("form.sorting-form").submit();
});

$('.manufacturer-input').change(
		function(v) {
			setPage(1);
			$("form.sorting-form > input[name*='manufacturer']").val(
					$(v.target).children("option:selected").val());
			$("form.sorting-form").submit();
		});

$('.price-min-input').change(function(v) {
	setPage(1);
	$("form.sorting-form > input[name*='price_min']").val($(v.target).val());
	$("form.sorting-form").submit();
});

$('.price-max-input').change(function(v) {
	setPage(1);
	$("form.sorting-form > input[name*='price_max']").val($(v.target).val());
	$("form.sorting-form").submit();
});

$('.items-on-page-input').change(
		function(v) {
			setPage(1);
			$("form.sorting-form > input[name*='items_on_page']").val(
					$(v.target).val());
			$("form.sorting-form").submit();
		});

$('.pagination-button:not(span)').click(function(v) {
	setPage($(v.target).attr("value"));
	$("form.sorting-form").submit();
	return false;
});

$('.order-button').click(function(v) {
	setPage(1);
	var oldSorting = $("form.sorting-form > input[name*='order_by']").val();
	var newSorting = $(v.target).attr("value");
	$("form.sorting-form > input[name*='order_by']").val(newSorting);
	var direction = 1;
	if (oldSorting == newSorting) {
		direction ^= $("form.sorting-form > input[name*='order_dir']").val();
	}
	$("form.sorting-form > input[name*='order_dir']").val(direction);
	$("form.sorting-form").submit();
	return false;
});
