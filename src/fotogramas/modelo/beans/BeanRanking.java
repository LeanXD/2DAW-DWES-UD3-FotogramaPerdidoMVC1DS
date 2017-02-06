package fotogramas.modelo.beans;

public class BeanRanking {
	public String login;
	public int puntos;
	
	public BeanRanking(String login, int puntos) {
		super();
		this.login = login;
		this.puntos = puntos;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	
}
