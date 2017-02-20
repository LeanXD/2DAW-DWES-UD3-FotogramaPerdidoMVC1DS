/**
 * Instancia objetos de tipo Acción.
 * Es una clase abstracta que impide que se puedan instanciar objetos de ella,
 * pero permite que se obtengan clases derivadas.
 * Se encarga de obtener los objetos Acción específicos para una determinada acción.
 */
package fotogramas.controlador;

import fotogramas.modelo.acciones.*;

/**
 * Factoría que devuelve los objetos Accion que
 * procesarán las peticiones
 * @author Eduardo A. Ponce
 * @version 2.0
 */
public abstract class FactoriaAcciones {
	public static Accion creaAccion(String accion)
	  {
		// Acción por defecto. Conduce a index.html. Puede escogerse otra.
		Accion accionF = new AccionIndex();
	    // Devuelve objetos Accion en función del parámetro de acción proporcionado 
		// Este código puede modificarse a libre elección
		if(accion!=null){
		    if (accion.equals("login"))
		      accionF = new AccionLogin();
		    if (accion.equals("Entrar"))
		      accionF = new AccionLogin();
		    if (accion.equals("Inicio"))
		      accionF = new AccionIndex();
		    if(accion.equals("Volver"))
		    	accionF = new AccionConcurso();
		    if (accion.equals("registrar"))
		      accionF = new AccionRegistro();
		    if(accion.equals("Ranking"))
		    	accionF = new AccionRanking();
		}
	    return accionF;
	  }

}
