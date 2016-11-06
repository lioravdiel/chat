/**
 * 
 */
$(document).ready(function(){

	
	//User login
	$("#user_login").submit(function(){
		
		var username = $("#username").val();
		var password = $("#password").val();
		
		$.ajax({
			type: "post",
			url: "checkLogin.jsp",
			data: "username=" + username + "&password=" + password,
			success: function(data){
				window.location = "login.jsp?username"+data;
			}
			
		});
		
		return false;
	});


});

