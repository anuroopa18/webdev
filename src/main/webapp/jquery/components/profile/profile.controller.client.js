(function () {
    var username, phone,email,role,dateOfbirth;
    var userService = new UserServiceClient();
    var qUsername, qUserId;


    $(main);

    function main() {
        $('#btnLogout').click(logout);
        $('#btnUpdate').click(updateProfile);
        qUsername=GetURLParameter('username');
        qUserId=GetURLParameter('userId');
        findUserById(qUserId);
    }
    function logout() {
        window.location.href="/jquery/components/login/login.template.client.html";

    }

    function GetURLParameter(sParam) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam) {
                return sParameterName[1];
            }
        }
    }

    function findUserById(qUserId){
        userService.findUserById(qUserId).then(renderUser)
    }

    function renderUser(user){
        $('#username').val(user.username);
        $('#phone').val(user.phone);
        $('#email').val(user.email);
        $('#role').val(user.role);

        $('#datepicker').val(user.dateOfBirth);

    }


    function updateProfile() {
         username = $('#username').val();
         phone = $('#phone').val();
         email = $('#email').val();
         role = $('#role').val();
         dateOfbirth = $('#datepicker').val();

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