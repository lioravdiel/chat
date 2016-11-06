
<%@ page language="java" session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.getSession().getAttribute("userename");
	request.setAttribute("success", true);
	request.getRequestDispatcher("/chat").forward(request, response); 
%>

</body>
</html>