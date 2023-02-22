$(function () {

    $("#buybtn").click(function () {
        const ord_quantity = $("quantity").val();
        const proid = $(".proid").val();
        $.ajax({
            method: "post",
            url: "/orders",
            data: {
                ord_quantity: ord_quantity,
                proid: proid
            },
            success: function () {

            },
            error: function () {

            }

        });
    });
});