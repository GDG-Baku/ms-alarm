package az.gdg.msalarm.service;

import az.gdg.msalarm.mail.MailDto;

public interface QueueService {
    void sendToQueue(MailDto mailDto);
}
