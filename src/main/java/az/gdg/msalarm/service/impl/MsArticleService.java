package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsArticleClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.EmailService;
import az.gdg.msalarm.service.GenericMail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MsArticleService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsTeamService.class);
    private final MsArticleClient msArticleClient;
    private final EmailService emailService;

    public MsArticleService(MsArticleClient msArticleClient, EmailService emailService) {
        this.msArticleClient = msArticleClient;
        this.emailService = emailService;
    }

    @Override
    @Retryable(value = Exception.class, backoff = @Backoff(value = 2000))
    public void invoke() {
        logger.info("ActionLog.msArticle.start");
        msArticleClient.invokeMsArticle();
        logger.info("ActionLog.msArticle.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msArticle.failed");

        new GenericMail(emailService).sendMail("ms-article", ex.getMessage());
    }


}
