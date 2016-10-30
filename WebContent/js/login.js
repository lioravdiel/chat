/**
 * 
 */
$(document).ready(function(){

	
	//User login
	$("#user_login").submit(function(){
		
		var email = $("#email").val();
		var password = $("#password").val();
		
		$.ajax({
			type: "post",
			url: "checkLogin.jsp",
			data: "email="+email+"&password="+password,
			success: function(data){
	
			}
			
		});
		
		return false;
	});


});

