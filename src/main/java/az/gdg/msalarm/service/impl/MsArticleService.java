package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsArticleClient;
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
public class MsArticleService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsArticleService.class);
    private final MsArticleClient msArticleClient;
    private final MailService mailService;

    public MsArticleService(MsArticleClient msArticleClient, MailService mailService) {
        this.msArticleClient = msArticleClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msArticle.trying.start");
        msArticleClient.invokeMsArticle();
        logger.info("ActionLog.msArticle.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msArticle.failed");
        //new GenericMail(mailService).sendMail("ms-article", ex.getMessage());
    }


}
