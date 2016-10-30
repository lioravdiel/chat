
<%@ page language="java" session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%
	request.setAttribute("index", true);
	request.getRequestDispatcher("/chat").forward(request, response); 
%>
