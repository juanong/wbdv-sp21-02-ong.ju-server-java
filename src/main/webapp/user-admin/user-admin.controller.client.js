(function () {
    var $usernameFld, $passwordFld;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    /* Use an array to store users */
    var users

    function createUser(user) {
        userService.createUser(user)
            .then(function (actualUser) {
                users.push(actualUser)
                renderUsers(users)
            })
    }

    // Delete a user from the table body - helper function used in renderUsers
    function deleteUser(event) {
        $removeBtn = jQuery(event.target)
        var removeBtnIndex = $removeBtn.attr("id")
        var removeBtnID = users[removeBtnIndex]._id

        // As usual, calling our service functions returns a promise that needs to be followed through
        // In this case, we don't actual need what the server returns
        userService.deleteUser(removeBtnID)
            .then(function() {})

        // Once the server has removed the user, we now remove it from our local array
        users.splice(removeBtnIndex, 1)
        renderUsers(users)
    }

    function selectUser(event) {
        $editBtn = jQuery(event.target)
        // ID for the select button is the actual id of the user object, not the array index position
        var editBtnID = $editBtn.attr("id")
        // Find the user in the local array with the same ID as target ID
        var selectedUser = users.find(user => user.id === editBtnID)
        // Update input fields
        $usernameFld.val(selectedUser.username)
        $passwordFld.val(selectedUser.password)
        $firstNameFld.val(selectedUser.firstName)
        $lastNameFld.val(selectedUser.lastName)
        $roleFld.val(selectedUser.role)

    }
    function updateUser() {

    }
    function renderUsers(users) {
        // Empty the table body
        $tbody.empty()
        for (var i=0; i<users.length; i++) {
            var course = users[i]
            $tbody.append(`
            <tr class="wbdv-template wbdv-user wbdv-hidden">
                <td class="wbdv-username">${course.username}</td>
                <td>&nbsp;</td>
                <td class="wbdv-first-name">${course.firstName}</td>
                <td class="wbdv-last-name">${course.lastName}</td>
                <td class="wbdv-role">${course.role}</td>
                <td class="wbdv-actions">
                <span class="pull-right">
                  <i class="fa-2x fa fa-times wbdv-remove" id="${i}"></i>
                  <i class="fa-2x fa fa-pencil wbdv-edit" id="${course._id}"></i>
                </span>
                </td>
            </tr>
            `)
        }
        /* Delete user functionality. This listens specifically for the wbdv-remove class and uses the id */
        jQuery(".wbdv-remove")
            // Get the event target to access id
            .click(deleteUser)

        /* Have the select buttons waiting in case we want to edit a user */
        jQuery(".wbdv-edit")
            .click(selectUser)
    }

    function emptyInputFields() {
        $usernameFld.val("")
        $passwordFld.val("")
        $firstNameFld.val("")
        $lastNameFld.val("")
    }

    function findAllUsers() {

    } // optional - might not need this
    function findUserById() {

    } // optional - might not need this

    function main() {
        /* Define the variables */
        $usernameFld = $("#usernameFld")
        $passwordFld = $("#passwordFld")
        $firstNameFld = $("#firstNameFld")
        $lastNameFld = $("#lastNameFld")
        $roleFld = $("#roleFld")
        $createBtn = $(".wbdv-create")
        $tbody = $(".wbdv-tbody")

        users = []

        // Have the create button listen to create new users based on input field values
        $createBtn.click(()=> {
            var newUser = {
                username: $usernameFld.val(),
                password: $passwordFld.val(),
                firstName: $firstNameFld.val(),
                lastName: $lastNameFld.val(),
                role: $roleFld.val()
            }
            createUser(newUser)
            // Clear the input fields with helper function
            emptyInputFields()
        })
        // Fetch the users from the server
        userService.findAllUsers()
            // The server will return to us the objects it has
            .then(function (actualUsers) {
                users = actualUsers
                renderUsers(users)
            })
    }

    $(main);
})();
