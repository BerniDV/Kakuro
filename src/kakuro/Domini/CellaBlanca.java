package kakuro.Domini;

import java.io.Serializable;
import java.util.ArrayList;

public class CellaBlanca extends Cella{

    private int valor;

    private ArrayList<Integer> possibles;


    //creadora sense parametres
    public CellaBlanca(){
        super();
        this.valor = 0;
        possibles=new ArrayList();
    }

    //creadora amb valors de la cella
    public CellaBlanca(int valor){
        super();
        this.valor = valor;
        possibles = new ArrayList();
    }

    @Override
    public int getValor(){
        return this.valor;
    }

    public void setValor(int num){
        this.valor = num;
    }

    public void esborrarValor(){
        this.valor = 0;
    }

    public boolean estaBuida(){
        return this.valor > 0;
    }

    @Override
    public int getValorColumna() {
        throw new UnsupportedOperationException("Error: cela blanca."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValorFila() {
        throw new UnsupportedOperationException("Error: cela blanca. "); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public ArrayList<Integer> getPossibles(){
        return possibles;
    }
        
    @Override
    public void setPossibles(ArrayList<Integer> aux){
        this.possibles=aux;
    }
        
    @Override
    public void borrarPossibles(int i){
        for(int a=0;a<this.possibles.size();++a)
            if(this.possibles.get(a)==i )
                this.possibles.remove(a);
        this.possibles.trimToSize();
    }
    
   
  /*  public Cella trobacella(int fila, int col, int id)
    {

    }*/

    @Override
    public void actualitzaSumaColumna(int v) {
        throw new UnsupportedOperationException("Error: cela blanca. "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualitzaSumaFila(int v) {
        throw new UnsupportedOperationException("Error: cela blanca. "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSumaFila() {
        throw new UnsupportedOperationException("Error: cela blanca. "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSumaColumna() {
        throw new UnsupportedOperationException("Error: cela blanca. "); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValorFila(int valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setValorColumna(int valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}