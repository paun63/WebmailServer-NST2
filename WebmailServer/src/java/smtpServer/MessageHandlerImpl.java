/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpServer;

import domen.Korisnik;
import domen.Poruka;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MailDateFormat;
import javax.mail.internet.MimeBodyPart;
import logic.Kontroler;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.RejectException;
import org.subethamail.smtp.TooMuchDataException;

/**
 *
 * @author P
 */
public class MessageHandlerImpl implements MessageHandler {
    
        MessageContext ctx;
        Poruka p;
        Korisnik k1;
        Korisnik k2;

        public MessageHandlerImpl(MessageContext ctx) {
        	this.ctx = ctx;
                
        }
    	
        @Override
        public void from(String from) throws RejectException {
        	System.out.println("FROM:"+from);
                if(from.contains("@")){
                    k1 = new Korisnik(from.substring(0, from.indexOf("@")));
                }
                else
                {
                    k1 = new Korisnik(from);
                }
        }

        @Override
        public void recipient(String recipient) throws RejectException {
        	System.out.println("RECIPIENT:"+recipient);
                if(recipient.contains("@")){
                    k2 = new Korisnik(recipient.substring(0,recipient.indexOf("@") ));
                }
                else
                {
                    k2 = new Korisnik(recipient);
                }
        }

        @Override
        public void data(InputStream data) throws IOException {
        	System.out.println("MAIL DATA");
        	System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
        	System.out.println(this.convertStreamToString(data));
        	System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
        }

        @Override
        public void done() {
        	System.out.println("Finished");
        }

    	public String convertStreamToString(InputStream is) {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                
    		StringBuilder sb = new StringBuilder();
    		
    		String line = null;
    		try {
    			while ((line = reader.readLine()) != null) {
    				sb.append(line + "\n");
                                
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
                parse(sb);
    		return sb.toString();
    	}
        
        public void parse(StringBuilder sb)
        {
             p = new Poruka();
             p.setPosiljalac(k1);
             p.setPrimalac(k2);
             String[] niz = sb.toString().split("\\n");
            
             
             for (int i = 0; i < niz.length; i++) {
                 
                 if(niz[i].contains("Subject:"))
                 {
                    p.setTema(niz[i].substring(niz[i].indexOf(":")+2));
                 }
                 
                 if(niz[i].contains("Content-Transfer-Encoding:"))
                 {
                     
                     String s = "";
                     if(niz.length - i > 1)
                     {
                    
                        for (int j = i+2; j < niz.length; j++) 
                        {
                            if(niz[j].contains("c:\\"))
                            {
                                p.setPrilog(niz[j]);
                                j++;
                            }
                            if(niz[j].contains("null"))
                            {
                                j++;
                            }
                            s += niz[j]+"\n";
                     
                        }
                     }
                     else
                     {
                         //s = niz[niz.length-1];
                         s="";
                     }
                     p.setSadrzaj(s);
                 }
                 
                 if(niz[i].contains("(CEST)"))
                 {
                    String datum = niz[i].substring(0, niz[i].indexOf("(")-1).trim();
                    SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                     
                     
                     try {
                         Date date = format.parse(datum);
                         
                         Date date2 = konverzijaZaDateTime(date);
                         p.setDatum(date2);
                         
                     } catch (ParseException ex) {
                         Logger.getLogger(MessageHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                         System.out.println("ne valja datum");
                     }
                   //yyyy-MM-dd HH:mm:ss
                   //EEE, d MMM yyyy HH:mm:ss Z
                 }
  
            }
            p.setKategorija("sent");
            Kontroler.getInstance().sacuvajPoruku(p);
        }
        
        public void testFile(InputStream is) throws IOException
        {
            
            
         
        }
        
        public Date konverzijaZaDateTime(Date date) throws ParseException
        {
           
            
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setLenient(false);
             String s = format.format(date);
                         
                         Date date2 = format.parse(s);
                        
                         
           
                         return date2;
                         
        }

    }


