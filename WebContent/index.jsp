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



<!-- google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Bungee+Inline" rel="stylesheet">

<!-- Local -->
<link rel="stylesheet" href="css/style.css" type="text/css">
<link rel="stylesheet" href="css/back.css" type="text/css">
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/signup.js"></script>

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
    	
    	<!-- User Login -->
	    <form class="navbar-form" style="display: inline-block; float:right;" id="user_login">
	    	 
	    		<!-- <img src="images/loading.gif" id="gif" style="display: block; margin: 0 auto; width:10px; visibility:hidden;"> -->
	    		<div class="form-group">
	    			<h4 style="font-family: 'Bungee Inline', cursive;">Log<span style="font-size: 20px;">2</span>In</h4>
	    		</div>
	    		<div class="form-group">
		        	<div class="inner-addon left-addon">
			          	<i class="glyphicon glyphicon-user"></i>
			         	<input type="text" class="form-control" id="username" placeholder="User Name..." required autocomplete autofocus>
	        		</div>  
	       	 	</div>
	    		<div class="form-group">
		        	<div class="inner-addon left-addon">
			          	<i class="glyphicon glyphicon-qrcode"></i>
			         	<input type="password" class="form-control" id="password" placeholder="Password..." required autocomplete autofocus>
	        		</div>  
	       	 	</div>
	      		<button type="submit" class="form-control btn btn-info"><span class="glyphicon glyphicon-send"></span></button> 
	    </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>



	<div class="row">
		
		<div class="col-md-3"> </div>
		<div class="col-md-6">
		<div class="jumbotron text-center">
			
			<h1 style="font-family: 'Bungee Inline', cursive;">Talk<span style="font-size: 80px;">2</span>Talk</h1>
			<h3 style="font-family: 'Bungee Inline', cursive;">Sign<span style="font-size: 50px;">2</span>Up</h3>
			
			<!-- User SignUp -->
			<form class="form" id="user_signun">
			    <div class="row">
			    	<div class="col-lg-4"></div>
			    	<div class="col-lg-4">
			    	
			    		<div class="form-group">
				        	<div class="inner-addon left-addon">
					          	<i class="glyphicon glyphicon-user"></i>
					         	<input type="text" class="form-control" id="uname" placeholder="User Name..." required autocomplete autofocus>
			        		</div>  
			       	 	</div>
			    		<div class="form-group">
				        	<div class="inner-addon left-addon">
					          	<i class="glyphicon glyphicon-envelope"></i>
					         	<input type="email" class="form-control" id="email" placeholder="Email..." required autocomplete autofocus>
			        		</div>  
			       	 	</div>
			       	 	<div class="form-group">
				        	<div class="inner-addon left-addon">
					          	<i class="glyphicon glyphicon-time"></i>
					         	<input type="number" class="form-control" id="age" placeholder="Age..." required autocomplete autofocus>
			        		</div>  
			       	 	</div>
			        	<div class="form-group">
				        	<div class="inner-addon left-addon">
					          	<i class="glyphicon glyphicon-qrcode"></i>
					         	<input type="password" class="form-control" id="upass" placeholder="Password..." required autocomplete autofocus>
			        		</div>  
			       	 	</div>
			        	<div class="form-group">
				           	<button type="submit" class="form-control btn btn-info"><span class="glyphicon glyphicon-send"></span></button>
			        	</div> 
			         </div>
			    	<div class="col-lg-4"></div>
			    </div>
	    	</form>
			
		</div>
		
		<div class="col-md-3"> </div>
		<div class="row">
			<!-- spacing -->
			<div class="col-md-3">
				<div id="user_login_result" style="width: 300px; height: 300px; background-image: url('images/loading.gif'); visibility: hidden; background-repeat: no-repeat;"></div>
			</div>	
		</div>
	</div>
	
</div>

</div><!-- main container -->

</body>
</html>
