<%@page import="com.chat.Config"%>
<%@page import="com.chat.ChatServlet"%>
<%@ page language="java" session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" import="java.util.List, com.chat.Message"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<title>Talk2Talk</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.1.1.js" integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- Bootstrap Select -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>


<!-- google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Bungee+Inline" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">

<!-- Local -->
<link rel="stylesheet" href="css/style.css" type="text/css">
<script type="text/javascript" src="js/room_connection.js"></script>
<script type="text/javascript" src="js/upload_image_file"></script>

</head>

<body>

 <%
	String username = (String)request.getSession().getAttribute("username");
	String sessionId = (String)request.getSession().getAttribute("sessionId");
		
	String myImageProfile = Config.getMyInfo("img", username);
	if(myImageProfile.isEmpty()){
		myImageProfile = "anonymous_profile.png";
	}
	
	String firstname = Config.getMyInfo("firstname", username);
	String lastname = Config.getMyInfo("lastname", username);
	String email = Config.getMyInfo("email", username);
	String age = Config.getMyInfo("age", username);
%>

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
      <a class="navbar-brand" href="chat.jsp" style="font-family: 'Bungee Inline', cursive;">Talk<span style="font-size: 28px;">2</span>Talk</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" style="text-align: center;" id="talk_talk_collapse">
    	 <ul class="nav navbar-nav">
	      <li class="navbar-brand" style="font-family: 'Bungee Inline', cursive; margin-top: 3px;"><%=username %></li>
	      <img src="images/<%=myImageProfile %>" width="50" height="50" class="img-circle" style="margin-top: 2px;">
	    </ul>
        <form class="navbar-form" id="roomConnection">
		   
		       	<input type="hidden" id="username" value="<%=username %>">
		       	<input type="hidden" id="sessionId" value="<%=sessionId %>">
		       	
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
		       		<input type="submit" id="roomSubmit" class="form-control">  
		       		<a href="logout.jsp"><button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-off"></span></button></a>
	    </form>
    
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



	<div class="row">
		
		<!-- spacer -->
		<div class="col-md-2"> </div>
		
		<!-- user profile -->
		<div class="col-md-3">
			<div class="jumbotron" style="height: 500px;">
				<div style="width: 182px;height: 121px; line-height: 20px;display:inline-block;position:relative;">
					<img src="images/<%=myImageProfile %>" width="130" height="130" class="img-circle"><br>
					<button style="position:absolute;bottom:0px;right:62px;" type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target=".change_image_profile">
						<span class="glyphicon glyphicon-camera"></span>
					</button>
				</div>
				<div class="modal fade change_image_profile" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
				  <div class="modal-dialog modal-sm" role="document">
				    <div class="modal-content">
				       <form action="uploadServlet" method="post" enctype="multipart/form-data"></form>
		                File: <input type="file" id="image_file" name="image_file" class="form-control"><br>
		                <input type="submit" value="upload" name="upload" id="upload" class="form-control"> 
				    </div>
				  </div>
				</div>
				
				<h4 style="margin-left: 15px; font-family: 'Bungee Inline', cursive;"><%=username%></h4>
				<p style="font-family: 'Josefin Sans', sans-serif; font-size: 17px;">first name: <%=firstname %></p>
				<p style="font-family: 'Josefin Sans', sans-serif; font-size: 17px;">last name: <%=lastname %></p>
				<p style="font-family: 'Josefin Sans', sans-serif; font-size: 17px;">nick name: <%=username %>
				<p style="font-family: 'Josefin Sans', sans-serif; font-size: 17px;">email: <%=email %></p>
				<p style="font-family: 'Josefin Sans', sans-serif; font-size: 17px;">age: <%=age %></p>
				<a href="#configuration"><span class="glyphicon glyphicon-cog"></span></a>		
			</div>
			
			<!-- chat status -->
			<div class="jumbotron" style="height: 170px;">
				<h4 style="font-family: 'Bungee Inline', cursive;">Chat Status</h4>
				<div id="chat_status_result"></div>
			</div>
		</div>
		<!-- chat -->
		<div class="col-md-5">
		<div class="jumbotron">
			<h3 style="font-family: 'Bungee Inline', cursive; text-align: center;">Chat<span style="font-size: 50px;">2</span>Room</h3>
		
		<div class="panel panel-info"> 
			<div class="panel-heading panel-info">Room</div>
			<div class="panel-body" style="height: 400px; overflow: auto;" id="chat">
			
			<%
			// Quick and dirty way to print existing messages - better use JSTL
			@SuppressWarnings("unchecked")
			List<Message> messages = (List<Message>)request.getAttribute("messages");
			if (messages!=null) {
				
				for(Message msg : messages) { %>
					<img src='images/<%=myImageProfile %>' width='20px' height='20px'><span style='font-size: 9px; color:red;'>
					<%= msg.getId() %>
					</span><br>
					<%= msg.getMessage()%>
					<br><hr>
				<%	}
			}
			%>
			</div>
			<div class="panel-footer">
				<form id="msgForm" action="/chat/chat" method="post" onsubmit="return sendMsg(this);"> 
			        <div class="inner-addon left-addon">
	          			<i class="glyphicon glyphicon-send"></i>
	         			<input type="text" class="form-control" id="msg" name="msg" placeholder="What you think?..." disabled required>
	         			<audio id="sirine"><source src="sound/face_notification.mp3" type="audio/mpeg"></audio>
        			</div>

				</form>
			</div>
			</div>
		
		</div>
		
		<!-- spacer -->
		<div class="col-md-2"> </div>
		
	</div>
	
</div>

</div><!-- main container -->

</body>
</html>
