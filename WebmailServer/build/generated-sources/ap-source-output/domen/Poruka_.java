package domen;

import domen.Korisnik;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-10T22:48:59")
@StaticMetamodel(Poruka.class)
public class Poruka_ { 

    public static volatile SingularAttribute<Poruka, Boolean> pregledana;
    public static volatile SingularAttribute<Poruka, Date> datum;
    public static volatile SingularAttribute<Poruka, Integer> porukaid;
    public static volatile SingularAttribute<Poruka, Korisnik> posiljalac;
    public static volatile SingularAttribute<Poruka, String> tema;
    public static volatile SingularAttribute<Poruka, Korisnik> primalac;
    public static volatile SingularAttribute<Poruka, String> prilog;
    public static volatile SingularAttribute<Poruka, String> sadrzaj;
    public static volatile SingularAttribute<Poruka, String> kategorija;

}