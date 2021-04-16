package org.foi.nwtis.rsudec.ejb.eb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.foi.nwtis.rsudec.ejb.eb.Myairports;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-02T22:04:06")
@StaticMetamodel(Korisnici.class)
public class Korisnici_ { 

    public static volatile SingularAttribute<Korisnici, String> ime;
    public static volatile SingularAttribute<Korisnici, String> prezime;
    public static volatile ListAttribute<Korisnici, Myairports> myairportsList;
    public static volatile SingularAttribute<Korisnici, String> lozinka;
    public static volatile SingularAttribute<Korisnici, Date> datumPromjene;
    public static volatile SingularAttribute<Korisnici, String> korIme;
    public static volatile SingularAttribute<Korisnici, Date> datumKreiranja;
    public static volatile SingularAttribute<Korisnici, String> emailAdresa;

}