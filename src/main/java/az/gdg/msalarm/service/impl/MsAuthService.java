package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsAuthClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.EmailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MsAuthService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsAuthService.class);
    private final MsAuthClient msAuthClient;
    private final EmailService emailService;

    public MsAuthService(MsAuthClient msAuthClient, EmailService emailService) {
        this.msAuthClient = msAuthClient;
        this.emailService = emailService;
    }

    @Override
    @Retryable(value = Exception.class, backoff = @Backoff(value = 2000))
    public void invoke() {
        logger.info("ActionLog.msAuth.trying.start");
        msAuthClient.invokeMsAuth();
        logger.info("ActionLog.msAuth.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msAuth.failed");
        //new GenericMail(emailService).sendMail("ms-auth", ex.getMessage());
    }


}
