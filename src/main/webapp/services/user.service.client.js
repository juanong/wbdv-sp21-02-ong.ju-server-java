function AdminUserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    // The server is what actually interacts with the database
    this.url = 'https://wbdv-generic-server.herokuapp.com/api/001733643/users';
    var self = this;

    // Store a new user in our server
    function createUser(user) {
        return fetch(self.url, {
            // We are posting a new user to the server
            method: 'POST',
            // Headers tell the server what kind of data we're sending
            headers: {
                'content-type': 'application/json'
            },
            // Provide the server with a string version of the actual user object
            body: JSON.stringify(user)
            // Once the user is posted into the database, we return its response to the controller
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

    function updateUser(userId, user) {
        // The PUT method takes in the userID in the server URL. It will replace that.
        return fetch(`${self.url}/${userId}`, {
            method: 'PUT',
            headers : {'content-type': 'application/json'},
            // Provide the actual user to the server as a string. We put this user in the given user's place
            body: JSON.stringify(user)
        })  // Once the server responds, we return the JSON version of our updated user to our controller
            .then(response => response.json())
    }

    function deleteUser(userId) {
        // To use DELETE, request the data from the server using the URL and the specific userID
        return fetch(`${self.url}/${userId}`,
            {method: 'DELETE'})
    }
}
