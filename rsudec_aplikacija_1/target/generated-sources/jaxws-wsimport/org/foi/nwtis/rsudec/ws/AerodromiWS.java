
package org.foi.nwtis.rsudec.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AerodromiWS", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AerodromiWS {


    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajTrajanjeCiklusa", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajTrajanjeCiklusa")
    @ResponseWrapper(localName = "dajTrajanjeCiklusaResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajTrajanjeCiklusaResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajTrajanjeCiklusaRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajTrajanjeCiklusaResponse")
    public int dajTrajanjeCiklusa(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param trajanjeCiklusa
     * @param korisnickoIme
     */
    @WebMethod
    @RequestWrapper(localName = "promjeniTrajanjeCiklusa", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PromjeniTrajanjeCiklusa")
    @ResponseWrapper(localName = "promjeniTrajanjeCiklusaResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PromjeniTrajanjeCiklusaResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/promjeniTrajanjeCiklusaRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/promjeniTrajanjeCiklusaResponse")
    public void promjeniTrajanjeCiklusa(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "trajanjeCiklusa", targetNamespace = "")
        int trajanjeCiklusa);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajBrojPoruka", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajBrojPoruka")
    @ResponseWrapper(localName = "dajBrojPorukaResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajBrojPorukaResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajBrojPorukaRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajBrojPorukaResponse")
    public int dajBrojPoruka(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param brojPoruka
     * @param korisnickaLozinka
     * @param korisnickoIme
     */
    @WebMethod
    @RequestWrapper(localName = "promjeniBrojPoruka", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PromjeniBrojPoruka")
    @ResponseWrapper(localName = "promjeniBrojPorukaResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PromjeniBrojPorukaResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/promjeniBrojPorukaRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/promjeniBrojPorukaResponse")
    public void promjeniBrojPoruka(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "brojPoruka", targetNamespace = "")
        int brojPoruka);

    /**
     * 
     * @param korisnickaLozinka
     * @param drzavaAerodrom
     * @param idAerodrom
     * @param nazivAerodrom
     * @param lonAerodrom
     * @param korisnickoIme
     * @param latAerodrom
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajNoviAerodromGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajNoviAerodromGrupi")
    @ResponseWrapper(localName = "dodajNoviAerodromGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajNoviAerodromGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajNoviAerodromGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajNoviAerodromGrupiResponse")
    public Boolean dodajNoviAerodromGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom,
        @WebParam(name = "nazivAerodrom", targetNamespace = "")
        String nazivAerodrom,
        @WebParam(name = "drzavaAerodrom", targetNamespace = "")
        String drzavaAerodrom,
        @WebParam(name = "latAerodrom", targetNamespace = "")
        String latAerodrom,
        @WebParam(name = "lonAerodrom", targetNamespace = "")
        String lonAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.util.List<org.foi.nwtis.rsudec.ws.Aerodrom>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajSveAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajSveAerodromeGrupe")
    @ResponseWrapper(localName = "dajSveAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajSveAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajSveAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajSveAerodromeGrupeResponse")
    public List<Aerodrom> dajSveAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "registrirajGrupu", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.RegistrirajGrupu")
    @ResponseWrapper(localName = "registrirajGrupuResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.RegistrirajGrupuResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/registrirajGrupuRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/registrirajGrupuResponse")
    public Boolean registrirajGrupu(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deregistrirajGrupu", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DeregistrirajGrupu")
    @ResponseWrapper(localName = "deregistrirajGrupuResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DeregistrirajGrupuResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/deregistrirajGrupuRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/deregistrirajGrupuResponse")
    public Boolean deregistrirajGrupu(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajGrupu", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajGrupu")
    @ResponseWrapper(localName = "blokirajGrupuResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajGrupuResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajGrupuRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajGrupuResponse")
    public Boolean blokirajGrupu(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajGrupu", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajGrupu")
    @ResponseWrapper(localName = "aktivirajGrupuResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajGrupuResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajGrupuRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajGrupuResponse")
    public Boolean aktivirajGrupu(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns org.foi.nwtis.rsudec.ws.StatusKorisnika
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajStatusGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajStatusGrupe")
    @ResponseWrapper(localName = "dajStatusGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajStatusGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajStatusGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajStatusGrupeResponse")
    public StatusKorisnika dajStatusGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param aerodrom
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajAerodromGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAerodromGrupi")
    @ResponseWrapper(localName = "dodajAerodromGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAerodromGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAerodromGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAerodromGrupiResponse")
    public Boolean dodajAerodromGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "aerodrom", targetNamespace = "")
        Aerodrom aerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param idAerodrom
     * @param korisnickoIme
     * @return
     *     returns java.util.List<org.foi.nwtis.rsudec.ws.Avion>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajSveAvioneAerodroma", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajSveAvioneAerodroma")
    @ResponseWrapper(localName = "dajSveAvioneAerodromaResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajSveAvioneAerodromaResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajSveAvioneAerodromaRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajSveAvioneAerodromaResponse")
    public List<Avion> dajSveAvioneAerodroma(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param avioniNovi
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "postaviAvioneGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PostaviAvioneGrupe")
    @ResponseWrapper(localName = "postaviAvioneGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.PostaviAvioneGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/postaviAvioneGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/postaviAvioneGrupeResponse")
    public boolean postaviAvioneGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "avioniNovi", targetNamespace = "")
        List<Avion> avioniNovi);

    /**
     * 
     * @param avionNovi
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajAvionGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAvionGrupi")
    @ResponseWrapper(localName = "dodajAvionGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAvionGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAvionGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAvionGrupiResponse")
    public boolean dodajAvionGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "avionNovi", targetNamespace = "")
        Avion avionNovi);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ucitajUgradeneAvioneGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.UcitajUgradeneAvioneGrupe")
    @ResponseWrapper(localName = "ucitajUgradeneAvioneGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.UcitajUgradeneAvioneGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/ucitajUgradeneAvioneGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/ucitajUgradeneAvioneGrupeResponse")
    public boolean ucitajUgradeneAvioneGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "autenticirajGrupu", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AutenticirajGrupu")
    @ResponseWrapper(localName = "autenticirajGrupuResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AutenticirajGrupuResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/autenticirajGrupuRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/autenticirajGrupuResponse")
    public Boolean autenticirajGrupu(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiSveAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiSveAerodromeGrupe")
    @ResponseWrapper(localName = "obrisiSveAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiSveAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiSveAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiSveAerodromeGrupeResponse")
    public Boolean obrisiSveAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ucitajUgradeneAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.UcitajUgradeneAerodromeGrupe")
    @ResponseWrapper(localName = "ucitajUgradeneAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.UcitajUgradeneAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/ucitajUgradeneAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/ucitajUgradeneAerodromeGrupeResponse")
    public boolean ucitajUgradeneAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka);

    /**
     * 
     * @param korisnickaLozinka
     * @param idAerodrom
     * @param korisnickoIme
     * @return
     *     returns org.foi.nwtis.rsudec.ws.AerodromStatus
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dajStatusAerodromaGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajStatusAerodromaGrupe")
    @ResponseWrapper(localName = "dajStatusAerodromaGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DajStatusAerodromaGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajStatusAerodromaGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dajStatusAerodromaGrupeResponse")
    public AerodromStatus dajStatusAerodromaGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param idAerodrom
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajAerodromGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajAerodromGrupe")
    @ResponseWrapper(localName = "blokirajAerodromGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajAerodromGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajAerodromGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajAerodromGrupeResponse")
    public boolean blokirajAerodromGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param idAerodrom
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajAerodromGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajAerodromGrupe")
    @ResponseWrapper(localName = "aktivirajAerodromGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajAerodromGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajAerodromGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajAerodromGrupeResponse")
    public boolean aktivirajAerodromGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param idAerodrom
     * @param korisnickoIme
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiAerodromGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiAerodromGrupe")
    @ResponseWrapper(localName = "obrisiAerodromGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiAerodromGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiAerodromGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiAerodromGrupeResponse")
    public boolean obrisiAerodromGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "idAerodrom", targetNamespace = "")
        String idAerodrom);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @param odabraniAerodromi
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "aktivirajOdabraneAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajOdabraneAerodromeGrupe")
    @ResponseWrapper(localName = "aktivirajOdabraneAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.AktivirajOdabraneAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajOdabraneAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/aktivirajOdabraneAerodromeGrupeResponse")
    public boolean aktivirajOdabraneAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabraniAerodromi", targetNamespace = "")
        List<String> odabraniAerodromi);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @param odabranaAerodromi
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "blokirajOdabraneAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajOdabraneAerodromeGrupe")
    @ResponseWrapper(localName = "blokirajOdabraneAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.BlokirajOdabraneAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajOdabraneAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/blokirajOdabraneAerodromeGrupeResponse")
    public boolean blokirajOdabraneAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabranaAerodromi", targetNamespace = "")
        List<String> odabranaAerodromi);

    /**
     * 
     * @param korisnickaLozinka
     * @param korisnickoIme
     * @param odabraniAerodromi
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "obrisiOdabraneAerodromeGrupe", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiOdabraneAerodromeGrupe")
    @ResponseWrapper(localName = "obrisiOdabraneAerodromeGrupeResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.ObrisiOdabraneAerodromeGrupeResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiOdabraneAerodromeGrupeRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/obrisiOdabraneAerodromeGrupeResponse")
    public boolean obrisiOdabraneAerodromeGrupe(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "odabraniAerodromi", targetNamespace = "")
        List<String> odabraniAerodromi);

    /**
     * 
     * @param korisnickaLozinka
     * @param icao
     * @param korisnickoIme
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dodajAerodromIcaoGrupi", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAerodromIcaoGrupi")
    @ResponseWrapper(localName = "dodajAerodromIcaoGrupiResponse", targetNamespace = "http://serveri.ws.dkermek.nwtis.foi.org/", className = "org.foi.nwtis.rsudec.ws.DodajAerodromIcaoGrupiResponse")
    @Action(input = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAerodromIcaoGrupiRequest", output = "http://serveri.ws.dkermek.nwtis.foi.org/AerodromiWS/dodajAerodromIcaoGrupiResponse")
    public Boolean dodajAerodromIcaoGrupi(
        @WebParam(name = "korisnickoIme", targetNamespace = "")
        String korisnickoIme,
        @WebParam(name = "korisnickaLozinka", targetNamespace = "")
        String korisnickaLozinka,
        @WebParam(name = "icao", targetNamespace = "")
        String icao);

}
