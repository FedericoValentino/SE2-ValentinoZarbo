package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattleStatus;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import org.javatuples.Pair;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.topics.BattleTopic;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattleStatus;

@Service
public class BattleService
{

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DBMSApplication DB;

    public void createBattle(int tID, int userID, String BattleName, Pair<Integer, Integer> groupRule,
                             String assignment, Pair<Date, Date> deadline, ArrayList<String> testcases)
    {
        ArrayList<Integer> currentTournaments = DB.getCurrentTournament();

        int bID = -1;
        if(currentTournaments.contains(tID))
        {
            if(DB.checkEducatorPermission(tID, userID))
            {
                bID = DB.addBattle(tID, userID, BattleName, assignment, deadline.getValue0(), deadline.getValue1(), groupRule.getValue0(), groupRule.getValue1(), testcases);
            }
        }

        if(bID >= 0)
            publishBattleEvent(bID, BattleStatus.REGISTRATION_PHASE);
    }

    public Pair<Integer, Integer> getGroupRules(int UserId, int BattleID)
    {
        Pair<Integer, Integer> pair = DB.getBattleGroupRules(BattleID);
        return pair;
    }

    public String getAssignmentText(int UserId, int BattleId)
    {


        return DB.getBattleAssignement(BattleId);
    }

    public Pair<Date, Date> getDeadlines(int UserId, int BattleID)
    {
        Pair<Date, Date> pair = null;
        pair = DB.getBattleDeadlines(BattleID);
        return pair;
    }

    public void joinBattle(int UserId, int tID, int BattleID, String Type, ArrayList<Integer> StudentID)
    {
        //first check if IDs are from actual students

        for(int i = 0; i < StudentID.size(); i++)
        {
            boolean found = false;
            for(int user : DB.getSubscribedStudents(tID))
            {
                DBMSUserEntry userEntry = DB.getUserInfo(user);
                if (user == StudentID.get(i) && userEntry.userType == UserType.STUDENT && !userEntry.UserBattles.contains(BattleID)) {
                    found = true;
                    break;
                }
            }
            //if that ID is not found or it's from an Educator dont do anything
            if(!found)
            {
                return;
            }
        }

        //if all is good check for the battle and if min and max are respected add to battle
        Pair<Integer, Integer> rules = DB.getBattleGroupRules(BattleID);

        if(StudentID.size() <= rules.getValue1() && StudentID.size() >= rules.getValue0())
        {
            Group students = new Group(StudentID);
            DB.addGroup(students, BattleID);
        }
    }

    private void publishBattleEvent(int bID, BattleStatus status)
    {
        publisher.publishEvent(new BattleTopic(bID, status));
    }

}
