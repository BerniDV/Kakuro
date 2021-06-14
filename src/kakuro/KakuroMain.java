/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro;

import java.io.IOException;
import java.util.HashMap;
import kakuro.Dades.CtrlDades;
import kakuro.Domini.CreadorKakuro;
import kakuro.Domini.CtrlPartidaEnCurs;
import kakuro.Domini.CtrlRanking;
import kakuro.Domini.CtrlResoldreKakuro;
import kakuro.Domini.Partida;
import kakuro.Domini.Tauler;
import kakuro.Domini.Usuari;
import kakuro.Presentacio.UsersView;
import kakuro.pair.Pair;


/**
 *
 * @author berni
 */
public class KakuroMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here 
         new UsersView().setVisible(true);

        /*CreadorKakuro C = new CreadorKakuro();
        CtrlResoldreKakuro r = new CtrlResoldreKakuro();
        while(true){
          //r.getKakuroResolt(CP.getTaulerRepositori("Kakuro2Solucions")) ;
            Tauler t = C.GeneradorKakuro(4,5);
            r.getKakuroResolt(t);
            System.out.println(t.getSolucions());
            //return;
        }*/
    }
    
}  
