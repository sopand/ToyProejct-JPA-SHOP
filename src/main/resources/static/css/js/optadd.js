$(function () {


    $('.optbtn').click(function () {
        $('#opt1').css("display", "flex");
        $("#opt2").css("display", "none");

    });
    $('.optbtn2').click(function () {
        $('#opt2').css("display", "flex");
        $("#opt1").css("display", "none");
    });

    $(".optadd").click(function () {
        let opt1 ;
        let opt2 = [];
        let optquantity = [];
        const proid=$('input[name=proid]').val();
            if($('input[name=opt1]').val()!=''){
                opt1=$('input[name=opt1]').val();
            }

        $("input[name=opt2]").each(function(index, item){
            if($(item).val()!=''){
                opt2.push($(item).val());
            }
        });
        $("input[name=optquantity]").each(function(index, item){
            if($(item).val()!=''){
                optquantity.push($(item).val());
            }
        });
        console.log(opt1);
        console.log(opt2);
        $.ajax({
            url: "/products/option",
            method: "POST",
            data:{
                proid:proid,
                opt1:opt1,
                opt2:opt2,
                optquantity:optquantity
            },
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("데이터 통신중 에러발생");
            }
        });


    });
});
