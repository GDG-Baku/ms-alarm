package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MailClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.QueueService;
import az.gdg.msalarm.util.QueueUtil;

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
    private final QueueService queueService;

    public MailServiceImpl(MailClient mailClient, QueueService queueService) {
        this.mailClient = mailClient;
        this.queueService = queueService;
    }

    @Override
    @Scheduled(fixedRate = 25 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msMail.trying.start");
        mailClient.invokeMail();
        logger.info("ActionLog.msMail.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msMail.failed");
        QueueUtil.sendMail("ms-mail", ex.getMessage(), queueService);
    }
}
