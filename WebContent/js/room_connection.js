/**
 * 
 */



$(document).ready(function(){
//Scroll To Bottom
var $result = $('#chat');
$result.animate({"scrollTop": $('#chat')[0].scrollHeight}, "slow");

//User Connection
$("#roomConnection").submit(function(){
	
	var username = $("#username").val();
	var room = $("#room").val();
	var sessionId = $("#sessionId").val();
	
	$.ajax({
		type: "post",
		url: "/chat/room",
		data: "username="+username+"&room="+room+"&sessionId="+sessionId,
		success: function(data){
			
			$("#roomConnection")[0].reset();
			document.getElementById("room").hidden = true;
			document.getElementById("roomSubmit").hidden = true;
			document.getElementById("msg").disabled = false;
			document.getElementById("msg").focus();
			$(".panel-heading").html(data);
			$("#chat_status_result").html("You just connect to : " + data);
			// Check that browser supports EventSource 
			if (!!window.EventSource) {
				// Subscribe to url to listen
				var source = new EventSource("/chat/chat");
				
				// Define what to do when server sent new event
				source.addEventListener("message", function(e) {
					
					var el = document.getElementById("chat");
					el.innerHTML += e.data + "<br/>";
					el.scrollTop += 50;
					$result.animate({"scrollTop": $('#chat')[0].scrollHeight}, "slow");
				}, false);
				
			} else {
				alert("Your browser does not support EventSource!");
			}
		},
		error: function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);
	        alert(thrownError);
	      }
	
	});
	
	return false;
});

});


//Send message to the server using AJAX call
function sendMsg(form) {
	
	// Init http object
	var http = false;
	if (typeof ActiveXObject != "undefined") {
		try {
			http = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (ex) {
			try {
				http = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (ex2) {
				http = false;
			}
		}
	} else if (window.XMLHttpRequest) {
		try {
			http = new XMLHttpRequest();
		} catch (ex) {
			http = false;
		}
	}

	if (!http) {
		alert("Unable to connect!");
		return;
	}

	// Prepare data
	var parameters = "msg=" + encodeURIComponent(form.msg.value.trim());
	parameters=parameters+"&sessionId="+$("#sessionId").val();
	
	http.onreadystatechange = function () {
		if (http.readyState == 4 && http.status == 200) {
			if (typeof http.responseText != "undefined") {
				var result = http.responseText;
				form.msg.value = "";
			}
		}
	};

	http.open("POST", form.action, true);
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	http.send(parameters);

	return false;
}


function audioPlay(){
    document.getElementById("sirine").play();
    return false;
}

