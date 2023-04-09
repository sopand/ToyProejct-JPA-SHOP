$(function () {
    const opt1val = opt[0].opt1;
    const proId = $(".proId").val();
    const email = $("input[name=email]").val();
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
    if (email != null && email != "") {
        $.ajax({
            method: "get",
            url: "/orders/favorite/찜하기",
            data: {
                proId: proId,
            },
            success: function (data) {
                if (data != null && data != '') {
                    $(".favo").css("color", "red");
                    $("#favoritebtn").val(data);
                }
            },
            error: function () {
            }

        });
    }


    $("#firebtn").click(function () {
        if (email == null || email == '') {
            alert("로그인 후 이용가능합니다");
            return;
        }
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
        if (email == null || email == "") {
            alert("로그인후 구매가능합니다.");
            return;
        }
        $(".cart_modal_con").css("display", "flex");
    });
    $(document).mouseup(function (e) {
        if ($(".cart_modal_con").has(e.target).length === 0) {
            $(".cart_modal_con").hide();
        }
    });
    $(document).keydown(function (e) {
        let code = e.keyCode || e.which;
        if (code == 27) { // 27은 ESC 키번호
            $('.cart_modal_con').hide();
        }
    });
    $(document).on("click", "#address_addbtn", function () {
        const ordquantity = $(".quantity").val();
        const optid = $(".opt2").val();
        const ordchk = "구매";
        let ordaddress = "";
        ordaddress += "(우편번호 : " + $(".addr1").val() + ")";
        ordaddress += $(".addr2").val();
        ordaddress += "  상세 주소 : " + $(".addr3").val();
        let ordhuname = $(".ord_huname").val();
        if ($(".addr1").val() == null || $(".addr1").val() == "") {
            alert("우편번호를 입력해주세요");
            return;
        }
        ajaxCall("/orders", "POST", {
            ordquantity, proId, optid, ordchk, ordaddress, ordhuname
        }, function () {
            alert("구매완료");
            $('.cart_modal_con').hide();
        }, function () {
            alert("구매 실패 구매수량이 재고량보다 많은 것 같습니다");
        });

    });
    $("#addPost").click(function () {
        new daum.Postcode({
            oncomplete: function (data) {
                let addr = ''; // 주소 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }


                $(".addr1").val(data.zonecode);
                $(".addr2").val(addr);
                $(".addr3").focus();
            }
        }).open();
    });


    $("#cartbtn").click(function () {
        const ordquantity = $(".quantity").val();
        const optid = $(".opt2").val();
        const ordchk = "장바구니";
        if (email == null || email == "") {
            alert("로그인 후 장바구니를 사용 가능 합니다");
            return;
        }
        ajaxCall("/orders/favorite/" + ordchk, "GET", {
            proId: proId,
        }, function (data) {
            if (data == null || data == '') {
                ajaxCall("/orders", "POST", {
                    ordquantity, proId, optid, ordchk
                }, function () {
                    alert("장바구니 추가완료");
                }, function () {
                    alert("장바구니 추가 실패");
                });
            } else {
                alert("이미 장바구니에 존재하는 상품입니다.");
            }
        }, function () {

        });


    });
    $("#favoritebtn").click(function () {
        const ordid = $(this).val();
        const ordchk = "찜하기";
        if (email == null || email == "") {
            alert("로그인 후 찜하기를 사용 가능 합니다");
            return;
        }
        if (ordid == null || ordid == '') {
            ajaxCall("/orders", "POST", {proId, ordchk},
                function (data) {
                    alert("찜 완료");
                    $(".favo").css("color", "red");
                    $("#favoritebtn").val(data);
                }, function () {

                });
        } else {
            ajaxCall("/orders/favorite", "DELETE", {ordid},
                function () {
                    alert("찜하기 취소");
                    $(".favo").css("color", "black");
                    $("#favoritebtn").val('');
                }, function () {

                });

        }

    });
});