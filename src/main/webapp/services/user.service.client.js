function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/001733643/users';
    var self = this;

    // Store a new user in our server
    function createUser(user) {
        return fetch(self.url, {
            method: 'POST',
            // Tell the server what kind of data we're sending
            headers: {
                'content-type': 'application/json'
            },
            // Convert the JSON object back into a string
            body: JSON.stringify(user)
        }).then(function (response) {
            return response.json()
        })
    }

    // Fetch all the users in the server
    function findAllUsers() {
        return fetch(self.url)
            // Request data from the server and await response, then convert the response into a JSON object
            .then(function (response) {
            return response.json()
        })
    }

    function findUserById(userId) {}
    function updateUser(userId, user) {}

    function deleteUser(userId) {
        return fetch(`${self.url}/${userId}`, {
            method: 'DELETE'
        })
    }
}
