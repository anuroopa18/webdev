(function () {
    var username, phone,email,role,dateOfbirth;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('#btnLogout').click(logout);
        $('#btnUpdate').click(updateProfile);
    }
    function logout() {
        window.location.href="/jquery/components/login/login.template.client.html";

    }
    function updateProfile() {
        //var userId = $('#userIdFld').val();
         username = $('#username').val();
         phone = $('#phone').val();
         email = $('#email').val();
         role = $('#role').val();
         dateOfbirth = $('#dateOfbirth').val();

        var user = {
            username: username,
            phone: phone,
            email: email,
            role: role,
            dateOfBirth: dateOfbirth

        }
        userService.updateProfile(user).then(function (response) {
            alert('User profile updated!')
        });
    }
})();