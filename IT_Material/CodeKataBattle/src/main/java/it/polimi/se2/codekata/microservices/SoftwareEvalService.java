package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSBattleSourceEntry;
import it.polimi.se2.codekata.DBMS.DBMSSource;
import it.polimi.se2.codekata.topics.CommitsTopic;
import it.polimi.se2.codekata.topics.ScoresTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SoftwareEvalService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    DBMSSource DBS;


    private int compileAnalyze(int bID, int gID)
    {
        int score = 0;
        for(DBMSBattleSourceEntry entry : DBS.getSources(bID))
        {
            if(gID == entry.groupID)
            {
                score = (int)(Math.random()%10);
                break;
            }
        }
        return score;
    }
    @EventListener
    private void CommitsListener(CommitsTopic event)
    {
        int result = compileAnalyze(event.getBattleID(), event.getGroupID());

        publisher.publishEvent(new ScoresTopic(event.getGroupID(), result, event.getBattleID()));
    }
}
