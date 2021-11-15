console.log("hi");

jQuery(document).ready(function(){

    getAllUsers();

    console.log("inside JQ")
});

//Function that call AJAX to get all the users of the DB, it returns a list of users
function getAllUsers(){

    console.log("inside Ajax")

    $.ajax({
        type: "GET",
        url: "getorder/1",
        success: function (response) {
            console.log("bien");
            console.log(response);
        },
        error: function (e){
            console.log("error1" , e)
        }
    });

}


function AddNewUser(){

    console.log("adding user")

    let user = {
        name: "Joel",
        username: "joelo",
        pass: "lepass",
        role: "cashier"
    }

    console.log("inside Ajax")

    $.ajax({
        type: "POST",
        contentType : "application/json",
        url: "saveuser",
        data : JSON.stringify(user),
        dataType : 'json',
        success: function (response) {
            console.log("bien");
            console.log(response);
        },
        error: function (e){
            console.log("error1" , e)
        }
    });

    console.log("user added")
}


