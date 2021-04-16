/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.konfiguracije.bp;

import java.io.File;
import java.util.Properties;
import org.foi.nwtis.rsudec.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.rsudec.konfiguracije.NemaKonfiguracije;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author roby0
 */
public class BP_KonfiguracijaTest {
    
    BP_Konfiguracija instance = null;
    private String datoteka = "konf1.xml";
    public BP_KonfiguracijaTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws NemaKonfiguracije, NeispravnaKonfiguracija {
        File f = new File(datoteka);
        
        if(f.exists() && f.isFile()){ 
            instance = new BP_Konfiguracija(datoteka);
        } else {
            throw new NemaKonfiguracije("Datoteka pod nazivom " + datoteka + " ne postoji ili nije datoteka.");
        }

    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAdminDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetAdminDatabase() {
        System.out.println("getAdminDatabase");
        String expResult = "";
        String result = instance.getAdminDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminPassword method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetAdminPassword() {
        System.out.println("getAdminPassword");
        String expResult = "";
        String result = instance.getAdminPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   @Test
    @Disabled
    public void testGetAdminUsername() {
        System.out.println("getAdminUsername");
        String expResult = "";
        String result = instance.getAdminUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDriverDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetDriverDatabase_0args() {
        System.out.println("getDriverDatabase");
        
        String expResult = "org.apache.derby.jdbc.ClientDriver";
        String result = instance.getDriverDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDriverDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetDriverDatabase_String() {
        System.out.println("getDriverDatabase");
        String bp_url = "";
        String expResult = "";
        String result = instance.getDriverDatabase(bp_url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDriversDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetDriversDatabase() {
        System.out.println("getDriversDatabase");
        Properties expResult = null;
        Properties result = instance.getDriversDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServerDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetServerDatabase() {
        System.out.println("getServerDatabase");
        String expResult = "";
        String result = instance.getServerDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDatabase method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetUserDatabase() {
        System.out.println("getUserDatabase");
        
        String expResult = "nwtis_g3";
        String result = instance.getUserDatabase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getUserPassword method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetUserPassword() {
        System.out.println("getUserPassword");
        String expResult = "";
        String result = instance.getUserPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserUsername method, of class BP_Konfiguracija.
     */
    @Test
    @Disabled
    public void testGetUserUsername() {
        System.out.println("getUserUsername");
        String expResult = "";
        String result = instance.getUserUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
