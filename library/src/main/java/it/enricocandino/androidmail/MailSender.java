package it.enricocandino.androidmail;

import android.util.Log;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.Security;

import it.enricocandino.androidmail.provider.GmailProvider;
import it.enricocandino.androidmail.provider.JSSEProvider;
import it.enricocandino.androidmail.provider.MailProvider;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class MailSender extends javax.mail.Authenticator {

    private String user;
    private String password;
    private Session session;

    static {
        Security.addProvider(new JSSEProvider());
    }

    public MailSender(String user, String password) {
        this.user = user;
        this.password = password;

        MailProvider mailProvider = new GmailProvider();
        session = Session.getDefaultInstance(mailProvider.getProperties(), this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public void sendMail(Mail mail) throws Exception {
        try {
            MimeMessage message = new MimeMessage(session);

            message.setSender(new InternetAddress(mail.getSender()));
            message.setSubject(mail.getSubject());

            for(Recipient recipient : mail.getRecipients()) {
                Message.RecipientType recipientType = null;
                switch (recipient.getType()) {
                    case TO:
                        recipientType = Message.RecipientType.TO;
                        break;
                    case CC:
                        recipientType = Message.RecipientType.CC;
                        break;
                    case BCC:
                        recipientType = Message.RecipientType.BCC;
                        break;
                }
                message.addRecipient(recipientType, new InternetAddress(recipient.getAddress()));

            }

            DataHandler handler = new DataHandler(new ByteArrayDataSource(mail.getBody().getBytes(), "text/plain"));
            message.setDataHandler(handler);

            Transport.send(message);
            
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error sending mail", e);
        }
    }

}
