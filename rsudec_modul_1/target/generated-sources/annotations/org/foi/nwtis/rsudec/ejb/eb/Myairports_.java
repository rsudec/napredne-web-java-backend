package org.foi.nwtis.rsudec.ejb.eb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.foi.nwtis.rsudec.ejb.eb.Airports;
import org.foi.nwtis.rsudec.ejb.eb.Korisnici;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-02T22:04:06")
@StaticMetamodel(Myairports.class)
public class Myairports_ { 

    public static volatile SingularAttribute<Myairports, Airports> ident;
    public static volatile SingularAttribute<Myairports, Date> stored;
    public static volatile SingularAttribute<Myairports, Integer> id;
    public static volatile SingularAttribute<Myairports, Korisnici> username;

}