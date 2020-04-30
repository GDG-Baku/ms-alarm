package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-auth-client", url = "https://gdg-ms-auth.herokuapp.com/health")
public interface MsAuthClient {
    @GetMapping
    void invokeMsAuth();
}
