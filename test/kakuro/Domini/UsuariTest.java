/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kakuro.Domini;

import java.io.IOException;
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
public class UsuariTest {
    
    public UsuariTest() {
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
    public void UsuariTest() {
        System.out.println("UsuariTest");
        Usuari U;
        U = new Usuari();
        assertEquals(U.getNom(), null);
        assertEquals(U.getPassword(), null);
    }
    /*
    @Test
    public void UsuariSenseParam() {
        System.out.println("UsuariSenseParam");
        String n = "User";
        String p = "1234";
        Usuari U;
        U = new Usuari(n, p);
        assertEquals(U.getNom(), n);
        assertEquals(U.getPassword(), p);
    }
    
    @Test
    public void validaNom() throws IOException {
        System.out.println("Valida Nom");
        String n = "User";
        String p = "1234";
        Usuari U;
        U = new Usuari();
        boolean b = U.Valida(n,p);
        //primera prova amb usuari existent al sistema
        //s'espera la b true
        if(b) System.out.println("User és al sistema");
        else System.out.println("User no és al sistema");
        assertEquals(b, true);
        n = "User2";
        b = U.Valida(n, p);
        //segona prova amb usuari existent al sistema però escrit amb minuscules
        //s'espera la b false
        if(b) System.out.println("User2 és al sistema");
        else System.out.println("User2 no és al sistema");
        assertEquals(b, false);
        n = "User3";
        b = U.Valida(n,p);
        //tercera prova amb usuari no existent al sistema
        //s'espera la b false
        if(b) System.out.println("User3 és al sistema");
        else System.out.println("User3 no és al sistema");
        assertEquals(b, false);
    }
    
    @Test
    public void validaPwrd() throws IOException {
        System.out.println("Valida Password");
        String n = "User";
        String p = "1234";
        Usuari U;
        U = new Usuari();
        boolean b = U.Valida(n,p);
        //primera prova amb usuari existent al sistema i pwrd correcte
        //s'espera la b true
        if(b) System.out.println("User és al sistema i té pwrd 1234");
        else System.out.println("User no té pwrd 1234");
        assertEquals(b, true);
        p = "111";
        b = U.Valida(n,p);
        //segona prova amb usuari existent al sistema però pwrd incorrecte
        //s'espera la b false
        if(b) System.out.println("User és al sistema i té pwrd 111");
        else System.out.println("User no té pwrd 111");
        assertEquals(b, false);
        n = "User3";
        b = U.Valida(n,p);
        //tercera prova amb usuari no existent al sistema i sense pwrd
        //s'espera la b false
        if(b) System.out.println("User3 és al sistema i té pwrd 234");
        else System.out.println("User3 no és al sistema");
        assertEquals(b, false);
    }*/
}
