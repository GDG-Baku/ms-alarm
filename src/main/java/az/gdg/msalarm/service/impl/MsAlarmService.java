package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsAlarmClient;
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
public class MsAlarmService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsAlarmService.class);
    private final MsAlarmClient msAlarmClient;
    private final MailService mailService;

    public MsAlarmService(MsAlarmClient msAlarmClient, MailService mailService) {
        this.msAlarmClient = msAlarmClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 28 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msAlarm.trying.start");
        msAlarmClient.invokeMsAlarm();
        logger.info("ActionLog.msAlarm.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msAlarm.failed");
        //new GenericMail(mailService).sendMail("ms-alarm", ex.getMessage());
    }
}

