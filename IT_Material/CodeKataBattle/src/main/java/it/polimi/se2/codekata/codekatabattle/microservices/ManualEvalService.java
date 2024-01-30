package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.*;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ManualEvalService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    DBMSSource DBS;

    @Autowired
    DBMSApplication DB;


    public ArrayList<DBMSBattleSourceEntry> getSourcesForEval(int uID, int bID)
    {
        DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(DB.getBattleInfo(bID).tID);

        if(tournamentEntry.collaborators.contains(uID))
            return DBS.getSources(bID);
        else
            return null;
    }

    public void addManualScore(int uID, int score, int groupID, int bID)
    {
        DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(DB.getBattleInfo(bID).tID);

        if(tournamentEntry.collaborators.contains(uID))
            publisher.publishEvent(new ScoresTopic(groupID, score, bID));
    }

}
