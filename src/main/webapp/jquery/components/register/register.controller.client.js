(function () {
    var usernameFld, passwordFld, verifyPasswordFld;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('.btn').click(register);
    }

    function register() {
        usernameFld= $('#username').val();
        passwordFld= $('#password').val();
        verifyPasswordFld=$('#verifyPassword').val();

        if(passwordFld != verifyPasswordFld){
           alert('Passwords doesnt match');
        }
        else {
            var registerUser = {
                username: usernameFld,
                password: passwordFld

            }
            userService.register(registerUser);
        }



    }
})();
