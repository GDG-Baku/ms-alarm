package az.gdg.msalarm.service.impl;

import az.gdg.msalarm.client.ComplaintClient;
import az.gdg.msalarm.service.AlarmService;
import az.gdg.msalarm.service.QueueService;
import az.gdg.msalarm.util.MailSenderUtil;

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
    private final QueueService queueService;

    public ComplaintServiceImpl(ComplaintClient complaintClient, QueueService queueService) {
        this.complaintClient = complaintClient;
        this.queueService = queueService;
    }

    @Override
    @Scheduled(fixedRate = 20 * 60 * 1000)
    @Retryable(value = Exception.class, backoff = @Backoff(value = 5000))
    public void invoke() {
        logger.info("ActionLog.msComplaint.trying.start");
        complaintClient.invokeComplaint();
        logger.info("ActionLog.msComplaint.success");
    }

    @Recover
    private void recover(Exception ex) {
        logger.error("ActionLog.msComplaint.failed");
        MailSenderUtil.sendMail("ms-complaint", ex.getMessage(), queueService);
    }
}
