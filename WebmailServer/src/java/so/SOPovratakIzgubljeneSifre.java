/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.IDomenskiObjekat;
import domen.Korisnik;

/**
 *
 * @author P
 */
public class SOPovratakIzgubljeneSifre{

//    String odgovor;
//    @Override
//    public void izvrsi(IDomenskiObjekat ido) {
//      odgovor = super.db.vratiIzgubljenuSifru(ido);
//    }
//    
//    public String vratiSifru()
//    { 
//      return odgovor;
//    }
    
    public String izvrsi(Korisnik k)
    {
        return DatabaseBroker.vratiIzgubljenuSifru(k);
    }
    
}
