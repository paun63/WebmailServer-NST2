/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.IDomenskiObjekat;
import domen.Korisnik;
import domen.Poruka;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author P
 */
public class DatabaseBroker {

    //public static XMLGregorianCalendar poslednjaModifikacijaPoruka;
    public static boolean registrujKorisnika(Korisnik k) {
        // Korisnik k = (Korisnik) ido;
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        try {
            entitymanager.persist(k);
            entitymanager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Neuspesno perzistiranje korisnika" + e.getMessage());
            return false;
        } finally {
            entitymanager.close();
            emfactory.close();
        }

        return true;
    }

    public static boolean sacuvajPoruku(Poruka p) {

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        try {
            int a = (Integer) entitymanager.createQuery("select max(p.porukaid) from Poruka p").getSingleResult();
            p.setPorukaid(a + 1);
            entitymanager.persist(p);
            entitymanager.getTransaction().commit();
            //poslednjaModifikacijaPoruka = getXMLGregorianCalendarNow();
            //System.out.println("DBB pormena cache:"+poslednjaModifikacijaPoruka);
        } catch (Exception e) {
            System.out.println("Neuspesno perzistiranje poruke" + e.getMessage());

            return false;
        } finally {
            entitymanager.close();
            emfactory.close();
        }

        return true;
    }

    public static Korisnik prijaviKorisnika(IDomenskiObjekat ido) {
        Korisnik k = (Korisnik) ido;

        Korisnik k1 = new Korisnik();

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        k1 = entitymanager.find(Korisnik.class, k.getKorisnikid());

        if (k1 != null) {
            if (k.getLozinka().equals(k1.getLozinka())) {

                entitymanager.close();
                emfactory.close();
                return k1;
            }
        }

        entitymanager.close();
        emfactory.close();

        return null;
    }

    public static String vratiIzgubljenuSifru(Korisnik k) {

        Korisnik k1 = new Korisnik();

        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        k1 = entitymanager.find(Korisnik.class, k.getKorisnikid());

        if (k1 != null) {
            if (k.getTp().equals(k1.getTp())) {

                try {
                    k1.setLozinka(k.getLozinka());
                    entitymanager.getTransaction().commit();
                } catch (Exception e) {
                    System.out.println("Greska pri upisivanju nove lozinke " + e.getMessage());
                }

                entitymanager.close();
                emfactory.close();
                return "Uspesno je postavljena nova lozinka";
            }
        }

        entitymanager.close();
        emfactory.close();

        return "Pogresan e-mail ili odgovor na tajno pitanje";
    }

    public static List<Poruka> vratiListuPoruka(Korisnik k) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        //String upit = "SELECT p FROM poruka p WHERE posiljalac = '"+k.getKorisnikid()+"' OR primalac = '"+k.getKorisnikid()+"'";

        List<Poruka> poruke;

        poruke = entitymanager.createQuery("select p from Poruka p where p.posiljalac = :korisnik or p.primalac = :korisnik order by p.datum desc", Poruka.class).setParameter("korisnik", k).getResultList();

//        System.out.println("Poruke iz baze:");
//        for (Poruka poruka : poruke) {
//            System.out.println(poruka.getPorukaid() +" "+ poruka.getPosiljalac() +" "+ poruka.getPrimalac());
//        }
//        
        entitymanager.close();
        emfactory.close();
        return poruke;
    }

    public static List<String> vratiListuKorisnikID() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        List<Korisnik> korisnici;
        List<String> korisniciID = new ArrayList();
        korisnici = entitymanager.createQuery("SELECT k FROM Korisnik k").getResultList();

        for (Korisnik k : korisnici) {
            korisniciID.add(k.getKorisnikid());

        }
        entitymanager.close();
        emfactory.close();
        return korisniciID;
    }

    public static List<Korisnik> vratiListuKontakata(Korisnik k) {
        List<Korisnik> kontakti = new ArrayList();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        List<Poruka> poruke = vratiListuPoruka(k);

        for (Poruka p : poruke) {
            if (p.getPosiljalac().equals(k)) {
                if (!kontakti.contains(p.getPrimalac())) {
                    kontakti.add(p.getPrimalac());
                }
            }
            if (p.getPrimalac().equals(k)) {
                if (!kontakti.contains(p.getPosiljalac())) {
                    kontakti.add(p.getPosiljalac());
                }
            }
        }

        entitymanager.close();
        emfactory.close();
        return kontakti;
    }

    public static void postaviPregledano(Poruka p) {
        Poruka p1 = new Poruka();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        p1 = entitymanager.find(Poruka.class, p.getPorukaid());

        if (p1 != null) {

            p1.setPregledana(true);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();

        } else {
            System.out.println("Greska kod azuriranja pregledanih poruka");
            entitymanager.close();
            emfactory.close();
        }

    }
    
    public static void obrisiPoruku(Poruka p) {
        Poruka p1 = new Poruka();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebmailServerPU");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        p1 = entitymanager.find(Poruka.class, p.getPorukaid());

        if (p1 != null) {

            entitymanager.remove(p1);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();

        } else {
            System.out.println("Greska kod brisanja poruke");
            entitymanager.close();
            emfactory.close();
        }

    }

//       public static XMLGregorianCalendar getXMLGregorianCalendarNow()    
//       {
//         try {
//             GregorianCalendar gregorianCalendar = new GregorianCalendar();
//             DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
//             XMLGregorianCalendar now =
//                     datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
//             return now;
//         } catch (DatatypeConfigurationException ex) {
//             System.out.println("Greska kod generisanja datuma za cache" +ex.getMessage());
//             return null;
//            }
//        }
}
