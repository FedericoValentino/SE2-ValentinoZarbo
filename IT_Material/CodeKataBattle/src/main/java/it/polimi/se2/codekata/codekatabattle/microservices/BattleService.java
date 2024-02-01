package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSSource;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattleStatus;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.topics.RepositoryTopic;
import org.javatuples.Pair;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.topics.BattleTopic;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private DBMSSource DBS;

    public int createBattle(int tID, int userID, String BattleName, Pair<Integer, Integer> groupRule,
                             String assignment, Pair<Date, Date> deadline, ArrayList<String> testcases)
    {
        ArrayList<Integer> currentTournaments = DB.getCurrentTournament();

        int bID = -1;
        if(currentTournaments.contains(tID))
        {
            if(DB.checkEducatorPermission(tID, userID))
            {
                bID = DB.addBattle(tID, userID, BattleName, assignment, deadline.getValue0(), deadline.getValue1(), groupRule.getValue0(), groupRule.getValue1());
                DBS.addBattleTestCases(bID, testcases);
            }
        }

        if(bID >= 0) {
            publishBattleEvent(bID, BattleStatus.REGISTRATION_PHASE);
            publisher.publishEvent(new RepositoryTopic(bID));
        }

        return bID;

    }
    private boolean canAccessBattleInfo(int UserId, int BattleID, int tid){
        DBMSUserEntry user =DB.getUserInfo(UserId);
        if(user.userType.equals(UserType.EDUCATOR)){
            return user.UserBattles.contains(BattleID);
        }else{
            return user.UserTournaments.contains(tid) && DB.getTournamentInfo(tid).Battles.contains(BattleID);
        }
    }

    public Pair<Integer, Integer> getGroupRules(int UserId, int BattleID, int tid)
    {
        if(canAccessBattleInfo(UserId,BattleID,tid))
        {
            Pair<Integer, Integer> pair = DB.getBattleGroupRules(BattleID);
            return pair;
        }
        return null;
    }

    public String getAssignmentText(int UserId, int BattleId, int tid)
    {
        if(canAccessBattleInfo(UserId,BattleId,tid))
            return DB.getBattleAssignement(BattleId);
        else
            return null;//todo instead of returning null send an error if possible
    }

    public Pair<Date, Date> getDeadlines(int UserId, int BattleID, int tid)
    {
        Pair<Date, Date> pair = null;
        if(canAccessBattleInfo(UserId,BattleID,tid))
            pair = DB.getBattleDeadlines(BattleID);
        return pair;
    }

    public BattleStatus getBattleStatus(int UserId, int BattleID, int tid)
    {
        if(canAccessBattleInfo(UserId,BattleID,tid))
            return DB.getBattleInfo(BattleID).status;
        return null;
    }

    public void joinBattle(int UserId, int tID, int BattleID, ArrayList<String> usersNames)
    {
        //first check if IDs are from actual students
        ArrayList<Integer> StudentID=new ArrayList<>(usersNames.size());
        for (String usname :
                usersNames) {
            StudentID.add(DB.getUserByName(usname).getUserID());
        }
        if(!StudentID.contains(UserId))
            StudentID.add(UserId);
        for(int student : StudentID)
        {
            boolean found = false;
            for(int user : DB.getSubscribedStudents(tID))
            {
                DBMSUserEntry userEntry = DB.getUserInfo(user);
                if (user == student && userEntry.userType == UserType.STUDENT && !userEntry.UserBattles.contains(BattleID)) {
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

    @Scheduled(fixedDelay = 1000)
    private void checkBattlesDeadLine()
    {
        ArrayList<Integer> currentT = DB.getCurrentTournament();

        for(int tID : currentT)
        {
            DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(tID);
            for(int bID : tournamentEntry.Battles)
            {
                if(DB.getBattleInfo(bID).status != BattleStatus.FINISHED)
                {
                    Date time = new Date();
                    Pair<Date, Date> deadline = DB.getBattleDeadlines(bID);
                    if (time.compareTo(deadline.getValue0()) > 0 && time.compareTo(deadline.getValue1()) < 0) {
                        DB.getBattleInfo(bID).status = BattleStatus.BATTLE_PHASE;
                        publishBattleEvent(bID, BattleStatus.BATTLE_PHASE);
                    }
                    else if (time.compareTo(deadline.getValue1()) > 0) {
                        DB.getBattleInfo(bID).status = BattleStatus.CONSOLIDATION_PHASE;
                        publishBattleEvent(bID, BattleStatus.CONSOLIDATION_PHASE);
                    }
                }
            }
        }
    }


    private void publishBattleEvent(int bID, BattleStatus status)
    {
        publisher.publishEvent(new BattleTopic(bID, status));
    }

}
