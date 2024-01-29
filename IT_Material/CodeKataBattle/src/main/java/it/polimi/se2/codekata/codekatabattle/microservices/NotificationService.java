package it.polimi.se2.codekata.codekatabattle.microservices;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.BattleTopic;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class NotificationService
{

    @Autowired
    DBMSApplication DB;

    private void sendEmail(String text)
    {
        System.out.println(text);
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
