(function () {
    var usernameFld, passwordFld, verifyPasswordFld;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('.btn').click(register);
    }

    function validatePassword (password) {
        var pwdReg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$/;
        return pwdReg.test(password);
    }

    function register() {
        if($('#username').val().length ==0){
            alert('Please provide a valid username');
            return;
        }
        else{
            usernameFld= $('#username').val();
        }

        if($('#password').val().length ==0){
            alert('Please provide a valid password');
            return;
        }
        else{

            passwordFld= $('#password').val();
            if(validatePassword(passwordFld) == true)
            {
                passwordFld= $('#password').val();
            }
            else{
                alert('Password must contain atleast 6 characters,1 digit,1 lower case and 1 upper case');
                return;
            }
        }

        if($('#verifyPassword').val().length ==0){
            alert('Verify password cannot be empty');
            return;
        }
        else{
            verifyPasswordFld=$('#verifyPassword').val();
        }


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
            }).catch(function () {
                alert('User already exists!');
            });
        }



    }
})();
