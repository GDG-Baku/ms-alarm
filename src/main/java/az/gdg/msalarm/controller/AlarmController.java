package az.gdg.msalarm.controller;

import az.gdg.msalarm.service.AlarmService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wake-up")
public class AlarmController {
    private final AlarmService msArticle;
    private final AlarmService msTeam;
    private final AlarmService msAuth;

    public AlarmController(@Qualifier("msArticleService") AlarmService msArticle,
                           @Qualifier("msTeamService") AlarmService msTeam,
                           @Qualifier("msAuthService") AlarmService msAuth) {
        this.msArticle = msArticle;
        this.msTeam = msTeam;
        this.msAuth = msAuth;
    }

    @GetMapping
    public void invokeOtherApps() {
        msArticle.invoke();
        msTeam.invoke();
        msAuth.invoke();
    }
}
