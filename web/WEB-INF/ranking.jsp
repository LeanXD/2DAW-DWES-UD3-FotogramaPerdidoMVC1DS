<%@page import="fotogramas.modelo.beans.BeanRanking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ranking de Juegadores</title>
</head>
<%Connection conexion = (Connection) session.getAttribute("conexion"); 
	ArrayList<BeanRanking> modelo = (ArrayList<BeanRanking>) session.getAttribute("modelo");
	String puntos;
	String usuario = (String) session.getAttribute("login");
	String jugador;
%>
<body>
<center>
<%
if(conexion != null){
%>
	 <P><jsp:include page="head.jsp" flush="true"/></P>
	 <br/>
	 <br/> 
	<%if(modelo!=null){ %>
		<table border = 10px >
			<tr>
				<td><b>Puesto</td>
				<td><b>Jugador</td>
				<td><b>Puntos</td>
			</tr>
			<tr>
		<%for(int i = 0; i<modelo.size(); i++){ 
			//Controlamos que la puntación no se ha nula, si lo es la puntuación será cero
			jugador = modelo.get(i).getLogin();
			try{
				puntos = Integer.toString(modelo.get(i).getPuntos());
			}catch (NumberFormatException e){
				puntos = "0";
			}
			if(jugador.equals(usuario)){%>
				<td style= "color: red;"><%=Integer.toString(i) %></td>
				<td style= "color: blue;"><%=usuario %></td>
				<td style="color: green;"><%=puntos %></td>
			<%			
			}else{
			%>
			<td><%=Integer.toString(i) %></td>
			<td><%=jugador %></td>
			<td><%=puntos %></td>
		<%}
		}%>
		</tr>
		</table>
	<%}else{ %>
		<p style="color: red;">No hay participantes</p>
	<%} %>
	
	<form action="controlador" method="post">
		<input name="accion" value="Volver" type="submit" style="width: 84px; height: 33px">
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
</body>
</html>