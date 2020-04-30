package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-team-client", url = "https://ms-team-gdg.herokuapp.com/api/health")
public interface MsTeamClient {
    @GetMapping
    void invokeMsTeam();
}
