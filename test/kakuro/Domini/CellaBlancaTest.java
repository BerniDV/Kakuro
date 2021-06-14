/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.util.ArrayList;
import java.util.Scanner;
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
public class CellaBlancaTest {
    
    public CellaBlancaTest() {
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
    public void CellaBlancaTest() {
        System.out.println("CellaBlancaTest");
        CellaBlanca cb = new CellaBlanca();
        assertEquals(cb.getValor(), -1); //comprovem que el valor és -1
    }
    
    @Test
    public void SetValorTest() {
        Scanner scan = new Scanner(System.in);
        System.out.println("SetValorTest");
        CellaBlanca cb = new CellaBlanca();
        int valor= 1;
        cb.setValor(valor);
        assertEquals(cb.estaBuida(), false); //comprovem que no és buida
        assertEquals(cb.getValor(), 1); //comprovem que el nou valor és 1
    }
    
    @Test
    public void EsborrarTest() {
        System.out.println("EsborrarTest");
        CellaBlanca cb = new CellaBlanca();
        cb.setValor(1); //donem valor previ per posteriorment esborrar
        assertEquals(cb.getValor(), 1); //comprovem que el valor és 1
        cb.esborrarValor();
        assertEquals(cb.estaBuida(), true); //comprovem que és buida
    }
    
    @Test
    public void PossiblesTest() {
        System.out.println("PossiblesTest");
        CellaBlanca cb = new CellaBlanca();
        ArrayList<Integer> p= new ArrayList<Integer>();
        p.add(1);
        p.add(2);
        p.add(5);
        cb.setPossibles(p);
        ArrayList<Integer> possibles = cb.getPossibles();
        assertEquals(possibles, p);
        cb.borrarPossibles(2);
        assertEquals(cb.getPossibles(), p);
    }
}
