package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsAuthClient;
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
public class MsAuthService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsAuthService.class);
    private final MsAuthClient msAuthClient;
    private final MailService mailService;

    public MsAuthService(MsAuthClient msAuthClient, MailService mailService) {
        this.msAuthClient = msAuthClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msAuth.trying.start");
        msAuthClient.invokeMsAuth();
        logger.info("ActionLog.msAuth.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msAuth.failed");
        //new GenericMail(mailService).sendMail("ms-auth", ex.getMessage());
    }


}
