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
        const proId=$('input[name=proId]').val();
            if(notNull($('input[name=opt1]').val())){
                opt1=$('input[name=opt1]').val();
            }
        $("input[name=opt2]").each(function(index, item){
            if(notNull($(item).val())){
                opt2.push($(item).val());
            }
        });
        $("input[name=optquantity]").each(function(index, item){
            if(notNull($(item).val())){
                optquantity.push($(item).val());
            }
        });
        ajaxCall("/products/option","POST",{
            proId:proId,
            opt1:opt1,
            opt2:opt2,
            optquantity:optquantity
        },function (data) {
            alert(data);
        }, function () {
            alert("데이터 통신중 에러발생");
        });

    });
});
