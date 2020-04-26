package az.gdg.msalarm.controller;

import az.gdg.msalarm.service.AlarmService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private final AlarmService msArticle;
    private final AlarmService msTeam;
    private final AlarmService msAuth;
    private final AlarmService msComplaint;
    private final AlarmService msSubscriber;
    private final AlarmService msMail;

    public AlarmController(@Qualifier("msArticleService") AlarmService msArticle,
                           @Qualifier("msTeamService") AlarmService msTeam,
                           @Qualifier("msAuthService") AlarmService msAuth,
                           @Qualifier("msComplaintService") AlarmService msComplaint,
                           @Qualifier("msSubscriberService") AlarmService msSubscriber,
                           @Qualifier("msMailService") AlarmService msMail) {
        this.msArticle = msArticle;
        this.msTeam = msTeam;
        this.msAuth = msAuth;
        this.msComplaint = msComplaint;
        this.msSubscriber = msSubscriber;
        this.msMail = msMail;
    }

    @GetMapping
    public void invokeOtherApps() {
        //msArticle.invoke();
        msTeam.invoke();
        msAuth.invoke();
        msComplaint.invoke();
        msSubscriber.invoke();
        msMail.invoke();
    }
}
