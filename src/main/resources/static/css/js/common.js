

    function ajaxCall(url,method,param,successFn,errorFn){
        $.ajax({
            url: url,
            method: method,
            data: param,
            success: function (data) {
               if(typeof successFn =="function"){
                   successFn(data);
               }
            },
            error: function () {
                if(typeof errorFn =="function"){
                    errorFn();
                }
            }
        });
    };
    function nullChk(chk){
        if(chk==null||chk==""){
            return true;
        }
        return false;
    }
    function  notNull(chk){
        if(chk!=null && chk!=""){
            return true;
        }
        return false;
    }
