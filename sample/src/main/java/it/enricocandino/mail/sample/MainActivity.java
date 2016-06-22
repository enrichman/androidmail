package it.enricocandino.mail.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.enricocandino.androidmail.Mail;
import it.enricocandino.androidmail.MailSender;
import it.enricocandino.androidmail.Recipient;
import it.enricocandino.sample.R;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MailSender mailSender = new MailSender("test@email.com", "");

        Mail.MailBuilder builder = new Mail.MailBuilder();
        Mail mail = builder
                .setBody("Ciao")
                .setSender("test@email.com")
                .addRecipient(new Recipient("test@email.com"))
                .addRecipient(new Recipient(Recipient.TYPE.TO, "test@email.com"))
                .addRecipient(new Recipient(Recipient.TYPE.CC, "test@email.com"))
                .addRecipient(new Recipient(Recipient.TYPE.BCC, "test@email.com"))
                .build();

        mailSender.sendMail(mail);

        System.out.println();
    }
}
