package usuarios;

public class ControladorDomini {
	
	private UsuarioDom current;
	
	public void actUsuari(UsuarioDom u){
		current=u;
	}
	
	public UsuarioDom getUsr(){
		return current;
	}
}
