package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSBattleSourceEntry;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSSource;
import it.polimi.se2.codekata.codekatabattle.topics.CommitsTopic;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.javatuples.Pair;
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
