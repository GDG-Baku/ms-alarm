package az.gdg.msalarm.service.impl

import az.gdg.msalarm.client.SubscriberClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class SubscriberServiceImplTest extends Specification {

    def subscriberClient
    def queueService
    SubscriberServiceImpl subscriberService

    void setup() {
        subscriberClient = Mock(SubscriberClient)
        queueService = Mock(QueueService)
        subscriberService = new SubscriberServiceImpl(subscriberClient, queueService)
    }

    def "should invoke subscriber client"() {
        when:
            subscriberService.invoke()
        then:
            1 * subscriberClient.invokeSubscriber()
            notThrown(Exception)
    }
}
