/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import kakuro.pair.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oriol
 */
public class CtrlDadesTest {
    
    public CtrlDadesTest() {
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
    
    @Test
    public void ExisteixID() {
        CtrlTauler ct = new CtrlTauler();
        boolean existeix = ct.ExisteixID("Tauler1");
        if(existeix){
            System.out.println("El fitxer existeix");
        }
        else{
            System.out.println("El fitxer no existeix o hi ha errors");
        }
    }
}
