import java.util.Scanner;

public class DriverTimer {
	
	private static final int OP_CONF = 1;
	private static final int OP_SHOW = 2;
    private static final int OP_STOP = 3;
    private static final int OP_EXIT = 4;
    
    private static Contador menuNewContador(Scanner input) {
    	boolean b = true;
    	int valor = -1;
    	
    	Contador c;
    	
    	while(b) {
    		System.out.println("\nIntroduzca un valor inicial");
        	System.out.println("(0 para contar normal y mayor que 0 para cuenta atras):");
        	if(input.hasNextInt()) valor = input.nextInt();
        	if(valor < 0) System.out.println("Ese no es un valor valido.");
        	else b = false;
    	}
    	
    	if(valor > 0) c = new Contador(valor);
    	else c = new Contador();
    	
    	c.iniciar();
    	
    	return c;
    }
    
    private static void menuShowSec(Scanner input, Contador c) {
    	System.out.println("Segundos: " + c.getSegundos());
    }
    
    private static void menuStopContador(Scanner input, Contador c) {
    	c.detener();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Contador c = new Contador();
        
        boolean b = true;
        int op = 0;
        
        while(b) {
            System.out.println("\nOpciones:");
            System.out.println(OP_CONF + ". Configurar un nuevo contador");
            System.out.println(OP_SHOW + ". Mostrar los segundos");
            System.out.println(OP_STOP + ". Detener el contador");
            System.out.println(OP_EXIT + ". Salir");
            
            if (!input.hasNextInt()) {
                input.next();
                op = 0;
            }
            else op = input.nextInt();
            
            switch (op) {
            case OP_CONF:
                c = menuNewContador(input);
                break;
                
            case OP_SHOW:
                menuShowSec(input, c);
                break;
            
            case OP_STOP:
                menuStopContador(input, c);
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
