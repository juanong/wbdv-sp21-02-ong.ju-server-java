function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/001733643/users';
    var self = this;
    function createUser(user) {}
    function findAllUsers() {}
    function findUserById(userId) {}
    function updateUser(userId, user) {}
    function deleteUser(userId) {}
}