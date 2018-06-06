/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.Poruka;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author P
 */
public class SOSlanjePoruke {

    public boolean izvrsi(Poruka p) {
        
        String to = p.getPrimalac().getKorisnikid();

        
        String from = p.getPosiljalac().getKorisnikid()+"@webmail.com";

       
        String host = "localhost";

       
        Properties properties = System.getProperties();

       
        properties.setProperty("mail.smtp.host", host);

        
        Session session = Session.getDefaultInstance(properties);

        try {
            
            MimeMessage message = new MimeMessage(session);

           
            message.setFrom(new InternetAddress(from));

            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            
            message.setSubject(p.getTema());

           
            message.setText(p.getPrilog()+"\n"+p.getSadrzaj());

            
            
            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }
}
