<%@page import="fotogramas.modelo.beans.BeanUsuario"%>
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
	ArrayList<BeanRanking> modelo = (ArrayList<BeanRanking>) request.getAttribute("modelo");
	String puntos;
	//Obtenemos tan solo el nombre del usuario registrado
	String usuario = ((BeanUsuario) session.getAttribute("usuario")).getLogin();
	String error = (String) session.getAttribute("errorRanking");
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
		<table border = 5px aling="center" bordercolor="gray" width="50%" height="50%" >
			<tr>
				<td align="center" ><b>Puesto</td>
				<td align="center" ><b>Jugador</td>
				<td align="center" ><b>Puntos</td>
			</tr>
		<%for(int i = 0; i<modelo.size(); i++){ 
			%>
			<tr>
			<%
			//Controlamos que la puntación no se ha nula, si lo es la puntuación será cero
			jugador = modelo.get(i).getLogin();
			try{
				puntos = Integer.toString(modelo.get(i).getPuntos());
			}catch (NumberFormatException e){
				puntos = "0";
			}
			if(jugador.equals(usuario)){%>
				<td style= "color: red;" align="center"><%=Integer.toString(i+1)%></td>
				<td style= "color: blue;" align="center"><%=usuario %></td>
				<td style="color: green;" align="center"><%=puntos %></td>
			<%			
			}else{
			%>
			<td align="center"><%=Integer.toString(i+1) %></td>
			<td align="center"><%=jugador %></td>
			<td align="center"><%=puntos %></td>
		<%}
		%>
		</tr>
		<%	
		}%>
		</table>
	<%}else{ 
		if(error!=null){
		%>
			<p style="color: red;">No hay participantes</p>
	<%
		}
	} %>
	
<% 
}else{
	
	%>
		<jsp:forward page="controlador">
			<jsp:param value="accion" name="Inicio"/>
		</jsp:forward>
	<% 
}
%>
<br/>
<br/>
<form action="controlador" method="post">
		<input name="accion" value="Volver" type="submit" style="width: 84px; height: 33px">
	</form>
</center>
</body>
</html>