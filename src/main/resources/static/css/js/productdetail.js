$(function () {
    const opt1val = opt[0].opt1;
    const proid = $(".proid").val();

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

    $.ajax({
        method: "get",
        url: "/orders/favorite/찜하기",
        data: {
            proid: proid,
        },
        success: function (data) {
            console.log(data);
            if (data != null && data != '') {
                $(".favo").css("color", "red");
                $("#favoritebtn").val(data.ordid);
            }
        },
        error: function () {
        }

    });

    $("#firebtn").click(function () {
        $(".detail_modal_con").css("display", "flex");
    });
    $(document).mouseup(function (e) {
        if ($(".detail_modal_con").has(e.target).length === 0) {
            $(".detail_modal_con").hide();
        }
    });
    $(document).keydown(function (e) {
        let code = e.keyCode || e.which;
        if (code == 27) { // 27은 ESC 키번호
            $('.detail_modal_con').hide();
        }

    });

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
        const optid = $(".opt2").val();
        const ordchk = "구매";
        $.ajax({
            method: "post",
            url: "/orders",
            data: {
                ordquantity: ordquantity,
                proid: proid,
                optid: optid,
                ordchk: ordchk

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
        const optid = $(".opt2").val();
        const ordchk = "장바구니";
        $.ajax({
            method: "get",
            url: "/orders/favorite/"+ordchk,
            data: {
                proid: proid,
            },
            success: function (data) {
                if (data == null || data == '') {
                    $.ajax({
                        method: "post",
                        url: "/orders",
                        data: {
                            ordquantity: ordquantity,
                            proid: proid,
                            optid: optid,
                            ordchk: ordchk

                        },
                        success: function () {
                            alert("장바구니 추가완료");
                        },
                        error: function () {

                        }

                    });
                }else{
                    alert(data);
                }
            },
            error: function () {
            },

        });


    });
    $("#favoritebtn").click(function () {
        const ordid = $(this).val();
        const ordchk = "찜하기";
        if (ordid == null || ordid == '') {
            $.ajax({
                method: "post",
                url: "/orders",
                data: {
                    proid: proid,
                    ordchk: ordchk

                },
                success: function (data) {
                    alert("찜 완료");
                    $(".favo").css("color", "red");
                    $("#favoritebtn").val(data);
                },
                error: function () {

                }

            });
        } else {
            console.log(ordid);
            $.ajax({
                method: "delete",
                url: "/orders/favorite",
                data: {
                    ordid: ordid
                },
                success: function () {
                    alert("찜하기 취소");
                    $(".favo").css("color", "black");
                    $("#favoritebtn").val('');
                },
                error: function () {

                }

            });
        }

    });
});