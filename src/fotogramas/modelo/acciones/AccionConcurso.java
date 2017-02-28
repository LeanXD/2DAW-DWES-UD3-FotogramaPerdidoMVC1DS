package fotogramas.modelo.acciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.BeanError;
import fotogramas.modelo.beans.BeanPartida;

public class AccionConcurso implements Accion {
		public String vistaOK = "WEB-INF/concurso.jsp";
		public String vista;
		public String vistaError = "index.html";
		private HttpSession sesion;
		
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conexion = (Connection)sesion.getAttribute("conexion");
		if(conexion!=null){
			//Comprobamos si el usuario tiene alguna partida almacenada
			if((BeanPartida)sesion.getAttribute("partida")!=null){
				//Eliminamos la partida anterior.
				sesion.removeAttribute("partida");
			}
			try {
				if(conexion.isClosed()){
					vista = vistaError;
				}else{
					vista = vistaOK;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public String getVista() {
		// TODO Auto-generated method stub
		return this.vista;
	}

	@Override
	public Object getModelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSc(ServletContext sc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BeanError getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDS(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSesion(HttpSession sesion) {
		// TODO Auto-generated method stub
		this.sesion = sesion;
	}

}
