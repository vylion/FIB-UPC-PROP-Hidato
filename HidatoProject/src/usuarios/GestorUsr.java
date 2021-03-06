package usuarios;
//import java.util.Vector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorUsr {
	
	/*Constructora*/
	public GestorUsr() {

	}
	
	//Afegir Usuari
	public boolean afegirUsuari(Usuario u){
		boolean x = false;
		try{
			File aux = new File("Usuaris/" + u.getUsr());
			if (!aux.exists()){
				aux.mkdirs();
				File axu = new File("Usuaris/" + u.getUsr() + "/", "password.txt");
				axu.createNewFile();
				FileWriter esc = new FileWriter("Usuaris/" + u.getUsr() + "/password.txt",true);
				BufferedWriter bw = new BufferedWriter(esc);
				bw.write(u.getPass());
				bw.close();
				esc.close();
				x = true;
			}
		}catch(IOException ex){
			
		}
		return x;
	}
	
	//Comprovar Usuari
	public boolean comprovarUsr(Usuario u){
		boolean trobat=false;
		try{
			File aux = new File("Usuaris/" + u.getUsr());
			if (aux.exists()){
				FileReader fr = new FileReader("Usuaris/" + u.getUsr() + "/password.txt");
				BufferedReader bf = new BufferedReader(fr);
				String cont =bf.readLine();
				bf.close();
		        fr.close();
		        if (cont.equals(u.getPass())) trobat=true;
			}
		}catch(IOException ex){
			// S'ha de fer encara
		}
		return trobat;
	}
        
        public boolean comprovarUsrMod(String u){
		boolean trobat=false;
                File aux = new File("Usuaris/" + u);
                if (aux.exists()) trobat = true;
                else trobat = false;
		return trobat;
	}
	
	//Modifica el nom d'un Usuari
	public boolean modUsr(String uact, String unou, String pass){
		boolean trobat=false;
		File fichero = new File("Usuaris/" + uact);
		if (fichero.exists()){
			File fichero2 = new File("Usuaris/" + unou);
			fichero.renameTo(fichero2);
			trobat = true;
		}
		return trobat;
	}
	
	//Modificar contrasenya
	public boolean modPassword(String u, String pass){
		boolean trobat=false;
		try{
			File fichero = new File("Usuaris/" + u);
			if (fichero.exists()){
				FileWriter esc =new FileWriter("Usuaris/" + u + "/password.txt");
				BufferedWriter bw = new BufferedWriter(esc); 
				bw.write(pass);
				bw.close();
				esc.close();
				trobat=true;
			}
		}catch(IOException ex){
			// S'ha de fer encara
		}
		return trobat;
	}
	
	public boolean esbUsuari(String u){
		boolean borr = false;
		File fichero = new File("Usuaris/" + u);
		File esc = new File("Usuaris/" + u + "/password.txt");
		esc.delete();
		fichero.delete();
		borr = true;
		return borr;
	}
}
