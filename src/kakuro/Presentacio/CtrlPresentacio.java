/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Presentacio;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kakuro.Domini.CtrlPartidaEnCurs;
import kakuro.pair.Pair;
import kakuro.Domini.CtrlUsuaris;
import kakuro.Domini.CtrlGenerarKakuro;
import kakuro.Domini.CtrlRanking;

/**
 *
 * @author rhoms
 */
public class CtrlPresentacio {
    
    private CtrlUsuaris CU;
  
    private CtrlPartidaEnCurs CP;
    
    private CtrlGenerarKakuro CGK;
    
    private CtrlRanking CR;
    
    public CtrlPresentacio(){
        CP = new CtrlPartidaEnCurs();
        CU = new CtrlUsuaris();
        CGK = new CtrlGenerarKakuro();
        CR = new CtrlRanking();
    }
    

     public Pair[][] getTaulerPartida() throws IOException{
        
        return CP.getTaulerDeControlador();       
    }
    public Boolean actaulitzaTauler(int idTauler,int i,int j, int v){
        return CP.nouValor(i,j,v);
    }
    public int getAjudaCela(int i,int j){
        return CP.getAjudaCela(i, j);
    }
    
    public boolean iniciarSessio(String usuari, String contrasenya) throws IOException{
        boolean result = CU.iniciarSessio(usuari, contrasenya);
        if(result){
            
            CP.inicialitzaUsuariIPartida(usuari);

        }
        return result;
    }
    
    public boolean registrarUsuari(String usuari, String contrasenya) throws IOException{
        boolean result = CU.registrarUsuari(usuari, contrasenya);
        return result;
    }
    
    public void crearKakuroAutomaticament(int files, int columnes) throws IOException{
        CGK.GenerarKakuro(files,columnes);
    }
    
    public void GuardarPartidaEnCurs(Pair[][] p) throws IOException{
        CP.gaurdarPartidaEnCurs(p);
    }
    
    public String getNomUsuari(){
        return CP.getNomUsuari();
    }
    public String getContrasenya(){
        return CP.getContrasenya();
    }
    public int getRecord(){
        return CP.getRecord();
    }
    
    public void comencarPartida(int f, int c) throws IOException{
        CP.CrearPartidaAmbKakuroAutomatic(f, c);
        TaulerView tv = new TaulerView(this);
        CP.activarCrono();
    }
    
    public void comencarPartidaRepositori(String nomTauler) throws IOException{
        CP.CrearPartidaAmbKakuroRepositori(nomTauler);
        TaulerView tv2 = new TaulerView(this);
        CP.activarCrono();
    }
    public void comencarPartidaActual() throws IOException{
        TaulerView tv = new TaulerView(this);
    }

    public Pair[][] getTaulerResolt() throws IOException {
       return this.CP.getTaulerResolt();
    }

    public Boolean validarTauler(Pair[][] matriu) throws IOException {
        return CP.validarTauler(matriu);
            
    }
    
    public void crearPartidaManual(int files, int columnes){
            EntrarTauler et = new EntrarTauler(files,columnes,this);
            et.setVisible(true);
    }

    void GuardarTaulerRepo(Pair[][] pmat, String nom) throws IOException {
        this.CP.GuardarTaulerRepo(pmat, nom);
    }
    
    public void carregarPartida() throws IOException{
       this.CP.inicialitzaUsuariIPartida(this.getNomUsuari());
       TaulerView tv = new TaulerView(this);
    }
    
    public int getFilesTaulerPartida() throws IOException{
        return this.CP.getFilesTaulerPartida();
    }
    
    public int getColumnesTaulerPartida() throws IOException{
        return this.CP.getColumnesTaulerPartida();
    }
    
    public LinkedHashMap getRanking() throws IOException{
        return CR.getRankingGlobal();
    }
    
    public String[] getNomTaulersRepositori(){
        ArrayList<String> al = CP.getNomTaulersRepositori();
        String[] taulers = new String[al.size()]; 
        taulers = al.toArray(taulers); 
        return taulers;
    }
    
    public int obtenirPuntuacio() throws IOException{
       return this.CP.obtenirPuntuacio();
    }
    
    public String getTemps(){
        String temps = this.CP.getTemps();
        return temps.substring(0,7);
    }
}
