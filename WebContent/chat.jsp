<%@ page language="java" session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" import="java.util.List, com.howopensource.demo.chat.Message"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<title>Java SSE Chat Example</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.1.1.js" integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- Bootstrap Select -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/i18n/defaults-*.min.js"></script>


<!-- google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Bungee+Inline" rel="stylesheet">

<!-- Local -->
<link rel="stylesheet" href="css/style.css" type="text/css">
<script type="text/javascript" src="js/jsfile.js"></script>

</head>

<body>

<div class="container-fluid">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#talk_talk_collapse" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp" style="font-family: 'Bungee Inline', cursive;">Talk<span style="font-size: 28px;">2</span>Talk</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" style="text-align: center;" id="talk_talk_collapse">
    <div id="user_connection_result">
	    <form class="navbar-form" id="user_connection">
		    <ul class="nav navbar-nav" style="display: inline-block; float: none;">
		    	<li>
		    		<div class="form-group">
			        	<div class="inner-addon left-addon">
				          	<i class="glyphicon glyphicon-user"></i>
				         	<input type="text" class="form-control" id="username" placeholder="User Name..." required autocomplete autofocus>
		        		</div>  
		       	 	</div>
		        </li>
		        <li>
		        	<div class="form-group">
			        	 <select class="selectpicker" id="room" data-live-search="true" data-style="btn-info">
			        	 <option>Click to choose Room</option>
				         <optgroup label="Tora">
				        	<option data-tokens="ToraRoom-1">ToraRoom-1</option>
							<option data-tokens="ToraRoom-2">ToraRoom-2</option>
							<option data-tokens="ToraRoom-3">ToraRoom-3</option> 
				         </optgroup>
						<optgroup label="Gmara">
				        	<option data-tokens="GmaraRoom-1">GmaraRoom-1</option>
							<option data-tokens="GmaraRoom-2">GmaraRoom-2</option>
							<option data-tokens="GmaraRoom-3">GmaraRoom-3</option> 
				         </optgroup>
						<optgroup label="Mishna">
				        	<option data-tokens="MishnaRoom-1">MishnaRoom-1</option>
							<option data-tokens="MishnaRoom-2">MishnaRoom-2</option>
							<option data-tokens="MishnaRoom-3">MishnaRoom-3</option> 
				         </optgroup>
						<optgroup label="Talmud">
				        	<option data-tokens="TalmudRoom-1">TalmudRoom-1</option>
							<option data-tokens="TalmudRoom-2">TalmudRoom-2</option>
							<option data-tokens="TalmudRoom-3">TalmudRoom-3</option> 
				         </optgroup>
						<optgroup label="Havruta">
				        	<option data-tokens="HavrutaRoom-1">HavrutaRoom-1</option>
							<option data-tokens="HavrutaRoom-2">HavrutaRoom-2</option>
							<option data-tokens="HavrutaRoom-3">HavrutaRoom-3</option> 
				         </optgroup>
						<optgroup label="Masechet">
				        	<option data-tokens="MasechetRoom-1">MasechetRoom-1</option>
							<option data-tokens="MasechetRoom-2">MasechetRoom-2</option>
							<option data-tokens="MasechetRoom-3">MasechetRoom-3</option> 
				         </optgroup>
						</select>
		       		</div>	
		        </li>
		        <li>
		       		<input type="submit" class="form-control"> 
		        </li>
		    </ul>  
	    </form>
    </div>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



	<div class="row">
		
		<div class="col-md-3"> </div>
		<div class="col-md-6">
		<div class="jumbotron text-center">
			<h1 style="font-family: 'Bungee Inline', cursive;">Talk<span style="font-size: 80px;">2</span>Talk</h1>
		</div>
		
		<div class="panel panel-info"> 
			<div class="panel-heading panel-info"></div>
			<div class="panel-body" style="height: 200px; overflow: auto;" id="chat">
			
			<%
			// Quick and dirty way to print existing messages - better use JSTL
			@SuppressWarnings("unchecked")
			List<Message> messages = (List<Message>)request.getAttribute("messages");
			if (messages!=null) {
				for(Message msg : messages) { %>
					<img src='images/user_profile.png' width='20px' height='20px'><span style='font-size: 9px; color:red;'>
					<%= msg.getId() %>
					</span><br>
					<%= msg.getMessage()%>
					<br><hr>
				<%	}
			}
			%>
			</div>
			<div class="panel-footer">
				<form id="msgForm" action="/chat/chat" method="post" onsubmit="return sendMsg(this); audioPlay();"> 
			        <div class="inner-addon left-addon">
	          			<i class="glyphicon glyphicon-send"></i>
	         			<input type="text" class="form-control" id="msg" name="msg" placeholder="What you think?..." disabled required>
	         			<audio id="sirine"><source src="sound/face_notification.mp3" type="audio/mpeg"></audio>
        			</div>

				</form>
			</div>
		</div>
		<div class="col-md-3"> </div>
		
	</div>
	
</div>

</div><!-- main container -->

</body>
</html>
