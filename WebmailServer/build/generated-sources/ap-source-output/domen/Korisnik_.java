package domen;

import domen.Poruka;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T21:46:26")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, String> prezime;
    public static volatile SingularAttribute<Korisnik, String> lozinka;
    public static volatile ListAttribute<Korisnik, Poruka> porukaList1;
    public static volatile SingularAttribute<Korisnik, String> alt;
    public static volatile ListAttribute<Korisnik, Poruka> porukaList;
    public static volatile SingularAttribute<Korisnik, String> korisnikid;
    public static volatile SingularAttribute<Korisnik, String> tp;

}