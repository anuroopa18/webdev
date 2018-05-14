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

        if(passwordFld != passwordFld){
           alert('Passwords doesnt ')
        }

        var registerUser = {
            username: usernameFld,
            password: passwordFld

        }
        userService.register(registerUser);


    }
})();
