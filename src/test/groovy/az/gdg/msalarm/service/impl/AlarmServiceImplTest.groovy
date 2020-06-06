package az.gdg.msalarm.service.impl

import az.gdg.msalarm.client.AlarmClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class AlarmServiceImplTest extends Specification {

    def alarmClient
    def queueService
    AlarmServiceImpl alarmService

    void setup() {
        alarmClient = Mock(AlarmClient)
        queueService = Mock(QueueService)
        alarmService = new AlarmServiceImpl(alarmClient, queueService)
    }

    def "should invoke alarm client"() {
        when:
            alarmService.invoke()
        then:
            1 * alarmClient.invokeAlarm()
            notThrown(Exception)
    }
}
