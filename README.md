# AndroidMail

Android wrapper for JavaMail to easily send email without intents.


Download
--------

// TODO

Examples
--------

Create a MailSender with your email and password, build a Mail and send it.
The mail will be sent in background and you will be notified through a listener.

```
MailSender mailSender = new MailSender(email, password);
 
Mail.MailBuilder builder = new Mail.MailBuilder();
Mail mail = builder
    .setSender(senderMail)
    .addRecipient(new Recipient(recipient))
    .addRecipient(new Recipient(Recipient.TYPE.CC, recipientCC))
    .setText("Hello")
    .setHtml("<h1 style=\"color:red;\">Hello</h1>")
    .addAttachment(new Attachment(filePath, fileName))
    .build();
    
MailSender.OnMailSentListener onMailSentListener = new MailSender.OnMailSentListener() {

    @Override
    public void onSuccess() {
        // mail sent!
    }

    @Override
    public void onError(Exception error) {
        // something bad happened :(
    }
});
 
mailSender.sendMail(mail, onMailSentListener);
```

Remember to add the INTERNET permissions and (for the attachments) the READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE:

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

Note: since API 23+ you have to check again in the app if the user granted the storage permissions.

The only provider supported (at the moment) is Gmail.

Check the sample for a working app!

Apps
--------

Let me know if you used the library in your project.
I will be happy to list it here!

Developed By
--------

Enrico Candino - www.enricocandino.it

<a href="https://twitter.com/enrichmann">
  <img alt="Follow me on Twitter"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/twitter-icon.png" />
</a>
<a href="https://plus.google.com/+EnricoCandino">
  <img alt="Follow me on Google+"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/google-plus-icon.png" />
</a>
<a href="https://it.linkedin.com/in/enrico-candino-78995553">
  <img alt="Follow me on LinkedIn"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/linkedin-icon.png" />
</a>


License
--------

    The MIT License (MIT)

    Copyright (c) 2015 Enrico Candino

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

