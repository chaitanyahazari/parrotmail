package com.pjm.queueinfo;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    static final String FROM = "chaitanya.hazari@gmail.com";
    static final String FROMNAME = "PJM Parrot";

    // Use only verified email addresses.
    static final String TO = "krishnaha@gmail.com";

    static final String SMTP_USERNAME = "AKIAJUNRCMZDPYX6KPEA";
    static final String SMTP_PASSWORD = "AnNSmbUcBB73tNmrm5TO/v9Zc0DloNcbNEh0wU9dwfRd";

    static final String HOST = "email-smtp.us-east-1.amazonaws.com";
    static final int PORT = 587;

    public void email(String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);

        if (to == null) {
            to = TO;
        }
        if (subject == null) {
            subject = "test";
        }

        if (body == null) {
            body = "test";
        }

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");

        Transport transport = session.getTransport();

        try
        {
            System.out.println("After kinesis triggered...");

            transport.connect(HOST, PORT, SMTP_USERNAME, SMTP_PASSWORD);

            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            transport.close();
        }
    }
}
