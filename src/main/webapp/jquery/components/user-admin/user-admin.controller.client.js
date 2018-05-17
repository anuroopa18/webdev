(function () {
    var tbody;
    var template;
    var username,password,firstName,lastName,role,userId;
    var userService = new UserServiceClient();
    jQuery(main);

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('.wbdv-create').click(createUser);
        $('.wbdv-update').click(updateUser);
        findAllUsers();
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function validatePassword (password) {
        var pwdReg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$/;
        return pwdReg.test(password);
    }

    function createUser() {
        if ($('#usernameFld').val().length == 0){
            alert('Please provide Username');
            return;
        }
        else {
            username = $('#usernameFld').val();
        }
        if($('#passwordFld').val().length ==0){
            alert('Please provide a valid password');
            return;
        }
        else{

            password= $('#passwordFld').val();
            if(validatePassword(password) == true)
            {
                password= $('#passwordFld').val();
            }
            else{
                alert('Password must contain atleast 6 characters,1 digit,1 lower case and 1 upper case');
                return;
            }
        }
        if ($('#firstNameFld').val().length == 0){
            alert('Please provide first name');
            return;
        }
        else {
            firstName = $('#firstNameFld').val();
        }

        if ($('#lastNameFld').val().length == 0){
            alert('Please provide last name');
            return;
        }
        else {
            lastName = $('#lastNameFld').val();
        }
        if ($('#roleFld').val().length == 0){
            alert('Please select one of the roles');
            return;
        }
        else {
            role = $('#roleFld').val();
        }


        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role

        }
        userService
            .createUser(user)
            .then(findAllUsers).then(function (response) {
            alert('User added!!')
        });

    }

    function updateUser(){

        userId = $('#userIdFld').val();
        if ($('#usernameFld').val().length == 0){
            alert('Please provide Username');
            return;
        }
        else {
            username = $('#usernameFld').val();
        }
        if($('#passwordFld').val().length ==0){
            alert('Please provide a valid password');
            return;
        }
        else{

            password= $('#passwordFld').val();
            if(validatePassword(passwordFld) == true)
            {
                password= $('#passwordFld').val();
            }
            else{
                alert('Password must contain atleast 6 characters,1 digit,1 lower case and 1 upper case');
                return;
            }
        }
        if ($('#firstNameFld').val().length == 0){
            alert('Please provide first name');
            return;
        }
        else {
            firstName = $('#firstNameFld').val();
        }

        if ($('#lastNameFld').val().length == 0){
            alert('Please provide last name');
            return;
        }
        else {
            lastName = $('#lastNameFld').val();
        }
        if ($('#roleFld').val().length == 0){
            alert('Please select one of the roles');
            return;
        }
        else {
            role = $('#roleFld').val();
        }


        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role

        }
        userService.updateUser(userId,user).then(findAllUsers).then(function(response){
            alert('User updated!');

        });
    }

    function renderUsers(users) {
        tbody.empty();
        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id)
            clone.removeClass();
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);
            clone.find('.username')
                .html(user.username);
            clone.find('.firstName')
                .html(user.firstName);
            clone.find('.lastName')
                .html(user.lastName);
            clone.find('.role')
                .html(user.role);
            tbody.append(clone);
        }

        function deleteUser(event) {
            var deletebtn = $(event.currentTarget);
            var userId = deletebtn.parent().parent().parent().attr('id');
            userService
                .deleteUser(userId)
                .then(findAllUsers).then(function (response) {
                alert('User deleted!!')
            });
        }

        function editUser(event) {
            var editbtn = $(event.currentTarget);
            var userId = editbtn.parent().parent().parent().attr('id');
            userService.findUserById(userId).then(renderUser);

        }

        function renderUser(user){
            $('#userIdFld').val(user.id)
            $('#usernameFld').val(user.username);
            $('#passwordFld').val(user.password);
            $('#firstNameFld').val(user.firstName);
            $('#lastNameFld').val(user.lastName);
            $('#roleFld').val(user.role);

        }
    }

})();


