(function () {
    var usernameFld, passwordFld;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('.btn').click(login);
    }
    function login() {
        usernameFld= $('#username').val();
        passwordFld= $('#password').val();
        var loginUser = {
            username: usernameFld,
            password: passwordFld

        }
        userService.login(loginUser);

    }
})();