package az.gdg.msalarm.service;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GenericMail {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final MailService mailService;

    public GenericMail(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendMail(String appName, String errorMessage) {
        String time = dateFormat.format(new Date());
        String mailBody = appName + " crashed at " + time + "<br>" + "<h3>reason:<br>" + errorMessage + "</h3>";
        mailService.sendToQueue(mailService.prepareMail(mailBody));
    }
}
