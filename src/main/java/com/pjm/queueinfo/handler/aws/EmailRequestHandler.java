package com.pjm.queueinfo.handler.aws;

import com.pjm.queueinfo.request.EmailRequest;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class EmailRequestHandler extends SpringBootRequestHandler<EmailRequest, String> {
}
