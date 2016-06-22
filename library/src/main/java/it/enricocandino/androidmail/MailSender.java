package it.enricocandino.androidmail;

import android.os.AsyncTask;
import android.util.Log;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.security.Security;

import it.enricocandino.androidmail.model.Attachment;
import it.enricocandino.androidmail.model.Mail;
import it.enricocandino.androidmail.model.Recipient;
import it.enricocandino.androidmail.provider.GmailProvider;
import it.enricocandino.androidmail.provider.JSSEProvider;
import it.enricocandino.androidmail.provider.MailProvider;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class MailSender extends javax.mail.Authenticator {

    private OnMailSentListener listener;

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

    public void sendMail(Mail mail) {
        sendMail(mail, null);
    }

    public void sendMail(Mail mail, OnMailSentListener listener) {
        this.listener = listener;
        new MailTask().execute(mail);
    }

    public interface OnMailSentListener {
        void onSuccess();
        void onError(Exception error);
    }

    private class MailTask extends AsyncTask<Mail, Void, Void> {

        private Exception error;

        @Override
        protected Void doInBackground(Mail... params) {
            Mail mail = params[0];

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

                if(!mail.getAttachments().isEmpty()) {
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();

                    for(Attachment attachment : mail.getAttachments()) {
                        DataSource source = new FileDataSource(attachment.getPath());
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(attachment.getFilename());
                        multipart.addBodyPart(messageBodyPart);
                    }
                    message.setContent(multipart);
                }

                Transport.send(message);

            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Error sending mail", e);
                error = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(listener != null) {
                if(error != null) {
                    listener.onError(error);
                } else {
                    listener.onSuccess();
                }
            }
        }
    }
}
