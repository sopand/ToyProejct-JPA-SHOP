$(function () {
    $(".logout_href").click(function () {
        let f = document.createElement('form')
        const value = $(this).val();
        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', 'email');
        obj.setAttribute('value', value);

        f.appendChild(obj);
        f.setAttribute('method', 'post');
        f.setAttribute('action', '/members/logout');
        document.body.appendChild(f);
        f.submit();


    });
});