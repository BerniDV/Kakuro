/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ANIOL
 */
public class PartidaTest {
    
    public PartidaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    // Test of iniciarPartida method, of class Partida.
    /*
    @Test
    public void testIniciarPartida() {
        System.out.println("iniciarPartida");
        Partida instance = new Partida();
        instance.iniciarPartida();
        for(int i=1;i<5;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}    
            System.out.println(i);    
        } 
        // TODO review the generated test code and remove the default call to fail.
        String temps = instance.getActualTime();
        System.out.println(temps);
        assert temps != null : "Temps is null";
    }

    
    // Test of aturarPartida method, of class Partida.
    
    @Test
    public void testAturarPartida() {
        System.out.println("aturarPartida");
        Partida instance = new Partida();
        instance.iniciarPartida();
        for(int i=1;i<5;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}    
            System.out.println(i);    
        } 
        Duration result = instance.aturarPartida();
        System.out.println(result.getNano());
        assert result != null : "El temps de volta es null";
    }

    
    // Test of getElapsedTime method, of class Partida.
    
    @Test
    public void testGetElapsedTime() {
        System.out.println("getElapsedTime");
        Partida instance = new Partida();
        //si iniciem la partida i la parem:
        instance.iniciarPartida();
        for(int i=1;i<5;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}    
            System.out.println(i);    
        } 
        instance.aturarPartida();
        Duration result = instance.getElapsedTime();
        System.out.println(result);
        assert result != null : "El temps de volta es null";
    }

    
    // Test of reset method, of class Partida.
    
    @Test
    public void testReset() {
        System.out.println("reset");
        Partida instance = new Partida();
        instance.reset();
        Duration now = instance.getElapsedTime();
        // TODO review the generated test code and remove the default call to fail.
        assert now == null : "The actual time has not been reset";
    }
    */
    @Test
    public void testPunts() throws IOException {
        System.out.println("Punts");
        Tauler t = new Tauler(5,5);
        Usuari u = new Usuari("a", "a", 10);
        System.out.println("puntuació més alta del usuari al iniciar: " + u.getPuntuacioMesAlta());
        Partida p = new Partida(t, u);
        p.iniciarPartida();
        for(int i=1;i<10;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}  
        } 
        p.acabarPartida();
        System.out.println("puntuació de la partida: "+ p.getPunts());
        System.out.println("puntuació més alta del usuari fins ara: " + u.getPuntuacioMesAlta());
        HashMap <String, Integer> RankMap = new HashMap <>();
        Ranking r = new Ranking();
        r.ActualitzaRecord(p);
        System.out.println("puntuació més alta del usuari actual: " + u.getPuntuacioMesAlta());
        System.out.println("Ranking: ");
        r.PrintRanking();
        /*
        System.out.println("Punts2");
        Tauler t2 = new Tauler(4,5);
        System.out.println("puntuació més alta del usuari al iniciar p2: " + u.getPuntuacioMesAlta());
        Partida p2 = new Partida(t2, u);
        p2.iniciarPartida();
        for(int i=1;i<10;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}  
        } 
        p2.acabarPartida();
        System.out.println("puntuació de la partida2: "+ p2.getPunts());
        System.out.println("puntuació més alta del usuari fins ara: " + u.getPuntuacioMesAlta());
        r.ActualitzaRecord(p2);
        System.out.println("puntuació més alta del usuari actual: " + u.getPuntuacioMesAlta());
        System.out.println("Ranking: ");
        r.PrintRanking();
        */
        System.out.println("Punts3");
        Tauler t3 = new Tauler(9,9);
        Usuari u2 = new Usuari("Uri", "r", 100);
        System.out.println("puntuació més alta del usuari2 al iniciar p3: " + u2.getPuntuacioMesAlta());
        Partida p3 = new Partida(t3, u2);
        p3.iniciarPartida();
        for(int i=1;i<10;i++)  
        {    
            try  
            {  
                Thread.sleep(500);  
            }catch(InterruptedException e){System.out.println(e);}  
        } 
        p3.acabarPartida();
        System.out.println("puntuació de la partida2: "+ p3.getPunts());
        System.out.println("puntuació més alta del usuari fins ara: " + u2.getPuntuacioMesAlta());
        r.ActualitzaRecord(p3);
        System.out.println("puntuació més alta del usuari actual: " + u2.getPuntuacioMesAlta());
        System.out.println("Ranking sense ordenar: ");
        r.PrintRanking();
        System.out.println("Ranking ordenat: ");
        r.OrdenarRanking();
        r.PrintRanking();
    }
    
}
