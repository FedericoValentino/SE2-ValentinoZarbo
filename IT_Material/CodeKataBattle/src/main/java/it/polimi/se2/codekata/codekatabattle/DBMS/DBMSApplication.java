package it.polimi.se2.codekata.codekatabattle.DBMS;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class DBMSApplication
{
    ArrayList<DBMSUserEntry> UserEntries;
    ArrayList<DBMSTournamentEntry> TournamentEntries;
    ArrayList<DBMSBattleEntry> BattleEntries;


    //Used by Leaderboard Service
    public Map<Integer, Integer> getScoresOfTournament(int tID)
    {
        for(int i = 0; i < this.TournamentEntries.size(); i++)
        {
            if(TournamentEntries.get(i).tID == tID)
            {
                return TournamentEntries.get(i).scores;
            }
        }
        return null;
    }
    public void setScoresUserTournament(int userID, int tID, int score)
    {
        for(int i = 0; i < this.TournamentEntries.size(); i++)
        {
            if(TournamentEntries.get(i).tID == tID)
            {
                TournamentEntries.get(i).scores.replace(userID, score);
            }
        }
    }
    public void setScoresGroupBattle(int groupID, int bID, int score)
    {
        for(int i = 0; i < this.BattleEntries.size(); i++)
        {
            if(BattleEntries.get(i).bID == bID)
            {
                BattleEntries.get(i).scores.replace(groupID, score);
            }
        }
    }
    public Map<Integer, Integer> getScoresOfBattle(int bID)
    {
        for(int i = 0; i < this.BattleEntries.size(); i++)
        {
            if(BattleEntries.get(i).bID == bID)
            {
                return BattleEntries.get(i).scores;
            }
        }

        return null;
    }

    //Used by Notification Service
    public ArrayList<Integer> getAllSignedStudent()
    {
        ArrayList<Integer> allIDS = new ArrayList<>();

        for(DBMSUserEntry entry : this.UserEntries)
        {
            if(entry.userType == UserType.STUDENT)
            {
                allIDS.add(entry.userID);
            }
        }
        return allIDS;
    }

    public ArrayList<Integer> getSubscribedStudents(int tID)
    {
        for(int i = 0; i < this.TournamentEntries.size(); i++)
        {
            if(TournamentEntries.get(i).tID == tID)
            {
                return TournamentEntries.get(i).userID;
            }
        }
        return null;
    }


    public ArrayList<Group> getGroups(int bID)
    {
        for(int i = 0; i < this.BattleEntries.size(); i++)
        {
            if(BattleEntries.get(i).bID == bID)
            {
                return BattleEntries.get(i).participatingGroups;
            }
        }
        return null;
    }
    public ArrayList<Integer> getInvolvedEDUBattle(int bID)
    {

        return null;
    }
    //Used by Tournament Service
    public void addTournament(int EduID)
    {

    }
    public void grantBattleCreation(int tID, int EduID)
    {

    }
    public ArrayList<Integer> getCurrentTournament()
    {
        return null;
    }
    public ArrayList<Integer> getBattlesOfTournament(int tID)
    {
        return null;
    }
    public boolean checkEducatorPermission(int tID, int EduID )
    {
        return true;
    }

    //Used by Battle Service
    public int addBattle(String TournamentID, String assignment, int submDL, int subsDL, int maxsize, int minsize)
    {
        return 0;
    }

    public Pair<Integer, Integer> getBattleGroupRules(int bID)
    {
        return new Pair<Integer, Integer>(0,0);
    }

    public String getBattleAssignement(int bID)
    {
        return "";
    }

    public void getBattleDeadlines(int bID)
    {

    }
    public void addGroup(Group students, String bID)
    {

    }


    //Used by User Service
    public void addStudent(String UserName, String email, String Password)
    {

    }
    public void addEducator(String UserName, String email, String Password)
    {

    }

}
