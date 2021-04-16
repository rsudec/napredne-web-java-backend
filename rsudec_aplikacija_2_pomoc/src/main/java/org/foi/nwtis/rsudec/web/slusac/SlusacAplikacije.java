/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.web.slusac;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.Start;
import org.foi.nwtis.rsudec.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.rsudec.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.sb.WebSocketEndpoint;
import org.foi.nwtis.rsudec.web.dretve.PreuzimanjeLetova;

/**
 * Web application lifecycle listener.
 *
 * @author NWTiS_1
 */
@WebListener
public class SlusacAplikacije implements ServletContextListener {
    
    @EJB
    Start start;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        ServletContext sc = sce.getServletContext();
        String datoteke = sc.getInitParameter("konfiguracija");
        String putanja = sc.getRealPath("WEB-INF") + File.separator + datoteke;
        try {
            BP_Konfiguracija konf = new BP_Konfiguracija(putanja);
            sc.setAttribute("BP_Konfig", konf);
            start.start(konf);
        } catch (NemaKonfiguracije | NeispravnaKonfiguracija ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        System.out.println("rsudec_aplikacija_2_pomoc je pokrenuta!");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        if(pl!= null)
//            pl.interrupt();
        System.out.println("rsudec_aplikacija_2_pomoc je zaustavljana!");
    }
}
