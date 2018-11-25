package com.pjm.queueinfo;

import com.pjm.queueinfo.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.function.Function;

@Component("emailFunction")
public class EmailFunction implements Function<EmailRequest, String> {

    @Autowired
    EmailService emailService;

    @Override
    public String apply(EmailRequest request) {
        try {
            emailService.email(request.getTo(), request.getSubject(), request.getBody());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "done";
    }

}
