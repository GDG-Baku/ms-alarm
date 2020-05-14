package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.SubscriberClient;
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
public class SubscriberServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(SubscriberServiceImpl.class);
    private final SubscriberClient subscriberClient;
    private final MailService mailService;

    public SubscriberServiceImpl(SubscriberClient subscriberClient, MailService mailService) {
        this.subscriberClient = subscriberClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msSubscriber.trying.start");
        subscriberClient.invokeMsSubscriber();
        logger.info("ActionLog.msSubscriber.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msSubscriber.failed");
        //new GenericMail(mailService).sendMail("ms-subscriber", ex.getMessage());
    }
}
