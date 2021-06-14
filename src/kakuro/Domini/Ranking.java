/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 *
 * @author oriol
 */
public class Ranking {
    
    //<UserName, puntuacioMesAlta>
    LinkedHashMap<String, Integer> Ranking; 
    
    
    public Ranking(){
        Ranking = new LinkedHashMap<>(); 
    }
    
    public void ActualitzaRecord(Partida p) throws IOException{
        Usuari u = p.getUsuari();
        int puntsPart = p.getPunts();
        int puntsUsu = u.getPuntuacioMesAlta();
        if(puntsPart > puntsUsu ){
            u.setPuntuacioMesAlta(puntsPart);
            Ranking.put(u.getNom(), puntsPart);
        }
    }
    
    
    
    public void PrintRanking(){
        for (String i : Ranking.keySet()) {
            System.out.println("UserName: " + i + " PuntuacióMax: " + Ranking.get(i));
        }
    }
    
    public LinkedHashMap getRanking(){
        
        this.OrdenarRanking();
        return Ranking;
    }
    
    public void OrdenarRanking(){
        List<String> Claus = new ArrayList<>(Ranking.keySet());
        List<Integer> Valors = new ArrayList<>(Ranking.values());
        Collections.sort(Valors);
        Collections.reverse(Valors);
        Collections.sort(Claus);

        LinkedHashMap<String, Integer> ordenat = new LinkedHashMap<>();

        Iterator<Integer> valueIt = Valors.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = Claus.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = Ranking.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    ordenat.put(key, val);
                    break;
                }
            }
        }
        Ranking.clear();
        Ranking = ordenat;
    }

    
    public void setRanking(LinkedHashMap ranking){
        
       
        this.Ranking = ranking;
    };
}
