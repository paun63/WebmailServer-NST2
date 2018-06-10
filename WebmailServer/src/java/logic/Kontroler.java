/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.sun.mail.smtp.SMTPTransport;
import db.DatabaseBroker;
import domen.Korisnik;
import domen.Poruka;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.mail.Transport;
import javax.xml.datatype.XMLGregorianCalendar;
import smtpServer.SMTP;
import so.SOAzurirajPregledanePoruke;
import so.SOObrisiPoruku;
import so.SOPovratakIzgubljeneSifre;
import so.SOPrijavaKorisnika;
import so.SOProveriPoruke;
import so.SORegistracijaKorisnika;
import so.SOSacuvajPoruku;
import so.SOSlanjePoruke;
import so.SOVratiListuKontakata;
import so.SOVratiListuKorisnika;
import so.SOVratiListuPoruka;

/**
 *
 * @author P
 */
public class Kontroler {

    
    SMTP smtp = new SMTP();
    
    public static Kontroler instance;


    public static Kontroler getInstance()
    {
        if(instance == null)
        {
            instance = new Kontroler();
        }
        return instance;
    }
    
    public void pokreniSmtpServer(String mysql,String elastic)
    {
        try {
            Process processMysql = new ProcessBuilder(mysql).start();
            Process processElastic = new ProcessBuilder(elastic).start();
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        smtp.pokreniSMTPServer();
    }
    
     public void zaustaviSmtpServer()
    {
        smtp.zaustaviSMTPServer();
    }
    
    
    public Korisnik prijaviKorisnika(Korisnik k)
    {
        SOPrijavaKorisnika so = new SOPrijavaKorisnika();
        return so.izvrsi(k);
    }
    
    public List<Poruka> vratiListuPoruka(Korisnik k)
    {
        SOVratiListuPoruka so = new SOVratiListuPoruka();
        return so.izvrsi(k);
        
    }
    
   public List<String> vratiListuKorisnikID()
   {
       SOVratiListuKorisnika so = new SOVratiListuKorisnika();
       return so.izvrsi();
   }
   
   public boolean registracijaKorisnika(Korisnik k)
   {
       SORegistracijaKorisnika so = new SORegistracijaKorisnika();
       return so.izvrsi(k);
   }
   
   public List<Korisnik> vratiListuKontakata(Korisnik k)
   {
       SOVratiListuKontakata so = new SOVratiListuKontakata();
       return so.izvrsi(k);
   }

    public String povratakSifreKorisnika(Korisnik k) {
        SOPovratakIzgubljeneSifre so = new SOPovratakIzgubljeneSifre();
        return so.izvrsi(k);
    }
    
    public boolean slanjePoruke(Poruka p)
    {
        SOSlanjePoruke so = new SOSlanjePoruke();
        return so.izvrsi(p);
        
    }

    public void sacuvajPoruku(Poruka p) {
        SOSacuvajPoruku so = new SOSacuvajPoruku();
        so.izvrsi(p);
    }
    
    public void azurirajPregledano(Poruka p)
    {
        SOAzurirajPregledanePoruke so = new SOAzurirajPregledanePoruke();
        so.izvrsi(p);
    }
    
    public void obrisiPoruku(Poruka p)
    {
        SOObrisiPoruku so = new SOObrisiPoruku();
        so.izvrsi(p);
    }
    
//    public boolean proveriNovePoruke(XMLGregorianCalendar poslednjePromeneKlijenta)
//    {
//        SOProveriPoruke so = new SOProveriPoruke();
//        return so.izvrsi(poslednjePromeneKlijenta);
//    }
//    
//    public XMLGregorianCalendar vratiPoslednjuIzmenu()
//    {
//       
//        return DatabaseBroker.poslednjaModifikacijaPoruka;
//    }
    
}
