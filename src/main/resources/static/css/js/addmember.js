
$(function (){
    let emailChk="";
    let emailCheckkingAnd=false;
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
        if(emailCheckkingAnd){
            $(".addmem_main_box").submit();
            return;
        }
        alert("이메일 체크가 되어있지 않습니다.");
    });

    $(".emailChk_btn").click(function (){
        const email=$("input[name=email]").val();
        if(email==null||email==""){
            alert("이메일을 입력하세요")
            return false;
        }
        ajaxCall("/members/email/checking","POST",{email},function (data){
            emailChk=data;
            alert("인증 코드가 전송되었습니다.");
        },function (){
            alert("이메일을 다시입력해주세요");
        },)

    });
    $(".emailSubmitBtn").click(function (){
        const number=$("input[name=emailChk]").val();
        if(number!=emailChk){
            alert("이메일 인증코드가 일치하지 않습니다");
            return;
        }
        $("input[name=emailChk]").attr("readonly",true);
        emailCheckkingAnd=true;
        alert("이메일 인증 성공하셨습니다");

    });
});
