/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Korisnik;
import java.util.List;

/**
 *
 * @author P
 */
public class SOVratiListuKontakata {
    
    public List<Korisnik> izvrsi(Korisnik k)
    {
        return DatabaseBroker.vratiListuKontakata(k);
    }
}
