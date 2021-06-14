/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Dades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kakuro.pair.Pair;
import org.json.*;

/**
 *
 * @author ANIOL
 */
public class CtrlDades {
    
    public CtrlDades(){
        
    }
    
    public boolean iniciarSessio(String user,String password) throws IOException{
        //Fer que vagi a buscar el usuari per verificar a la meva base de dades
        boolean dins;
        File FitxerUsuari = new File("../src/kakuro/Dades/PermanenciaUsuaris/"+user+".json");
        
        if(FitxerUsuari.isFile() && FitxerUsuari.exists()){
            dins = this.getPasswordDeUsuari(user).equals(password);            
        }else{
            
            dins = false;
        }
        
        return dins;
        
    }
    
    
    
    public boolean registrarUsuari(String user, String password) throws IOException{
        
        if(ExisteixUsuari(user)){
            
            return false;
        }
       
        if(!ExisteixUsuari(user)){ //Canviar per a que al registrar nomes registri a la meva base de dades
            
            Pair[][] tauler = new Pair[1][1];   
            tauler[0][0] = new Pair();
                    
            GuardarPartida(user, password, tauler, "-1", 0);
            
        }
        
        return true;
        
        
    }


    
    public Pair[][] getTaulerRepositori(String NomTauler) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PersistenciaRepositori/"+NomTauler+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonRepositori = new JSONObject(StringJson);
        
        //Desde aqui fins el seguent copmentari el algoritme només extrau el tauler del JSON
        JSONArray InfoTaulerEnJson = JsonRepositori.getJSONArray("TaulerRepositori");
        
        int DimensionInfo = InfoTaulerEnJson.length();
        
        int NumFiles = JsonRepositori.getInt("NumFiles");
        int NumColumnes = JsonRepositori.getInt("NumColumnes");
                
        Pair[][] TaulerAmbTipusPrimitius = new Pair[NumFiles][NumColumnes];
        
        if(!ExisteixTaulerARepositori(NomTauler)){
            
            System.out.println("Tauler amb nom "+NomTauler+" no existeix!!");
            
        }else {
    
        for(int i = 0; i<DimensionInfo; i++){
            
             JSONArray InfoTaulerPerFiles = InfoTaulerEnJson.getJSONArray(i);

             int DimensioFila = InfoTaulerPerFiles.length();
             
            for(int j=0; j<DimensioFila; ++j){
                
                JSONObject InfoElements = InfoTaulerPerFiles.getJSONObject(j);
               
                    
                    int ValorFila = InfoElements.getInt("valorFila");
                    int ValorColumna = InfoElements.getInt("valorColumna");
                    int ValorCellaBlanca = InfoElements.getInt("valorBlanca");
                            
                    TaulerAmbTipusPrimitius[i][j] = new Pair(ValorFila,ValorColumna,ValorCellaBlanca);
                
            }
            
        }
        //Aqui ja ha extret el tauler i el retorna
        RdFitchers.close();
        }
        return TaulerAmbTipusPrimitius;
    }
    
    public Pair[][] getTaulerParitda(String userName) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PermanenciaUsuaris/"+userName+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonPartida = new JSONObject(StringJson);
        
        //Desde aqui fins el seguent copmentari el algoritme només extrau el tauler del JSON
        JSONArray InfoTaulerEnJson = JsonPartida.getJSONArray("TaulerPartida");
        
        int DimensionInfo = InfoTaulerEnJson.length();
        
        int NumFiles = JsonPartida.getInt("NumFiles");
        int NumColumnes = JsonPartida.getInt("NumColumnes");
                
        Pair[][] TaulerAmbTipusPrimitius = new Pair[NumFiles][NumColumnes];
        
        for(int i = 0; i<DimensionInfo; i++){
            
             JSONArray InfoTaulerPerFiles = InfoTaulerEnJson.getJSONArray(i);

             int DimensioFila = InfoTaulerPerFiles.length();
             
            for(int j=0; j<DimensioFila; ++j){
                
                JSONObject InfoElements = InfoTaulerPerFiles.getJSONObject(j);
               
                    
                    int ValorFila = InfoElements.getInt("valorFila");
                    int ValorColumna = InfoElements.getInt("valorColumna");
                    int ValorCellaBlanca = InfoElements.getInt("valorBlanca");
                            
                    TaulerAmbTipusPrimitius[i][j] = new Pair(ValorFila,ValorColumna,ValorCellaBlanca);
                
            }
            
        }
        //Aqui ja ha extret el tauler i el retorna
        RdFitchers.close();
        return TaulerAmbTipusPrimitius;
    }
    
    
    public int getPuntuacioMesAltaDeUsuari(String userName) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PermanenciaUsuaris/"+userName+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonPartida = new JSONObject(StringJson);
        
        int PuntuacioMesAlta = JsonPartida.getInt("PuntuacioMesAlta");
        
        RdFitchers.close();
        
        return PuntuacioMesAlta;
    }    
    
    public String getPasswordDeUsuari(String userName) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PermanenciaUsuaris/"+userName+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonPartida = new JSONObject(StringJson);
        
        String Password = JsonPartida.getString("Password");
        
        RdFitchers.close();
        
        return Password;
    }  
    
    public String getDificultatTaulerRepositori(String NomTauler) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PersistenciaRepositori/"+NomTauler+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonPartida = new JSONObject(StringJson);
        
        String DificultatTauler = JsonPartida.getString("DificultatTauler");
        
        RdFitchers.close();
        
        return DificultatTauler;
    }    
    
    public String getDificultatTaulerPartidaUsuari(String userName) throws FileNotFoundException, IOException{
        
        BufferedReader RdFitchers;
        RdFitchers = new BufferedReader(new FileReader("../src/kakuro/Dades/PermanenciaUsuaris/"+userName+".json")); 
        
        String StringJson = RdFitchers.readLine();
        
        JSONObject JsonPartida = new JSONObject(StringJson);
        
        String DificultatTauler = JsonPartida.getString("DificultatTauler");
        
        RdFitchers.close();
        
        return DificultatTauler;
    }
    
    
    //Crea un fitxer Json amb totes les dades que guarda una Partida (de moment només el tauler}
    public boolean GuardarPartida(String userName, String Password, Pair[][] taulerPartida, String dificultatTauler, int PuntuacioMesAlta) throws IOException{
        
        JSONObject JsonPartida = new JSONObject();
        
        int NumFiles = taulerPartida.length;
        int NumColumnes = taulerPartida[0].length;
        
        JsonPartida.put("NumFiles", NumFiles);
        JsonPartida.put("NumColumnes", NumColumnes);
        JsonPartida.put("PuntuacioMesAlta", PuntuacioMesAlta);
        JsonPartida.put("TaulerPartida", taulerPartida);
        JsonPartida.put("DificultatTauler", dificultatTauler);
        JsonPartida.put("Password", Password);
        
        FileWriter FitxerWr = new FileWriter("../src/kakuro/Dades/PermanenciaUsuaris/"+userName+".json");
        String resultadoJson = JsonPartida.toString();
        FitxerWr.write(resultadoJson);
        FitxerWr.close();
        return true;
    }
    

    public boolean GuardarTaulerARepositori(String NomTauler, Pair[][] taulerRepositori, String dificultatTauler) throws IOException{
        
        File f = new File("../src/kakuro/Dades/PersistenciaRepositori/"+NomTauler+".json");
        
        if(f.exists()){
            
            return false;
        }
        
        JSONObject JsonPartida = new JSONObject();
        
        int NumFiles = taulerRepositori.length;
        int NumColumnes = taulerRepositori[0].length;
        
        JsonPartida.put("NumFiles", NumFiles);
        JsonPartida.put("NumColumnes", NumColumnes);
        JsonPartida.put("TaulerRepositori", taulerRepositori);
        JsonPartida.put("DificultatTauler", dificultatTauler);
        
        FileWriter FitxerWr = new FileWriter("../src/kakuro/Dades/PersistenciaRepositori/"+NomTauler+".json");
        String resultadoJson = JsonPartida.toString();
        FitxerWr.write(resultadoJson);
        FitxerWr.close();
        return true;
    }
    
    public boolean ExisteixTaulerARepositori(String NomTauler){
        
        File f = new File("../src/kakuro/Dades/PersistenciaRepositori/"+NomTauler+".json");
        
        return f.exists();
    }
    
     public boolean ExisteixUsuari(String NomUsuari){
        
        File f = new File("../src/kakuro/Dades/PermanenciaUsuaris/"+NomUsuari+".json");
        
        return f.exists();
    }
    
    public LinkedHashMap<String, Integer> getRankingGlobal() throws FileNotFoundException, IOException{
        
        LinkedHashMap<String, Integer> ranking = new LinkedHashMap<>();
        
        File CarpetaDatosUsuario = new File("../src/kakuro/Dades/PermanenciaUsuaris");
        
        
        for(File ficheroEntrada : CarpetaDatosUsuario.listFiles()){
            
            String userName = ficheroEntrada.getName();
            userName = userName.substring(0, userName.length()-5);
        
            int MaximaPuntuacioUsuari = getPuntuacioMesAltaDeUsuari(userName);
            ranking.put(userName, MaximaPuntuacioUsuari);
            
        }

        return ranking;
    }
    
    public ArrayList<String> getNomsTaulersRepositori(){
        
        ArrayList<String> llistaKakuros = new ArrayList<String>();
        
        File CarpetaDatosRepositori = new File("../src/kakuro/Dades/PersistenciaRepositori");

        for(File ficheroEntrada : CarpetaDatosRepositori.listFiles()){
            
            String NomTauler = ficheroEntrada.getName();
            NomTauler = NomTauler.substring(0, NomTauler.length()-5);

            llistaKakuros.add(NomTauler);
            
        }
        
        return llistaKakuros;
        
    }
}
