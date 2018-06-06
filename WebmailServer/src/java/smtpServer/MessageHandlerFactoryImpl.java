/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smtpServer;

import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;

/**
 *
 * @author P
 */
public class MessageHandlerFactoryImpl implements MessageHandlerFactory{

    @Override
    public MessageHandler create(MessageContext ctx) {
       return new MessageHandlerImpl(ctx);
    }
    
}
