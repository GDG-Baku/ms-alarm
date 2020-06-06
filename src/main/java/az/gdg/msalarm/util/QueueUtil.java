package az.gdg.msalarm.util;

import az.gdg.msalarm.mail.MailDto;

import java.util.ArrayList;
import java.util.List;

public class QueueUtil {
    private QueueUtil() {

    }

    public static MailDto prepareMailToSendTeamMembers(String mailBody) {
        List<String> receivers = new ArrayList<>();
        receivers.add("gdg.rubber.duck@gmail.com");
        receivers.add("movsum.nigar@gmail.com");
        receivers.add("asif.hajiyev@outlook.com");
        receivers.add("asifhaciyev1498@gmail.com");
        receivers.add("huseynov_ali@outlook.com");
        receivers.add("isgandarli_murad@mail.ru");

        return MailDto.builder()
                .to(receivers)
                .body("<h2>" + mailBody + "</h2>")
                .subject("APP CRASHED")
                .build();
    }
}
