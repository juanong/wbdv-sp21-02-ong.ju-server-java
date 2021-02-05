(function () {
    var $usernameFld, $passwordFld;
    var $firstNameFld, $lastNameFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    /* Use an array to store users */
    var users = [
        {username: "juanong", password: "none", firstName: "Juan", lastName: "Ong", role: "STUDENT"},
        {username: "mjcho", password: "secret", firstName: "Min Je", lastName: "Cho", role: "ADMIN"}
    ]

    function createUser(user) {
        users.push(user)
        renderUsers(users)
    }

    // Delete a user from the table body - helper function used in renderUsers
    function deleteUser(event) {
        $removeBtn = jQuery(event.target)
        var removeBtnID = $removeBtn.attr("id")
        users.splice(removeBtnID, 1)
        renderUsers(users)
    }
    function selectUser() {

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
                  <i class="fa-2x fa fa-pencil wbdv-edit" id="${i}"></i>
                </span>
                </td>
            </tr>
            `)
        }
        /* Delete user functionality. This listens specifically for the wbdv-remove class and uses the id */
        jQuery(".wbdv-remove")
            // Get the event target to access id
            .click(deleteUser)
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

        renderUsers(users)
    }

    $(main);
})();
