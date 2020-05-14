package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-subscriber-client", url = "${client.service.url.ms-subscriber}")
public interface MsSubscriberClient {
    @GetMapping
    void invokeMsSubscriber();
}
