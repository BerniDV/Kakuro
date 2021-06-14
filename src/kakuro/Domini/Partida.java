package kakuro.Domini;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

public class Partida{

    private Tauler t;
    private Usuari u;

    private Instant startTime, endTime;
    private Duration duration;
    private boolean isRunning;
    
    private int punts;

    //creadora sense parametres
    public Partida(){
        this.startTime = null;
        this.endTime = null;
        this.duration = null;
    }

    public Partida(Tauler tauler, Usuari user){
        this.startTime = null;
        this.endTime = null;
        this.duration = null;
        this.t = tauler;
        this.u = user;
        this.isRunning = false;
    }

    public Usuari getUsuari(){
        return this.u;
    }
    
    public void setUsuari(Usuari usu){
        this.u = usu;
    }

    public Tauler getTauler(){
        return this.t;
    }
    
    public void setTauler(Tauler tauler){
        this.t =tauler;
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    public void iniciarPartida(){
        if (this.isRunning){
            throw new RuntimeException("La partida ja ha començat!");
        }
        this.isRunning = true;
        this.startTime = Instant.now();
    }

    public Duration aturarPartida(){
        this.endTime = Instant.now();
        if (!this.isRunning){
            throw new RuntimeException("La partida encara no ha començat!");
        }
        this.isRunning = false;
        Duration result = Duration.between(this.startTime, this.endTime);
        if (this.duration == null){
            this.duration = result;
        } else {
            this.duration = this.duration.plus(result);
        }
        
        return this.getElapsedTime(); //retorna el temps en format Duration. Si es volgues retornar en format string, cridar a getActualTime().
    }

    public int acabarPartida(){//en un futur pot arribar a fer més coses
        
        aturarPartida();
        CalculaPunts();
        return this.punts;
    }
    
    public Duration getElapsedTime(){
        return this.duration;
    }

    public String getActualTime(){
        Instant now = Instant.now();
        if(this.isRunning){
            Duration actual = Duration.between(this.startTime, now);
            long minutes = actual.toMinutes();
            long seconds = actual.getSeconds() % 60;
            long nanoSeconds = actual.toNanos() % 1000_000_000L;
            String timer = (String.format("%d:%d.%d", minutes, seconds, nanoSeconds));
            return timer;
        }
        else{
            Duration actual = this.duration;
            long minutes = actual.toMinutes();
            long seconds = actual.getSeconds() % 60;
            long nanoSeconds = actual.toNanos() % 1000_000_000L;
            String timer = (String.format("%d:%d.%d", minutes, seconds, nanoSeconds));
            return timer;
        }
    }

    public void reset(){
        this.startTime = null;
        this.endTime = null;
        this.duration = null;
        this.isRunning = false;
    }
    
    private float retornaTempsSegons(){
        Duration result = this.duration;
        long seg = result.getSeconds();
        return seg;
    }
    
    private void CalculaPunts(){
        int bonus =  t.getBonus();
        float temps = (int) retornaTempsSegons()/30;
        int p=0;
        
        if(temps==0){
            p = (int) ((int) bonus * this.t.getFiles()* this.t.getColumnes());

        }else{
            int penalitzacioTemps = 0;
            
            if(this.t.getDificultat().equals("facil")){
                
                penalitzacioTemps = 1;
                
            }else if(this.t.getDificultat().equals("mig")){
                
                penalitzacioTemps = 3;
                
            }else{
                
                penalitzacioTemps = 5;
            }
            p = (int) (bonus * this.t.getFiles()* this.t.getColumnes()-(temps*penalitzacioTemps));

        }
        
        if(p<0){
            
            p = 0;
        }
        this.punts = p;
    }
    
    public int getPunts(){
        return this.punts;
    }
}