(function () {
    var $usernameFld, $passwordFld;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn, $updateBtn;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    // Use a local array to store users
    /*
    Remember that this local array only stores the JSON objects returned by the server.
    Therefore, they will have fields such as id that we can access
     */
    var users

    function createUser(user) {
        // Give the user to the service client
        userService.createUser(user)
            // .then() means we only execute this AFTER the server succeeds
            .then(function (actualUser) {
                // Push the user onto the local array and rerender the page
                users.push(actualUser)
                renderUsers(users)
            })
    }

    // Delete a user from the table body - helper function used in renderUsers
    function deleteUser(event) {
        // The event target stores the actual attributes of the user
        $removeBtn = jQuery(event.target)
        var removeBtnIndex = $removeBtn.attr("id")
        // Grab the actual ID attribute of the user we want to delete
        var removeBtnID = users[removeBtnIndex]._id
        // As usual, calling our service functions returns a promise that needs to be followed through
        // In this case, we don't actual need what the server returns
        userService.deleteUser(removeBtnID)
            .then(function() {})
        // Once the server has removed the user, we now remove it from our local array and re-render
        users.splice(removeBtnIndex, 1)
        renderUsers(users)
    }

    // Instantiate the selected user so we can update them
    var selectedUser = null
    function selectUser(event) {
        // The event target stores the actual attributes of the user
        $editBtn = jQuery(event.target)
        // ID for the select button is the actual id of the user object, not the array index position
        var editBtnID = $editBtn.attr("id")
        // Find the user in the local array with the same ID as target ID
        // Notice we use user._id and not user.id. _id is a field of the actual object
        selectedUser = users.find(user => user._id === editBtnID)
        // Update input fields
        $usernameFld.val(selectedUser.username)
        $passwordFld.val(selectedUser.password)
        $firstNameFld.val(selectedUser.firstName)
        $lastNameFld.val(selectedUser.lastName)
        $roleFld.val(selectedUser.role)
    }

    // Replace the values of a user with the new input field values. The update button is always listening
    function updateUser() {
        console.log(selectedUser)
        // Replace all the values of the LOCAL user object
        selectedUser.username = $usernameFld.val()
        selectedUser.password = $passwordFld.val()
        selectedUser.firstName = $firstNameFld.val()
        selectedUser.lastName = $lastNameFld.val()
        selectedUser.role = $roleFld.val()

        // Pass the UPDATED USER to the service
        userService.updateUser(selectedUser._id, selectedUser)
            // Once the server returns us the object, we can add update the user locally and re-render
            .then(function(status) {
                // Now that the server has responded, we can update the local array
                var updatedUserIndex = users.findIndex(user => user._id === selectedUser._id)
                // Replace the user with its new info in the local array
                users[updatedUserIndex] = selectedUser
                renderUsers(users)
                emptyInputFields()
            })
    }

    // Display all the users in the server as rows in the table
    function renderUsers(users) {
        // Empty the table body
        $tbody.empty()
        for (var i=0; i<users.length; i++) {
            // Grab the current index to assign to each row
            var user = users[i]
            // Append the current user's data as a row to the table
            $tbody.append(`
            <tr class="wbdv-template wbdv-user wbdv-hidden">
                <td class="wbdv-username">${user.username}</td>
                <td>&nbsp;</td>
                <td class="wbdv-first-name">${user.firstName}</td>
                <td class="wbdv-last-name">${user.lastName}</td>
                <td class="wbdv-role">${user.role}</td>
                <td class="wbdv-actions">
                <span class="pull-right">
                  <i class="fa-2x fa fa-times wbdv-remove" id="${i}"></i> 
                  <!-- IMPORTANT: we assign the ID the edit icon to be the ID of the USER -->
                  <i class="fa-2x fa fa-pencil wbdv-edit" id="${user._id}"></i>
                </span>
                </td>
            </tr>
            `)
        }
        // Every time we click a button, we're passing along an event to the function inside
        // Delete user functionality. This listens specifically for the wbdv-remove class and uses the id
        jQuery(".wbdv-remove")
            // Get the event target to access id
            .click(deleteUser)

        /* Have the select buttons waiting in case we want to edit a user */
        jQuery(".wbdv-edit")
            .click(selectUser)
    }

    // Fill all the input fields with empty strings
    function emptyInputFields() {
        $usernameFld.val("")
        $passwordFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
    }

    function findAllUsers() {} // optional - might not need this
    function findUserById() {} // optional - might not need this

    function main() {
        /* Define the variables */
        $usernameFld = $("#usernameFld")
        $passwordFld = $("#passwordFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")
        $createBtn = $(".wbdv-create")
        $updateBtn = $(".wbdv-update")
        $tbody = $(".wbdv-tbody")
        users = []

        // Have the create button listening to create new users based on input field values
        // .click has to be given a FUNCTION as its parameter
        $createBtn.click(()=> {
            var newUser = {
                username: $usernameFld.val(),
                password: $passwordFld.val(),
                firstName: $firstNameFld.val(),
                lastName: $lastNameFld.val(),
                role: $roleFld.val()
            }
            // Pass the new user along to the createUser function
            createUser(newUser)
            // Clear the input fields with helper function
            emptyInputFields()
        })

        // Have the update button listening to replace a current row with the new input field values
        $updateBtn.click(updateUser)

        // Fetch the users from the server
        userService.findAllUsers()
            // The server will return to us the objects it has
            .then(function (actualUsers) {
                // The local users array becomes identical to the array of the server
                users = actualUsers
                renderUsers(users)
            })
    }

    $(main);
})();
