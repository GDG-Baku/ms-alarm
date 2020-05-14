package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.ArticleClient;
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
public class ArticleServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleClient articleClient;
    private final MailService mailService;

    public ArticleServiceImpl(ArticleClient articleClient, MailService mailService) {
        this.articleClient = articleClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msArticle.trying.start");
        articleClient.invokeArticle();
        logger.info("ActionLog.msArticle.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msArticle.failed");
        //new GenericMail(mailService).sendMail("ms-article", ex.getMessage());
    }


}
