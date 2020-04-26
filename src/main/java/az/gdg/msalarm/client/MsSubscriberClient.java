package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-subscriber-client", url = "https://gdg-ms-subscriber.herokuapp.com/swagger-ui.html")
public interface MsSubscriberClient {
    @GetMapping
    void invokeMsSubscriber();
}
