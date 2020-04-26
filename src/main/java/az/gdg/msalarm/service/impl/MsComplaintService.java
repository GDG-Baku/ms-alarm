package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.MsComplaintClient;
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
public class MsComplaintService implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(MsComplaintService.class);
    private final MsComplaintClient msComplaintClient;
    private final EmailService emailService;

    public MsComplaintService(MsComplaintClient msComplaintClient, EmailService emailService) {
        this.msComplaintClient = msComplaintClient;
        this.emailService = emailService;
    }

    @Override
    @Retryable(value = Exception.class, backoff = @Backoff(value = 2000))
    public void invoke() {
        logger.info("ActionLog.msComplaint.trying.start");
        msComplaintClient.invokeMsComplaint();
        logger.info("ActionLog.msComplaint.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msComplaint.failed");
        new GenericMail(emailService).sendMail("ms-complaint", ex.getMessage());
    }
}
