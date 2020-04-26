package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-compliant-client", url = "https://gdg-ms-complaint.herokuapp.com/swagger-ui.html")
public interface MsComplaintClient {
    @GetMapping
    void invokeMsComplaint();
}
