package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-mail-client", url = "${client.service.url.ms-mail}")
public interface MsMailClient {
    @GetMapping
    void invokeMsMail();
}
