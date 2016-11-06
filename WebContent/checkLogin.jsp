<%@ page language="java" contentType="text/html; charset=windows-1255" pageEncoding="windows-1255"%>

	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String dbUsername = "toot";
		String dbPassword = "123";
		
		if (username.equalsIgnoreCase(dbUsername) && password.equalsIgnoreCase(dbPassword)){
			out.print(username);	
		}else{
			out.print("ERROR");
		}
	
	%>
