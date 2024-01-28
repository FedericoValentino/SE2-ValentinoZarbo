package it.polimi.se2.codekata.codekatabattle.DBMS;

import org.apache.catalina.User;
import org.javatuples.Pair;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Component
public class DBMSApplication
{
    ArrayList<DBMSUserEntry> UserEntries;
    ArrayList<DBMSTournamentEntry> TournamentEntries;
    ArrayList<DBMSBattleEntry> BattleEntries;

    DBMSApplication()
    {
        this.UserEntries = new ArrayList<>();
        this.TournamentEntries = new ArrayList<>();
        this.BattleEntries = new ArrayList<>();
    }


    //Used by Leaderboard Service
    public Map<Integer, Integer> getScoresOfTournament(int tID)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                return tournamentEntry.scores;
            }
        }
        return null;
    }
    public void setScoresUserTournament(int userID, int tID, int score)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                if(tournamentEntry.userID.contains(userID))
                {
                    tournamentEntry.scores.put(userID, score);
                }
            }
        }
    }
    public void setScoresGroupBattle(int groupID, int bID, int score)
    {
        for (DBMSBattleEntry battleEntry : this.BattleEntries) {
            if (battleEntry.bID == bID) {
                battleEntry.scores.put(groupID, score);
            }
        }
    }
    public Map<Integer, Integer> getScoresOfBattle(int bID)
    {
        for (DBMSBattleEntry battleEntry : this.BattleEntries) {
            if (battleEntry.bID == bID) {
                return battleEntry.scores;
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
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                return tournamentEntry.userID;
            }
        }
        return null;
    }


    public ArrayList<Group> getGroups(int bID)
    {
        for (DBMSBattleEntry battleEntry : this.BattleEntries) {
            if (battleEntry.bID == bID) {
                return battleEntry.participatingGroups;
            }
        }
        return null;
    }
    public ArrayList<Integer> getInvolvedEDUBattle(int bID)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            for (int j : tournamentEntry.Battles) {
                if (bID == j) {
                    return tournamentEntry.collaborators;
                }
            }
        }
        return null;
    }
    //Used by Tournament Service
    public int addTournament(int EduID)
    {
        int tID = -1;
        for (DBMSUserEntry userEntry : this.UserEntries) {
            if (userEntry.userID == EduID && userEntry.userType == UserType.EDUCATOR) {
                tID = this.TournamentEntries.size();
                this.TournamentEntries.add(new DBMSTournamentEntry(this.TournamentEntries.size(), EduID));
            }
        }
        return tID;
    }
    public void grantBattleCreation(int tID, int EduID)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries)
        {
            if (tID == tournamentEntry.tID) {
                tournamentEntry.collaborators.add(EduID);
            }
        }
    }

    public void subscribeToTournament(int tID, int userID)
    {

        getTournamentInfo(tID).userID.add(userID);
        getUserInfo(userID).UserTournaments.add(tID);
    }
    public ArrayList<Integer> getCurrentTournament()
    {
        ArrayList<Integer> currentTournaments = new ArrayList<>();

        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.status) {
                currentTournaments.add(tournamentEntry.tID);
            }
        }

        return currentTournaments;
    }
    public ArrayList<Integer> getBattlesOfTournament(int tID)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                return tournamentEntry.Battles;
            }
        }
        return null;
    }
    public boolean checkEducatorPermission(int tID, int EduID )
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                if (tournamentEntry.collaborators.contains(EduID)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Used by Battle Service
    public int addBattle(int tID, int EduID, String BattleName, String assignment, Date submDL, Date subsDL, int maxsize, int minsize, ArrayList<String> testcases)
    {
        int bID = -1;
        boolean added = false;
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                bID = this.BattleEntries.size();
                tournamentEntry.Battles.add(bID);
                this.BattleEntries.add(new DBMSBattleEntry(tID, bID, BattleName, new Pair<>(minsize, maxsize), assignment, new Pair<>(subsDL, submDL), testcases));
            }
        }

        return bID;
    }

    public Pair<Integer, Integer> getBattleGroupRules(int bID)
    {
        for (DBMSBattleEntry battleEntry : BattleEntries) {
            if (battleEntry.bID == bID) {
                return battleEntry.groupRule;
            }
        }

        return new Pair<>(0,0);
    }

    public String getBattleAssignement(int bID)
    {
        for (DBMSBattleEntry battleEntry : BattleEntries) {
            if (battleEntry.bID == bID) {
                return battleEntry.Assignment;
            }
        }
        return "";
    }

    public Pair<Date, Date> getBattleDeadlines(int bID)
    {
        for (DBMSBattleEntry battleEntry : BattleEntries) {
            if (battleEntry.bID == bID) {
                return battleEntry.deadline;
            }
        }

        return null;
    }

    public void addGroup(Group students, int bID)
    {
        for (DBMSBattleEntry battleEntry : BattleEntries) {
            if (battleEntry.bID == bID) {
                battleEntry.participatingGroups.add(students);
            }
        }

        //battle is confirmed for student, so i'm adding it to the student's battles.
        for(int i = 0; i < students.getStudentsID().size(); i++)
        {
            for(DBMSUserEntry userEntry : UserEntries)
            {
                if (userEntry.userID == students.getStudentsID().get(i) && userEntry.userType == UserType.STUDENT)
                {
                    userEntry.UserBattles.add(bID);
                }
            }
        }
    }


    //Used by User Service
    public void addStudent(String UserName, String email, String Password)
    {
        this.UserEntries.add(new DBMSUserEntry(this.UserEntries.size(), UserName, email, Password, UserType.STUDENT));
    }
    public void addEducator(String UserName, String email, String Password)
    {
        this.UserEntries.add(new DBMSUserEntry(this.UserEntries.size(), UserName, email, Password, UserType.EDUCATOR));
    }

    public DBMSUserEntry getUserInfo(int uID)
    {
        return UserEntries.get(uID);
    }

    public DBMSTournamentEntry getTournamentInfo(int tID)
    {
        return TournamentEntries.get(tID);
    }

    public DBMSBattleEntry getBattleInfo(int bID)
    {
        return BattleEntries.get(bID);
    }



}
