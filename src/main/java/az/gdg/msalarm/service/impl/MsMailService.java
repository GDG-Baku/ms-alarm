package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsMailClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MsMailService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsMailService.class);
    private final MsMailClient msMailClient;
    private final EmailService emailService;

    public MsMailService(MsMailClient msMailClient, EmailService emailService) {
        this.msMailClient = msMailClient;
        this.emailService = emailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msMail.trying.start");
        msMailClient.invokeMsMail();
        logger.info("ActionLog.msMail.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msMail.failed");
        //new GenericMail(emailService).sendMail("ms-mail", ex.getMessage());
    }
}
