/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ss.klijent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robi
 */
public class ServerSocketKlijent {
    
    public ServerSocketKlijent(){
        
    }
    
    public static String posalji(String komanda, int port){
        String res = "";
         InputStream in = null;
        OutputStreamWriter out = null;
        Socket klijent = null;
        try {
                klijent = new Socket("localhost", port);
                in = klijent.getInputStream();
                out = new OutputStreamWriter(klijent.getOutputStream());

                out.write(komanda);
                out.flush();
                klijent.shutdownOutput();   //klijent zatvara svoj dio slanja prema posluzitelju, server ne ocekuje vise podatke od klijenta

                StringBuilder porukaDolazna = new StringBuilder();
                while (true) {
                    int i = in.read();  //citanje iz input streama bajt po bajt
                    if (i == -1) {    // ako nema vise bajtova, onda prekid
                        break;
                    }
                    porukaDolazna.append((char)i);
                }
                res = porukaDolazna.toString();
//                System.out.println("Odgovor: " + porukaDolazna);
                //TODO upis u dnevnik ako treba (nisam siguran)
            } catch (IOException ex) {
            Logger.getLogger(ServerSocketKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
                if (in != null)
                try {
                    in.close();
                } catch (IOException ex) {
                    System.out.println(" Problem kod zatvaranja io: " + ex.getMessage());
                }

                if (out != null)
                try {
                    out.close();
                } catch (IOException ex) {
                    System.out.println(" Problem kod zatvaranja io: " + ex.getMessage());
                }

                if (klijent != null)
                try {
                    klijent.close();
                } catch (IOException ex) {
                    System.out.println(" Problem kod zatvaranja io: " + ex.getMessage());
                }
            }
        return res;
    }
}
