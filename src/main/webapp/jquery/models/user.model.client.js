function User(username, password, firstName, lastName,role,phone,email,dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.phone = phone;
    this.email = email;
    this.dateOfBirth = dateOfBirth;

    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    this.getFirstName = getFirstName;
    this.setFirstName = setFirstName;
    this.getLastName = getLastName;
    this.setLastName = setLastName;
    this.getRole = getRole;
    this.setRole = setRole;
    this.getPhone = getPhone;
    this.setPhone = setPhone;
    this.getEmail = getEmail;
    this.setEmail = setEmail;
    this.getDateOfBirth = getDateOfBirth;
    this.setDateOfBirth = setDateOfBirth;


    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }

    function setPassword(password) {
        this.password = password;
    }
    function getPassword() {
        return this.password;
    }
    function setFirstName(firstName) {
        this.firstName = firstName;
    }
    function getFirstName() {
        return this.firstName;
    }
    function setLastName(lastName) {
        this.lastName = lastName;
    }
    function getLastName() {
        return this.lastName;
    }
    function setRole(role) {
        this.role = role;
    }
    function getRole() {
        return this.role;
    }
    function setPhone(phone) {
        this.phone = phone;
    }
    function getPhone() {
        return this.phone;
    }
    function setEmail(email) {
        this.email = email;
    }
    function getEmail() {
        return this.email;
    }
    function setDateOfBirth(dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    function getDateOfBirth() {
        return this.dateOfBirth;
    }
}
