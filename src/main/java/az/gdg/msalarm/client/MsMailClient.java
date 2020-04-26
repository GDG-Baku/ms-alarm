package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-mail-client", url = "https://gdg-ms-mail.herokuapp.com/health")
public interface MsMailClient {
    @GetMapping
    void invokeMsMail();
}
