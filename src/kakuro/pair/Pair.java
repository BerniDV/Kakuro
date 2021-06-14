/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.pair;

/**
 *
 * @author rhoms
 */
public class Pair {
    
    private int fila;
    private int columna;
    private int celaBlanca;
    
    public Pair(){
        fila=0;
        columna=0;
        celaBlanca=-1;
    }
    public Pair(int cela){
        fila=-1;
        columna=-1;
        celaBlanca=cela;
    }
    public Pair(int f, int c){
        fila=f;
        columna=c;
        celaBlanca=-1;
    }
    
    public Pair(int f, int c, int blanca){
        fila=f;
        columna=c;
        celaBlanca=blanca;
    }
    
    public Boolean NegraNoBuida(){
        return (fila>0 || columna>0);
    }
    public Boolean NegraBuida(){
        return (fila==0 && columna==0);
    }
    
    public Boolean Negra(){
        return (celaBlanca==-1);
    }
    
    public Boolean Blanca(){
        return (celaBlanca!=-1);
    }

    public int getValorColumna() {
        return columna;
    }

    public int getValorFila() {
       return fila;
    }
    public int getValorBlanca(){
        return celaBlanca;
    }
    
}
