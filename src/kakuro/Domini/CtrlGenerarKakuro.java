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
public class CtrlGenerarKakuro {
    private CreadorKakuro GK;
    
    
    public CtrlGenerarKakuro(){
        this.GK = new CreadorKakuro();
    }
    
    public Tauler GenerarKakuro(int files,int columnes) throws IOException{
       Tauler T=new Tauler(files,columnes);
       T=GK.GeneradorKakuro(files,columnes);
        return T;
        
    }
}
