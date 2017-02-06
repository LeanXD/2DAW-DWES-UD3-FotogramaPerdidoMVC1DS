package fotogramas.modelo.acciones;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.BeanError;

public class AccionConcurso implements Accion {
		public String vistaOK = "WEB-INF/concurso.jsp";
		public String vista;
		public String vistaError = "index.html";
		private HttpSession sesion;
		
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if((Connection)sesion.getAttribute("conexion")!=null){
			vista = vistaOK;
		}else{
			vista = vistaError;
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
