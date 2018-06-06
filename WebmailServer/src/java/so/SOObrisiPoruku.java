/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.IDomenskiObjekat;
import domen.Poruka;

/**
 *
 * @author P
 */
public class SOObrisiPoruku {

//    @Override
//    public void izvrsi(IDomenskiObjekat ido) {
//        super.db.obrisiPoruku(ido);
//    }
    public void izvrsi(Poruka p)
    {
        DatabaseBroker.obrisiPoruku(p);
    }
}
