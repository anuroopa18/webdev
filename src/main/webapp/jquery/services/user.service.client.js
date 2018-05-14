function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.findUserByUsername = findUserByUsername;
    this.register = register;
    this.url =
        'http://localhost:8080/api/user';
    this.findUserUrl =
        'http://localhost:8080/api/findUser';
    this.registerurl =
        'http://localhost:8080/api/register';
    var self = this;

    function findAllUsers(){
        return fetch(self.url).then(function (response) {
            return response.json();
        });
    }

    function deleteUser(userId){
        return fetch(self.url + '/' + userId,{
            method:'delete'
        }).then(function (response) {
            alert('User Deleted!')
        });
    }

    function findUserById(userId){
        return fetch(self.url + '/' + userId,{
            method:'get'
        }).then(function(response){
            return response.json();
        });
    }
    function createUser(user) {
        return fetch(self.url,{
            method:'post',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }


        }).then(function (response) {
            alert('User added');

        });
    }
    function updateUser(userId,user){
        return fetch(self.url + '/' + userId,{
            method:'put',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        }).then(function(response){
            alert('User updated!');
            return response.json();

        });
    }

    function findUserByUsername(username){
        return fetch(self.findUserUrl + '/' + username,{
            method:'get'
        }).then(function(response){
            return response.json();
        });
    }

    function register(registerUser) {
        return fetch(self.registerurl,{
            method:'post',
            body: JSON.stringify(registerUser),
            headers:{
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if(response.status == 401)
            {
                alert('User already exists!');
            }
            else
            {
                alert('Registration successful!')
            }
        });

    }



}
