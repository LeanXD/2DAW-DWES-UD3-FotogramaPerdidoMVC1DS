package fotogramas.modelo.acciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.BeanError;
import fotogramas.modelo.beans.BeanPartida;
import fotogramas.modelo.beans.BeanUsuario;

public class AccionPartida implements Accion{

	// Aquí se deben declarar las propiedades de la acción
		private String vista;
		private  String vistaOK = "WEB-INF/partida.jsp";
		private final String vistaError = "gesError.jsp";
		private BeanPartida modelo = new BeanPartida();
		
		// Estas variables las necesitan todas las acciones 
		private ServletContext sc;
		private HttpSession sesion;
		private DataSource DS;	
		private fotogramas.modelo.beans.BeanError error;
		private String opcionSelect;
		private boolean resultado;
		
		
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conexion = (Connection) sesion.getAttribute("conexion");
		Statement st;
		String accion = (String) request.getParameter("accion");
		if(conexion!=null){
			modelo = (BeanPartida) sesion.getAttribute("partida");
			if(modelo!=null){
				try {
						st = conexion.createStatement();
					//Controlamos si el usuario ha pulsado finalizar la partida
					if(accion!=null){
						if(accion.equals("Fin Partida")){
							modelo.setFinPartida(true);
						}
					}
					if(modelo.isFinPartida()){
						sesion.setAttribute("partida", FinalizarPartida(st));
						resultado = true;
					}else{
						ComprobarRespuesta(request);
						if(modelo.getPorAdivinar().size()>0){						
							sesion.setAttribute("partida", SiguienteImagen(st));
							resultado = true;
						}else{
							sesion.setAttribute("partida", FinalizarPartida(st));
							resultado = true;
						}		
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultado = false;
				}
			}else{
				try {
					st = conexion.createStatement();
					sesion.setAttribute("partida", CrearPartida(st));
					resultado = true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultado = false;
				}
			}
			
		}else{
			vistaOK = "index.html";
			resultado = true;
		}
		
		if(resultado){
			vista = vistaOK;
		}else{
			vista = vistaError;
		}
		
		return resultado;
	}

	@Override
	public String getVista() {
		// TODO Auto-generated method stub
		return this.vista;
	}

	@Override
	public Object getModelo() {
		// TODO Auto-generated method stub
		return this.modelo;
	}

	@Override
	public void setSc(ServletContext sc) {
		// TODO Auto-generated method stub
		this.sc = sc;
	}

	@Override
	public BeanError getError() {
		// TODO Auto-generated method stub
		return this.error;
	}

	@Override
	public void setDS(DataSource ds) {
		// TODO Auto-generated method stub
		this.DS = ds;
	}

	@Override
	public void setSesion(HttpSession sesion) {
		// TODO Auto-generated method stub
		this.sesion = sesion;
	}
	
	public BeanPartida CrearPartida(Statement st){
		BeanPartida partida = new BeanPartida();
		boolean borrado = false;
		int aleatorio = 0;
		int n = 0;
		try {
			//Añadimos una partida jugada
			partida.setPartidasJugadas();
			
			ResultSet rs = st.executeQuery("SELECT idFotograma, titPelicula FROM fotogramas");
			//Obtenemos todas las peliculas.
			while(rs.next()){
				partida.addOpciones(rs.getString("titPelicula"));
				partida.addPorAdivinar(rs.getInt("idFotograma"));
			}
			//Obtenemos el número aleatorio de la posición de la película para obtener
			aleatorio = (int) (Math.random()*partida.getPorAdivinar().size()+0);
			partida.setidPeli(partida.getPorAdivinar().get(aleatorio));
			//Borramo la película seleccionada.
			partida.delePorAdivinar(aleatorio);
			
			rs = st.executeQuery("SELECT titPelicula, archivo FROM fotogramas WHERE idFotograma = "+Integer.toString(partida.getidPeli()));
			rs.next();
			partida.setNombrePeli(rs.getString("titPelicula"));
			partida.setRutaImagen(rs.getString("archivo"));
			
			//Obtenemos las dos opciones que acompañara a la opción correcta
			while(n<2){
				//Para que se ha diferente las opciones obtenemos un número aleatorio
				aleatorio = (int) (Math.random()*partida.getOpciones().size()+0);
				//Controlamos que no se repita las opciones
				if(partida.getNombresOpciones().size()>0){
					if(!partida.getNombresOpciones().get(0).equals(partida.getOpciones().get(aleatorio))){
						n++;
						partida.addNombreOpciones(partida.getOpciones().get(aleatorio));
					}
				}else{
					partida.addNombreOpciones(partida.getOpciones().get(aleatorio));
					n++;
				}
			}
			//Borramos la película elegida Antigua Forma. 
			//i = 0;
			/*while(!borrado && i<partida.getPorAdivinar().size()){
				if(partida.getPorAdivinar().get(i)==partida.getidPeli()){
					partida.delePorAdivinar(i);
					borrado = true;
				}
				i++;
			}*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return partida;
	}
	
	public void ComprobarRespuesta(HttpServletRequest request){
		String repuesta = (String) request.getParameter("respuesta");
		if(repuesta.equals(this.modelo.getNombrePeli())){
			this.modelo.setAciertos();
			this.modelo.setPuntos();
		}else{
			this.modelo.setFalladas();
		}
	}
	
	public BeanPartida SiguienteImagen(Statement st){
		int aleatorio = 0;
		int i = 0;
		int n = 0;
		ResultSet rs = null;
		//Añadimos una partida jugada
		this.modelo.setPartidasJugadas();
		//Limpiamos las opciones anteriores que podia seleccionar el usuario
		this.modelo.deteNombreOpciones();
		//Obtenemos el número aleatorio de la posición de la película para obtener
		aleatorio = (int) (Math.random()*this.modelo.getPorAdivinar().size()+0);
		this. modelo.setidPeli(this.modelo.getPorAdivinar().get(aleatorio));
		//Borramos la película seleccionada.
		this.modelo.delePorAdivinar(aleatorio);
		try{	
			rs = st.executeQuery("SELECT titPelicula, archivo FROM fotogramas WHERE idFotograma = "+Integer.toString(this.modelo.getidPeli()));
			rs.next();
			this.modelo.setNombrePeli(rs.getString("titPelicula"));
			this.modelo.setRutaImagen(rs.getString("archivo"));
			
			//Obtenemos las dos opciones que acompañara a la opción correcta
			while(n<2){
				//Para que se ha diferente las opciones obtenemos un número aleatorio
				aleatorio = (int) (Math.random()*this.modelo.getOpciones().size()+0);
				if(!this.modelo.getNombrePeli().equals(this.modelo.getOpciones().get(aleatorio))){
					//Controlamos que no se repita las opciones
					if(this.modelo.getNombresOpciones().size()>0){
						if(!this.modelo.getNombresOpciones().get(0).equals(this.modelo.getOpciones().get(aleatorio))){
							n++;
							this.modelo.addNombreOpciones(this.modelo.getOpciones().get(aleatorio));
						}
					}else{
						this.modelo.addNombreOpciones(this.modelo.getOpciones().get(aleatorio));
						n++;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return this.modelo;
	}
	
	public BeanPartida FinalizarPartida(Statement st){
		ResultSet rs = null;
		BeanUsuario datosUsario = (BeanUsuario) sesion.getAttribute("usuario");
		String puntosUsuario = (String) sesion.getAttribute("puntos");
		String puntuacion = "";
		if(this.modelo.getPuntos()>Integer.parseInt(puntosUsuario)){
			//Actualizamos los puntos del usuario
			try {
				puntuacion = Integer.toString(Integer.parseInt(puntosUsuario)+this.modelo.getPuntos());
				st.executeUpdate("UPDATE ranking SET puntos = "+ puntuacion +" WHERE login = '"+ datosUsario.getLogin() +"'");
				this.sesion.setAttribute("puntos", puntuacion);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.modelo.setFinPartida(true);
		
		return this.modelo;
	}
	

}
