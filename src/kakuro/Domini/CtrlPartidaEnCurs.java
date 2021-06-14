/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import kakuro.Dades.CtrlDades;
import kakuro.Domini.Cella;
import kakuro.Domini.CellaNegra;
import kakuro.Domini.Tauler;
import kakuro.Presentacio.TaulerView;
import kakuro.pair.Pair;

/**
 *
 * @author rhoms
 */
public class CtrlPartidaEnCurs {
    
    private Pair[][] p;
    private CtrlResoldreKakuro CR;
    private CtrlGenerarKakuro CG;
    private CtrlDades CD;

    private Partida PartidaDelUsuari;
    private Usuari UsuariQueJugaPartida;
    private Tauler TaulerDePartidaResolt;
    
    public CtrlPartidaEnCurs(){
       // tv=new TaulerView();
       CD = new CtrlDades();
       CR = new CtrlResoldreKakuro();
       CG = new CtrlGenerarKakuro();
    }
        
    public void inicialitzaMatriu(Tauler T){
        
        Cella[][] aux=T.getMatriu();
        //sense new s'activa l'excepció null pointer
        p = new Pair[aux.length][aux[0].length];
        
        for(int i=0;i<T.getFiles();++i){
            for(int j=0;j<T.getColumnes();++j){
                if(aux[i][j] instanceof CellaNegra){
                    p[i][j]=new Pair(T.getValorFila(i, j), T.getValorColumna(i,j));
                }
                else{
                    p[i][j]=new Pair(T.getValorCela(i, j));
                }
            }
        }
    }
    
    
    public ArrayList<String> getNomTaulersRepositori(){
        
        return CD.getNomsTaulersRepositori();
        
    }
    
    
    
    public Pair[][] iniMat(Tauler T){
 
        
        Pair[][] aux = new Pair[T.getFiles()][T.getColumnes()];
        
        for(int i=0;i<T.getFiles();++i){
            for(int j=0;j<T.getColumnes();++j){
                if(this.p[i][j].Negra()){
                    aux[i][j]=new Pair(T.getValorFila(i, j), T.getValorColumna(i,j));
                }
                else{
                    aux[i][j]=new Pair(T.getValorCela(i, j));
                }
            }
        }
        return aux;
    }
    
    
    public void CrearPartidaAmbKakuroAutomatic(int NumFiles, int NumColumnes) throws IOException{
        
        Tauler taulerGenerat = CG.GenerarKakuro(NumFiles, NumColumnes);
        this.PartidaDelUsuari.setTauler(taulerGenerat);
        this.inicialitzaMatriu(taulerGenerat);
        //GuardarPartida();
        //inicialitzaUsuariIPartida(UsuariQueJugaPartida.getNom());
        this.TaulerDePartidaResolt = CR.getKakuroResolt(taulerGenerat);
        
    }
    
    public boolean CrearPartidaAmbKakuroRepositori(String NomKakuroRepositori) throws IOException{
        
        if(CD.ExisteixTaulerARepositori(NomKakuroRepositori)){
            
            Tauler TaulerRepositori = this.getTaulerRepositori(NomKakuroRepositori);
            this.PartidaDelUsuari.setTauler(TaulerRepositori);
            this.inicialitzaMatriu(TaulerRepositori);
            //GuardarPartida();
            //inicialitzaUsuariIPartida(UsuariQueJugaPartida.getNom());
            this.TaulerDePartidaResolt = CR.getKakuroResolt(TaulerRepositori);
            return true;
        }
        
        return false;
    }
    
    public void activarCrono(){
        try{
            
            PartidaDelUsuari.reset();
            
        }catch(Exception a){
            
            
        }
        PartidaDelUsuari.iniciarPartida();
    }
    
    
    public void GuardarPartida() throws IOException{
        
        this.inicialitzaMatriu(PartidaDelUsuari.getTauler());
        CD.GuardarPartida(UsuariQueJugaPartida.getNom(), UsuariQueJugaPartida.getPassword(), p, PartidaDelUsuari.getTauler().getDificultat(), UsuariQueJugaPartida.getPuntuacioMesAlta());
        
    }
    
    public Pair[][] getTaulerResolt() throws IOException{
        this.CR =new CtrlResoldreKakuro();
        this.TaulerDePartidaResolt=this.CR.getKakuroResolt(TaulerDePartidaResolt);
        return this.iniMat(this.TaulerDePartidaResolt);
    }
    
    public Boolean check(int i,int j, int valor, Tauler T){
        Cella[][] aux=T.getMatriu();
        if(aux[i][j].getValor()== valor)
            return true;
        
        return false;
        
    }
    
    public int getFilesTaulerPartida() throws IOException{
        this.inicialitzaUsuariIPartida(this.UsuariQueJugaPartida.getNom());
        return this.PartidaDelUsuari.getTauler().getFiles();
    }
    
    public int getColumnesTaulerPartida() throws IOException{
        this.inicialitzaUsuariIPartida(this.UsuariQueJugaPartida.getNom());
        return this.PartidaDelUsuari.getTauler().getColumnes();
    }
            
            
    public void gaurdarPartidaEnCurs(Pair[][] taulerModificat) throws IOException{
        
        this.p=taulerModificat;
        int numFiles = p.length;
        int numColumnes = p[0].length;
        
        Cella[][] MatriuTauler = new Cella[numFiles][numColumnes];
        
        for(int i = 0; i<numFiles; i++){
            
            for(int j = 0; j<numColumnes; j++){
                
                if(p[i][j].Negra()){
                    
                    int ValorFila = p[i][j].getValorFila();
                    int ValorColumna = p[i][j].getValorColumna();
                    
                    MatriuTauler[i][j] = new CellaNegra(ValorColumna, ValorFila);
                    
                }else if(p[i][j].Blanca()){
                    
                    int ValorCellaBlanca = p[i][j].getValorBlanca();
                    MatriuTauler[i][j] = new CellaBlanca(ValorCellaBlanca);
                }
                
            }
        }
        
        Tauler TaulerPartida = new Tauler(numFiles, numColumnes);
        
        TaulerPartida.setMatriu(MatriuTauler);
        
        this.PartidaDelUsuari.setTauler(TaulerPartida);
        this.GuardarPartida();
        this.inicialitzaUsuariIPartida(this.UsuariQueJugaPartida.getNom());
    }

    
    public Pair[][] getTaulerDeControlador(){
        
        return p;
    }

    public boolean nouValor(int i, int j, int v) {
       return (TaulerDePartidaResolt.getValorCela(i, j)==v);     
    }

    public int getAjudaCela(int i, int j) {
        return TaulerDePartidaResolt.getValorCela(i, j);
    }
    
    public Tauler getTaulerRepositori(String NomTauler) throws IOException{

        Pair[][] TaulerDadesPrimitives = CD.getTaulerRepositori(NomTauler);
        int numFiles = TaulerDadesPrimitives.length;
        int numColumnes = TaulerDadesPrimitives[0].length;
        String dificultat = CD.getDificultatTaulerRepositori(NomTauler);
        
        Cella[][] MatriuTauler = new Cella[numFiles][numColumnes];
        
        for(int i = 0; i<numFiles; i++){
            
            for(int j = 0; j<numColumnes; j++){
                
                if(TaulerDadesPrimitives[i][j].Negra()){
                    
                    int ValorFila = TaulerDadesPrimitives[i][j].getValorFila();
                    int ValorColumna = TaulerDadesPrimitives[i][j].getValorColumna();
                    
                    MatriuTauler[i][j] = new CellaNegra(ValorColumna, ValorFila);
                    
                }else if(TaulerDadesPrimitives[i][j].Blanca()){
                    
                    int ValorCellaBlanca = TaulerDadesPrimitives[i][j].getValorBlanca();
                    MatriuTauler[i][j] = new CellaBlanca(ValorCellaBlanca);
                }
                
            }
        }
        
        Tauler TaulerRepositori = new Tauler(numFiles, numColumnes, dificultat);
        TaulerRepositori.setMatriu(MatriuTauler);
        
        return TaulerRepositori;
    }
    
    public void inicialitzaUsuariIPartida(String Username) throws IOException{
        
        
        int puntuacioMesAlta = CD.getPuntuacioMesAltaDeUsuari(Username);
        String Password = CD.getPasswordDeUsuari(Username);
        
        Usuari u = new Usuari(Username, Password, puntuacioMesAlta);
        
        Partida partidaUsuari = getPartidaUsuari(u);
        u.GuaradarPartida(partidaUsuari);
        
        
        Tauler TaulerAResoldre = partidaUsuari.getTauler().clonarTauler();
        TaulerAResoldre.TreureValorsDeCellesBlanques();
        
        this.UsuariQueJugaPartida = u;  
        this.TaulerDePartidaResolt = CR.getKakuroResolt(TaulerAResoldre);
        
    }
    
    //Retorna la partida guardada del usuari especificat
    private Partida getPartidaUsuari(Usuari user) throws IOException{
        
        String userName = user.getNom();
        
        Pair[][] TaulerDadesPrimitives = CD.getTaulerParitda(userName);
        
        this.p = TaulerDadesPrimitives;
        
        int numFiles = TaulerDadesPrimitives.length;
        int numColumnes = TaulerDadesPrimitives[0].length;
        String dificultat = CD.getDificultatTaulerPartidaUsuari(userName);
        
        Cella[][] MatriuTauler = new Cella[numFiles][numColumnes];
        
        for(int i = 0; i<numFiles; i++){
            
            for(int j = 0; j<numColumnes; j++){
              
                if(TaulerDadesPrimitives[i][j].Negra()){
                    
                    int ValorFila = TaulerDadesPrimitives[i][j].getValorFila();
                    int ValorColumna = TaulerDadesPrimitives[i][j].getValorColumna();
                    
                    MatriuTauler[i][j] = new CellaNegra(ValorColumna, ValorFila);
                    
                }else if(TaulerDadesPrimitives[i][j].Blanca()){
                    
                    int ValorCellaBlanca = TaulerDadesPrimitives[i][j].getValorBlanca();
                    MatriuTauler[i][j] = new CellaBlanca(ValorCellaBlanca);
                }
                
            }
        }
        
        Tauler TaulerPartidaUsuari = new Tauler(numFiles, numColumnes, dificultat);
        TaulerPartidaUsuari.setMatriu(MatriuTauler);

        Partida partidaUsuari = new Partida();
        
        partidaUsuari.setUsuari(user);
        partidaUsuari.setTauler(TaulerPartidaUsuari);

        this.PartidaDelUsuari = partidaUsuari;
        
        return partidaUsuari;
    }
    
    public String getNomUsuari(){
        return this.UsuariQueJugaPartida.getNom();
    }
    public String getContrasenya(){
        return this.UsuariQueJugaPartida.getPassword();
    }
    public int getRecord(){
        return this.UsuariQueJugaPartida.getPuntuacioMesAlta();
    }

    public Boolean validarTauler(Pair[][] matriu) throws IOException {
        Tauler T=new Tauler(matriu.length, matriu[0].length);
                
        Cella[][] MatriuTauler = new Cella[matriu.length][matriu[0].length];
        
        for(int i = 0; i<matriu.length; i++){
            
            for(int j = 0; j<matriu[0].length; j++){
                
                if(matriu[i][j].Negra()){


                    int ValorFila = matriu[i][j].getValorFila();
                    int ValorColumna = matriu[i][j].getValorColumna();
                    
                    MatriuTauler[i][j] = new CellaNegra(ValorColumna, ValorFila);
                    
                }else if(matriu[i][j].Blanca()){
                    
                    int ValorCellaBlanca = matriu[i][j].getValorBlanca();
                    MatriuTauler[i][j] = new CellaBlanca(ValorCellaBlanca);
                }
                
            }
        }
        T.setMatriu(MatriuTauler);
        T.TreureValorsDeCellesBlanques();
       this.CR.getKakuroResolt(T);
       int sol;
       sol= T.getSolucions();
      
       if(sol==1){
            
            T.TreureValorsDeCellesBlanques();
            this.PartidaDelUsuari.setTauler(T);
            this.inicialitzaMatriu(T);
            //this.GuardarPartida();
            //this.inicialitzaUsuariIPartida(UsuariQueJugaPartida.getNom());
            Tauler TaulerAResoldre = T.clonarTauler();
            this.TaulerDePartidaResolt = CR.getKakuroResolt(TaulerAResoldre);
            this.PartidaDelUsuari.iniciarPartida();
            return true;
       }
       else{
           return false;
           
       }
    }

    public boolean GuardarTaulerRepo(Pair[][] pmat, String NomTauler) throws IOException {
       
        
        if(!CD.ExisteixTaulerARepositori(NomTauler)){
            
        
        int numFiles = pmat.length;
        int numColumnes = pmat[0].length;
        
        Cella[][] MatriuTauler = new Cella[numFiles][numColumnes];
        
        for(int i = 0; i<numFiles; i++){
            
            for(int j = 0; j<numColumnes; j++){
                
                if(pmat[i][j].Negra()){
                    
                    int ValorFila = pmat[i][j].getValorFila();
                    int ValorColumna = pmat[i][j].getValorColumna();
                    
                    MatriuTauler[i][j] = new CellaNegra(ValorColumna, ValorFila);
                    
                }else if(pmat[i][j].Blanca()){
                    
                    int ValorCellaBlanca = pmat[i][j].getValorBlanca();
                    MatriuTauler[i][j] = new CellaBlanca(ValorCellaBlanca);
                }
                
            }
        }
        
        Tauler TaulerRepo = new Tauler(numFiles, numColumnes);
        
        TaulerRepo.setMatriu(MatriuTauler);

        CD.GuardarTaulerARepositori(NomTauler, pmat, TaulerRepo.getDificultat() );
        return true;
        
        }
        return false;
    }

    public int obtenirPuntuacio() throws IOException{
        
       int Puntuacio = 0;
        try {
            Puntuacio = this.PartidaDelUsuari.acabarPartida();
            this.UsuariQueJugaPartida.setPuntuacioMesAlta(Puntuacio);
            this.GuardarPartida();
        } catch (RuntimeException ex) {
            Puntuacio = 0;
        }
       
       return Puntuacio;
    }
    
    public String getTemps(){
        
        String temps="0";
        
        try {
        temps = this.PartidaDelUsuari.getActualTime();
        
        } catch (Exception ex){
            temps = "0:0.000000000";
        }           
        
        return temps;
    }
}
