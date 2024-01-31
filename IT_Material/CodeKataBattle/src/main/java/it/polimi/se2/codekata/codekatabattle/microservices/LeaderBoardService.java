package it.polimi.se2.codekata.codekatabattle.microservices;

import org.javatuples.Pair;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


    public List<Pair<String, Integer>> getLeaderBoardTournament(int idT)
    {
        List<Pair<String, Integer>> leadeboard;
        Map<Integer, Integer> leaderboardReal = DB.getScoresOfTournament(idT);
        ArrayList<Pair<String, Integer>> temp = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : leaderboardReal.entrySet())
        {
            String Username = DB.getUserInfo(entry.getKey()).UserName;
            Pair<String, Integer> tempEntry = new Pair<>(Username, entry.getValue());
            temp.add(tempEntry);
        }

        Collections.sort(temp, Comparator.comparing(p -> p.getValue1()));

        leadeboard = temp.stream().toList().reversed();

        return leadeboard;
    }

    public List<Pair<Integer, Integer>>  getLeadBoardBattle(int idB)
    {

        List<Pair<Integer, Integer>> leadeboard;
        Map<Integer, Integer> leaderboardReal = DB.getScoresOfBattle(idB);
        ArrayList<Pair<Integer, Integer>> temp = new ArrayList<>();

        for(Map.Entry<Integer, Integer> entry : leaderboardReal.entrySet())
        {
            Pair<Integer, Integer> tempEntry = new Pair<>(entry.getKey(), entry.getValue());
            temp.add(tempEntry);
        }
        Collections.sort(temp, Comparator.comparing(p -> p.getValue1()));

        leadeboard = temp.stream().toList().reversed();
        return leadeboard;
    }


}
