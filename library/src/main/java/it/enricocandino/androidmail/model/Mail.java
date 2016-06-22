package it.enricocandino.androidmail.model;

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
    private List<Attachment> attachments;

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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public static class MailBuilder {

        private String sender;
        private List<Recipient> recipients = new ArrayList<>();
        private String subject;
        private String body;
        private List<Attachment> attachments = new ArrayList<>();

        public Mail build() {
            Mail mail = new Mail();
            mail.sender = this.sender;
            mail.recipients = this.recipients;
            mail.subject = this.subject;
            mail.body = this.body;
            mail.attachments = this.attachments;
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

        public MailBuilder addAttachment(Attachment attachment) {
            this.attachments.add(attachment);
            return this;
        }
    }
}
