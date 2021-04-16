/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.rs.server;

import com.google.gson.Gson;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.foi.nwtis.podaci.Odgovor;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;
import org.foi.nwtis.rsudec.ejb.sb.AirplanesFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.LogFacadeLocal;
import org.foi.nwtis.rsudec.ejb.sb.MyairportsFacadeLocal;
import org.foi.nwtis.rsudec.websocket.client.WebSocketClient;
import org.foi.nwtis.rsudec.ws.klijent.Aerodrom;
import org.foi.nwtis.rsudec.ws.klijent.SoapWSKlijent;

/**
 * REST Web Service
 *
 * @author Robi
 */
@Path("/rest")
public class JaxRS {

    @EJB
    MyairportsFacadeLocal mafl;
    @EJB
    AirplanesFacadeLocal airplfl;
    @EJB
    LogFacadeLocal lfl;
    @Inject
    JMS_Red_2 jms2;

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest req;

    /**
     * Creates a new instance of JaxRS
     */
    public JaxRS() {

    }

    /**
     * Retrieves representation of an instance of
     * org.foi.nwtis.rsudec.rs.server.JaxRS
     *
     * @return an instance of javax.ws.rs.core.Response
     */
    private Boolean autentikacijaKorisnika(String k, String l) {
        SoapWSKlijent swsk = new SoapWSKlijent();
        return swsk.provjeraKorisnika(k, l);

    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response dajAerodomeKorisnika(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka) {

        long start = System.currentTimeMillis();
        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }

        SoapWSKlijent swsk = new SoapWSKlijent();

        List<Aerodrom> aerodromi = swsk.dajAerodromeKorisnika(korisnik, lozinka);

        if (aerodromi == null) {
            aerodromi = new ArrayList<>();
        }

        Odgovor odgovor = new Odgovor();
        odgovor.setStatus("10");
        odgovor.setPoruka("OK");
        odgovor.setOdgovor(aerodromi.toArray());

        long stop = System.currentTimeMillis();

        lfl.zapisiLog("RestService dajAerodromeKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response dodajAerodromKorisniku(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            String body
    ) {
        long start = System.currentTimeMillis();

        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }

        Gson gson = new Gson();
        StringReader sr = new StringReader(body);
        Properties bodyJSON = gson.fromJson(sr, Properties.class);
        sr.close();
        String icao = bodyJSON.getProperty("icao");

        WebSocketClient wsc = new WebSocketClient();
        wsc.sendMessage(korisnik + "," + icao);

        String[] a = new String[]{};
        Odgovor odgovor = new Odgovor();
        odgovor.setOdgovor(a);
        odgovor.setStatus("10");
        odgovor.setPoruka("OK");

        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService dodajAerodromKorisniku", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    @Path("{icao}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response dajAerodromKorisnika(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            @PathParam("icao") String icao) {
        long start = System.currentTimeMillis();
        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }

        org.foi.nwtis.podaci.Aerodrom result = null;
        List<org.foi.nwtis.podaci.Aerodrom> aerodromi = mafl.dajAerodromeKorisnika(korisnik);
        for (org.foi.nwtis.podaci.Aerodrom a : aerodromi) {
            if (a.getIcao().equals(icao)) {
                result = a;
            }
        }
        List<org.foi.nwtis.podaci.Aerodrom> listResult = new ArrayList();
        Odgovor odgovor = new Odgovor();
        if (result != null) {
            listResult.add(result);
            odgovor.setStatus("10");
            odgovor.setPoruka("OK");
            odgovor.setOdgovor(listResult.toArray());
        } else {
            odgovor.setStatus("40");
            odgovor.setPoruka("Aerodrom nije pridružen korisniku");
            odgovor.setOdgovor(listResult.toArray());
        }
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService dajAerodromKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();

    }

    @Path("{icao}")
    @DELETE
    public Response obrisiAerodromKorisnika(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            @PathParam("icao") String icao) {
        long start = System.currentTimeMillis();
        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }
        Odgovor odgovor = new Odgovor();
        List<org.foi.nwtis.podaci.Aerodrom> aerodromi = mafl.dajAerodromeKorisnika(korisnik);

        org.foi.nwtis.podaci.Aerodrom aerodromKorisnika = null;
        for (org.foi.nwtis.podaci.Aerodrom aer : aerodromi) {
            if (aer.getIcao().equals(icao)) {
                aerodromKorisnika = aer;
            }
        }
        if (aerodromKorisnika == null) {
            odgovor.setStatus("40");
            odgovor.setPoruka("Aerodrom nije pridružen korisniku");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }
        Boolean aerodromImaLetove = airplfl.imaAvionaSAerodroma(aerodromKorisnika.getIcao());

        if (aerodromImaLetove) {
            odgovor.setStatus("40");
            odgovor.setPoruka("Aerodrom ima pridružene letove aviona");
            odgovor.setOdgovor(new String[]{});
        } else {
            Boolean res = mafl.obrisiAerodromKorisnika(korisnik, icao);
            odgovor.setStatus("10");
            odgovor.setPoruka("OK");
            odgovor.setOdgovor(new String[]{res.toString()});

        }
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService obrisiAerodromKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    @Path("{icao}/avioni")
    @DELETE
    public Response obrisiLetoveAerodromaKorisnika(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            @PathParam("icao") String icao) {
        System.out.println("JAX KORISNIK : " + korisnik);
        long start = System.currentTimeMillis();
        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }
        Odgovor odgovor = new Odgovor();
        List<org.foi.nwtis.podaci.Aerodrom> aerodromi = mafl.dajAerodromeKorisnika(korisnik);

        org.foi.nwtis.podaci.Aerodrom aerodromKorisnika = null;
        for (org.foi.nwtis.podaci.Aerodrom aer : aerodromi) {
            if (aer.getIcao().equals(icao)) {
                aerodromKorisnika = aer;
            }
        }
        if (aerodromKorisnika == null) {
            odgovor.setStatus("40");
            odgovor.setPoruka("Aerodrom nije pridružen korisniku");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }
        Boolean aerodromImaLetove = airplfl.imaAvionaSAerodroma(aerodromKorisnika.getIcao());

        if (!aerodromImaLetove) {
            odgovor.setStatus("40");
            odgovor.setPoruka("Aerodrom nema letova");
            odgovor.setOdgovor(new String[]{});

        } else {
            int res = airplfl.obrisiAvioneSAerodroma(icao);
            odgovor.setStatus("10");
            odgovor.setPoruka("OK");
            odgovor.setOdgovor(new String[]{res + ""});

        }
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService obrisiLetoveAerodromaKorisnika", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    @Path("/svi")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response dajAerodrome(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            @QueryParam("naziv") String naziv,
            @QueryParam("drzava") String drzava) {
        long start = System.currentTimeMillis();
        if (!autentikacijaKorisnika(korisnik, lozinka)) {
            Odgovor odgovor = new Odgovor();
            odgovor.setStatus("40");
            odgovor.setPoruka("Ne postoji korisnik");
            odgovor.setOdgovor(new String[]{});
            return Response
                    .ok(odgovor)
                    .status(200)
                    .build();
        }
        SoapWSKlijent swsk = new SoapWSKlijent();

        List<Aerodrom> result = new ArrayList<>();
        if (drzava != null && !drzava.isEmpty() && naziv != null && !naziv.isEmpty()) {
            result.addAll(swsk.dajAerodromeNaziv(korisnik, lozinka, naziv));
            for (Aerodrom a : swsk.dajAerodromeDrzava(korisnik, lozinka, drzava)) {
                boolean postoji = false;
                for (Aerodrom b : result) {
                    if (a.getIcao().equals(b.getIcao())) {
                        postoji = true;
                    }
                }
                if (!postoji) {
                    result.add(a);
                }
            }
        } else if ((drzava == null || drzava.isEmpty()) && (naziv != null && !naziv.isEmpty())) {
            result = swsk.dajAerodromeNaziv(korisnik, lozinka, naziv);
        } else if ((drzava != null && !drzava.isEmpty()) && (naziv == null || naziv.isEmpty())) {
            result = swsk.dajAerodromeDrzava(korisnik, lozinka, drzava);
        } else {
            result = swsk.dajAerodromeNaziv(korisnik, lozinka, "%");
        }
        if (result == null) {
            result = new ArrayList<>();
        }
        Odgovor odgovor = new Odgovor();
        odgovor.setStatus("10");
        odgovor.setPoruka("OK");
        odgovor.setOdgovor(result.toArray());
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService dajAerodrome", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    @Path("/komande")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response prijemKomande(
            @HeaderParam("korisnik") String korisnik,
            @HeaderParam("lozinka") String lozinka,
            @HeaderParam("komanda") String komanda,
            @HeaderParam("vrijeme") String vrijeme
    ) {
        long start = System.currentTimeMillis();
        if (ima(korisnik) && ima(lozinka) && ima(komanda) && ima(vrijeme)) {
            jms2.salji(korisnik, komanda, vrijeme);
        }
        Odgovor odgovor = new Odgovor();
        odgovor.setStatus("10");
        odgovor.setPoruka("OK");
        odgovor.setOdgovor(new String[]{});
        long stop = System.currentTimeMillis();
        lfl.zapisiLog("RestService prijemKomande", req.getRequestURL().toString(), req.getRemoteAddr(), korisnik, BigInteger.valueOf(stop - start));
        return Response
                .ok(odgovor)
                .status(200)
                .build();
    }

    private Boolean ima(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * PUT method for updating or creating an instance of JaxRS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Response content
    ) {

    }
}
