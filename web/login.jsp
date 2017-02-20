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
<center>
<%
 String error = (String) request.getAttribute("ErrorLogin");
 if (error!=null)
 {
	 out.println("<br><b>"+error+"</b>");
 }
%>
<br/>
<h3>¡Bienvenido! Por favor logeate</h3>
<table align="center">
	<tr>
		<td>
			<form action="controlador" method="post">
			Login: <input type="text" name="login" value="">
			<br/>
		</td>
	</tr>
	<tr>
		<td>
			Password: <input type="password" name="clave" value"">
			<br/>
			<br/>
		</td>
	</tr>
</table>
<table aling="center">
	<tr>
		<td>
			<input name="accion" value="Entrar" type="submit">
			</form>
		</td>
		<td>	
			<form action="controlador" method="post">
			<input name="accion" value="Inicio" type="submit">
			</form>
		</td>
	</tr>
</table>
</center>
</body>
</html>