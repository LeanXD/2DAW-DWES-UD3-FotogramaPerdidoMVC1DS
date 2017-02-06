<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bienvenido al juego</title>
</head>
<%Connection conexion = (Connection) session.getAttribute("conexion"); %>
<body>
<center>
<%
if(conexion != null){
%>
	 <P><jsp:include page="head.jsp" flush="true"/></P>
	 <br/>
	 <br/> 
	<form action="controlador" method="post">
		<input type="hidden" name="accion" value="ranking">
	<input name="ranking" value="Ver ranking" type="submit" style="width: 84px; height: 33px">
	</form>
	
	<form action="controlador" method="post">
		<input type="hidden" name="accion" value="jugar">
		<input name="jugar" value="Jugar" type="submit" style="width: 84px; height: 33px">
	</form>
<% 
}else{
	
	%>
		<jsp:forward page="controlador">
			<jsp:param value="accion" name="Inicio"/>
		</jsp:forward>
	<% 
}
%>

</center>
</body>
</html>