jQuery(document).ready(function(){

    $("#loadingview").hide();
    $("#contentviewadmin").hide();

    $("#allorders").on("click", function(){
       console.log("all orders")
        getOrdersAjaxCall();
    });
    $("#allproducts").on("click", function(){
        console.log("all products")
        getProductAjaxCall()
    });
    $("#allcashiers").on("click", function(){
        console.log("all cashiers")
        getUsersAjaxCall();
    });

});

//Functions to add the load view or hide it
function loadshow(){
    $("#contentviewadmin").hide();
    $("#loadingview").fadeIn();
}
function loadhide(){
    $("#loadingview").fadeOut();
}

function changetitle(newtitle){
    $("#maintitle").html(newtitle)
}

//Clear the last view
function clearview(){

    //Check if the view already exist
    let userview = document.getElementById("userview");
    if (userview != undefined){
        userview.remove();
    }

    //Check if the view already exist
    let orderview = document.getElementById("orderview");
    if (orderview != undefined){
        orderview.remove();
    }

}

//CASHIERS FUNCTIONS START
//Functions that help to show all the cashiers in the system
function getUsersAjaxCall(){

    clearview();

    loadshow();

    $.ajax({
        type: "GET",
        url: "getallusers",
        success: function (response) {
            filterAllUsersToGetJustCashiers(response)
        },
        error: function (e){
            console.log("error1" , e)
        }
    });
}

function filterAllUsersToGetJustCashiers(users){
    let filterusers = {};

    changetitle("List of All Cashiers");

    $("#contentviewadmin").html(`
        <div id="userview">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-primary" id="addnewuser">Add new user</button>    
                </div>
            </div>
            <div class="row">
                <ul id="userlist" class="list-group">
                    <li id="listuserheader" class="listheaderview list-group-item">
                           <div class="row">
                                <div class="col">
                                    Name
                                </div>
                                <div class="col">
                                     UserName
                                </div>
                                <div class="col">
                                    Role
                                </div>
                                <div class="col">
                                    Orders Value
                                </div>
                                <div class="col">
                                    Actions
                                </div>
                          </div>
                        </li>
                </ul>
            </div>
            <div id="newusermodal" title="Add new user"> 
                           
                <form id="newuserform">
                  <div class="mb-3">
                    <label for="newcashiername" class="form-label">Name</label>
                    <input type="text" class="form-control" id="newcashiername" aria-describedby="emailHelp">                
                  </div>
                  <div class="mb-3">
                    <label for="newcashierusername" class="form-label">UserName</label>
                    <input type="text" class="form-control" id="newcashierusername">
                  </div>
                  <div class="mb-3">
                    <label for="newpassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="newpassword">
                  </div>
                  
                </form>      
                          
            </div>
            
        </div>
        
    `)

    for (let id in users){

        let user = users[id];

        if (user.role != "admin"){
            console.log(user);
            //Extra info we are going to use inside our view

            let liID = `userid-${user.id}`;
            let ordersValues = 0
            ordersValues = ordersValue(user);

            //Butons IDS
            let deleteid = `delete-user${user.id}`;
            let checkuser = `check-user${user.id}`;

            //View user modal id
            let modalcheckuserid = `modal-user-${user.id}`;

            let orderDetailsID = `order-user-details-id-${user.id}`;

            $("#userlist").append(`                
                
                <li id="${liID}" class="useritemlist list-group-item">
                   <div class="row">
                        <div class="col">
                            ${user.name}
                        </div>
                        <div class="col">
                             ${user.username}
                        </div>
                        <div class="col">
                            ${user.role}
                        </div>
                        <div class="col">
                            $${ordersValues}
                        </div>
                        <div class="col">
                            <button type="button" class="btn btn-primary" id="${checkuser}">Order Details</button>
                            <button type="button" class="btn btn-danger" id="${deleteid}">Delete</button>
                        </div>
                  </div>
                </li>
                
                <div id="${modalcheckuserid}" title="Check ${user.name}'s orders">
                    <ul id="${orderDetailsID}" class="list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-8">Date</div>
                                <div class="col-4">Value</div>
                            </div>                            
                        </li>
                      
                    </ul>
                </div>
            `)

            userOrderList(user, orderDetailsID)

            jQuery(`#${checkuser}`).on("click", function (){
                jQuery(`#${modalcheckuserid}`).dialog("open");
            })

            $(`#${modalcheckuserid}`).dialog({
                autoOpen: false,
                height: "auto",
                width: 600,
                modal: true,
                show: {
                    effect: "fade",
                    duration: 500
                },
                hide: {
                    effect: "fade",
                    duration: 500
                }
            })

            $(`#${deleteid}`).on("click",function(){
                deleteUserAJAX(user.id)
            })

        }
    }

    //Prevent add new user form submit event
    $("#newuserform").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        addUserAJAX();
    });


    //Modal to open the new user form
    jQuery("#addnewuser").on("click", function (){
        jQuery("#newuserform").dialog("open");
    })

    $("#newuserform").dialog({
        autoOpen: false,
        height: "auto",
        width: 600,
        modal: true,
        show: {
            effect: "fade",
            duration: 500
        },
        hide: {
            effect: "fade",
            duration: 500
        },
        buttons: {
            "Add User": function () {
                addUserAJAX();
                $(this).dialog("close");
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    })


    loadhide();

    $("#contentviewadmin").fadeIn();


}

//Function that helps us to get the value of all the orders a user has made
function ordersValue(user){

    let orders = user.orders;
    let value = 0;

    for (order in orders){

        let orderOBJ = user.orders[order];

        value += orderOBJ.value;
    }

    return value;

}

//Function that add a list view of all the orders of each user
function userOrderList(user, ulid){
    let orders = user.orders;

    for (order in orders){

        let orderOBJ = user.orders[order];

        $(`#${ulid}`).append(`
            <li class="list-group-item">
                <div class="row">
                    <div class="col-8">${orderOBJ.date}</div>
                    <div class="col-4">$${orderOBJ.value}</div>
                </div>                            
            </li>
        `)
    }
}

//AJAX Call to delete an user
function deleteUserAJAX (id){
    let urlConst = `/deleteuser/${id}`;

    $.ajax({
        type: "DELETE",
        url: urlConst,
        success: function (response) {
            getUsersAjaxCall();
        },
        error: function (e){
            console.log("error1" , e)
        }
    });

}

//Function that create a modal to add a new user
function addUserAJAX(){

    let newUserData = {
        name: $("#newcashiername").val(),
        username: $("#newcashierusername").val(),
        pass: $("#newpassword").val(),
        role: "cashier"
    }

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "newuserapi",
        data : JSON.stringify(newUserData),
        dataType : 'json',
        success : function(result) {

            getUsersAjaxCall();
        },
        error : function(e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

}
//CASHIERS FUNCTIONS END

//ORDERS FUNCTIONS START

//AJAX to get all orders
function getOrdersAjaxCall(){
    //Check if the view already exist
    clearview();

    loadshow();

    $.ajax({
        type: "GET",
        url: "getallorders",
        success: function (response) {

            ordersView(response)
        },
        error: function (e){
            console.log("error1" , e)
        }
    });
}

//Function to create the orders view
function ordersView(orders){

    clearview();

    changetitle("List of All Orders");

    $("#contentviewadmin").html(`
        
        <div id="orderview">
        
            <div class="row">
                <ul id="orderlist" class="list-group">
                    <li id="orderuserheader" class="listheaderview list-group-item">
                           <div class="row">
                                <div class="col-1">
                                    ID
                                </div>
                                <div class="col-2">
                                    Cashier
                                </div>
                                <div class="col-3">
                                     Date
                                </div>
                                <div class="col-2">
                                    Value
                                </div>
                                <div class="col-2">
                                    Margin
                                </div>
                                <div class="col-2">
                                    Actions
                                </div>
                          </div>
                    </li>
                </ul>
            </div>        
        </div>
    
    `)

    for (let idf in orders){

        let order = orders[idf];

        let id = order.id;
        let date = order.date;
        let cashierName = order.cashier.name;
        let value = order.value;
        let margin = order.margin;

        //Button id
        let productlist = `productlist-id-${id}`
        let modalviewid = `modal-product-list-${id}`
        let orderDetailsIDproduct = `productvieworder-id-${id}`

        $(`#orderlist`).append(`
            
            <li id="orderuserheader" class="listheaderview list-group-item">
                   <div class="row">
                        <div class="col-1">
                            ${id}
                        </div>
                        <div class="col-2">
                            ${cashierName}
                        </div>
                        <div class="col-3">
                             ${date}
                        </div>
                        <div class="col-2">
                            $${value}
                        </div>
                        <div class="col-2">
                            $${margin}
                        </div>
                        <div class="col-2">
                            <button type="button" class="btn btn-primary" id="${productlist}">Order Details</button>
                        </div>
                  </div>
                </li>
                
                <div id="${modalviewid}">
                    <ul id="${orderDetailsIDproduct}" class="list-group">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-3">Name</div>
                                <div class="col-3">Price</div>
                                <div class="col-3">Cost</div>
                                <div class="col-3">Margin</div>
                            </div>                            
                        </li>                      
                    </ul>
                </div>
            
        `)

        orderListOfProducts(order, orderDetailsIDproduct);

        jQuery(`#${productlist}`).on("click", function (){
            jQuery(`#${modalviewid}`).dialog("open");
        })

        $(`#${modalviewid}`).dialog({
            autoOpen: false,
            height: "auto",
            width: 600,
            modal: true,
            show: {
                effect: "fade",
                duration: 500
            },
            hide: {
                effect: "fade",
                duration: 500
            }
        })


    }

    loadhide();

    $("#contentviewadmin").fadeIn();

}

//Funtion to add the products to the order views
function orderListOfProducts(order, orderViewID){
    console.log(order)
    console.log(orderViewID)


    let products = order.products;

    for (id in products){

        let product = order.products[id];

        $(`#${orderViewID}`).append(`
            <li class="list-group-item">
                <div class="row">
                    <div class="col-3" id="${product.id}-name">${product.name}</div>
                    <div class="col-3">$${product.price}</div>
                    <div class="col-3">$${product.cost}</div>
                    <div class="col-3">$${product.margin}</div>
                </div>                            
            </li>
        `)
    }


}

//ORDERS FUNCTIONS START


//PRODUCT FUNCTION START
function getProductAjaxCall(){
    //Check if the view already exist
    clearview();

    loadshow();

    $.ajax({
        type: "GET",
        url: "getallproducts",
        success: function (response) {
            productListView(response)

        },
        error: function (e){
            console.log("error1" , e)
        }
    });
}

function productListView(products){

    clearview();

    changetitle("List of All Products");

    $("#contentviewadmin").html(`
        
        <div id="orderview">
        
            <div class="row">
                <ul id="productlist" class="list-group">
                    <li id="productheaderlist" class="listheaderview list-group-item">
                           <div class="row">
                                <div class="col-1">
                                    ID
                                </div>
                                <div class="col-2">
                                    Name
                                </div>
                                <div class="col-3">
                                    Description
                                </div>
                                <div class="col-1">
                                     Price
                                </div>
                                <div class="col-1">
                                    Cost
                                </div>
                                <div class="col-1">
                                    Margin
                                </div>
                                <div class="col-1">
                                    Stock
                                </div>
                                <div class="col-2">
                                    Actions
                                </div>
                          </div>
                    </li>
                </ul>
            </div>        
        </div>
    
    `)

    for (let idf in products){


        let product = products[idf];

        console.log(product)

        let id = product.id;
        let name = product.name;
        let price = product.price;
        let cost = product.cost;
        let margin = product.margin;
        let stock = product.stock;
        let description = product.description;

        let buttonupdate = `updateproduct-${id}`;

        let modalupdate = `update-product-${id}`;

        let formupdateid = `update-product-form-id-${id}`;

        let formname = `productname-${id}`;
        let formdescription = `description-product-${id}`;
        let formproductprice = `product-price-form-${id}`;
        let formcost = `product-cost-form-${id}`;
        let formstock = `form-stock-product-${id}`;

        $(`#productlist`).append(`
            
            <li id="orderuserheader" class="listheaderview list-group-item">
                   <div class="row">
                        <div class="col-1">
                            ${id}
                        </div>
                        <div class="col-2">
                            ${name}
                        </div>
                        <div class="col-3">
                            ${description}
                        </div>
                        <div class="col-1">
                             $${price}
                        </div>
                        <div class="col-1">
                            $${cost}
                        </div>
                        <div class="col-1">
                            $${margin}
                        </div>
                        <div class="col-1">
                            ${stock}
                        </div>
                        <div class="col-2">
                            <button type="button" class="btn btn-primary" id="${buttonupdate}">Update</button>
                        </div>
                  </div>
            </li>
            
            <div id="${modalupdate}" title="Update ${name}"> 
                           
                <form id="${formupdateid}">
                  <div class="mb-3">
                    <label for="${formname}" class="form-label">Name</label>
                    <input type="text" class="form-control" id="${formname}" aria-describedby="emailHelp" value="${name}">                
                  </div>
                  <div class="mb-3">
                    <label for="${formdescription}" class="form-label">Description</label>
                    <input type="text" class="form-control" id="${formdescription}" value="${description}">
                  </div>
                  <div class="mb-3">
                    <label for="${formproductprice}" class="form-label">Price</label>
                    <input type="number" class="form-control" id="${formproductprice}" value="${price}" min="0" ">
                  </div>
                  <div class="mb-3">
                    <label for="${formcost}" class="form-label">Cost</label>
                    <input type="number" class="form-control" id="${formcost}" value="${cost}" min="0" ">
                  </div>
                  <div class="mb-3">
                    <label for="${formstock}" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="${formstock}" value="${stock}" min="0" ">
                  </div>
                  
                </form>      
                          
            </div>
                
        `)

        jQuery(`#${buttonupdate}`).on("click", function (){
            jQuery(`#${modalupdate}`).dialog("open");
        })

        $(`#${modalupdate}`).dialog({
            autoOpen: false,
            height: "auto",
            width: 600,
            modal: true,
            show: {
                effect: "fade",
                duration: 500
            },
            hide: {
                effect: "fade",
                duration: 500
            },
            buttons: {
                "Update Product": function () {
                    updateProductAjax(id, formname, formdescription, formproductprice, formcost, formstock);
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        })


    }

    loadhide();

    $("#contentviewadmin").fadeIn();
}

//Update a product
function updateProductAjax (id, name, description, price, cost, stock){

    //Create the produc to update
    console.log(id);

    //Set variables
    let priceval = parseFloat($(`#${price}`).val()) ;
    let costval = parseFloat($(`#${cost}`).val()) ;
    let margin = priceval - costval;

    let product = {
        id: id,
        name: $(`#${name}`).val(),
        price: priceval,
        cost: costval,
        margin: margin,
        description: $(`#${description}`).val(),
        stock: parseFloat($(`#${stock}`).val())
    }

    console.log(product);

    loadshow();

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "updateproduct",
        data : JSON.stringify(product),
        dataType : 'json',
        success : function(result) {

            getProductAjaxCall();
        },
        error : function(e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });


}
