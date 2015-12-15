package hidato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import score.CtrlRanking;

public class CtrlPartida {
	private String usr;
	private Partida actual;
	
	//Creadora
	
	//1=Partida Random
	//2=Partida Personalitzada
	//3=Partida del Repositori
	//4=Carregar partida comensada
	//Tant en l'opcio 3 i 4, utilitzarem un id per indicara a quin taulell/partida ens referim dins del repositori
	public CtrlPartida(String usuari, int tipus_par, int mida, int forats, int fixos, String id){
		usr = new String(usuari);
		
		if(tipus_par==1)		actual = partidaAleatoria();
		else if (tipus_par==2)	actual = partidaCustom(mida, forats, fixos);
		else if (tipus_par==3)	actual = partidaRepo(id);
		else					actual = partidaCarregada(id);
	}
	
	//1.- Partida Aleatoria
	private Partida partidaAleatoria() {
		Random aux = new Random();
		int x = aux.nextInt(12)+4;
		TaulellHidato taulellh = crearTaulellRandom((x*x)/5, (x*x)/3, x);
		Contador temps = new Contador();
		temps.iniciar();
		Integer score = 0;
		int penal = 0;
		
		return new Partida(score, temps, taulellh, penal);
	}
	
	//2.- Partida Personalitzada
	private Partida partidaCustom(int forats, int mida, int fixos) { //anadir entrada de mas datos desde las Vistas
		TaulellHidato taulellh = crearTaulellRandom(forats, fixos, mida);
		
		return new Partida(taulellh);
	}
	
	//3.- Partida del Repositori
	private Partida partidaRepo(String id) {
		GestorTaulells tau = new GestorTaulells();
		int[][] input = tau.getTaulell(id,usr);
		TaulellHidato aux = fesTaulell(input);
		
		if(aux.getMida()>0) return new Partida(aux);
		
		System.out.println("Taulell Invalid");
		return null;
	}
	
	//4.- Partida Carregada
	private Partida partidaCarregada(String id) {
		GestorPartides par = new GestorPartides();
		Partida aux = par.getPartida(id,usr);
		return new Partida(aux);
	}
	
	public boolean guardarPartida(String id){
		GestorPartides aux = new GestorPartides();
		System.out.println(usr);
		return aux.guardarPartida(id,actual,usr);
	}
	
	public void guardarRanking(CtrlRanking r){
		actual.setScore();
		int score = actual.getScore();
		int dificultad = 1;
		r.saveScore(dificultad, score, usr);
	}
	
	//Si el valor == 0, aleshores esborrem una casella
	//Si el retorn es fals, es que la casella no es pot modificar!!!
	//En cas que s'haigui modificat, si el valor de retorn==1, indica que hem rellenat tot el taulell
	public boolean[] modificarCas(int fila, int columna, int valor){//---------------------------------------------------------------
		return actual.modCas(fila, columna, valor);
	}
		
	
	//Consultores
	public int[][] mostrarTaulell(){
		return actual.getTauActual();		
	}
	
	public int getTime(){
		return actual.getTime();
	}
	
	public int getScore(){
		return actual.getScore();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	private TaulellHidato fesTaulell (int[][] input) {
		TaulellHidato th = null;
		
		int tamany = input.length;
		int[] given = null;
		int[] start = null;
		
		int perposar = 0;
		int posats = 0;
		
        List<Integer> list = new ArrayList<Integer>();
        Casilla[][] matriuJoc = new Casilla[tamany][tamany];
 
        int[][] matriuSolucio = new int[input.length+2][input[0].length+2];
        
        for (int x=0; x<tamany+2;  ++x){
        	matriuSolucio[0][x] = -1;
        	matriuSolucio[x][0] = -1;
        	matriuSolucio[tamany+1][x] = -1;
        	matriuSolucio[x][tamany+1] = -1;
        }
        
        for(int i=1; i<tamany+1; ++i){
        	for (int j=1;j<tamany+1; ++j){
        		matriuSolucio[i][j]= input[i-1][j-1];
        		if (input[i-1][j-1]==-1){
        			matriuJoc[i-1][j-1].setPosicion(j-1,i-1);
        			matriuJoc[i-1][j-1].setValor(-1);
        			matriuJoc[i-1][j-1].setEditable(false);
        		}
        		else if (input[i-1][j-1]==0){
        			matriuJoc[i-1][j-1].setPosicion(j-1,i-1);
        			matriuJoc[i-1][j-1].setValor(0);
        			matriuJoc[i-1][j-1].setEditable(true);
        			++perposar;
        		}
        		else {
        			matriuJoc[i-1][j-1].setPosicion(j-1,i-1);
        			matriuJoc[i-1][j-1].setValor(input[i-1][j-1]);
        			matriuJoc[i-1][j-1].setEditable(false);
        			list.add(input[i-1][j-1]);
                    if (input[i-1][j-1] == 1) start = new int[]{i,j};
        		}
        	}
        }
        
        Collections.sort(list);

        given = new int[list.size()];
        for (int i = 0; i < given.length; i++){
            given[i] = list.get(i);
            System.out.println(given[i]);
        }
        
        CtrlAlgorismes ca = new CtrlAlgorismes();
        if((start != null) && (given != null) && (matriuSolucio != null)) System.out.println("\nSolucionant...");
        else System.out.println("\nHi ha hagut un error");
        
        
        if(ca.solucionador(start[0],start[1],1,0,given,matriuSolucio))
        		th = new TaulellHidato(matriuJoc, matriuSolucio, given, start, perposar, posats);
        
        return th;
	}
	
	private TaulellHidato crearTaulellRandom(int forats, int nombresFixos, int tamany){
		int perposar=(tamany*tamany)-forats-2-nombresFixos;
		int posats = 0;
		int[] given = null;
		int[] start = null;
		List<Integer> list = new ArrayList<Integer>();
		
		CtrlAlgorismes ca = new CtrlAlgorismes();
		
		int[][] input = new int[tamany][tamany];
		boolean[][] inputb = new boolean [tamany][tamany];
		
		
		for (int i=0; i<tamany; ++i){
			for (int j=0;j<tamany; ++j){
				inputb[i][j] = true;
				input[i][j] = 0;
			}
		}
		
		ca.generador(forats, nombresFixos, tamany, input, inputb);
		
		int[][] matriuSolucio = new int[input.length+2][input[0].length+2];
		Casilla[][] matriuJoc = new Casilla[tamany][tamany];
		
        for (int x=0; x<tamany+2;  ++x){
        	matriuSolucio[0][x] = -1;
        	matriuSolucio[x][0] = -1;
        	matriuSolucio[tamany+1][x] = -1;
        	matriuSolucio[x][tamany+1] = -1;
        }
		
        for(int i=1; i<tamany+1; ++i){
        	for (int j=1;j<tamany+1; ++j){
        		matriuSolucio[i][j]= input[i-1][j-1];

        		matriuJoc[i-1][j-1].setPosicion(j-1,i-1);
    			matriuJoc[i-1][j-1].setEditable(inputb[i-1][j-1]);
    			if(inputb[i-1][j-1]) matriuJoc[i-1][j-1].setValor(0);
    			else {
    				matriuJoc[i-1][j-1].setValor(input[i-1][j-1]);
    				if (input[i-1][j-1]!=-1) list.add(input[i-1][j-1]);
    				if (input[i-1][j-1]==1) start = new int[]{i,j};
    			}
        	}
        }
        
        Collections.sort(list);
        given = new int[list.size()];
        for (int i = 0; i < given.length; i++){
            given[i] = list.get(i);
            System.out.println(given[i]);
        }
        
        return new TaulellHidato(matriuJoc, matriuSolucio, given, start, perposar, posats);
	}
}

