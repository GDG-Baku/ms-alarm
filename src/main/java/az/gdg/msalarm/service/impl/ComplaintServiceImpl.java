package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.ComplaintClient;
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
public class ComplaintServiceImpl implements AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(ComplaintServiceImpl.class);
    private final ComplaintClient complaintClient;
    private final MailService mailService;

    public ComplaintServiceImpl(ComplaintClient complaintClient, MailService mailService) {
        this.complaintClient = complaintClient;
        this.mailService = mailService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msComplaint.trying.start");
        complaintClient.invokeMsComplaint();
        logger.info("ActionLog.msComplaint.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msComplaint.failed");
        //new GenericMail(mailService).sendMail("ms-complaint", ex.getMessage());
    }
}
