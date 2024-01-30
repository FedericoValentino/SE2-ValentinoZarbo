package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LeaderBoardService
{
    @Autowired
    DBMSApplication DB;


    /***
     * Sets score for a battle whenever an event comes through
     * @param event the score for a group in a battle
     */
    @EventListener
    public void ScoringTopicListener(ScoresTopic event)
    {
        DB.setScoresGroupBattle(event.getGroupID(), event.getbID(), event.getScore());
    }


    public Map<Integer, Integer> getLeaderBoardTournament(int idT)
    {
        return DB.getScoresOfTournament(idT);
    }
//todo same as other, instead of id give usname
    public Map<Integer, Integer> getLeadBoardBattle(int idB)
    {
        return DB.getScoresOfBattle(idB);
    }
//todo leaderboard must be ordered by score,


}
