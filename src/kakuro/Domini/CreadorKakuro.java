

package kakuro.Domini;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bernat Delgado
 */
public class CreadorKakuro{
    
    
    private Cella[][] Matriu;
         
    public CreadorKakuro() {
        
        Matriu = null;
        
    };
    
    
    public Tauler GeneradorKakuro(int numFiles, int numColumnes)  throws FileNotFoundException, IOException{
        
        Tauler t=new Tauler(numFiles,numColumnes);
        
        if(numFiles<2 || numColumnes<2){
           
            
            
        }else{
            resoldreTauler res=new resoldreTauler();
            
            while(t.getSolucions()!=1){
                
                AlgoritmeCrearTauler(numFiles,numColumnes);
                t.setMatriu(Matriu);
                t.TreureValorsDeCellesBlanques();
                t=res.BackTracking(t);
                
            }

        }
        
        t.TreureValorsDeCellesBlanques();
        return t;
        
 
    }
    //Crea un tauler de les dimensions especificades i el guarda en el arxiu KakuroGeneratAutomaticament de la capa de dades
    private  void AlgoritmeCrearTauler(int numFiles, int numColumnes) throws IOException{
        
        Boolean KakuroEnFitxerCorrecte = false;
        IOKakuro IOTauler = new IOKakuro();
        String NomFitxer = "KakuroGeneratAutomaticament.txt";
        //Utilitzem una classe per tal de poguer escriure el tauler generat per tal de testejar.
        //Som concients que incompleix el funcionament de l'arquitectura de les tres capes.
        //Un cop realitzada aquesta primera entrega, l cada de dades s'encarregarà d'aquesta funció.
        ObtenirPossiblesCombinacions Combinacions = new ObtenirPossiblesCombinacions();
        HashMap<String,Integer[]> CombinacionsUniques;
        CombinacionsUniques = new HashMap();
        Combinacions.ObtenirCombinacionsUniques(CombinacionsUniques);
        
        HashMap<String,Integer[]> PossiblesNombres;
        PossiblesNombres = new HashMap();
        Combinacions.ObtenirNumerosUnicsSegonsCelles(PossiblesNombres);
     
     
        while(!KakuroEnFitxerCorrecte){
            
            
        Matriu = new Cella[numFiles][numColumnes];
        
        
        CrearEscenari(Matriu, numFiles, numColumnes);

        
        Boolean primer = true;
        
        for(int i = 1; i< numFiles; ++i){
            
            for(int j = 0; j<numColumnes-1; ++j){
                
                if(Matriu[i][j+1] instanceof CellaBlanca && Matriu[i][j] instanceof CellaNegra){
                   
                    int numCellesBlanquesConsecutives = ContarCellesBlanquesConsecutivesFila(Matriu, i, j);
                    
                    String CellesConsecutives = String.valueOf(numCellesBlanquesConsecutives);
                    
                    Integer[] NombresPossiblesCandidats = PossiblesNombres.get(CellesConsecutives).clone();
                    
                   
                    Boolean NoValid = true;
                    
                    int contadorNoValid = 0;
                    while(NoValid){

                        NoValid= true;
                        Boolean EscollitCorrecte = false;
                        int valorEscollit = -1;
                        
                        int contadorEscollitCorrecte = 0;
                        while(!EscollitCorrecte || valorEscollit == -1){
                            
                            int random = (int)(Math.random()*(NombresPossiblesCandidats.length));
                            valorEscollit = NombresPossiblesCandidats[random];
                            
                            Boolean EstaRepetit = false;
                            for(int k =i-1 ; k >= 0; --k){
                                
                                if(Matriu[k][j] instanceof CellaNegra && Matriu[k][j].getValorFila() == valorEscollit){
                                    
                                    EstaRepetit = true;
                                }
                                
                            }
                      
                            
                            if(!EstaRepetit){
                                
                                EscollitCorrecte = true;
                            }else{
                                
                                EscollitCorrecte = false;
                            }
                            
                            if(contadorEscollitCorrecte >= 50){
                            
                                EscollitCorrecte=true;
                            }
                            ++contadorEscollitCorrecte;
                            
                            
                        }
                        
                        
                        if(Matriu[i][j] instanceof CellaNegra){
                        
                            Matriu[i][j].setValorFila(valorEscollit); 
                      
                        }
                    
                        String ClauCombinacionsPossibles = String.valueOf(valorEscollit+CellesConsecutives);
                        Integer[] CombinacionsValorEscollit = CombinacionsUniques.get(ClauCombinacionsPossibles);
                    
                        if(primer){
                        
                            EmplenarCellesBlanquesPrimeraFila(Matriu, i, j, CombinacionsValorEscollit);
                            primer = false;
                            NoValid = false;
                        
                        }else{
                        
                          
                            //Possar possibles valors a tota la fila
                            PosarPossiblesValorsInicials(Matriu, i, j, CombinacionsValorEscollit);
                            //Filtrado PossiblesValores de toda la fila
                            NoValid = FiltrarPossiblesValorsDeColumnesDeTotaFila(Matriu, i, j, CombinacionsValorEscollit.length);
                            //Emplenar Celles blanques Fent un filtre de possibles valors cada vegada que posem un numero en una cella blanca
                            if(!NoValid){
                                
                                NoValid = PosarCellesBlanques(Matriu, i, j, CombinacionsValorEscollit.length);
                            }else{
                                
                                Matriu[i][j].setValorFila(-1);
                            }
                            
                        }
                        
                        if(contadorNoValid >= 50){
                            
                                NoValid=false;
                            }
                            ++contadorNoValid;
                            
                        
                    }
                }
                
            }
             
        }
        
        //Sumar totes les columnes i posar per cada una la suma en la seva cellaNegra
        
        KakuroEnFitxerCorrecte = SumarColumnesIPosarValorEnCellaNegra(Matriu);
        
        }
        
    }
    
    private  void CrearEscenari(Cella[][] Matriu, int NumFiles, int NumColumnes){
        
        PosarCellesNegresInicials(Matriu, NumFiles, NumColumnes);
        EmplenarRestaTaulerAmbCellesNegres(Matriu);
        PosarCellesBlanquesInicials(Matriu, NumFiles, NumColumnes);
    }
    
    private void EmplenarRestaTaulerAmbCellesNegres(Cella[][] Matriu){
        
        int NumColumnes = Matriu[0].length;
        int NumFiles = Matriu.length;
        
        int NumCellesNegresActuals = NumColumnes + NumFiles - 1;
        int NumCellesTotals = NumColumnes * NumFiles;
        
        int ProporcioCellesRespecteCellesNegres = NumCellesNegresActuals/NumCellesTotals;
        
        int CellesNegresNecessaries =(int)((0.6 * NumCellesTotals)-NumCellesNegresActuals);
        
        while(CellesNegresNecessaries>0){
        
            if(ProporcioCellesRespecteCellesNegres < 0.6){
            
                int FilaRandom = (int)(Math.random()*(NumFiles));
                int ColumnaRandom = (int)(Math.random()*(NumColumnes));
            
                if(!(Matriu[FilaRandom][ColumnaRandom] instanceof CellaNegra)){
                
                
                    if(PossicioValidaPerPosarCellaNegra(Matriu, FilaRandom, ColumnaRandom, NumFiles, NumColumnes)){
                    
                    
                        Matriu[FilaRandom][ColumnaRandom] = new CellaNegra();
                        --CellesNegresNecessaries;
                    }
                
                }
            
            }
        
        }
        
        
    }
    
    
    private Boolean PossicioValidaPerPosarCellaNegra(Cella[][] Matriu, int fila, int columna, int numFiles, int numColumnes){
        
        --numFiles;
        --numColumnes;
        
        if(Matriu[fila][columna] instanceof CellaNegra){
            
            return false;
        }else{
            
            if(fila!=0 && fila!=numFiles && columna!=0 && columna!=numColumnes){
            
                if(Matriu[fila-1][columna] instanceof CellaNegra && Matriu[fila+1][columna] instanceof CellaNegra){
                     
                    return false;
                    
                }else if(Matriu[fila][columna-1] instanceof CellaNegra && Matriu[fila][columna+1] instanceof CellaNegra){
                    
                    return false;
                }
                
            }else{
                
                if(fila==numFiles && Matriu[fila-1][columna] instanceof CellaNegra){
                    
                    return false;
                    
                }
                
                if(columna==numColumnes && Matriu[fila][columna-1] instanceof CellaNegra){
                    
                    return false;
                }
                
                
            }
            
        }
        

        return true;
    }
    
    

    private Boolean PosarCellesBlanques(Cella[][] Matriu, int fila, int columna, int NumCombinacions){    
        
        
        
        Boolean NegraTrobada=false;
        int PrimeraColumna = -1;
         for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                PrimeraColumna = j;
                ++PrimeraColumna;
                NegraTrobada=true;
            }
        }
        
        int ComencamentColumna = PrimeraColumna;
        Boolean NotValid = false;
        //Loop() de lo de abaix
        for(int i = ComencamentColumna; i<NumCombinacions+1 && !NotValid; ++i){               
        //Mirar si a la fila hi ha algun possible valor unic i posarlo si es el cas
        Boolean HiHaCellaAmbUnicPossible = false;
        HiHaCellaAmbUnicPossible = BuscarCellaAmbPossibleValorUnicIPosarlo(Matriu, fila, columna, NumCombinacions);              
        
        
        if(!HiHaCellaAmbUnicPossible){
            
            //Buscar Cella amb menys Possibles triar un aleatoriament posarlo i filtrar
            Integer[] FilaIColumna = new Integer[2];
            BuscarCellaAmbMinimPossibles(Matriu, fila, columna, FilaIColumna, NumCombinacions);
            BuscarRandomEntrePossiblesIPossarlo(Matriu, FilaIColumna[0], FilaIColumna[1], NumCombinacions);
        }
        //Filtrar tota La fila per eliminar els possibles igual al valor posat i mirar per cada cella filtrada si els possibles resultants son 0 en aquest cas provem amb un altre numero per la fila
        NotValid = BuascarCeldasSinPossiblesEnFila(Matriu, fila, columna+1, NumCombinacions);
        //i tornar al punt 2
         }
        
        return NotValid;
    }
    
    private void BuscarCellaAmbMinimPossibles(Cella[][] Matriu, int fila, int columna, Integer[] FilaIColumna, int numCombinacions){
        
       
            
        Boolean NegraTrobada=false;
        int PrimeraColumna = -1;
         for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                PrimeraColumna = j;
                ++PrimeraColumna;
                NegraTrobada=true;
            }
        } 
       
        int ComencamentColumna = PrimeraColumna; 
        
        int minimFila=fila, minimColumna= ComencamentColumna;
        int minimPossibles=10;
        
        for(int i = ComencamentColumna; i<numCombinacions+1; ++i){
            
            if(Matriu[fila][i].getPossibles().size()<minimPossibles){
                
                minimPossibles = Matriu[fila][i].getPossibles().size();
                minimColumna = i;
            }
        }
        
        FilaIColumna[0] = minimFila;
        FilaIColumna[1] = minimColumna;
    }
    
    private void BuscarRandomEntrePossiblesIPossarlo(Cella[][] Matriu, int fila, int columna, int numCombinacions){
        
        int random = (int)(Math.random()*(Matriu[fila][columna].getPossibles().size()));
        int NumAColocar = Matriu[fila][columna].getPossibles().get(random);
        
        Matriu[fila][columna].setValor(NumAColocar);
        
        FiltrarPossiblesValorsFilaSegonsValor(Matriu, fila, columna, numCombinacions, NumAColocar);
        
    }
    
    private Boolean BuscarCellaAmbPossibleValorUnicIPosarlo(Cella[][] Matriu, int fila, int columna, int NumCombinacions){
        
        Boolean HiHaUnic = false;
        
        
        
        Boolean NegraTrobada=false;
        int PrimeraColumna = -1;
         for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                PrimeraColumna = j;
                ++PrimeraColumna;
                NegraTrobada=true;
            }
        }
        
        
        int ComencamentColumna = PrimeraColumna;
        
        for(int i = ComencamentColumna; ((i<NumCombinacions+1) && (Matriu[fila][i] instanceof CellaBlanca)); ++i){
            
          if(Matriu[fila][i].getValor()==0 && Matriu[fila][i].getPossibles().size()==1){
            
            int ValorAColocar = Matriu[fila][i].getPossibles().get(0);
            Matriu[fila][i].setValor(ValorAColocar);
            HiHaUnic = true;   
            Boolean NoEsPotPosar =false;
            NoEsPotPosar = FiltrarPossiblesValorsFilaSegonsValor(Matriu, fila, columna, NumCombinacions, ValorAColocar);
            
            if(NoEsPotPosar){
                
                Matriu[fila][i].setValor(-1);
                //cuidado
            }
            
          }
          
        }
        
        return HiHaUnic;
    }
    
    private Boolean FiltrarPossiblesValorsDeColumnesDeTotaFila(Cella[][] Matriu, int fila, int columna, int NumCombinacions){
       
        
        for(int j = columna+1; j<NumCombinacions+1; ++j){
           
            
            Boolean NegraTrobada=false;
            int PrincipiFila = -1;
            for(int i = fila; i>=0; --i){
            
                if(Matriu[i][j] instanceof CellaNegra && !NegraTrobada){
                
                    PrincipiFila = i;
                    ++PrincipiFila;
                    NegraTrobada = true;
                }
            
            }
            
            
            
            int ComencamentFila = PrincipiFila;
                
            if(Matriu[fila][j] instanceof CellaBlanca && !Matriu[fila][j].getPossibles().isEmpty()){
                
                for(int i = ComencamentFila; (i<fila); ++i){
                    
                    if(Matriu[i][j].getValor()!=-1){
                  
                        int ElementATreureDePossibles = Matriu[i][j].getValor();
                    
                        int indexElementATreure = Matriu[fila][j].getPossibles().indexOf(ElementATreureDePossibles);
                        
                        if(indexElementATreure!=-1){
                        
                            Matriu[fila][j].getPossibles().remove(indexElementATreure);
                        
                            indexElementATreure = -1;

                        }
                    
                    }
                    
                }
            }
        }
        
        //Si alguna cella es queda sense possibles valors no es pot resoldre el kakuro i hem de provar amb un altre valor a la cella negra de la fila
        return BuascarCeldasSinPossiblesEnFila(Matriu, fila, columna+1, NumCombinacions);
    }
    
    private Boolean FiltrarPossiblesValorsFilaSegonsValor(Cella[][] Matriu, int fila, int columna, int NumCombinacions, int ValorAFiltrar){
        
     
        
        Boolean NegraTrobada=false;
        int PrimeraColumna = -1;
         for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                PrimeraColumna = j;
                ++PrimeraColumna;
                NegraTrobada=true;
            }
        }
        
        
        
        
        int ComencamentColumna = PrimeraColumna;
        
        for(int i = ComencamentColumna; ((i<NumCombinacions+1) && (Matriu[fila][i] instanceof CellaBlanca)); ++i){
            
          if(Matriu[fila][i].getValor()==0 && Matriu[fila][i].getPossibles().contains(ValorAFiltrar)){
              
              int indexElementATreure = Matriu[fila][i].getPossibles().indexOf(ValorAFiltrar);
              Matriu[fila][i].getPossibles().remove(indexElementATreure);
                
          }
          
        }
        
        return BuascarCeldasSinPossiblesEnFila(Matriu, fila, columna+1, NumCombinacions);
    }
    
    private Boolean BuascarCeldasSinPossiblesEnFila(Cella[][] Matriu, int fila, int ComencamentColumna, int NumCombincacions){
        
        Boolean resultat = false;
        
        for(int i = ComencamentColumna; i<NumCombincacions+1; ++i){
            
            if(Matriu[fila][i] instanceof CellaBlanca && Matriu[fila][i].getPossibles().isEmpty()){
                
                return true;
            }
            
        }
        
        return resultat;
    }
    
    private void PosarPossiblesValorsInicials(Cella[][] Matriu,int fila, int columna, Integer[] CombinacionsValorEscollit){

     
        for(int i = (columna+1); (i-(columna+1))<CombinacionsValorEscollit.length; ++i ){
            
            ArrayList<Integer> PossiblesValors = new ArrayList();
            CopiarVectorEnArrayList(PossiblesValors, CombinacionsValorEscollit);
            Matriu[fila][i].setPossibles(PossiblesValors);
            
        }
        
    }
    
    //Inicialitza la matriu posant la primera fila i la primera columna com celles negres
    private void PosarCellesNegresInicials(Cella[][] tauler, int numFiles, int numColumnes){
        
  
        for(int i = 0; i<numFiles; ++i){
            
            for(int j=0; j<numColumnes; ++j){
                
                if(i==0 || j==0){
                    
                    tauler[i][j] = new CellaNegra();

                }
            }
        }
    }
    
       //Inicialitza la matriu posant la primera fila i la primera columna com celles negres
    private void PosarCellesBlanquesInicials(Cella[][] tauler, int numFiles, int numColumnes){
        
  
        for(int i = 0; i<numFiles; ++i){
            
            for(int j=0; j<numColumnes; ++j){
                
                if(!(tauler[i][j] instanceof CellaNegra) && !(tauler[i][j] instanceof CellaBlanca)){

                    tauler[i][j] = new CellaBlanca();
                }
                
            }
        }
    }
    
    
    

    private int ContarCellesBlanquesConsecutivesFila(Cella[][] Matriu, int fila, int columna) {
       
        int num = 0;
        Boolean fin= false;
      
        for(int i = columna+1; i<Matriu[fila].length; i++){
            
            if(Matriu[fila][i] instanceof CellaNegra){
                
                fin=true;
            }
            if(Matriu[fila][i] instanceof CellaBlanca && !fin){
                
                ++num;
            }
            
        }
        
        return num;
    }
    
    
    private int ContarCellesBlanquesConsecutivesColumna(Cella[][] Matriu, int fila, int columna) {
       
        int num = 0;
        Boolean fin= false;
        
        for(int i = fila+1; i<Matriu.length; i++){
            
            if(Matriu[i][columna] instanceof CellaNegra){
                
                fin=true;
            }
            if(Matriu[i][columna] instanceof CellaBlanca && !fin){
                
                ++num;
            }
        }
        
        return num;
    }

    private void CopiarVectorEnArrayList(ArrayList<Integer> a, Integer[] b){
        
        for (Integer b1 : b) {
            a.add(b1);
        }
    }
    
    
    private void EmplenarCellesBlanquesPrimeraFila(Cella[][] Matriu, int fila, int columna, Integer[] CombinacionsValorEscollit) {
        
        int Mida = CombinacionsValorEscollit.length;
        ArrayList<Integer> NumerosAColocar = new ArrayList();
        
        CopiarVectorEnArrayList(NumerosAColocar, CombinacionsValorEscollit);
        
        for(int i = (columna+1); (i-(columna+1))<Mida; ++i ){
            
            int random = (int)(Math.random()*(NumerosAColocar.size()));
            
            Matriu[fila][i].setValor(NumerosAColocar.get(random));
            
            NumerosAColocar.remove(random);
        }
    }
    
    //S'ha de posar fila i columna sent la interseccio una cella blanca
    private Integer[] ObtenirComencamentFilaColumna(Cella[][] Matriu, int fila, int columna){
        
        Integer[] FilaColumnaResult = {-1,-1};
        Boolean NegraTrobada=false;
        
         for(int i = fila; i>=0; --i){
            
            if(Matriu[i][columna] instanceof CellaNegra && !NegraTrobada){
                
                FilaColumnaResult[0] = i;
                ++FilaColumnaResult[0];
                NegraTrobada=true;
            }
            
        }
         
         NegraTrobada=false;
         
         for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                FilaColumnaResult[1] = j;
                ++FilaColumnaResult[1];
                NegraTrobada=true;
            }
        }
         
         return FilaColumnaResult;
    }
    
    //Repassa totes les normes de kakuro per un nobre en una cella i retorna true si el valor pot colllocarse en aquella posicio i fals altrament
    private Boolean ValorEnCellaCompleixRegles(Cella[][] Matriu, int fila, int columna, int valorCandidat ){
        
        int comencamentFila = -1;
        int comencamentColumna = -1;
        
        Boolean resultat = true;
        
        if(Matriu[fila][columna] instanceof CellaNegra){
            
            resultat = false;
            
        }
        
        Boolean NegraTrobada=false;
        for(int i = fila; i>=0; --i){
            
            if(Matriu[i][columna] instanceof CellaNegra && !NegraTrobada){
                
                comencamentFila = i;
                ++comencamentFila;
                NegraTrobada=true;
            }
            
        }
        
        NegraTrobada=false;
        //que no passi adalt de tot fer un bool que pari a la primera cella negra
        for(int j = columna; j>=0; j--){
            
            if(Matriu[fila][j] instanceof CellaNegra && !NegraTrobada){
                
                comencamentColumna = j;
                ++comencamentColumna;
                NegraTrobada=true;
            }
        }
        
       
        
        for(int j = comencamentColumna; ((j<Matriu.length) && (Matriu[fila][j] instanceof CellaBlanca)); j++){
            
            
            if(Matriu[fila][j].getValor() == valorCandidat){
                
                resultat = false;
            }
        }
        
  
        
        for(int i = comencamentFila; ((i<Matriu[fila].length) && (Matriu[i][columna] instanceof CellaBlanca)); i++){
            
            if(Matriu[i][columna].getValor() == valorCandidat){
                
                resultat = false;
            }
        }
        
        return resultat;
    }

    private Boolean SumarColumnesIPosarValorEnCellaNegra(Cella[][] Matriu) {
        
        Boolean SumesCorrectes = true;
        
        for(int i=0; i<Matriu.length-1; ++i){
            
            for(int j=0; j<Matriu[0].length; ++j){
                
                if(Matriu[i][j] instanceof CellaNegra && Matriu[i+1][j] instanceof CellaBlanca){
                    
                    int suma =0;
                    
                    for(int k = i+1; k<Matriu.length && Matriu[k][j] instanceof CellaBlanca; ++k){
                        
                        if(Matriu[k][j].getValor()==0 || Matriu[k][j].getValor()==-1){
                            
                            SumesCorrectes =false;
                        }else{
                            
                          suma += Matriu[k][j].getValor();
                        }
                    }
                    
                    Matriu[i][j].setValorColumna(suma);
                }
            }
        }
        
        return SumesCorrectes;
    }
    

}
