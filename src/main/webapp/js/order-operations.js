$('#accept-purchase-button').click(function() {
	var payType = $('#payment-type').val();
	var payRequisites = $('#pay-requisites').val();
	acceptPurchase(payType, payRequisites);
});

function setModalOrderNumber(id) {
	$('#order-number').html(id);
}

const proposeSignIn = function() {
	$('#unauthorized').modal({
		backdrop : 'static',
		keyboard : false
	});
}

const showErrorModal = function() {
	$('#errorModal').modal();
}

const showCartEmpty = function() {
	$('#cartEmpty').modal();
}

const actions = {
	'400' : showCartEmpty,
	'422' : proposeSignIn,
	'500' : showErrorModal
}

function acceptPurchase(payType, payRequisites) {
	$.ajax({
		url : 'orders',
		type : 'PUT',
		contentType : 'application/x-www-form-urlencoded',
		data : {
			pay_type : payType,
			pay_requisites : payRequisites
		},
		success : function(data) {
			setModalOrderNumber(data);
			$('#successOrderCreation').modal({
				backdrop : 'static',
				keyboard : false
			});
		},
		error : function(xhr, status, error) {
			actions[xhr.status]();
		}
	});
}
