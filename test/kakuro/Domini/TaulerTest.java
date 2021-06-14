/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rhoms
 */
public class TaulerTest {
    
    public TaulerTest() {
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

    /**
     * Test of getId method, of class Tauler.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Tauler instance = new Tauler();
        int expResult = -1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFiles method, of class Tauler.
     */
    @Test
    public void testGetFiles() {
        System.out.println("getFiles");
        Tauler instance = new Tauler(3,3);
        int expResult = 3;
        int result = instance.getFiles();
        assertEquals(expResult, result);
       
        instance=new Tauler(5,4);
        expResult=5;
        result = instance.getFiles();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getColumnes method, of class Tauler.
     */
    @Test
    public void testGetColumnes() {
        System.out.println("getColumnes");
        Tauler instance = new Tauler(5,5);
        int expResult = 5;
        int result = instance.getColumnes();
        assertEquals(expResult, result);
        
        instance=new Tauler(5,4);
        expResult=4;
        result = instance.getColumnes();
        assertEquals(expResult, result);

        
    }

    /**
     * Test of getDificultat method, of class Tauler.
     */
    @Test
    public void testGetDificultat() throws IOException {
        System.out.println("getDificultat");
        String s="dificil";
        Tauler instance = new Tauler(3,3,s);
        String expResult = s;
        String result = instance.getDificultat();
        assertEquals(expResult, result);
       

    }
     /**
     * Test of setColumnes method, of class Tauler.
     */
    @Test
    public void testsetColumnes() {
        System.out.println("setColumnes");
        Tauler instance = new Tauler();
        int  expResult = 3; 
        instance.setColumnes(3);
        int result=instance.getColumnes();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of setSolucions method, of class Tauler.
     */
    @Test
    public void testsetSolucions() {
        System.out.println("setColumnes");
        Tauler instance = new Tauler();
        int  expResult = 1; 
        instance.setSolucions(1);
        int result=instance.getSolucions();
        assertEquals(expResult, result);
    }


    /**
     * Test of getMatriu method, of class Tauler.
     */
    @Test
    public void testGetMatriu() {
        System.out.println("getMatriu");
        Tauler instance = new Tauler(3,3);
        Cella[][] expResult = new Cella[3][3]; 
        Cella[][] result = instance.getMatriu();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of totalCeles method, of class Tauler.
     */
    @Test
    public void testTotalCeles() {
        System.out.println("totalCeles");
        Tauler instance = new Tauler(2,3);
        int expResult = 2*3;
        assertEquals(expResult, instance.totalCeles());
        
        int a=20;
        int b=12;
        Tauler instance2 = new Tauler(a,b);
        int expResult2=a*b; 
        assertEquals(expResult2, instance2.totalCeles());

    }
    @Test
    public void Dificultat() {
        System.out.println("CalcularDificultat");
        Tauler t = new Tauler(4,5);
        String dif = t.getDificultat();
        System.out.println(dif);
    }
}
