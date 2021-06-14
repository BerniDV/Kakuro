/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Dades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import kakuro.Domini.CreadorKakuro;
import kakuro.Domini.CtrlPartidaEnCurs;
import kakuro.Domini.Tauler;
import kakuro.pair.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berni
 */
public class CtrlDadesTest {
    
    
    public CtrlDadesTest() {
        

 
    }
    
    @Test
    public void TestGuardarPartida() throws IOException{
        
        CtrlDades CD;
        CtrlPartidaEnCurs PC;
        CreadorKakuro CK;
        Pair[][] tauler;
        Tauler t;
        
        CD = new CtrlDades();
        PC = new CtrlPartidaEnCurs();
        CK = new CreadorKakuro();
        t = new Tauler();
        
        t = CK.GeneradorKakuro(3,3);
        PC.inicialitzaMatriu(t);
        tauler = PC.getTaulerDeControlador();

        assertEquals( "Should display true", true,CD.GuardarPartida("prova2","contraa", tauler,"alta",30) ); 
        
        
    }
    
    @Test
    public void TestGetPartida() throws IOException{
        
        CtrlDades CD;
        CtrlPartidaEnCurs PC;
        CreadorKakuro CK;
        Pair[][] tauler;
        Tauler t;
        
        CD = new CtrlDades();
        PC = new CtrlPartidaEnCurs();
        CK = new CreadorKakuro();
        t = new Tauler();
        CD.getTaulerParitda("manolo");
        

        assertEquals( "Should display true", true,true); 
        
        
    }
    
    @Test
    public void TestGuardarTaulerRepositori() throws IOException{
        
        CtrlDades CD;
        CtrlPartidaEnCurs PC;
        CreadorKakuro CK;
        Pair[][] tauler;
        Tauler t;
        
        CD = new CtrlDades();
        PC = new CtrlPartidaEnCurs();
        CK = new CreadorKakuro();
        t = new Tauler();
        
        t = CK.GeneradorKakuro(6, 6);
        PC.inicialitzaMatriu(t);
        tauler = PC.getTaulerDeControlador();

        assertEquals( "Tauler ja existeix", true,CD.GuardarTaulerARepositori("Tauler4", tauler, "6") ); 
        
        
    }
    
    @Test
    public void TestGetTaulerRepositori() throws IOException{
        
        CtrlDades CD;
        CtrlPartidaEnCurs PC;
        CreadorKakuro CK;
        Pair[][] tauler;
        Tauler t;
        
        CD = new CtrlDades();
        PC = new CtrlPartidaEnCurs();
        CK = new CreadorKakuro();
        t = new Tauler();
        CD.getTaulerRepositori("Tauler1");
        

        assertEquals( "Should display true", true,true); 
        
        
    }
    
    
}
