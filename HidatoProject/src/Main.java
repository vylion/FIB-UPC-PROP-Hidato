
import vistas.VistaContainer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleue*/


////////////////////////

public class Main
{
  public static void main (String[] args) {
    javax.swing.SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
          VistaContainer aux = new VistaContainer("HidatoMeister 0.3.0");
          aux.inicializarPresentacion();
    }});
  }
}

////////////////////////
