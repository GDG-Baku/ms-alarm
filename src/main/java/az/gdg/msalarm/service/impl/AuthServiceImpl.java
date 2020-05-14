package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.AuthClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.QueueService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final AuthClient authClient;
    private final QueueService queueService;

    public AuthServiceImpl(AuthClient authClient, QueueService queueService) {
        this.authClient = authClient;
        this.queueService = queueService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msAuth.trying.start");
        authClient.invokeAuth();
        logger.info("ActionLog.msAuth.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msAuth.failed");
        //MailSenderUtil.sendMail("ms-auth", ex.getMessage(), queueService);
    }
}
