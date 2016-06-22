package it.enricocandino.mail.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.enricocandino.androidmail.Mail;
import it.enricocandino.androidmail.MailSender;
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

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    MailSender mailSender = new MailSender("", "");

                    Mail.MailBuilder builder = new Mail.MailBuilder();
                    Mail mail = builder
                            .setBody("Hello")
                            .setSender("")
                            .addRecipient("")
                            .build();

                    mailSender.sendMail(mail);

                } catch (Exception e) {
                    System.out.println();
                }

            }
        }).start();

        System.out.println();
    }
}
