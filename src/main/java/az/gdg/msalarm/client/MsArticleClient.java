package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-article-client", url = "")
public interface MsArticleClient {
    @GetMapping
    void invokeMsArticle();
}
