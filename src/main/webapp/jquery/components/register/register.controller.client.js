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
            userService.register(registerUser).then(function(response) {
                if(response.status == 401)
                {
                    alert('User already exists!');
                }
                else
                {
                    window.location.href = "/jquery/components/profile/profile.template.client.html?username="
                        + response.username + "&userId=" + response.id;
                }
            });
        }



    }
})();
