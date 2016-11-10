<%@ page session="true"%>
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
	String username=request.getParameter("username");
	session.setMaxInactiveInterval(24 * 60 * 60);
	session.setAttribute("username",username);
	session.setAttribute("sessionId",java.util.UUID.randomUUID().toString());
	request.setAttribute("success", true);
	request.getRequestDispatcher("/chat").forward(request, response);
	%>


</body>
</html>