package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsTeamClient;
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
public class MsTeamService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsTeamService.class);
    private final MsTeamClient msTeamClient;
    private final EmailService emailService;

    public MsTeamService(MsTeamClient msTeamClient, EmailService emailService) {
        this.msTeamClient = msTeamClient;
        this.emailService = emailService;
    }


    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msTeam.trying.start");
        msTeamClient.invokeMsTeam();
        logger.info("ActionLog.msTeam.success");
    }


    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msTeam.failed");
        //new GenericMail(emailService).sendMail("ms-team", ex.getMessage());
    }
}
