/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;

/**
 *
 * @author ANIOL
 */
public class CtrlResoldreKakuro {
    private resoldreTauler RT;
    
    public CtrlResoldreKakuro(){
        this.RT = new resoldreTauler();
    }
    
    public Tauler getKakuroResolt(Tauler T) throws IOException{
        
        Tauler taulerResolt = RT.BackTracking(T);
        return taulerResolt;
       
    }

}
