/**
 * 
 */

$(document).ready(function(){
	
	$("#user_signin").submit(function(){
	
		var username = $("#uname").val();
		var email = $("#email").val();
		var age = $("#age").val();
		var password = $("#upass").val();
		
		$('#user_login_result').css('visibility', 'visible');
		
		this.timer = setTimeout(function () {
		$.ajax({
			url: "checkSignIn.jsp",
			data: "username="+username +"&email="+email+"&age="+age+"&password="+password,
			type: "post",
			success: function(msg){
			
				if(msg.trim() == "ERROR") {
					$("#user_login_result").fadeOut(500);
					$("#user_login_result").fadeIn(500);
					$("#user_login_result").fadeOut(500);
					$("#user_login_result").fadeTo(1500,0.1,function(){
						$(this).html('Sorry, Wrong Combination Of Username And Password.').removeClass().addClass('myerror').fadeTo(2500,1,
							function(){
								document.location='index.jsp';
							});
						});
					
				}
				else {
					$("#user_login_result").html('').addClass('myinfo').fadeTo(1500,1,
							function(){
								document.location='login.jsp?username='+msg;
							});
				}
			}
		});
		}, 200);
		
		return false;
	});
	 
});

