package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.TeamClient;
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
public class TeamServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);
    private final TeamClient teamClient;
    private final MailService mailService;

    public TeamServiceImpl(TeamClient teamClient, MailService mailService) {
        this.teamClient = teamClient;
        this.mailService = mailService;
    }


    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msTeam.trying.start");
        teamClient.invokeMsTeam();
        logger.info("ActionLog.msTeam.success");
    }


    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msTeam.failed");
        //new GenericMail(mailService).sendMail("ms-team", ex.getMessage());
    }
}
