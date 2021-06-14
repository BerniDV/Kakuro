package kakuro.Domini;

import java.io.Serializable;

public class Tauler
{

    private int id;
    private int columnes;
    private int files;
    //Dificultat nomes té 3 valors (facil, mig i dificil)
    private String dificultat;
    private Cella[][] matriu;
    private int solucions;
    
    //creadora sense parametres
    public Tauler(){
        this.id = -1;
        this.columnes = 0;
        this.files = 0;
        this.dificultat = "";
        this.matriu= new Cella[files][columnes];
        this.solucions=0;
    }
    

    public Tauler(int n_files, int n_columnes){
        if(n_files>0 && n_columnes>0){
            this.id = -1;
            this.columnes = n_columnes;
            this.files = n_files;
            CalculaDificultat();
            this.matriu = new Cella[n_files][n_columnes]; 
            this.solucions=0;
        }
    }

    //creadora amb parametres
    public Tauler( int files,int columnes, String dificultat){
        if(files>0 && columnes>0){
            this.id = 0;
            this.columnes = columnes;
            this.files = files;
            this.dificultat = dificultat;
            this.solucions=0;
        }
    }

    public int getId(){
        return this.id;
    }
    

    public int getFiles() {
	return this.files;   
    }
	
    public int getColumnes() {
	return this.columnes;
    }
    
    public void setFiles(int a) {
	this.files=a;   
    }
	
    public void setColumnes(int a) {
	this.columnes=a;
    }
    public String getDificultat(){
        return this.dificultat;
    }
    public void setSolucions(int a){
        this.solucions=a;
    }
     public int getSolucions(){
        return this.solucions;
    }

    public Cella[][] getMatriu(){
        return this.matriu;
    }
    public void setMatriu(Cella[][] aux){
        this.matriu=aux;
    }
    public int getValorCela(int i,int j){
        if(this.matriu[i][j] instanceof CellaBlanca)
            return this.matriu[i][j].getValor();
        return -1;
    }
    public void setValorCela(int i,int j, int v){
       if(this.matriu[i][j] instanceof CellaBlanca)
            this.matriu[i][j].setValor(v);
    }
    public int getValorColumna(int i,int j){
        return this.matriu[i][j].getValorColumna();
    }
    public int getValorFila(int i,int j){
        return this.matriu[i][j].getValorFila();
    }
    

    public int totalCeles(){
        return this.columnes*this.files;
    }

    public void setDificultat(String dificultat) {
        
        this.dificultat = dificultat;
    }
    
    public void TreureValorsDeCellesBlanques(){
        
        
        for(int i = 0; i<this.files ; ++i){
            
            for(int j = 0; j<this.columnes ; ++j){
                
                if(matriu[i][j] instanceof CellaBlanca){
                    
                    matriu[i][j].setValor(0);
                }
                
            }
        }
        
    }
    
    public Tauler clonarTauler(){
        
        Cella[][] matriuCopia = new Cella[this.matriu.length][this.matriu[0].length];
        
        for(int i = 0; i< this.matriu.length; ++i){
            
            for(int j = 0; j< this.matriu[0].length; ++j){
                
              if(matriu[i][j] instanceof CellaNegra){
                  
                  matriuCopia[i][j] = new CellaNegra(matriu[i][j].getValorColumna(),matriu[i][j].getValorFila());
                  
              }else if(matriu[i][j] instanceof CellaBlanca){
                  
                  matriuCopia[i][j] = new CellaBlanca(matriu[i][j].getValor());
                  
              }
                
                
            }
        }

        int id = this.id;
        int files = this.files;
        int columnes = this.columnes;
        int solucions = this.solucions;
        String dificultat = this.dificultat;
        
        Tauler Tclonat = new Tauler(files, columnes, dificultat);
        Tclonat.setMatriu(matriuCopia);
        Tclonat.setSolucions(solucions);
        
        return Tclonat;
    }
            
    private void CalculaDificultat(){
        int mida = totalCeles();
        if (mida <= 9){ // seran facils aquells que la mida max sigui un 3x3
            this.dificultat = "facil";
        }
        else if (mida <= 20){ // seran facils aquells que la mida max sigui un 5x4
            this.dificultat = "mig";
        }
        else{// seran facils aquells que la mida superior als anteriors
            this.dificultat = "dificil";
        }
    }
    
    private int CalculaBonus() {
        int bonus = 0;
        switch(this.dificultat) {
            case "facil":
                bonus = 1;
                break;
            case "mig":
                bonus = 5;
                break;
            case "dificil":
                bonus = 10;
                break;
            default:
                
        }
        return bonus;
    }
  
    public int getBonus(){
        return CalculaBonus();
    }
}
    
    
    
    
    
    