/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author berni
 */
public class ObtenirPossiblesCombinacions {
    
    
    final private int NumeroCombincaionsUniques = 43;
    final private int NumeroCombinacionsTotals = 120;
    final private int NumeroRangsCelles = 9;
    BufferedReader RdFitchers;
    
    
    public ObtenirPossiblesCombinacions() {
      
        RdFitchers = null;
        
    };
    
     public void ObtenirCombinacionsUniques(HashMap<String, Integer[]> Combinacions) throws FileNotFoundException, IOException{
        
        RdFitchers = new BufferedReader(new FileReader("../suport/CombinacionsUniques.txt")); 
        
        
        for(int i = 0; i < NumeroCombincaionsUniques; ++i){
            
            String InformacioCombinacioCompleta;
            InformacioCombinacioCompleta = RdFitchers.readLine();
            
            String[] InformacioCombinacioPerParts = InformacioCombinacioCompleta.split(" ");
            
            String NumICelles = InformacioCombinacioPerParts[0];
            Integer[] CombinacionsDeSumes = new Integer[InformacioCombinacioPerParts.length-1];
            
            for(int j = 1; j < InformacioCombinacioPerParts.length; ++j){
                
                CombinacionsDeSumes[j-1] = Integer.parseInt(InformacioCombinacioPerParts[j]); 
                
            }
            
            Combinacions.put(NumICelles, CombinacionsDeSumes);
                
        
        }
        
    }
     
     public void ObtenirNumerosUnicsSegonsCelles(HashMap<String, Integer[]> CellesContigues) throws IOException{
         
         RdFitchers = new BufferedReader(new FileReader("../suport/NombresUnicsSegonsCelles.txt")); 
        
        
        for(int i = 0; i < NumeroRangsCelles; ++i){
            
            String InformacioCompletaNombresPossibles;
            InformacioCompletaNombresPossibles = RdFitchers.readLine();
            
            String[] InformacioCombinacioPerParts = InformacioCompletaNombresPossibles.split(" ");
            
            String NumCellesBlanques = InformacioCombinacioPerParts[0];
            Integer[] PossiblesNombres = new Integer[InformacioCombinacioPerParts.length-1];
            
            for(int j = 1; j < InformacioCombinacioPerParts.length; ++j){
                
                PossiblesNombres[j-1] = Integer.parseInt(InformacioCombinacioPerParts[j]); 
                
            }
            
            CellesContigues.put(NumCellesBlanques, PossiblesNombres);
                
        
        }

     }
}
