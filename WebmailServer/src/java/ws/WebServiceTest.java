/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import domen.Korisnik;
import domen.Poruka;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.datatype.XMLGregorianCalendar;
import logic.Kontroler;

/**
 *
 * @author P
 */
@WebService(serviceName = "wst")
public class WebServiceTest {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "registracija")
    public boolean registracija(@WebParam(name = "korisnik") Korisnik korisnik) {

        return Kontroler.getInstance().registracijaKorisnika(korisnik);

    }

    @WebMethod(operationName = "prijava")
    public Korisnik prijava(@WebParam(name = "korisnik") Korisnik korisnik) {
        //System.out.println("Korisnik za prijavu:" +korisnik.toString());

        Korisnik ko = Kontroler.getInstance().prijaviKorisnika(korisnik);

        return ko;

    }

    @WebMethod(operationName = "vratiListuPoruka")
    public List<Poruka> vratiListuPoruka(@WebParam(name = "korisnik") Korisnik korisnik) {
        List<Poruka> poruke = new ArrayList<>();
        poruke = Kontroler.getInstance().vratiListuPoruka(korisnik);
        return poruke;
    }

    @WebMethod(operationName = "vratiListuKorisnikID")
    public List<String> vratiListuKorisnikID() {

        List<String> korisnici = new ArrayList<>();
        korisnici = Kontroler.getInstance().vratiListuKorisnikID();
        return korisnici;
    }

    @WebMethod(operationName = "vratiListuKontakata")
    public List<Korisnik> vratiListuKontakata(@WebParam(name = "korisnik") Korisnik korisnik) {

        List<Korisnik> kontakti = new ArrayList<>();
        kontakti = Kontroler.getInstance().vratiListuKontakata(korisnik);
        return kontakti;

    }

    @WebMethod(operationName = "povratakSifre")
    public String povratakSifre(@WebParam(name = "korisnik") Korisnik korisnik) {
        //System.out.println("Korisnik za prijavu:" +korisnik.toString());

        String s = Kontroler.getInstance().povratakSifreKorisnika(korisnik);

        return s;

    }

    @WebMethod(operationName = "slanjePoruke")
    public boolean slanjePoruke(@WebParam(name = "poruka") Poruka poruka) {

        boolean uspesnost = Kontroler.getInstance().slanjePoruke(poruka);

        return uspesnost;
    }
    
    @WebMethod(operationName = "cuvanjeDrafta")
    public void cuvanjeDrafta(@WebParam(name = "poruka") Poruka poruka) {

        Kontroler.getInstance().sacuvajPoruku(poruka);

       
    }
    
    @WebMethod(operationName = "azurirajPregledano")
    public void azurirajPregledano(@WebParam(name = "poruka") Poruka poruka) {

        Kontroler.getInstance().azurirajPregledano(poruka);

       
    }
    
    @WebMethod(operationName = "obrisiPoruku")
    public void obrisiPoruku(@WebParam(name = "poruka") Poruka poruka) {

        Kontroler.getInstance().obrisiPoruku(poruka);

       
    }
}
