package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.mail.MailDto;
import az.gdg.msalarm.service.QueueService;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class QueueServiceImpl implements QueueService {
    private final Source source;

    public QueueServiceImpl(Source source) {
        this.source = source;
    }

    @Override
    public void sendToQueue(MailDto mailDto) {
        source.output().send(MessageBuilder.withPayload(mailDto).build());
    }
}
