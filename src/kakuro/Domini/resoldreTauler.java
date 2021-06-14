/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;


/**
 *
 * @author rhoms
 */
public class resoldreTauler{
    
    private Cella [][] matriuAux;
    
    
      public resoldreTauler() {

      }
    //A partir d'aqui esta tot el codi generat per Bernat Delgado que no te cap dependencia amb el codi de més amunt
    
    public boolean esEstable(Cella[][] matriu, int fila, int columna, int num){
        
        int primeraFilaEnRang;
        int primeraColumnaEnRang;
        int sumaDeFila = this.getSumaDeFila(fila, columna, matriu);   
        int sumaDeColumna = this.getSumaDeColumna(fila, columna, matriu);   
              
                                                                            
        primeraFilaEnRang = BuscarIniciDeFila(fila, columna,matriu);
        primeraColumnaEnRang = BuscarIniciDeColumna(fila, columna ,matriu);
        
        if(num<1 || num>9){
            
            return false;
            
        }

        //mirar si el num es repeteix en la seva fila
        for(int i = primeraColumnaEnRang; i<matriu[0].length && matriu[fila][i] instanceof CellaBlanca; ++i){
            
            if(matriu[fila][i].getValor() == num){
                
                return false;
            }
        }
        //si el num es repteix en la columna retorna fals
        for(int i = primeraFilaEnRang; i<matriu.length && matriu[i][columna] instanceof CellaBlanca; ++i){
            
            if(matriu[i][columna].getValor() == num){
                
                return false;
            }
        }
        
        //Mirar si ens passem amb la suma
        int sumaAcomuladaFila = num;
        int foratsFila=0;
        
        for(int i = primeraColumnaEnRang; i<matriu[0].length && matriu[fila][i] instanceof CellaBlanca; ++i){
            if(matriu[fila][i].getValor()==0)
                ++foratsFila;
            sumaAcomuladaFila += matriu[fila][i].getValor();
            
            if(sumaAcomuladaFila>sumaDeFila){
                
                return false;
            }
        }
        
        int sumaAcomuladaColumna = num;
        int foratsColumna=0;
        for(int i = primeraFilaEnRang; i<matriu.length && matriu[i][columna] instanceof CellaBlanca; ++i){
            if(matriu[i][columna].getValor()==0)
                ++foratsColumna;
            sumaAcomuladaColumna += matriu[i][columna].getValor();

            if(sumaAcomuladaColumna>sumaDeColumna){
                
                return false;
            }
        }
        
        
       
        if(this.filaPlena(fila, columna, matriu, num)){
            
            if(!ComprovarFilaCorrecta(fila, columna, matriu, num)){
                
                return false;
            }
           
        }
        
        if(this.columnaPlena(fila, columna, matriu, num)){
            
            if(!ComprovarColumnaCorrecta(fila, columna, matriu, num)){
                
                return false;
            }
            
        }
        // if(foratsFila==0 && sumaDeFila-sumaAcomuladaFila!=0)
       //     return false;
       // if(foratsColumna==0 && sumaDeColumna-sumaAcomuladaColumna!=0)
        //    return false;
        if(foratsFila==1 && sumaDeFila-sumaAcomuladaFila>9)
            return false;
        if(foratsColumna==1 && sumaDeColumna-sumaAcomuladaColumna>9)
            return false;
        if(foratsFila==2 && sumaDeFila-sumaAcomuladaFila>17)
            return false;
        if(foratsColumna==2 && sumaDeColumna-sumaAcomuladaColumna>17)
            return false;
         if(foratsFila==3 && sumaDeFila-sumaAcomuladaFila>24)
            return false;
        if(foratsColumna==3 && sumaAcomuladaColumna>24)
            return false;
        
        return true;
    }    
    
    boolean filaPlena(int fila, int columna, Cella[][] matriu, int num){

        boolean filaPlena = true;
        matriu[fila][columna].setValor(num);
        
        for(int i = this.BuscarIniciDeColumna(fila, columna, matriu); i<matriu[0].length && matriu[fila][i] instanceof CellaBlanca; i++){
            
            if(matriu[fila][i].getValor() == 0){
                
                filaPlena = false;
            }
   
        }
        matriu[fila][columna].setValor(0);
        return filaPlena;
    }
    
    boolean columnaPlena(int fila, int columna, Cella[][] matriu, int num){

        boolean columnaPlena = true;
        matriu[fila][columna].setValor(num);
        
        for(int i = this.BuscarIniciDeFila(fila, columna, matriu); i<matriu.length && matriu[i][columna] instanceof CellaBlanca; i++){
            
            if(matriu[i][columna].getValor() == 0){
                
                columnaPlena = false;
            }
   
        }
         matriu[fila][columna].setValor(0);
        return columnaPlena;
    }
    
    boolean ComprovarFilaCorrecta(int fila, int columna, Cella[][] matriu, int num){
        
        int sum = 0;
         matriu[fila][columna].setValor(num);
        for(int i = this.BuscarIniciDeColumna(fila, columna, matriu); i<matriu[0].length && matriu[fila][i] instanceof CellaBlanca; i++){

            sum += matriu[fila][i].getValor();
 
        }
        
        matriu[fila][columna].setValor(0);
        return (sum == this.getSumaDeFila(fila, columna, matriu));
    }
    
    boolean ComprovarColumnaCorrecta(int fila, int columna, Cella[][] matriu, int num){
        
        int sum = 0;
        matriu[fila][columna].setValor(num);
         
        for(int i = this.BuscarIniciDeFila(fila, columna, matriu); i<matriu.length && matriu[i][columna] instanceof CellaBlanca; i++){

            sum += matriu[i][columna].getValor();
 
        }
        
         matriu[fila][columna].setValor(0);
        return (sum == this.getSumaDeColumna(fila, columna, matriu));
    }
    
    int BuscarIniciDeFila(int fila, int columna, Cella[][] matriu){
        
        int principiFila = 0;
        
        for(int i = fila; i>0 && matriu[i][columna] instanceof CellaBlanca; --i){
            
            if(matriu[i-1][columna] instanceof CellaNegra){
                
                principiFila = i;
            }
        }
        
        return principiFila;
    }
    
    int BuscarIniciDeColumna(int fila, int columna, Cella[][] matriu){
        
        int principiColumna = 0;
        
        for(int i = columna; i>0 && matriu[fila][i] instanceof CellaBlanca; --i){
            
            if(matriu[fila][i-1] instanceof CellaNegra){
                
                principiColumna = i;
            }
        }
        
        return principiColumna;
    }
    
    private int getSumaDeFila(int i,int j, Cella[][] matriu){
        
      
        while(matriu[i][j] instanceof CellaBlanca){
            
            --j;
        }
        
        return matriu[i][j].getValorFila();
        
    }
     
    private int getSumaDeColumna(int i,int j, Cella[][] matriu){
        int auxfila=i-1;
         while(matriu[auxfila][j] instanceof CellaBlanca){           
               auxfila--;
            }
        if(matriu[auxfila][j] instanceof CellaNegra)          
                return matriu[auxfila][j].getValorColumna();
        return -1;    
    }
    
    public boolean solucionaKakuro(Cella[][] matriu, int[] numSolucions){
        
        int fila = -1;  
        int columna = -1;
        boolean estaPle = true;
        
        for(int i = 0; i < matriu.length; ++i){
            
            for(int j = 0; j< matriu[0].length; ++j){
                
                if(matriu[i][j] instanceof CellaBlanca && matriu[i][j].getValor() == 0){
                    
                    fila = i;
                    columna = j;
                    estaPle = false;
                    break;
                    
                }

            }
            
            if(!estaPle){
            
                break;
            
            }
        }
        
        if(estaPle){
            ++numSolucions[0];

            if(numSolucions[0]==1){
                
                this.matriuAux = this.clonarMatriu(matriu);
            }
           return true;
   
        }
        
        
        for(int num = 1; num<=9; ++num){
            
            if(this.esEstable(matriu, fila, columna, num)){
                
                matriu[fila][columna].setValor(num);
                
                if(!this.solucionaKakuro(matriu, numSolucions)){
                    
                    matriu[fila][columna].setValor(0);
                }
                
            }else{
                
                matriu[fila][columna].setValor(0);
            }
            
        }
        
        return false;
        
    }
    
    public Cella[][] clonarMatriu(Cella[][] matriuAcopiar){
        
        Cella[][] matriuCopia = new Cella[matriuAcopiar.length][matriuAcopiar[0].length];
        
        for(int i = 0; i< matriuAcopiar.length; ++i){
            
            for(int j = 0; j< matriuAcopiar[0].length; ++j){
                
              if(matriuAcopiar[i][j] instanceof CellaNegra){
                  
                  matriuCopia[i][j] = new CellaNegra(matriuAcopiar[i][j].getValorColumna(),matriuAcopiar[i][j].getValorFila());
                  
              }else if(matriuAcopiar[i][j] instanceof CellaBlanca){
                  
                  matriuCopia[i][j] = new CellaBlanca(matriuAcopiar[i][j].getValor());
                  
              }
                
                
            }
        }
        return matriuCopia;
    }
    
    public Tauler BackTracking(Tauler t){
        
        int[] numSolucions = {0};
        this.solucionaKakuro(t.getMatriu(), numSolucions);
        t.setMatriu(this.matriuAux);
        t.setSolucions(numSolucions[0]);
        return t;
    }

}
