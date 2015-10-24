import java.util.Scanner;

public class Main {
	
	public static final int OP_LOGIN = 1;
	public static final int OP_REGISTER = 2;
	public static final int OP_EXIT = 3;
	
	private static CtrPresUser cpu		= new CtrPresUser();
	private static CtrPresHidato cph	= new CtrPresHidato();
	private static CtrPresRanquing cpr	= new CtrPresRanquing();
	
	public static void main(String[] args) {
		Scanner input 	= new Scanner(System.in);
		cpu.setInput(input);
		boolean b = true;
		int op = 0;
		
		while(b) {
			System.out.println("\nOpciones:");
			System.out.println(OP_LOGIN + ". Entrar");
			System.out.println(OP_REGISTER + ". Registrarse");
			System.out.println(OP_EXIT + ". Salir");
			
			if (!input.hasNextInt()) {
				input.next();
				op = 0;
			}
			else op = input.nextInt();
			
			switch (op) {
			case OP_LOGIN:
				System.out.println("Hola de nuevo");
				cpu.login();
				break;
			
			case OP_REGISTER:
				System.out.println("Bienvenido");
				cpu.register();
				break;
			
			case OP_EXIT:
				System.out.println("Cerrando...");
				input.close();
				b = false;
				break;
			
			default:
				System.out.println("Opcion incorrecta");
				break;
			}

		}
	}

}
