package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-compliant-client", url = "${client.service.url.ms-complaint}")
public interface ComplaintClient {
    @GetMapping
    void invokeComplaint();
}
