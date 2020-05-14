package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-alarm-client", url = "${client.service.url.ms-alarm}")
public interface AlarmClient {
    @GetMapping
    void invokeMsAlarm();
}
