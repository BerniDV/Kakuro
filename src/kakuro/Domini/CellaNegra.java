package kakuro.Domini;


import java.io.Serializable;
import java.util.ArrayList;


public class CellaNegra extends Cella{

	private int columna;
	private int fila;

	private int sumacolumna; 
	private int sumafila;

	//creadora sense parametres
	public CellaNegra(){
		super();
		this.columna = -1;
		this.fila = -1;
		this.sumacolumna = 0;
		this.sumafila = 0;
	}

	//creadora amb valors de la cella
	public CellaNegra(int columna, int fila){
		super();
		this.columna = columna;
		this.fila = fila;
		this.sumacolumna = 0;
		this.sumafila = 0;
	}
        
        @Override
        public int getValorColumna(){
            return this.columna;
        }
        @Override
        public int getValorFila(){
            return this.fila;
        }
        @Override
        public void actualitzaSumaColumna(int v){
            sumacolumna +=v;
        }
        @Override
        public void actualitzaSumaFila(int v){
            sumafila +=v;
        }
        
        @Override
        public int getSumaColumna(){
            return sumacolumna;
        }
        @Override
        public int getSumaFila(){
            return sumafila;
        }
        
        @Override
        public ArrayList<Integer> getPossibles(){
            throw new UnsupportedOperationException("Error: cela negra. ");
        }

    @Override
    public void setPossibles(ArrayList<Integer> aux) {
        throw new UnsupportedOperationException("Error: cela negra."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValor(int num) {
        throw new UnsupportedOperationException("Error: cela negra."); //To change body of generated methods, choose Tools | Templates.
    }
        
  /*  public CellaNegra trobacella(int fila, int col, int id)
    {

    }   */ 

    @Override
    public int getValor() {
        throw new UnsupportedOperationException("Error: cela negra."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarPossibles(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValorFila(int valor) {
        
        this.fila = valor;
    }
    
    @Override
    public void setValorColumna(int valor) {
        
        this.columna = valor;
    }
        
}