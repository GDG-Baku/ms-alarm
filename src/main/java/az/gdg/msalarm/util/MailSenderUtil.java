package az.gdg.msalarm.util;

import az.gdg.msalarm.service.QueueService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MailSenderUtil {
    private MailSenderUtil() {
        
    }

    public static void sendMail(String appName, String errorMessage, QueueService queueService) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(new Date());
        String mailBody = appName + " crashed at " + time + "<br>" + "<h3>reason:<br>" + errorMessage + "</h3>";
        queueService.sendToQueue(QueueUtil.prepareMailToSendTeamMembers(mailBody));
    }
}
