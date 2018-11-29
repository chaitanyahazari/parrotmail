package com.pjm.queueinfo.handler.aws;

import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.pjm.queueinfo.request.EmailRequest;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import java.util.Map;

public class EmailRequestHandler extends SpringBootRequestHandler<KinesisEvent, String> {
}
