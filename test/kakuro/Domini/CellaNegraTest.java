/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

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
public class CellaNegraTest {
    
    public CellaNegraTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void CellaNegraTest() {
        System.out.println("CellaNegraTest");
        CellaNegra cn = new CellaNegra();
        assertEquals(cn.getValorColumna(), -1);
        assertEquals(cn.getValorFila(), -1);
        assertEquals(cn.getSumaColumna(), 0);
        assertEquals(cn.getSumaFila(), 0);
    }
    
    @Test
    public void CellaNegraParamTest() {
        System.out.println("CellaNegraParamTest");
        int c = 2;
        int f = 2;
        CellaNegra cn = new CellaNegra(c, f);
        assertEquals(cn.getValorColumna(), c);
        assertEquals(cn.getValorFila(), f);
        assertEquals(cn.getSumaColumna(), 0);
        assertEquals(cn.getSumaFila(), 0);
        
    }
    
    @Test
    public void ActualitzaValors() {
        System.out.println("ActualitzaValors");
        int c = 2;
        int f = 2;
        CellaNegra cn = new CellaNegra(c, f);
        assertEquals(cn.getSumaColumna(), 0);
        assertEquals(cn.getSumaFila(), 0);
        int sc = 3;
        int sf = 10;
        cn.actualitzaSumaColumna(sc);
        cn.actualitzaSumaFila(sf);
        assertEquals(cn.getSumaColumna(), sc);
        assertEquals(cn.getSumaFila(), sf);
    }
}
