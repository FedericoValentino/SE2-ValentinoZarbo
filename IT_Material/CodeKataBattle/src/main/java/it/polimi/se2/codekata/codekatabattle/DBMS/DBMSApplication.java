package it.polimi.se2.codekata.codekatabattle.DBMS;

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
    public void addTournament(int EduID)
    {
        for (DBMSUserEntry userEntry : this.UserEntries) {
            if (userEntry.userID == EduID && userEntry.userType == UserType.EDUCATOR) {
                this.TournamentEntries.add(new DBMSTournamentEntry(this.TournamentEntries.size(), EduID));
            }
        }
    }
    public void grantBattleCreation(int tID, int EduID)
    {
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tID == tournamentEntry.creatorID) {
                for(DBMSUserEntry userEntry : this.UserEntries)
                {
                    if(EduID == userEntry.userID && userEntry.userType == UserType.EDUCATOR)
                    {
                        tournamentEntry.collaborators.add(EduID);
                    }
                }
            }
        }
    }

    public void subscribeToTournament(int tID, int userID)
    {
        for (DBMSUserEntry userEntry : this.UserEntries)
        {
            if(userID == userEntry.userID && userEntry.userType == UserType.STUDENT)
            {
                for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
                    if (tournamentEntry.status && tournamentEntry.tID == tID) {
                        tournamentEntry.userID.add(userID);
                        userEntry.UserTournaments.add(tID);
                    }
                }
            }
        }
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
        boolean foundEdu = false;
        //-1 means the battle hasn't been added for some reason.


        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                for(int collaborator : tournamentEntry.collaborators)
                {
                    if(collaborator == EduID)
                    {
                        foundEdu = true;
                    }
                }
            }
        }

        if(!foundEdu)
        {
            return -1;
        }

        int bID = -1;
        boolean added = false;
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                bID = this.BattleEntries.size();
                tournamentEntry.Battles.add(bID);
                added = true;
            }
        }

        if(added)
        {
            this.BattleEntries.add(new DBMSBattleEntry(tID, bID, BattleName, new Pair<>(maxsize, minsize), assignment, new Pair<>(subsDL, submDL), testcases));
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

}
