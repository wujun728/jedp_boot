jQuery(document).ready(function () {

    $('.page-container .divForm button').click(function () {
        var form = $('.page-container .divForm');
            var username = form.find('.username').val();
            var password = form.find('.password').val();
            if (username == '') {
                form.find('.error').fadeOut('fast', function () {
                    $(this).css('top', '27px');
                });
                form.find('.error').fadeIn('fast', function () {
                    $(this).parent().find('.username').focus();
                });
                return ;
            }
            if (password == '') {
                form.find('.error').fadeOut('fast', function () {
                    $(this).css('top', '96px');
                });
                form.find('.error').fadeIn('fast', function () {
                    $(this).parent().find('.password').focus();
                });
                return ;
            }
            $.post("acc/login", {'username': username, 'password': password},function (data,a,c) {
                if(data.stautsCode == 1000000){
                    window.location.href = '';
                }else {
                    form.find('.message').html(data.stautsMessage);
                    form.find('.message').show();
                }
            }, "json");
        }
    );
    $('.page-container .divForm .username, .page-container .divForm .password').keyup(function () {
        $(this).parent().find('.error').fadeOut('fast');
        $(this).parent().find('.message').fadeOut('fast');
    });

});
