package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MailClient;
import az.gdg.msalarm.mail.service.MailService;
import az.gdg.msalarm.service.AlarmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final MailClient mailClient;
    private final MailService mailService;

    public MailServiceImpl(MailClient mailClient, MailService mailService) {
        this.mailClient = mailClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msMail.trying.start");
        mailClient.invokeMsMail();
        logger.info("ActionLog.msMail.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msMail.failed");
        //new GenericMail(mailService).sendMail("ms-mail", ex.getMessage());
    }
}
