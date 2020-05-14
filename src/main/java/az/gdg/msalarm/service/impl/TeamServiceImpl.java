package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.TeamClient;
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
public class TeamServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamClient teamClient;
    private final QueueService queueService;

    public TeamServiceImpl(TeamClient teamClient, QueueService queueService) {
        this.teamClient = teamClient;
        this.queueService = queueService;
    }


    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msTeam.trying.start");
        teamClient.invokeTeam();
        logger.info("ActionLog.msTeam.success");
    }


    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msTeam.failed");
        //MailSenderUtil.sendMail("ms-team", ex.getMessage(), queueService);
    }
}
