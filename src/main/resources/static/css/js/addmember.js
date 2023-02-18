
$(function (){

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

$("#addmem_btn").click(function(){
    $(".addmem_main_box").submit();
});
});
