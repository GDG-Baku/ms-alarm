package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.AlarmClient;
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
public class AlarmServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);
    private final AlarmClient alarmClient;
    private final MailService mailService;

    public AlarmServiceImpl(AlarmClient alarmClient, MailService mailService) {
        this.alarmClient = alarmClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 28 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msAlarm.trying.start");
        alarmClient.invokeAlarm();
        logger.info("ActionLog.msAlarm.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msAlarm.failed");
        //new GenericMail(mailService).sendMail("ms-alarm", ex.getMessage());
    }
}

