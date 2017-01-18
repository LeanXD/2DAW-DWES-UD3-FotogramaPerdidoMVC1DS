/**
 * 
 */
package fotogramas.modelo.beans;

import java.io.*;

/**
 * @author Eduardo A. Ponce
 *
 */
@SuppressWarnings("serial")
public class BeanUsuario implements Serializable {
	//Propiedades de la clase
	// Datos asociados al usuario
	private String login;
	private String clave;
	private String nombre;
	private String apellidos;
	private String email;
	private int edad;
	// Indica si la instancia recoge los datos de un usuario
	private boolean encontrado = false;
	
	/**
	 * Constructor por defecto.
	 */
	public BeanUsuario()
	{
		
	}
	
	/**
	 * Constructor con parámetros
	 */
	public BeanUsuario(String login, String clave, String nombre, String apellidos, String email, int edad)
	{
		this.login = login;
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.edad = edad;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param encontrado the encontrado to set
	 */
	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}
	/**
	 * @return the encontrado
	 */
	public boolean isEncontrado() {
		return encontrado;
	}

	//Métodos getter y setter
	
}
