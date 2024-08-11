$(() => {

    $('#signupButton').click("on", function () {
        $("#display").html('')
        var credentials = {
            inputFName: $("#inputFName").val(),
            inputLName: $("#inputLName").val(),
			inputEmail: $("#inputEmail").val(),
            password: $("#password").val(),
			inputPhone: $("#inputPhone").val(),
            inputRegion: $("#inputRegion").val()
			
        }

        if (credentials.inputEmail == "") {
            alert("Please Enter Your inputEmail");
        } else {
            $.ajax({

                url: 'http://localhost:8080/user/login',
                type: "POST",
                data: credentials,
                caches: false,
                success: function (returnedJson) {

                    var text = "";

                    if (returnedJson.user != undefined) {
                        localStorage.setItem("POS_Token", returnedJson.token);
                        localStorage.setItem('POS_username', returnedJson.user.firstname)
                        if (returnedJson.user.role === 'staff') {
                            location.replace("../HTML/Homepage.html");
                        } else {
                            localStorage.setItem('POS_admin', true)
                            location.replace("../HTML/Admin.html");
                        }
                    } else {
                        text += "Invalid LogIn, Please Try Again";
                    }
                    $('#display').html(text);
                },
                error: function (error) {

                    $("#display").html("404 ERROR " + error.returnedJson.message);
                }

            });
        }

    });
});