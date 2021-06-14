package kakuro.Domini;

import java.io.Serializable;

public class Usuari{

    private String userName;
    private String password;
    private Partida partida;
    private int PuntuacioMesAlta;
    
    //creadora sense parametres
    public Usuari(){
        this.userName = null;
        this.password = null;
        this.partida = null;
        this.PuntuacioMesAlta = 0;
    }

    //creadora amb paràmetres
    public Usuari(String username, String password){
        this.userName = username;
        this.password = password;
        this.partida = null;
    }
    
    public Usuari(String username, String password, int PuntuacioMesAlta){
        this.userName = username;
        this.password = password;
        this.partida = null;
        this.PuntuacioMesAlta = PuntuacioMesAlta;
    }

    public String getNom(){
        return this.userName;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public Partida getPartida(){
        return this.partida;
    }
    
    public int getPuntuacioMesAlta(){
        return this.PuntuacioMesAlta;
    }
    
    public void setPuntuacioMesAlta(int punts){
        
        if(this.PuntuacioMesAlta<punts)
        this.PuntuacioMesAlta = punts;
        
    }
    
    public void canviaNom(String nom){
        this.userName = nom;
    }
    
    
    public void canviaPassword(String password){
        this.password = password;
    }
    
    public void GuaradarPartida(Partida p){
        this.partida = p;
    }
    
    public Partida CarregaPartida(){
        return this.partida;
    }
}