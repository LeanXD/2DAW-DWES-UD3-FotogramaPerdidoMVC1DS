<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrate</title>
<%String error =  (String) request.getAttribute("ErrorRegistrado");%>
</head>
<body>
<center>
	<h3>Se uno de los nuestro</h3>
	<form action="controlador" method="post">
		<label>Ingresa tu nick: </label>
		<input type="text" maxlength="12" name="login" requiered>
		<br/>
		<br/>
		<label>Ingresa tu clave: </label>
		<input type="password" maxlength="12" name="clave" requiered>
		<br/>
		<br/>
		<input type="submit" name="button" value="Resgistrarme">
		<input type="hidden" name="accion" value="registrar"/>
	</form>
	<form action="controlador" method="post">
			<input name="accion" value="Inicio" type="submit">
	</form>
	<br/>
	<br/>
	<%
		if(error!=null){
			%>
			<p syle="color: red;"><%=error %></p>
			<%
		}
	%>
</center>
</body>
</html>