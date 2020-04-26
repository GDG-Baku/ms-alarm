package az.gdg.msalarm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "ms-article-client", url = "https://gdg-ms-article.herokuapp.com/api/health")
public interface MsArticleClient {
    @GetMapping
    void invokeMsArticle();
}
