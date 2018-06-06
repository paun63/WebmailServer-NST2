/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Korisnik;
import domen.Poruka;
import java.util.List;

/**
 *
 * @author P
 */
public class SOVratiListuPoruka {
    
    public List<Poruka> izvrsi(Korisnik k)
    {
        return DatabaseBroker.vratiListuPoruka(k);
        
    }
}
