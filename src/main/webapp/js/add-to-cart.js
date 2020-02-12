$('.add-to-cart-button').click(function(v) {
    var id = $(v.target).closest('.add-to-cart-button').attr("value");
    addToCart(id);
    return false;
});

function addToCart(id) {
    $.ajax({
        url: 'cart',
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded',
        data: {
            product_id: id
        },
        success: function(data) {
            $('.modal-product-name').html($('a.name-link[value*="' + id + '"]').html());
            $('.modal-product-count').html(data);
            $('#exampleModalCenter').modal();
        }
    });
}
