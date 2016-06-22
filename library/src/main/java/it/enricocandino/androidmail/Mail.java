package it.enricocandino.androidmail;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class Mail {

    private String sender;
    private List<Recipient> recipients;
    private String subject;
    private String body;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Recipient> getRecipientsByType(Recipient.TYPE type) {
        List<Recipient> recipientsByType = new ArrayList<>();
        if(this.recipients != null) {
            for(Recipient r : recipients) {
                if(r.getType() == type)
                    recipientsByType.add(r);
            }
        }
        return recipientsByType;
    }

    public String getRecipientsAsString(Recipient.TYPE type) {
        List<Recipient> recipients = getRecipientsByType(type);

        String recipientsString = "";

        if(!recipients.isEmpty()) {
            StringBuilder sb = new StringBuilder(recipients.get(0).getAddress());
            for(int i=1; i<this.recipients.size(); i++) {
                sb.append(',').append(this.recipients.get(i));
            }
            recipientsString = sb.toString();
        }

        return recipientsString;
    }

    public static class MailBuilder {

        private String sender;
        private List<Recipient> recipients = new ArrayList<>();
        private String subject;
        private String body;

        public Mail build() {
            Mail mail = new Mail();
            mail.sender = this.sender;
            mail.recipients = this.recipients;
            mail.subject = this.subject;
            mail.body = this.body;
            return mail;
        }

        public MailBuilder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public MailBuilder addRecipient(Recipient recipient) {
            this.recipients.add(recipient);
            return this;
        }

        public MailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailBuilder setBody(String body) {
            this.body = body;
            return this;
        }
    }
}
