(function () {
    var username, phone,email,role,dateOfbirth;
    var userService = new UserServiceClient();
   // var qUsername, qUserId;


    $(main);

    function main() {
        $('#btnLogout').click(logout);
        $('#btnUpdate').click(updateProfile);
        //qUsername=getURLParameter('username');
        //qUserId=getURLParameter('userId');
        findUser();
    }
    function logout() {
        userService.logout().then(redirect);


    }

    function redirect() {
        window.location.href="/jquery/components/login/login.template.client.html";
    }

    /*function getURLParameter(sParam) {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam) {
                return sParameterName[1];
            }
        }
    }*/

   /* function findUserById(qUserId){
        userService.findUserById(qUserId).then(renderUser)
    }*/

    function findUser() {
        userService.profile().then(renderUser);
    }

    function renderUser(user){
        $('#username').val(user.username);
        $('#phone').val(user.phone);
        $('#email').val(user.email);
        $('#role').val(user.role);
        $('#datepicker').val(user.dateOfBirth);

    }

    function validateEmail(email) {
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        return emailReg.test( email );
    }



    function updateProfile() {
        if ($('#username').val().length == 0){
            alert('Please provide Username');
            return;
        }
        else {
            username = $('#username').val();
        }
        if($('#phone').val().length == 0){
            alert('Please provide a valid phone number');
            return;
        }
        else {
            phone = $('#phone').val();
        }
        if($('#email').val().length == 0){
            alert('Please provide a valid email address');
            return;
        }
        else {

            email = $('#email').val();
            if(validateEmail(email)== true){
                email = $('#email').val();
            }
            else
            {
                alert('Please provide valid email address');
                return;
            }
        }


        if($('#role').val().length == 0){
            alert('Please select one of the roles');
            return;
        }
        else {
            role = $('#role').val();
        }
        if($('#datepicker').datepicker({ dateFormat: "mm/dd/yyyy" }).val().length == 0){
            alert('Please select date of birth');
            return;
        }
        else {
            dateOfbirth = $('#datepicker').datepicker({ dateFormat: "mm/dd/yyyy" }).val();
        }

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