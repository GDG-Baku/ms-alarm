package az.gdg.msalarm.config;

import az.gdg.msalarm.client.MsAlarmClient;
import az.gdg.msalarm.client.MsArticleClient;
import az.gdg.msalarm.client.MsAuthClient;
import az.gdg.msalarm.client.MsComplaintClient;
import az.gdg.msalarm.client.MsMailClient;
import az.gdg.msalarm.client.MsSubscriberClient;
import az.gdg.msalarm.client.MsTeamClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {MsArticleClient.class, MsAuthClient.class, MsComplaintClient.class,
        MsMailClient.class, MsSubscriberClient.class, MsTeamClient.class, MsAlarmClient.class})
public class FeignConfig {
}
