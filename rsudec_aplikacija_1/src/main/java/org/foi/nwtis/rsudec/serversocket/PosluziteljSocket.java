/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.serversocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.podaci.Korisnik;
import org.foi.nwtis.rsudec.podaci.KorisnikDAO;

/**
 *
 * @author Robi
 */
public class PosluziteljSocket extends Thread{

    final KorisnikDAO kdao;
    final BP_Konfiguracija bpk;
    public boolean radiPreuzimanje = true;
    public ServerSocket s;
    public void pauza(){
        radiPreuzimanje = false;
    }
    public void radi(){
        radiPreuzimanje = true;
    }
    public boolean stanje(){
        return radiPreuzimanje;
    }
    public PosluziteljSocket(BP_Konfiguracija bpk) {
        this.bpk = bpk;
        this.kdao = new KorisnikDAO();
        if (provjeraPorta()) {
            //cekajNaZahtjev();
        }
    }

    @Override
    public void run() {
        if(!provjeraPorta())
            this.interrupt();
        
         try {
            
            s = new ServerSocket(Integer.parseInt(bpk.getKonfig().dajPostavku("posluzitelj.socket.port")));
            while(true){
                System.out.println("ČEKAM ZA ZAHTJEV");
                System.out.println("Preuzimanje " + radiPreuzimanje);
                 Socket veza = s.accept();
                 Thread dretva = new Zahtjev(this, veza, bpk);
                 dretva.start();
                 dretva.join();
            }
           
        } catch (IOException ex) {
            Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void interrupt() {
        try {
            s.close();
            super.interrupt(); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    private boolean provjeraPorta() {
        try {
            ServerSocket s = new ServerSocket(Integer.parseInt(bpk.getKonfig().dajPostavku("posluzitelj.socket.port")));
            s.close();
        } catch (BindException ex) {
            System.out.println("Odabrani port je zauzet");
            return false;
        } catch (IOException ex) {
            System.out.println("Greška kod otvaranja socketa");
            return false;
        }
        return true;
    }
}
