package usuarios;

public class Usuario {
	
	String username;
	String password;
	
	/*Constructora*/
	public Usuario(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	public String getUsr(){
		return username;
	}
	
	public String getPass(){
		return password;
	}
	
	public void setUsr(String usr){
		this.username = usr;
	}
	
	public void setPass(String pwd){
		this.password = pwd;
	}
}
