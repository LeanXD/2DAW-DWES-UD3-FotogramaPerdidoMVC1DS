/**
 * Acción: procesar login usuario
 */
package fotogramas.modelo.acciones;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.*;
import sun.tracing.NullProviderFactory;

/**
 * Procesa los datos de login y clave proporcionados por un usuario.
 * @author Eduardo A. Ponce
 */

public class AccionLogin implements Accion {

	// Aquí se deben declarar las propiedades de la acción
	private String vista;
	private final String vistaOK = "WEB-INF/concurso.jsp";
	private String vistaError = "gesError.jsp";
	//private final String vistaForm= "login.jsp";
	private BeanUsuario modelo = new BeanUsuario();
	
	// Estas variables las necesitan todas las acciones 
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private fotogramas.modelo.beans.BeanError error;
	
	/**
	 * Constructor por defecto
	 */
	public AccionLogin()
	{

	}
	/** 
	 * Ejecuta el proceso asociado a la acción.
	 * @param request Objeto que encapsula la petición.
	 * @param response Objeto que encapsula la respuesta.
	 * @return true o false en función de que no se hayan producido errores o lo contrario.
	 * @see fotogramas.controlador.Accion#ejecutar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		//Se debe implementar ajustándose al uso de datasource	
		String login, clave, puntos;
		
		//Si la accion es login, se valida el login.
		//Se obtienen login y clave0
		login = request.getParameter("login");
		clave = request.getParameter("clave");
		if(login!=null && clave != null){
		try {
			conexion = DS.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select login, clave from usuarios where login = '"+login+"'");
			if(rs.next()){
				if (!rs.getString("clave").equals(clave)) {
					vistaError = "login.jsp";
					request.setAttribute("ErrorLogin", "La clave no coincide.");
					resultado = false;
				}else{
					resultado = true;
					System.out.println("Aqui");
					//Creamos el modelo
					modelo = new BeanUsuario(rs.getString("login"), rs.getString("clave"));
					
					//Obtenemos la puntuación del usuario para ser mostrada;
					rs = st.executeQuery("select puntos from ranking where login = '"+login+"'");
					rs.next();	
					//Controlamos si el usuario no a jugado ninguna partida aun
					try{
						puntos = Integer.toString(rs.getInt("puntos"));
					}catch(NumberFormatException e){
						puntos = "0";
					}
					sesion.setAttribute("puntos", puntos);						
					//Guardamos la conexión para futuras consultas
					sesion.setAttribute("conexion", conexion);
					
					//Aun que el controlador guarde el modelo en la sesión,
					//yo lo guardo para poder ser utilizado en toda la página
					
					sesion.setAttribute("usuario", modelo);
				}
			}else{
				vistaError = "login.jsp";
				System.out.println("aqui");
				request.setAttribute("ErrorLogin", "El usuario no existe.");
				resultado = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			error = new BeanError(1,"Error en conexión a base de datos",e);
			resultado = false;
		}
		}else{
			resultado = false;
			vistaError = "login.jsp";
		}
		if (resultado==true)
			vista = vistaOK;
		else
			vista = vistaError;
		return resultado;
	}

	/**
	* Devuelve la vista actual.
	* @param La vista a devolver al usuario.
	*/
	public String getVista() 
	{
		return vista;
	}
	/**
	 * Establece la vista
	 * @param vista Página que se enviará al usuario
	 */
	public void setVista(String vista)
	{
		this.vista = vista;
	}
	/**
	 * Devuelve el modelo con el que trabajará la vista.
	 * @return El modelo a procesar por la vista. 
	 */
	public Object getModelo() 
	{
		return modelo;
	}
	/**
	 * Establece el modelo con el que trabajará  la vista.
	 * @param modelo Conjunto de datos a procesar por la vista.
	 */
	public void setModelo(BeanUsuario modelo)
	{
	    this.modelo = modelo;
	}
	/**
	 * Establece el contexto del servlet (nivel aplicación)
	 * @param sc Objeto ServletContext que encapsula el ámbito de aplicación.
	 */
	public void setSc(ServletContext sc) 
	{
		this.sc = sc;
	}
	/**
	 * Devuelve el contexto del servlet (nivel aplicación)
	 * @return Objeto que encapsula el nivel de aplicación.
	 */
	public ServletContext getSc()
	{
	    return sc;
	}
	/**
	 * Establece una situación de error.
	 * @param error Objeto BeanError que encapsula el error.
	 */
	public void setError(fotogramas.modelo.beans.BeanError error)
	{
	    this.error = error;
	}
	/**
	 * Devuelve un objeto de error asociado al procesamiento de la acción.
	 * @return Objeto que encapsula una situación de error producida durante
	 * la ejecución de la acción.
	 */
	public BeanError getError() {
		return error;
	}
	/**
	 * Establece el objeto que encapsula la sesión actual.
	 * @param sesion La sesión a establecer en la acción.
	 */

	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}
	/**
	 * Devuelve la sesión actual del usuario.
	 * @return sesion La sesión actual del usuario.
	 */

	public HttpSession getSesion() {
		return sesion;
	}
	
	/**
	 * Establece el datasource con el que se trabajará
	 * @param ds Datasource
	 */
	public void setDS(DataSource ds)
	{
		DS = ds;
	}
}
