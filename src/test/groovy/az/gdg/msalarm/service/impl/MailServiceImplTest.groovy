package az.gdg.msalarm.service.impl


import az.gdg.msalarm.client.MailClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class MailServiceImplTest extends Specification {

    def mailClient
    def queueService
    MailServiceImpl mailService

    void setup() {
        mailClient = Mock(MailClient)
        queueService = Mock(QueueService)
        mailService = new MailServiceImpl(mailClient, queueService)
    }

    def "should invoke mail client"() {
        when:
            mailService.invoke()
        then:
            1 * mailClient.invokeMail()
            notThrown(Exception)
    }
}
