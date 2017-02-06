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

public class AccionRegistro implements Accion {
	
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource ds;
	private String vistaOK = "login.jsp";
	private String vistaError = "gesError.jsp";
	private String vista;
	private fotogramas.modelo.beans.BeanUsuario modelo;
	private fotogramas.modelo.beans.BeanError error;

	
	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean resultado = false;
		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		Connection conexion = null;
		Statement st = null;
		ResultSet rl = null;
		int sy_code = 0;
		if(login!=null && clave!=null){
		try {
			conexion = ds.getConnection();
			st = conexion.createStatement();
			rl = st.executeQuery("SELECT login, clave FROM usuarios WHERE login = '"+login+"'");
			if(rl!=null){
				if(!rl.getString("login").equals(login)){
					sy_code = st.executeUpdate("INSERT INTO USUARIOS (login, clase) VALUES ('"+login+"', '"+clave+"')");
					if(sy_code == 0){
						
						//Insertamos al usuario en el ranking para mostrarlo en el ranking
						st.executeUpdate("INSERT INTO RANKING (login, puntos) VALUES ('"+login+"', 0)");
						resultado = true;
					}else{
						resultado = false;
						error = new BeanError(1,"Error en el registro");
					}
				}else{
					request.setAttribute("ErrorRegistrado", "El usuario introducido ya existe");
					vistaError = "registro.jsp";
					resultado = true;
				}
			}else{
				vistaError = "registro.jsp";
				resultado = false;
				request.setAttribute("ErrorRegistrado", "El usuario introducido ya existe");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error = new BeanError(1,"Error en conexión a base de datos",e);
			resultado = false;
		}
		}else{
			vistaOK = "registro.jsp";
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
		this.ds = ds;
	}

	@Override
	public void setSesion(HttpSession sesion) {
		// TODO Auto-generated method stub
		this.sesion = sesion;
	}
	

}
