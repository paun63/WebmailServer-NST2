/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.subethamail.smtp.server.SMTPServer;

/**
 *
 * @author P
 */
public class BasicSmtpServer {
    public static void main(String[] args) {
		MyMessageHandlerFactory myFactory = new MyMessageHandlerFactory() ;
		SMTPServer smtpServer = new SMTPServer(myFactory);
		smtpServer.setPort(25000);
		smtpServer.start();
	}
}
