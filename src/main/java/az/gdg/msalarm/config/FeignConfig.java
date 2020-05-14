package az.gdg.msalarm.config;

import az.gdg.msalarm.client.AlarmClient;
import az.gdg.msalarm.client.ArticleClient;
import az.gdg.msalarm.client.AuthClient;
import az.gdg.msalarm.client.ComplaintClient;
import az.gdg.msalarm.client.MailClient;
import az.gdg.msalarm.client.SubscriberClient;
import az.gdg.msalarm.client.TeamClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {ArticleClient.class, AuthClient.class, ComplaintClient.class,
        MailClient.class, SubscriberClient.class, TeamClient.class, AlarmClient.class})
public class FeignConfig {
}
