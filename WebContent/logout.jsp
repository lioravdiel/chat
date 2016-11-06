<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@ page import = "java.util.Date" session="true"%>
<%
	session.setAttribute("username", null);
	session.invalidate();
	response.sendRedirect("index.jsp");
%>