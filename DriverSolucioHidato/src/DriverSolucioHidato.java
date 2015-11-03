import java.util.Scanner;

public class DriverSolucioHidato {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Introdueixi la mida:");
		int mida = input.nextInt();
		TaulellHidato aux = new TaulellHidato(mida);
		
		System.out.println("Introdueixi les posicions dels forats (fila i columna del taulell) una darrere de l'altra: (Acabi les posicions amb -1)");
		
		int fila;
		int columna;
		
		while ((fila=input.nextInt())!=-1){		//Introduime els forats al taulell
			columna = input.nextInt();
			aux.intForat(fila,columna);
		}
		
		System.out.println("Introdueixi les posicions amb els valors una darrere de l'altra: (Acabi les posicions amb -1)");
		
		int valor;
		
		while ((fila=input.nextInt())!=-1){		//Introduime els forats al taulell
			columna = input.nextInt();
			valor = input.nextInt();
			aux.modifica_valor(valor, fila, columna, false);
		}
		
		SolHidato part = new SolHidato(aux);
		aux = part.resolt();
		
	}

}
