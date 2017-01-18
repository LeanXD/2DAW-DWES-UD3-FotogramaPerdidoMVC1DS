/**
 * Permite crear un objeto de ayuda que acepta el objeto de solicitud
 * en su constructor. Proporciona funcionalidad adicional a la solicitud.
 */
package fotogramas.controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Prepara la petición para que se pueda extraer de ella la acción correspondiente.
 * @author Eduardo A. Ponce
 * @version 2.0
 */
public class AyudaSolicitud {
	
	  /**
	   * La petición de la que se deducirá la acción a llevar a cabo
	   */
	  HttpServletRequest request;
	  
	  /**
	   * Constructor, que recibe la petición para ser procesada posteriormente
	   * @param request La petición
	   */
	  public AyudaSolicitud(HttpServletRequest request)
	    throws ServletException, IOException
	  {
	    this.request = request;
	  }
	  
	  /**
	   * Devuelve un objeto de tipo Accion que procesará
	   * la petición recibida
	   * @return Objeto de tipo Accion
	   */
	  public Accion getAccion()
	  {
	    String accion = (String) request.getParameter("accion");
	    System.out.println("Accion: "+accion);
	    // Antes de crear la acción puede haber procesamiento previo de la
	    // solicitud, para dejarla preparada para el método creaAccion().
	    return FactoriaAcciones.creaAccion(accion);
	  }

}
