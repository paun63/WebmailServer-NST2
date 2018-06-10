/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.mb;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import logic.Kontroler;

/**
 *
 * @author P
 */
@Named(value = "mbServer")
@SessionScoped
public class MBServer implements Serializable{

    String mysql;
    String elastic;

    public MBServer() {
    }
    
    public void pokreniSMTPServer()
    {
        Kontroler.getInstance().pokreniSmtpServer(mysql, elastic);
    }
    
    public void zaustaviSMTPServer()
    {
        Kontroler.getInstance().zaustaviSmtpServer();
    }

    public String getMysql() {
        return mysql;
    }

    public String getElastic() {
        return elastic;
    }

    public void setMysql(String mysql) {
        this.mysql = mysql;
    }

    public void setElastic(String elastic) {
        this.elastic = elastic;
    }
    
    
    
}
