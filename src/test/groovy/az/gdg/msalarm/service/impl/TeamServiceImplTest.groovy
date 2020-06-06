package az.gdg.msalarm.service.impl

import az.gdg.msalarm.client.TeamClient
import az.gdg.msalarm.service.QueueService
import spock.lang.Specification

class TeamServiceImplTest extends Specification {

    def teamClient
    def queueService
    TeamServiceImpl teamService

    void setup() {
        teamClient = Mock(TeamClient)
        queueService = Mock(QueueService)
        teamService = new TeamServiceImpl(teamClient, queueService)
    }

    def "should invoke team client"() {
        when:
            teamService.invoke()
        then:
            1 * teamClient.invokeTeam()
            notThrown(Exception)
    }
}
