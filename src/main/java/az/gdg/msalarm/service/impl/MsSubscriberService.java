package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsSubscriberClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MsSubscriberService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsSubscriberService.class);
    private final MsSubscriberClient msSubscriberClient;
    private final MailService mailService;

    public MsSubscriberService(MsSubscriberClient msSubscriberClient, MailService mailService) {
        this.msSubscriberClient = msSubscriberClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msSubscriber.trying.start");
        msSubscriberClient.invokeMsSubscriber();
        logger.info("ActionLog.msSubscriber.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msSubscriber.failed");
        //new GenericMail(mailService).sendMail("ms-subscriber", ex.getMessage());
    }
}
