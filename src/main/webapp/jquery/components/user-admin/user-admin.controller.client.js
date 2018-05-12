(function () {
    var tbody;
    var template;
    var userService = new UserServiceClient();
    jQuery(main);

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('.wbdv-create').click(createUser);
        findAllUsers();
    }

    function findAllUsers() {
        userService.findAllUsers().then(renderUsers);
    }

    function createUser() {
        console.log('createUser');
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
            .then(findAllUsers);

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
            console.log('deleteUser');
            console.log(event);
            var deletebtn = $(event.currentTarget);
            var userId = deletebtn.parent().parent().parent().attr('id');
            userService
                .deleteUser(userId)
                .then(findAllUsers);
        }

        function editUser(event) {
            console.log('editUser');
            console.log(event);
        }
    }

})();


