package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.mail.MailDto;
import az.gdg.msalarm.service.QueueService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public MailDto prepareMail(String mailBody) {
        List<String> receivers = new ArrayList<>();
        //receivers.add("gdg.rubber.duck@gmail.com");
        //receivers.add("movsum.nigar@gmail.com");
        receivers.add("asif.hajiyev@outlook.com");
        receivers.add("asifhaciyev1498@gmail.com");
        //receivers.add("huseynov_ali@outlook.com");
        //receivers.add("isgandarli_murad@mail.ru");

        return new MailDto().builder()
                .to(receivers)
                .body("<h2>" + mailBody + "</h2>")
                .subject("APP CRASHED")
                .build();
    }
}
