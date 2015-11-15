import java.util.Scanner;
import usuarios.CtrlUsr;

public class PresUsr {
	
	private Scanner input;
	private CtrlUsr cu;
	
	//Opcions del menu de gestio d'usuaris
	public static final int OP_MODUS = 1;
	public static final int OP_MODPASS = 2;
	public static final int OP_BORCU = 3;
	public static final int OP_LOGOUT = 4;
	
	/*Constructor*/
	public PresUsr() {
		cu = new CtrlUsr();
	}
	
	/*Modificadores|voids*/
	public void setInput(Scanner input) {
		this.input = input;
	}
	
	//Funci� per loguejar un usuari
	public void login() {
		System.out.println("Login:");
		System.out.println("Escribe tu nombre de usuario:");
		String u = input.next();
		System.out.println("Escribe tu password:");
		String p = input.next();
		boolean existeix = cu.logUsuari(u,p);
		if (existeix){
			System.out.println("Login correcto, por favor, seleccione una opcion");
		}
		else {
			System.out.println("Login incorrecto, por favor, vuelva a introducir sus datos:");
			login();
		}
	}
	
	//Funcio per registrar un nou usuari
	public void register() {
		System.out.println("Registro:");
		System.out.println("Escribe tu nombre de usuario:");
		String u = input.next();
		System.out.println("Escribe tu password:");
		String p = input.next();
		System.out.println("Confirma tu password:");
		String p2 = input.next();
		boolean registrat = cu.afegirUsuari(u,p);
		if(registrat & p.equals(p2)){
			System.out.println("Se ha registrado correctamente:");
		}
		else{
			System.out.println("Las password no coinciden o el nombre de usuario ya existe.");
			System.out.println("Por favor, vuelve a intentarlo:");
			register();
		}
	}
	
	//Menu de gestio d'usuaris
	public void usrs(){
		
		System.out.println("Indique que gestion quiere hacer:");
		System.out.println(OP_MODUS + ". Modificar su nombre de usuario");
		System.out.println(OP_MODPASS + ". Modificar su contrase�a");
		System.out.println(OP_BORCU + ". Borrar Cuenta");
		System.out.println(OP_LOGOUT + ". Salir al menu principal");
			
		int op = input.nextInt();
			
		switch (op) {
		case OP_MODUS:
			System.out.println("Modificar Nombre de Usuario:");
			modUs();
			break;
			
		case OP_MODPASS:
			System.out.println("Modificar Contrase�a:");
			modPass();
			break;
				
		case OP_BORCU:
			System.out.println("Borrar Cuenta:");
			borrCu();
			break;
				
		case OP_LOGOUT:
			System.out.println("Volviendo al menu de inicio...");
			break;
				
		default:
			System.out.println("Opcion incorrecta");
			break;
		}
	}
	
	//Funci� de modificaci� d'usuaris
	public void modUs(){
		System.out.println("Escribe el nombre de Usuario actual:");
		String u = input.next();
		System.out.println("Escribe su nuevo nombre:");
		String u2 = input.next();
		boolean canviat = cu.modUsr(u, u2);
		if (canviat) System.out.println("Nombre de Usuario actualizado");
		else {
			System.out.println("El nombre de usuario ya existe. Por favor, vuelve a intentarlo:");
			modUs();
		}
	}
	
	//Funci� de modificar Password
	public void modPass(){
		System.out.println("Escribe su Contrase�a actual:");
		String p = input.next();
		System.out.println("Escribe su nueva Contrase�a:");
		String p2 = input.next();
		boolean canviada = cu.modPass(p,p2);
		if (canviada)System.out.println("Contrase�a actualizada");
		else {
			System.out.println("La Contrase�a no corresponde a su Contrase�a actual. Por favor, vuelve a intentarlo:");
			modPass();
		}
	}
	
	//Funci� per esborrar un usuari
	public void borrCu(){
		
	}
}
