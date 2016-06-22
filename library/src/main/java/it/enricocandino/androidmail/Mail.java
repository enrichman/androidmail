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
    private List<String> recipients;
    private String subject;
    private String body;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
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

    public static class MailBuilder {

        private String sender;
        private List<String> recipients = new ArrayList<>();
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

        public MailBuilder addRecipient(String recipient) {
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
