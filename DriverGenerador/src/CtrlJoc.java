
import java.util.Random;

public class CtrlJoc {
	private Pair[] adjacent = new Pair[8];
	
	public void generador(int forats, int nombreFixos, int mida, int[][] mj, boolean[][] fixes){
		
    	int max = mida*mida-forats;
    	
    	Random aux = new Random();
    	int fila = aux.nextInt(mida);
    	int columna = aux.nextInt(mida);
    	
    	mj[fila][columna]= 1;
		fixes[fila][columna]= false;
		
		adjacent[0] = new Pair(-1,-1);
		adjacent[1] = new Pair(-1, 0);
		adjacent[2] = new Pair(-1,+1);
		adjacent[3] = new Pair( 0,-1);
		adjacent[4] = new Pair( 0,+1);
		adjacent[5] = new Pair(+1,-1);
		adjacent[6] = new Pair(+1, 0);
		adjacent[7] = new Pair(+1,+1);
		
		Random r = new Random();
		
		posAl(1, fila, columna, max, mj, fixes, r);
		
		ompleForats(mj, fixes, forats);

		fixarNombres(mj, fixes, nombreFixos, r);
    }
	
	private boolean posAl(int val, int f, int c, int max, int[][] mj, boolean[][] fixes, Random r) {
		int auxF, auxC;
		boolean trobat = false;
		
		mj[f][c] = val;
		if(val == max) {
			trobat = true;
			fixes[f][c] = false;
		}
		
		//Pseudorandom
		int n = 0;
		if((max) < mj.length*mj[0].length/2 || r.nextInt(10) < 10 - max/10) n = r.nextInt(8);
		else if(r.nextInt(5) < 3) {
			n = r.nextInt(5);
			if(f < mj.length/2) n += r.nextInt(3);
		}
		
		for(int i = 0; i < 8 && !trobat; ++i) {
			
			auxF = f + adjacent[(n+i)%8].getFila();
			auxC = c + adjacent[(n+i)%8].getColumna();
			if(auxF>=0 && auxF<mj.length && auxC>=0 && auxC<mj[0].length &&
					mj[auxF][auxC] == 0){
				trobat = posAl( val+1, auxF, auxC, max, mj, fixes, r); 
			}
		}
		if(!trobat) mj[f][c] = 0;
		
		return trobat;
	}
	
	private void ompleForats(int[][] mj, boolean[][] fixes, int forats) {
		
		for(int i = 0; i < mj.length; i++) {
			for(int j = 0; j < mj[0].length; j++) {
				if(mj[i][j] == 0) {
					mj[i][j] = -1;
					fixes[i][j] = false;
				}
			}
		}
	}
	
	private void fixarNombres(int[][] mj, boolean[][] fixes, int nf, Random r) {
		
		if (nf>0){
			
			int i = r.nextInt(mj.length);
			int j = r.nextInt(mj[0].length);
			if (fixes[i][j]){
				fixes[i][j] = false;
				fixarNombres(mj,fixes,nf-1,r);
			}
			else fixarNombres(mj,fixes,nf,r);
		}
	}
	
	public boolean solucionador(int r, int c, int n, int next, int[] given, int[][] Matriu_solucio) {
        if (n > given[given.length - 1])
            return true;
 
        if (Matriu_solucio[r][c] != 0 && Matriu_solucio[r][c] != n)
            return false;
 
        if (Matriu_solucio[r][c] == 0 && given[next] == n)
            return false;
 
        int back = Matriu_solucio[r][c];
        if (back == n)
            next++;
     
        Matriu_solucio[r][c] = n;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if (solucionador(r + i, c + j, n + 1, next, given, Matriu_solucio))
                    return true;
 
        Matriu_solucio[r][c] = back;
        return false;
    }
    
	
}