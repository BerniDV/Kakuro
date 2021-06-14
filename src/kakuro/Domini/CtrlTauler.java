/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;
import java.io.IOException;
import kakuro.Dades.CtrlDades;
import kakuro.pair.Pair;

/**
 *
 * @author oriol
 */
public class CtrlTauler {
     private CtrlDades cd = new CtrlDades();
    
    public CtrlTauler(){
        
    }
    
    public boolean ExisteixID(String nom){
        return cd.ExisteixTaulerARepositori(nom);
    }
    
    public boolean GuardaID(String NomTauler, Pair[][] taulerRepositori, String dificultatTauler) throws IOException{
        return cd.GuardarTaulerARepositori(NomTauler, taulerRepositori, dificultatTauler);
    }
    
    
}
