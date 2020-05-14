package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-auth-client", url = "${client.service.url.ms-auth}")
public interface AuthClient {
    @GetMapping
    void invokeAuth();
}
