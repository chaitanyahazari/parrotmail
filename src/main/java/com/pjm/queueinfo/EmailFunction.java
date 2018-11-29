package com.pjm.queueinfo;

import com.amazonaws.kinesis.deagg.RecordDeaggregator;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjm.queueinfo.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component("emailFunction")
public class EmailFunction implements Function<KinesisEvent, String> {

    @Autowired
    EmailService emailService;

    @Override
    public String apply(KinesisEvent input) {
        System.out.println(input);
        ByteBuffer byteBuffer = (ByteBuffer) input.getRecords().get(0).getKinesis().getData();
        String s = StandardCharsets.UTF_8.decode(byteBuffer).toString();
        System.out.println("Payload received" +s);
        ObjectMapper mapper = new ObjectMapper();
        EmailRequest request = new EmailRequest();
        try {
            request = mapper.readValue(s, EmailRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            emailService.email(request.getTo(), request.getSubject(), request.getBody());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "done";
    }

}
