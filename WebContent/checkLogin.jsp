<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>


	<%
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String dbEmail = "lior@avdiel.com";
		String dbPassword = "123";
		
		if (email.equalsIgnoreCase(dbEmail) && password.equalsIgnoreCase(dbPassword)){
			request.setAttribute("success", true);
			request.getRequestDispatcher("/chat").forward(request, response);  
		}else{
			out.print("error");
		}
	
	%>

</body>
</html>