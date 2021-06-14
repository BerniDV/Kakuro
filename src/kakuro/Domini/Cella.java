package kakuro.Domini;

import java.io.Serializable;
import java.util.ArrayList;

public  abstract class Cella{

	//private int fila;
	//int columna;
	
	//creadora sense parametres
	/*public Cella(){
		fila = -1;
		columna = -1;
	}

	//creadora amb coordenades com a parametre
	public Cella(int x, int y){
		this.fila = x;
		this.columna = y;
	}*/
        

        public abstract int getValorColumna();        
        
        public abstract int getValorFila();
        
        public abstract void setValorFila(int valor);
        
        public abstract void setValorColumna(int valor);
        
        public abstract void actualitzaSumaColumna(int v);
        
        public abstract void actualitzaSumaFila(int v);
        
        public abstract int getSumaFila();
        
        public abstract int getSumaColumna();
        
        public abstract ArrayList<Integer> getPossibles();
        
        public abstract void setPossibles(ArrayList<Integer> aux);
        
        public abstract void setValor(int num);
        
        public abstract void borrarPossibles(int i);
        
        public abstract int getValor();
        
    
 
}



