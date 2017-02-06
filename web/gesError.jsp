<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page import="fotogramas.modelo.beans.BeanError" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página de gestión de Errores</title>
</head>
<body>
<%
	BeanError err = (BeanError) request.getAttribute("error");
	Connection conexion = (Connection) session.getAttribute("conexion");
%> 
Se ha producido un error.<br>
El mensaje de la excepción es: <%=err.getMensError()%> <br>
El código de error es: <%=err.getCodError()%><br>
<center>
<!-- Mediante este control sabemos si el usuario sigue registrado o no -->
<!-- De esta forma sabemos a que página volver -->
	<form action="controlador" method="post">
		<%if(conexion != null){ %>
		<input name="accion" value="Volver" type="submit">
		<%}else{ %>
		<input name="accion" value="Inicio" type="submit">
		<%} %>
	</form>
</center>
</body>
</html>