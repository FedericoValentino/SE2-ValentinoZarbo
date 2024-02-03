package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.DBMS.DBMSBattleSourceEntry;
import it.polimi.se2.codekata.DBMS.DBMSSource;
import it.polimi.se2.codekata.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.DBMS.*;
import it.polimi.se2.codekata.topics.ScoresTopic;
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
        DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(DB.getBattleInfo(bID).gettID());

        if(tournamentEntry.collaborators.contains(uID))
            return DBS.getSources(bID);
        else
            return null;
    }

    public void addManualScore(int uID, int score, int groupID, int bID)
    {
        DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(DB.getBattleInfo(bID).gettID());

        if(tournamentEntry.collaborators.contains(uID))
            publisher.publishEvent(new ScoresTopic(groupID, score, bID));
    }

}
