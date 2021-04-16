/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.rsudec.serversocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.rsudec.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.rsudec.podaci.Korisnik;
import org.foi.nwtis.rsudec.podaci.KorisnikDAO;
import org.foi.nwtis.rsudec.rs.klijent.JaxRS_Klijent;
import org.foi.nwtis.rsudec.ws.StatusKorisnika;
import org.foi.nwtis.rsudec.ws.klijenti.AerodromiWS_Klijent;
import org.foi.nwtis.rsudec.ws.Aerodrom;

/**
 *
 * @author Robi
 */
public class Zahtjev extends Thread {

    Socket s;
    PosluziteljSocket server;
    BP_Konfiguracija bpk;

    public Zahtjev(PosluziteljSocket server, Socket s, BP_Konfiguracija bpk) {
        this.s = s;
        this.server = server;
        this.bpk = bpk;
    }

    @Override
    public void run() {
        System.out.println("Veza uspostavljenja: " + s.getInetAddress().getHostAddress());

        try (InputStream inps = s.getInputStream(); OutputStream outs = s.getOutputStream();) {
            StringBuilder tekst = new StringBuilder();
            while (true) {
                int i = inps.read();
                if (i == -1) {
                    break;
                }
                tekst.append((char) i);
            }
            if (!tekst.toString().contains("GRUPA")) {
                obradiKomanduKorisnik(tekst.toString(), outs);
            } else {
                obradiKomanduGrupa(tekst.toString(), outs);
            }
        } catch (IOException ex) {
            Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.run(); //To change body of generated methods, choose Tools | Templates.
    }

    private void obradiKomanduKorisnik(String tekst, OutputStream outs) {
        System.out.println("OBRADA 1" + tekst);
        String patternRegex = "KORISNIK ([A-Za-z0-9]+); LOZINKA ([A-Za-z0-9]+); ?([A-Z]+)?;?";
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher m = pattern.matcher(tekst);
        boolean status = m.matches();
        if (!status) {
            try {
                outs.write("ERROR X; Neispravna komanda;".getBytes());
            } catch (IOException ex) {
                Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String username = m.group(1);
            String password = m.group(2);
            String opcija = m.group(3);
            JaxRS_Klijent rs = new JaxRS_Klijent(username, password);
            if (opcija == null) {
                opcija = "";
                korisnikAutentikacija(username, password, outs, false);
            } else if (opcija.equals("DODAJ")) {
                korisnikDodaj(username, password, outs);
            } else if (korisnikAutentikacija(username, password, outs, true)) {
                switch (opcija) {
                    case "PAUZA":
                        korisnikPauza(username, password, outs);
                        break;
                    case "RADI":
                        //korisnikAutentikacija(username, password, outs, true);
                        korisnikRadi(username, password, outs);
                        break;
                    case "KRAJ":
                        //korisnikAutentikacija(username, password, outs, true);
                        korisnikKraj(username, password, outs);
                        break;
                    case "STANJE":
                        //korisnikAutentikacija(username, password, outs, true);
                        korisnikStanje(username, password, outs);
                        break;
                    default:
                        break;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSS");
            String vrijemeSlanja = sdf.format(new Date());
            rs.prijemKomande(String.class, tekst, vrijemeSlanja);

        }
    }

    private void obradiKomanduGrupa(String tekst, OutputStream outs) {
        System.out.println("OBRADA 2" + tekst);
        boolean provjera = true;
        String patternRegex = "KORISNIK ([A-Za-z0-9]+); LOZINKA ([A-Za-z0-9]+); GRUPA ([A-Z]+) ?(.+)?;";
        Pattern pattern = Pattern.compile(patternRegex);
        Matcher m = pattern.matcher(tekst);
        boolean status = m.matches();
        if (!status) {
            try {
                outs.write("ERROR X; Neispravna komanda;".getBytes());
            } catch (IOException ex) {
                Logger.getLogger(PosluziteljSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            String username = m.group(1);
            String password = m.group(2);
            String opcija = m.group(3);
            JaxRS_Klijent rs = new JaxRS_Klijent(username, password);
            if (opcija == null) {
                opcija = "";
            }
            if (korisnikAutentikacija(username, password, outs, true)) {
                switch (opcija) {
                    case "PRIJAVI":
                        //korisnikAutentikacija(username, password, outs, true);
                        grupaPrijavi(username, password, outs);
                        break;
                    case "ODJAVI":
                        //korisnikAutentikacija(username, password, outs, true);
                        grupaOdjavi(username, password, outs);
                        break;
                    case "AKTIVIRAJ":
                        //korisnikAutentikacija(username, password, outs, true);
                        grupaAktiviraj(username, password, outs);
                        break;
                    case "BLOKIRAJ":
                        //korisnikAutentikacija(username, password, outs, true);
                        grupaBlokiraj(username, password, outs);
                        break;
                    case "STANJE":
                        //korisnikAutentikacija(username, password, outs, true);
                        grupaStanje(username, password, outs);
                        break;
                    case "AERODROMI":
                        String aerodromi = m.group(4);
                        grupaAerodromi(username, password, aerodromi, outs);
                    default:

                        //korisnikAutentikacija(username, password, outs, false);
                        break;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSS");
            String vrijemeSlanja = sdf.format(new Date());
            rs.prijemKomande(String.class, tekst, vrijemeSlanja);
        }

    }

    private boolean korisnikAutentikacija(String user, String pass, OutputStream outs, boolean nastavak) {
        KorisnikDAO kdao = new KorisnikDAO();
        Korisnik k = kdao.dohvatiKorisnika(user, pass, bpk);
        try {
            if (k == null) {
                outs.write("ERR 11;".getBytes());
                outs.flush();
                return false;
            } else if (!nastavak) {
                outs.write("OK 10;".getBytes());
                outs.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void korisnikDodaj(String user, String pass, OutputStream outs) {
        KorisnikDAO kdao = new KorisnikDAO();
        boolean dodano = kdao.dodajKorisnika(user, pass, bpk);
        try {
            if (!dodano) {
                outs.write("ERR 12;".getBytes());
            } else {
                outs.write("OK 10;".getBytes());
            }
            outs.flush();
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void korisnikPauza(String user, String pass, OutputStream outs) {
        boolean stanje = server.stanje();

        try {
            if (stanje) {
                outs.write("OK 10;".getBytes());
            } else {
                outs.write("ERR 13;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

        server.pauza();
    }

    private void korisnikRadi(String user, String pass, OutputStream outs) {
        boolean stanje = server.stanje();

        try {
            if (!stanje) {
                outs.write("OK 10;".getBytes());
            } else {
                outs.write("ERR 14;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

        server.radi();
    }

    private void korisnikKraj(String user, String pass, OutputStream outs) {
        server.pauza();
        server.interrupt();
        try {
            outs.write("OK 10;".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void korisnikStanje(String user, String pass, OutputStream outs) {
        boolean stanje = server.stanje();
        try {
            if (stanje) {
                outs.write("OK 11;".getBytes());
            } else {
                outs.write("OK 12;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void grupaPrijavi(String user, String pass, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        Boolean res = aws.prijaviGrupu(user, pass);
        try {
            if (res) {
                outs.write("OK 20;".getBytes());
            } else {
                outs.write("ERR 20;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void grupaOdjavi(String user, String pass, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        boolean res = aws.odjaviGrupu(user, pass);
        try {
            if (res) {
                outs.write("OK 20;".getBytes());
            } else {
                outs.write("ERR 21;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void grupaAktiviraj(String user, String pass, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        StatusKorisnika stanje = aws.stanjeGrupe(user, pass);
        boolean res = aws.aktivirajGrupu(user, pass);

        try {
            if (stanje.value().equals("DEREGISTRIRAN")) {
                outs.write("ERR 21;".getBytes());
            } else if (!res) {
                outs.write("ERR 22;".getBytes());
            } else if (res) {
                outs.write("OK 20;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void grupaBlokiraj(String user, String pass, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        StatusKorisnika stanje = aws.stanjeGrupe(user, pass);
        boolean res = aws.blokirajGrupu(user, pass);
        try {
            if (stanje.value().equals("DEREGISTRIRAN")) {
                outs.write("ERR 21;".getBytes());
            } else if (!res) {
                outs.write("ERR 23;".getBytes());
            } else if (res) {
                
                outs.write("OK 20;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void grupaStanje(String user, String pass, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        StatusKorisnika res = aws.stanjeGrupe(user, pass);
        try{
            List<Aerodrom> aerodromi = aws.dajAerodromeGrupe(user, pass);
            System.out.println("Čitam aerodrome grupe");

            for (Aerodrom a : aerodromi) {
                System.out.println("aerodrom");
                System.out.println(a.getIcao());
            }
        } catch(Exception ex){
            System.out.println("Greška eerodromiGrupe");
            System.out.println(ex.getMessage());
        }
        try {
            if (res.value().equals("DEREGISTRIRAN")) {
                outs.write("ERR 21;".getBytes());
            } else if (res.value().equals("AKTIVAN")) {
                outs.write("OK 21;".getBytes());
            } else if (res.value().equals("BLOKIRAN") || res.value().equals("REGISTRIRAN")) {
                outs.write("OK 22;".getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(res.value());
    }

    private void grupaAerodromi(String user, String pass, String aerodromi, OutputStream outs) {
        AerodromiWS_Klijent aws = new AerodromiWS_Klijent();
        StatusKorisnika res = aws.stanjeGrupe(user, pass);
        Boolean greska = false;
        if (res.value().equals("BLOKIRAN")) {
            try {
                if (aws.brisiAerodrome(user, pass)) {
                    String[] icao = aerodromi.split(", ");
                    for (int i = 0; i < icao.length; i++) {
                        
                        if (!aws.dodajAerodromGrupi(user, pass, icao[i])) {
                            System.out.println("GREŠKA KOD DODAVANJA AERODROMA " + icao[i]);
                            greska = true;
                        }
                    }
                } else {
                    greska = true;
                }
                if(!greska)
                    outs.write("OK 20;".getBytes());
                else
                    outs.write("ERR 32;".getBytes());
            } catch (IOException ex) {
                Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
            try {
                outs.write("ERR 31;".getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Zahtjev.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
