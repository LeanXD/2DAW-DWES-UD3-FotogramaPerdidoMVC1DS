<%@page import="fotogramas.modelo.beans.BeanUsuario"%>
<%@page import="fotogramas.modelo.beans.BeanPartida"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Partida</title>
</head>
<%
	BeanPartida partida = (BeanPartida) session.getAttribute("partida");
	BeanUsuario usuario = (BeanUsuario) session.getAttribute("usuario");
	int ordenBotones = (int) (Math.random()*3+1);
%>
<body>
<center>
	 <P><jsp:include page="head.jsp" flush="true"/></P>
	 <br/>
	 <br/> 
	 <%
	 	if(!partida.isFinPartida()){
	 	%>
	 	<img src="images/<%=partida.getRutaImagen()%>" width="500px" height="500px" >		
	 	<br/>
	 	<br/>
	 	<form action="controlador" method="post">
	 	<%
	 	//Mediante este control ordenaremos los botones y se diferente cada partida
	 		if(ordenBotones % 2 == 0){
	 			%>
	 				<input type="submit" name="respuesta" value="<%=partida.getNombrePeli()%>"> 
	 				<br/>
	 				<br/>
	 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(0)%>"> 
	 				<br/>
	 				<br/>
	 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(1)%>"> 
	 				<br/>
	 				<br/>
	 			<%
	 		}else if(ordenBotones % 3 == 0){
	 			%>
 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(0)%>"> 
 				<br/>
 				<br/>
 				<input type="submit" name="respuesta" value="<%=partida.getNombrePeli()%>"> 
 				<br/>
 				<br/>
 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(1)%>"> 
 				<br/>
 				<br/>
 			<%
	 			
	 		}else if(ordenBotones == 1){
	 			%>
 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(1)%>"> 
 				<br/>
 				<br/>
 				<input type="submit" name="respuesta" value="<%=partida.getNombresOpciones().get(0)%>"> 
 				<br/>
 				<br/>
 				<input type="submit" name="respuesta" value="<%=partida.getNombrePeli()%>"> 
 				<br/>
 				<br/>
 			<%
	 		}
	 	 %>
	 	 <input type="hidden" name="accion" value="jugar">
	 	</form>
	 	<br/>
	 	<br/>
	 	<form action="controlador" method="post">
	 		<input type="submit" name="accion" value="Fin Partida">
	 	</form>		
	 	<% 
	 	}else{
	 	%>
	 	<h3>Fin de Partida</h3>
	 	<label style="color: purple;">Jugador: <%=usuario.getLogin()%></label>
	 	<br/>
	 	<br/>
	 	<label style="color: blue;">Puntos Conseguidos: <%=Integer.toString(partida.getPuntos())%></label>
	 	<br/>
	 	<br/>
	 	<label style="color: green;">Aciertos: <%=Integer.toString(partida.getAciertos())%></label>
	 	<br/>
	 	<br/>
	 	<label style="color: red;">Perdidos: <%=Integer.toString(partida.getFalladas())%></label>
	 	<br/>
	 	<br/>
	 	<form action="controlador" method="post">
	 		<input type="submit" name="accion" value="Volver">
	 	</form>
	 	
	 	<%	
	 	}
	 %>
</center>
</body>
</html>