package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.ArticleClient;
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
public class ArticleServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final ArticleClient articleClient;
    private final QueueService queueService;

    public ArticleServiceImpl(ArticleClient articleClient, QueueService queueService) {
        this.articleClient = articleClient;
        this.queueService = queueService;
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
        //MailSenderUtil.sendMail("ms-article", ex.getMessage(), queueService);
    }

}
