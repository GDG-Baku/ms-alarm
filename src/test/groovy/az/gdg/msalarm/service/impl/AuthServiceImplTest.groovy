package az.gdg.msalarm.service.impl

import az.gdg.msalarm.client.AuthClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class AuthServiceImplTest extends Specification {
    def authClient
    def queueService
    AuthServiceImpl authService

    void setup() {
        authClient = Mock(AuthClient)
        queueService = Mock(QueueService)
        authService = new AuthServiceImpl(authClient, queueService)
    }

    def "should invoke auth client"() {
        when:
            authService.invoke()
        then:
            1 * authClient.invokeAuth()
            notThrown(Exception)
    }
}
