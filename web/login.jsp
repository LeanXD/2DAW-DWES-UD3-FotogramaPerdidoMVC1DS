<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fotogramas.modelo.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>El Fotograma Perdido MVC v1</title>
</head>
<body>
Aquí se produce la petición de login y clave para un usuario registrado.
<%
 BeanError error = (BeanError) request.getAttribute("error");
 if (error!=null)
 {
	 out.println("<br><b>"+error.getMensError()+"</b>");
 }
%>
<form action="controlador" method="post">
Login: <input type="text" name="login" value="">
<br/>
Password: <input type="password" name="password" value"">
<br/>
<input name="accion" value="volver" type="submit">
<input name="accion" value="entrar" type="submit">
</form>
</body>
</html>