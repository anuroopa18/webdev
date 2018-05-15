function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.updateProfile = updateProfile;
    this.findUserByUsername = findUserByUsername;
    this.register = register;
    this.login=login;
    this.url =
        '/api/user';
    this.findUserUrl =
        '/api/findUser';
    this.registerurl =
        '/api/register';
    this.loginUrl=
        '/api/login';
    this.findUserByCredentailsUrl =
        '/api/findUserByCredentials';
    this.updateProfileUrl ='/api/profile';
    var self = this;

    function findAllUsers(){
        return fetch(self.url).then(function (response) {
            return response.json();
        });
    }

    function deleteUser(userId){
        return fetch(self.url + '/' + userId,{
            method:'delete'
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
            return response.json();
        });

    }

    function findUserByCredentials(user){
        return fetch(self.findUserByCredentailsUrl,{
            method:'post',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        }).then(function(response){
            return response.json();
        });
    }

    function login(user){
        return fetch(self.loginUrl,{
            method:'post',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        }).then(function(response){
            return response.json();

        });

    }

    function updateProfile(user){
        return fetch(self.updateProfileUrl ,{
            method:'put',
            body: JSON.stringify(user),
            headers:{
                'content-type': 'application/json'
            }
        }).then(function(response){
            return response.json();

        });
    }



}
