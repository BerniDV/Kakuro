/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import kakuro.Dades.CtrlDades;

/**
 *
 * @author berni
 */
public class CtrlRanking {
    
    private Ranking r;
    private CtrlDades CD;
    
    public CtrlRanking(){
        
        r = new Ranking();
        CD =new CtrlDades();
    }
    
    private LinkedHashMap getRanking(){

        return r.getRanking();
    }
    
    private void ActualitzaRankingDesordenat() throws IOException{
        
        r.setRanking(CD.getRankingGlobal()); 
        
    }
    
    public LinkedHashMap getRankingGlobal() throws IOException{
        
        ActualitzaRankingDesordenat();
        return getRanking();
    }
    
    
    
}
