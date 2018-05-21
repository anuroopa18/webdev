(function () {
    var usernameFld, passwordFld;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('.btn').click(login);
    }
    function login() {
        if($('#username').val().length ==0){
            alert('Please provide a valid username');
            return;
        }
        else{
            usernameFld= $('#username').val();
        }
        if($('#password').val().length == 0){
            alert('Please provide a password');
            return;
        }
        else{
            passwordFld= $('#password').val();
        }

        var loginUser = {
            username: usernameFld,
            password: passwordFld

        }
        userService.login(loginUser).then(function(response) {
                 window.location.href = "/jquery/components/profile/profile.template.client.html";
        }).catch(function () {
            alert('User does not exist!');
        });

    }
})();
