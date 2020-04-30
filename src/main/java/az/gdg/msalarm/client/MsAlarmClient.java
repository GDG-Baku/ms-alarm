package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-alarm-client", url = "https://ms-alarm-gdg.herokuapp.com/alarm")
public interface MsAlarmClient {
    @GetMapping
    void invokeMsAlarm();
}
