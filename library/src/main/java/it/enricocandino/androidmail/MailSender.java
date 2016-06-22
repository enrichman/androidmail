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

            if(mail.getRecipients().size() == 1) {
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getRecipients().get(0)));

            } else if(mail.getRecipients().size() > 1) {
                StringBuilder sb = new StringBuilder(mail.getRecipients().get(0));
                for(int i=1; i<mail.getRecipients().size(); i++) {
                    sb.append(',').append(mail.getRecipients().get(i));
                }
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sb.toString()));
            }

            DataHandler handler = new DataHandler(new ByteArrayDataSource(mail.getBody().getBytes(), "text/plain"));
            message.setDataHandler(handler);

            Transport.send(message);

            System.out.println();

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error sending mail", e);
        }
    }

}
