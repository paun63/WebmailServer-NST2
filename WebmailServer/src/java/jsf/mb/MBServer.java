/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.mb;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import logic.Kontroler;

/**
 *
 * @author P
 */
@Named(value = "mbServer")
@SessionScoped
public class MBServer implements Serializable{


    public MBServer() {
    }
    
    public void pokreniSMTPServer()
    {
        Kontroler.getInstance().pokreniSmtpServer();
    }
    
    public void zaustaviSMTPServer()
    {
        Kontroler.getInstance().zaustaviSmtpServer();
    }
    
}
