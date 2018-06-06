package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author P
 */
public class MyMessageHandlerFactory implements MessageHandlerFactory{
      @Override
      public MessageHandler create(MessageContext ctx) {
        return new Handler(ctx);
    }

    class Handler implements MessageHandler {
        MessageContext ctx;

        public Handler(MessageContext ctx) {
        	this.ctx = ctx;
        }
    	
        @Override
        public void from(String from) throws RejectException {
        	System.out.println("FROM:"+from);
        }

        @Override
        public void recipient(String recipient) throws RejectException {
        	System.out.println("RECIPIENT:"+recipient);
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
    		return sb.toString();
    	}

    }
}
