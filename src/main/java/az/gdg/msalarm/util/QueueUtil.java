package az.gdg.msalarm.util;

import az.gdg.msalarm.mail.MailDto;
import az.gdg.msalarm.service.QueueService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueueUtil {
    private QueueUtil() {

    }

    public static MailDto buildMail(String mailBody) {
        List<String> receivers = new ArrayList<>();
        receivers.add("gdg.rubber.duck@gmail.com");
        receivers.add("movsum.nigar@gmail.com");
        receivers.add("asif.hajiyev@outlook.com");
        receivers.add("huseynov_ali@outlook.com");
        receivers.add("isgandarli_murad@mail.ru");

        return MailDto.builder()
                .to(receivers)
                .body("<h2>" + mailBody + "</h2>")
                .subject("APP CRASHED")
                .build();
    }

    public static void sendMail(String appName, String errorMessage, QueueService queueService) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(new Date());
        String mailBody = appName + " crashed at " + time + "<br>" + "<h3>reason:<br>" + errorMessage + "</h3>";
        queueService.sendToQueue(buildMail(mailBody));
    }
}
