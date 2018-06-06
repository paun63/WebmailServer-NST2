/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import domen.IDomenskiObjekat;

/**
 *
 * @author P
 */
public abstract class AbstractSO {
    DatabaseBroker db = new DatabaseBroker();

    public abstract void izvrsi(IDomenskiObjekat ido);
    
    
    
}
