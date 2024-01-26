package it.polimi.se2.codekata.codekatabattle.microservices;

import ch.qos.logback.core.joran.sanity.Pair;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.BattleTopic;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class BattleService
{

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DBMSApplication DB;

    public void createBattle(int UserId, String UserType, String BattleName, Pair<Integer, Integer> groupRule,
                      String assignment, Pair<Date, Date> deadline, ArrayList<String> testcases)
    {

    }

    public Pair<Integer, Integer> getGroupRules(int UserId, int BattleID)
    {
        Pair<Integer, Integer> pair = null;
        //return : ruleset (deadlineSubs, deadlineSubm, groupMax, GroupMin)
        return pair;
    }

    public String getAssignemntText(int UserId, int BattleId)
    {
        return "Hello";
    }

    public Pair<Date, Date> getDeadlines(int UserId, int BattleID)
    {
        Pair<Date, Date> pair = null;
        return pair;
    }

    public void joinBattle(int UserId, int BattleID, String UserType, ArrayList<Integer> StudentID)
    {

    }

    private void publishBattleEvent(int bID, int status)
    {
        publisher.publishEvent(new BattleTopic(bID, status));
    }

}
