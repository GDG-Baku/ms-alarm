package az.gdg.msalarm.service.impl

import az.gdg.msalarm.client.ComplaintClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class ComplaintServiceImplTest extends Specification {
    def complaintClient
    def queueService
    ComplaintServiceImpl complaintService

    void setup() {
        complaintClient = Mock(ComplaintClient)
        queueService = Mock(QueueService)
        complaintService = new ComplaintServiceImpl(complaintClient, queueService)
    }

    def "should invoke complaint client"() {
        when:
            complaintService.invoke()
        then:
            1 * complaintClient.invokeComplaint()
            notThrown(Exception)
    }
}
