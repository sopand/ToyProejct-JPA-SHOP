$(function () {


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

    $("#buybtn").click(function (){
        $(".cart_modal_con").css("display","flex");
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

    $(document).on('click',"#address_addbtn",function (){
        let ordidList =[];
        let quantityList=[];
        $("input[name=ord_chkbox]:checked").each(function (index,item){
           let id=$(item).val();
            ordidList.push(id);
            quantityList.push($("#"+id).val());
        });
        let ordaddress="";
        ordaddress+="(우편번호 : "+$(".addr1").val()+")";
        ordaddress+=$(".addr2").val();
        ordaddress+="상세 : "+$(".addr3").val();
        let ordhuname=$(".ord_huname").val();
        $.ajax({
           url:"/orders/cart",
           method:"PUT",
           data:{
               ordaddress:ordaddress,
               quantityList:quantityList,
               ordidList:ordidList,
               ordchk:"구매",
               ordhuname:ordhuname
           } ,
            success:function (data){
                    alert(data);
                    location.reload();
            },
            error:function (){
                alert("에러");
            }
        });

    });

    $("#removebtn").click(function (){
        let ordidList=[];
        $("input[name=ord_chkbox]:checked").each(function (index,item){
            ordidList.push($(item).val());
        });
        console.log(ordidList);
        $.ajax({
           url:"/orders/cart",
            method:"delete",
            data:{
                ordidList:ordidList
            },
            success :function (date){
                alert(date);
                location.reload();
            },
            error :function (){
               alert("삭제 실패");
            }
        });

    });

    $(".all_chkbox").click(function() {
        if($(".all_chkbox").is(":checked")) $("input[name=ord_chkbox]").prop("checked", true);
        else $("input[name=ord_chkbox]").prop("checked", false);
    });

    $("input[name=ord_chkbox]").click(function() {
        let total = $("input[name=ord_chkbox]").length;
        let checked = $("input[name=ord_chkbox]:checked").length;

        if(total != checked) $(".all_chkbox").prop("checked", false);
        else $(".all_chkbox").prop("checked", true);
    });
});