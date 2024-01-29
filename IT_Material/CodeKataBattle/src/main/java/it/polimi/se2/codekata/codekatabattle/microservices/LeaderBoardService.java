package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardService
{
    @Autowired
    DBMSApplication DB;


    @EventListener
    public void ScoringTopicListener(ScoresTopic event)
    {
        
    }



}
