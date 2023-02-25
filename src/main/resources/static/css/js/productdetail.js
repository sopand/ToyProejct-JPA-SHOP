$(function () {
    const opt1val=opt[0].opt1;
    const opt2val=$("select[name=opt2]").val();
    if(opt1val!=null){
        let html="";
        $(opt).each(function (index, item) {
           let firstOpt2=$(this).val();
            if(opt1val==firstOpt2){
                html+=`<option></option>`;
            }
        });

    }
    $(".opt1").change(function () {
        const optChk = $(this).val();
        if (opt[0].opt1 != null) {
            $(opt).each(function (index, item) {
                $()
            });
        }else{

        }

    });

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