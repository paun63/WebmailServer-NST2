/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpServer;

import org.subethamail.smtp.server.SMTPServer;

/**
 *
 * @author P
 */
public class SMTP {

    SMTPServer smtpServer;
    
    public void pokreniSMTPServer() 
    {
        MessageHandlerFactoryImpl myFactory = new MessageHandlerFactoryImpl();
        smtpServer = new SMTPServer(myFactory);
        smtpServer.setPort(25);
        smtpServer.start();

    }
    
    public void zaustaviSMTPServer()
    {
        smtpServer.stop();
    }

}
