/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.Poruka;

/**
 *
 * @author P
 */
public class SOSacuvajPoruku {
    public boolean izvrsi(Poruka p)
    {
        return DatabaseBroker.sacuvajPoruku(p);
    }
}
