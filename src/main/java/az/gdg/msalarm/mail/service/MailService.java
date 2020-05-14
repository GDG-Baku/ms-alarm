package az.gdg.msalarm.mail.service;

import az.gdg.msalarm.mail.dto.MailDto;

public interface MailService {
    void sendToQueue(MailDto mailDto);

    MailDto prepareMail(String mailBody);
}
