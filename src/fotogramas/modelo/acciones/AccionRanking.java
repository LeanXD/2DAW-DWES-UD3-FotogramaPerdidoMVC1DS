package fotogramas.modelo.acciones;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.BeanError;
import fotogramas.modelo.beans.BeanRanking;

public class AccionRanking implements Accion {
	
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource ds;
	private final String vistaOK = "WEB-INF/ranking.jsp";
	private String vistaError = "index.html";
	private String vista;
	private ArrayList<fotogramas.modelo.beans.BeanRanking> modelo;
	private fotogramas.modelo.beans.BeanError error;

	@Override
	public boolean ejecutar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = false;
		modelo = new ArrayList<fotogramas.modelo.beans.BeanRanking>();
		Connection conexion = (Connection) sesion.getAttribute("conexion") ;
		Statement st = null;
		ResultSet rl = null;
		//Comprobamos si el usuario sigue conectado
		if(conexion!=null){
			try {
				st = conexion.createStatement();
				rl = st.executeQuery("SELECT login, puntos FROM ranking ORDER BY puntos DESC");
				while(rl.next()){
					modelo.add(new BeanRanking(rl.getString("login"), rl.getInt("puntos")));
					System.out.println(rl.getString("login")+" "+Integer.toString(rl.getInt("puntos")));
				}
				if(modelo.size()<=0){	
					sesion.setAttribute("errorRanking", "No hay participantes");
				}
				result = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				result = false;
				error = new BeanError(2,e.toString());
				vistaError = "gesError.jsp";
			}
		}else{
			result = false;
			sesion.setAttribute("error", "Se cerro la sesi&oacute;n");
		}
		
		if(result){
			vista = vistaOK;
		}else{
			vista = vistaError;
		}
		
		return result;
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
