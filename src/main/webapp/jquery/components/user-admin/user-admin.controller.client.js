(function () {
    var tbody;
    var template;
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

    function createUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role

        }
        userService
            .createUser(user)
            .then(findAllUsers,function (response) {
                alert('User added');

            });

    }

    function updateUser(){
        var userId = $('#userIdFld').val();
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role

        }
        userService.updateUser(userId,user).then(findAllUsers,function(response){
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
                .then(findAllUsers,function (response) {
                    alert('User Deleted!')
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


