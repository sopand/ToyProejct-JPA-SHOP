$(function () {
    const opt1val = opt[0].opt1;
    if (opt1val != null) {
        let html = "";
        $(opt).each(function (index, item) {
            let firstOpt2 = $(item);
            if (opt1val == firstOpt2[0].opt1) {
                html += `<option value="${firstOpt2[0].optid}">${firstOpt2[0].opt2}</option>`;
            }
        });
        $(".opt2").html(html);
    }
    $(".opt1").change(function () {
        const optChk = $(this).val();
        let html = "";
        $(opt).each(function (index, item) {
            let value = $(item);
            if (value[0].opt1 == optChk) {
                html += `<option value="${value[0].optid}">${value[0].opt2}</option>`;
            }
        })
        $(".opt2").html(html);


    });

    $("#buybtn").click(function () {
        const ordquantity = $(".quantity").val();
        const proid = $(".proid").val();
        const optid = $(".opt2").val();
        const ordchk= "구매";
        $.ajax({
            method: "post",
            url: "/orders",
            data: {
                ordquantity: ordquantity,
                proid: proid,
                optid:optid,
                ordchk:ordchk

            },
            success: function () {
                    alert("구매완료");
            },
            error: function () {

            }

        });
    });
    $("#cartbtn").click(function () {
        const ordquantity = $(".quantity").val();
        const proid = $(".proid").val();
        const optid = $(".opt2").val();
        const ordchk= "구매";
        $.ajax({
            method: "post",
            url: "/orders",
            data: {
                ordquantity: ordquantity,
                proid: proid,
                optid:optid,
                ordchk:ordchk

            },
            success: function () {
                alert("장바구니 추가완료");
            },
            error: function () {

            }

        });
    });
});