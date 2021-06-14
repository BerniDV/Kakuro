/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;
import java.io.IOException;
import kakuro.Dades.CtrlDades;

/**
 *
 * @author ANIOL
 */
public class CtrlUsuaris {
    
    private CtrlDades cd = new CtrlDades();
    
    public CtrlUsuaris(){
        
    }
    
    public boolean iniciarSessio(String user,String password) throws IOException{
        return cd.iniciarSessio(user,password);
    }
    
    public boolean registrarUsuari(String user, String password) throws IOException{
        return cd.registrarUsuari(user, password);
    }
    
}
