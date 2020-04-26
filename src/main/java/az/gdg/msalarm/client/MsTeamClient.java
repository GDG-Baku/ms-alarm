package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-team-client", url = "https://gdg-ms-team.herokuapp.com/api/swagger-ui.html")
public interface MsTeamClient {
    @GetMapping
    void invokeMsTeam();
}
