function getAllTableRows(){
	return $('.cart-items-table').find('tr:not(thead > tr)');
}

function calculateTotalPrice() {
	var total = 0.0;
	$(getAllTableRows()).each(
			function(i, e) {
				var price = parseFloat($(e).find('.total-product-price').html()
						.trim());
				total += price;
			});
	return total.toFixed(2);
}

function setTableData(container, data, count) {
	$(container).find('.count-label').html(count);
	var currencyFormat = parseFloat(data).toFixed(2);
	$(container).find('td > span.total-product-price').html(currencyFormat);
}

function getId(target) {
	return $(target).closest('.data-container').find('.data[name*="id"]').val();
}

function getCount(container) {
	return parseInt($(container).find('.count-label').html().trim());
}

$('.increment-item').click(function(v) {
	var container = $(v.target).closest('tr');
	var id = getId(v.target);
	var count = getCount(container);
	setProductCount(id, count + 1, container);
	return false;
});

$('.decrement-item').click(function(v) {
	var container = $(v.target).closest('tr');
	var id = getId(v.target);
	var count = getCount(container);
	setProductCount(id, count - 1, container);
	return false;
});

$('.remove-item').click(function(v) {
	var container = $(v.target).closest('tr');
	var id = getId(v.target);
	removeProduct(id, container);
	return false;
});

function setProductCount(id, count, container) {
	$.ajax({
		url : 'cart',
		type : 'PUT',
		contentType : 'application/x-www-form-urlencoded',
		data : {
			product_id : id,
			products_count : count
		},
		success : function(data) {
			setTableData(container, data, count);
			setTotalPrice(calculateTotalPrice());
		},
		error : function(xhr, status, error) {
			showErrorModal();
		}
	});
}

function removeProduct(id, container) {
	$.ajax({
		url : 'cart',
		type : 'DELETE',
		contentType : 'application/x-www-form-urlencoded',
		data : {
			action : 'one',
			product_id : id
		},
		success : function(data) {
			$(container).remove();
			setTotalPrice(calculateTotalPrice());
		},
		error : function(xhr, status, error) {
			showErrorModal();
		}
	});
}

$('#clear-all-button').click(function() {
	removeAllProducts();
});

function removeAllProducts() {
	$.ajax({
		url : 'cart',
		type : 'DELETE',
		contentType : 'application/x-www-form-urlencoded',
		data : {
			action : 'all'
		},
		success : function(data) {
			clearProductsTable();
		},
		error : function(xhr, status, error) {
			showErrorModal();
		}
	});
}

function clearProductsTable() {
	$('.cart-items-table').find('tbody').html('');
	setTotalPrice(0);
}

function setTotalPrice(price){
	$('.total-price-value').html(price);
}

function showErrorModal() {
	$('#errorModal').modal();
}

$('#buy-all-button').click(function() {
	purchaseAll();
});

function purchaseAll() {
	$.ajax({
		url : 'orders',
		type : 'POST',
		contentType : 'application/x-www-form-urlencoded',
		success : function(data) {
			acceptAndRedirect();
		},
		error : function(xhr, status, error) {
			proposeSignIn();
		}
	});
}

const proposeSignIn = function() {
	$('#unauthorized').modal();
}

const acceptAndRedirect = function() {
	window.location = 'orders';
}
