import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaulellHidato extends Taulell {
	
	private Casilla Matriu_joc [][];
	private int Matriu_solucio [][];
	private int[] given;
	private int[] start;
	private int tamany;

	public TaulellHidato(int tam) {
		super(tam);
		tamany = tam;
		Matriu_joc = new Casilla[tam][tam];
		for (int i=0; i<tam;++i){
			for (int j=0; j<tam; ++j){
				Matriu_joc[i][j] = new Casilla();
				Matriu_joc[i][j].setValor(0);
			}
		}
	}
	
	public boolean festaulell(int[][] input) {
		 
        List<Integer> list = new ArrayList<Integer>();
 
        Matriu_solucio = new int[input.length+2][input[0].length+2];
        for (int x=0; x<tamany+2;  ++x){
        	Matriu_solucio[0][x] = -1;
        	Matriu_solucio[x][0] = -1;
        	Matriu_solucio[tamany+1][x] = -1;
        	Matriu_solucio[x][tamany+1] = -1;
        }
        
        for(int i=1; i<tamany+1; ++i){
        	for (int j=1;j<tamany+1; ++j){
        		Matriu_solucio[i][j]= input[i-1][j-1];
        		if (input[i-1][j-1]==-1){
        			Matriu_joc[i-1][j-1].setPosicion(j-1,i-1);
        			Matriu_joc[i-1][j-1].setValor(-1);
        			Matriu_joc[i-1][j-1].setEditable(false);
        		}
        		else if (input[i-1][j-1]==0){
        			Matriu_joc[i-1][j-1].setPosicion(j-1,i-1);
        			Matriu_joc[i-1][j-1].setValor(0);
        			Matriu_joc[i-1][j-1].setEditable(true);
        		}
        		else {
        			Matriu_joc[i-1][j-1].setPosicion(j-1,i-1);
        			Matriu_joc[i-1][j-1].setValor(input[i-1][j-1]);
        			Matriu_joc[i-1][j-1].setEditable(false);
        			list.add(input[i-1][j-1]);
                    if (input[i-1][j-1] == 1) start = new int[]{i,j};
        		}
        	}
        }

        Collections.sort(list);
        given = new int[list.size()];
        for (int i = 0; i < given.length; i++)
            given[i] = list.get(i);
        
        ControladorJoc juego = new ControladorJoc();
        return juego.solucionador(start[0],start[1],1,0,given,Matriu_solucio);
	}
	
	public int[][] agafaSolucio(){
		return Matriu_solucio;
	}

	public void crearTaulellRandom(int forats, int nombresFixos){
		ControladorJoc juego = new ControladorJoc();
		juego.generador(forats, nombresFixos, tamany, Matriu_joc);
		int[][] input = new int[tamany][tamany];
		for (int i=0; i<tamany; ++i){
			for (int j=0;j<tamany; ++j){
				input[i][j] = Matriu_joc[i][j].getValor();
			}
		}
		if (!festaulell(input)) crearTaulellRandom(forats, nombresFixos);
	}
	
}
