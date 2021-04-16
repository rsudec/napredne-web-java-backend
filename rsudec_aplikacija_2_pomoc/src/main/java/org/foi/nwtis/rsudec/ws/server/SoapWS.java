/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.ws.server;

import com.sun.xml.ws.developer.JAXWSProperties;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.spi.http.HttpExchange;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.rest.podaci.AvionLeti;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;
import org.foi.nwtis.rsudec.ejb.sb.AirplanesFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.AirportsFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.KorisniciFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.LogFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;

/**
 *
 * @author Robi
 */
@WebService(serviceName = "SoapWS")
public class SoapWS {

    @EJB
    MyairportsFacadeLocal mafl;
    @EJB
    KorisniciFacadeLocal kfl;
    @EJB
    AirportsFacadeLocal afl;

    @EJB
    AirplanesFacadeLocal airplanesfl;

    @EJB
    LogFacadeLocal lfl;
    /**
     * This is a sample web service operation
     */
    //@Context HttpServletRequest req;
    @Resource
    WebServiceContext wsContext;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService hello", req.getRequestURL().toString(), req.getRemoteAddr(), txt, BigInteger.valueOf(stop - start));
        return "Hello " + txt + " !";

    }

    @WebMethod(operationName = "provjeraKorisnika")
    public Boolean provjeraKorisnika(@WebParam(name = "korisnik") String korisnik, @WebParam(name = "lozinka") String lozinka) {
        
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        
        System.out.println("KORISNIK U WEB SERVISU PROVJERA");
        System.out.println(korisnik);
        Korisnici k = kfl.find(korisnik);
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService provjeraKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        if (k != null && k.getLozinka().equals(lozinka)) {
            return true;
        }

        return false;
    }

    @WebMethod(operationName = "dajAerodromeNaziv")
    public List<Aerodrom> dajAerodromeNaziv(@WebParam(name = "korisnik") String korisnik, @WebParam(name = "lozinka") String lozinka, @WebParam(name = "naziv") String naziv) {
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        List<Aerodrom> res  = afl.dajPoNazivu(naziv);
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajAerodromeNaziv", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return res;
    }

    @WebMethod(operationName = "dajAerodromeDrzava")
    public List<Aerodrom> dajAerodromeDrzava(@WebParam(name = "korisnik") String korisnik, @WebParam(name = "lozinka") String lozinka, @WebParam(name = "drzava") String isoCountry) {
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        List<Aerodrom> res  = afl.dajPoDrzavi(isoCountry);
         long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajAerodromeDrzava", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        
        return res;
    }

    @WebMethod(operationName = "dajAerodromeKorisnika")
    public List<Aerodrom> dajAerodromeKorisnika(@WebParam(name = "korisnik") String korisnik, @WebParam(name = "lozinka") String lozinka) {
        long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        List<Aerodrom> res  = mafl.dajAerodromeKorisnika(korisnik);
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajAerodromeKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return res;
    }

    @WebMethod(operationName = "dajAvioneSAerodroma")
    public List<AvionLeti> dajAvioneSAerodroma(
            @WebParam(name = "korisnik") String korisnik,
            @WebParam(name = "lozinka") String lozinka,
            @WebParam(name = "icao") String icao,
            @WebParam(name = "vrijemeOd") long vrijemeOd,
            @WebParam(name = "vrijemeDo") long vrijemeDo) {
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        
        
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        List<AvionLeti> res  = airplanesfl.dajAvioneSAerodroma(icao, vrijemeOd, vrijemeDo);
         long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajAvioneSAerodroma", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        
        return res;
        //lfl.zapisiLog("WS SOAP Zahtjev : dajAvioneSAerodroma, argumenti: " + korisnik + ", " + lozinka + ", " + icao  + ", " + vrijemeOd + ", " + vrijemeDo);

    }

    @WebMethod(operationName = "dajLetoveAviona")
    public List<AvionLeti> dajLetoveAviona(
            @WebParam(name = "korisnik") String korisnik,
            @WebParam(name = "lozinka") String lozinka,
            @WebParam(name = "icao24") String icao24,
            @WebParam(name = "vrijemeOd") long vrijemeOd,
            @WebParam(name = "vrijemeDo") long vrijemeDo) {
        
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        
        List<AvionLeti> res  = airplanesfl.dajLetoveAviona(icao24, vrijemeOd, vrijemeDo);
         long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajLetoveAviona", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return res;
    }

    @WebMethod(operationName = "dajUdaljenostAerodroma")
    public double dajUdaljenostAerodroma(
            @WebParam(name = "korisnik") String korisnik,
            @WebParam(name = "lozinka") String lozinka,
            @WebParam(name = "icao1") String icao1,
            @WebParam(name = "icao2") String icao2) {
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return -1;
        }
        double res  = afl.dajUdaljenostAerodroma(icao1, icao2);
         long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajUdaljenostAerodroma", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return res;
 
    }

    @WebMethod(operationName = "dajAerodromeUKrugu")
    public List<Aerodrom> dajAerodromeUKrugu(
            @WebParam(name = "korisnik") String korisnik,
            @WebParam(name = "lozinka") String lozinka,
            @WebParam(name = "icao") String icao,
            @WebParam(name = "min") String min,
            @WebParam(name = "max") String max
            ) {
         long start = System.currentTimeMillis();
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        if (!provjeraKorisnika(korisnik, lozinka)) {
            return null;
        }
        List<Aerodrom> res = afl.dajAerodromeUKrugu(korisnik, icao, min, max);
        
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("WebService dajAerodromeUKrugu", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return res;
    }

}
