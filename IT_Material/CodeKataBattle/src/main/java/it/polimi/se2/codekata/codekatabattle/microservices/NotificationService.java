package it.polimi.se2.codekata.codekatabattle.microservices;
import it.polimi.se2.codekata.codekatabattle.topics.BattleTopic;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService
{

    private void sendEmail(String text)
    {

    }
    @EventListener
    public void tournamentTopicListener(TournamentTopic event)
    {
        if(event.getTournamentStatus())
        {
            sendEmail("Tournament "+event.getTournamentID()+" is starting!");
        }
        else
        {
            sendEmail("Tournament "+event.getTournamentID()+" has ended!");
        }
    }


    @EventListener
    public void BattleTopicListener(BattleTopic event)
    {
        switch(event.getBattleStatus())
        {
            default:
                sendEmail("Battle "+event.getBattleID()+"!");
                break;
        }
    }


}
