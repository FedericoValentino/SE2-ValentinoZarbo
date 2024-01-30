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

    //iether this or load from file a preset
    private void preGeneratedData(ArrayList<DBMSTournamentEntry> tEntries,ArrayList<DBMSUserEntry> usEntries,ArrayList<DBMSBattleEntry> bEntries){
        String []names = {"lask","asdas","lukas"};
         String []email= {"lask@mail.com","asdas@mail.com","lukas@mail.com"};
         String []password= {"lask","asdas","lukas"};
         UserType []userType= {UserType.EDUCATOR,UserType.EDUCATOR,UserType.STUDENT};
        for (int i = 0; i < 3; i++) {
            usEntries.add(new DBMSUserEntry(i,names[i],email[i],password[i],userType[i]));
        }
        String[] tournamentName={"torn1" ,"ttttew", "tartat", "TTT"};
        int [] creatorID={1,1,2,1};
        for (int i = 0; i < 4; i++) {
            tEntries.add(new DBMSTournamentEntry(i,creatorID[i],tournamentName[i]));
        }
         int tID[]={1,1,2,1};
         String []battleName={"b1","bb2","bbbbbr","jasdjj"};
         String assignment="lorem ipusm specchio paura";
         Pair<Date, Date> deadline=new Pair<>(new Date(121212),new Date(121280));
         ArrayList<Group> participatingGroups;
         Map<Integer, Integer> scores;
        for (int i = 0; i < 4; i++) {
            bEntries.add(new DBMSBattleEntry(tID[i],i,battleName[i],new Pair<>(1,4),assignment,deadline ));
        }


    }

    DBMSApplication()
    {
        this.UserEntries = new ArrayList<>();
        this.TournamentEntries = new ArrayList<>();
        this.BattleEntries = new ArrayList<>();


        preGeneratedData(this.TournamentEntries,this.UserEntries,this.BattleEntries);
    }
    /*
    =================================================================================================================================================
       Used by LeaderBoardService
    =================================================================================================================================================
    */
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
    /*
    =================================================================================================================================================
       Used by NotificationService
    =================================================================================================================================================
    */
    public ArrayList<Integer> getAllSignedUsers()
    {
        ArrayList<Integer> allIDS = new ArrayList<>();

        for(DBMSUserEntry entry : this.UserEntries)
        {
            allIDS.add(entry.userID);

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

    /*
    =================================================================================================================================================
       Used by TournamentService
    =================================================================================================================================================
    */
    public int addTournament(int EduID, String TournamentName)
    {
        int tID = -1;
        for (DBMSUserEntry userEntry : this.UserEntries) {
            if (userEntry.userID == EduID && userEntry.userType == UserType.EDUCATOR) {
                tID = this.TournamentEntries.size();
                this.TournamentEntries.add(new DBMSTournamentEntry(this.TournamentEntries.size(), EduID, TournamentName));
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

    public void closeTournament(int tID)
    {
        getTournamentInfo(tID).status = false;
    }


    /*
    =================================================================================================================================================
        Used by Battle Service
    =================================================================================================================================================
    */

    public int addBattle(int tID, int EduID, String BattleName, String assignment, Date submDL, Date subsDL, int maxsize, int minsize)
    {
        int bID = -1;
        for (DBMSTournamentEntry tournamentEntry : this.TournamentEntries) {
            if (tournamentEntry.tID == tID) {
                bID = this.BattleEntries.size();
                tournamentEntry.Battles.add(bID);
                this.BattleEntries.add(new DBMSBattleEntry(tID, bID, BattleName, new Pair<>(minsize, maxsize), assignment, new Pair<>(subsDL, submDL)));
            }
        }

        return bID;
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
    /*
    =================================================================================================================================================
       Used by UserService
    =================================================================================================================================================
    */
    public int addStudent(String UserName, String email, String Password)
    {
        int id = this.UserEntries.size();
        this.UserEntries.add(new DBMSUserEntry(id, UserName, email, Password, UserType.STUDENT));
        return id;
    }
    public int addEducator(String UserName, String email, String Password)
    {
        int id = this.UserEntries.size();
        this.UserEntries.add(new DBMSUserEntry(id, UserName, email, Password, UserType.EDUCATOR));
        return id;
    }
    /*
    =================================================================================================================================================
        Used by Everyone
    =================================================================================================================================================
    */
    public DBMSUserEntry getUserInfo(int uID)
    {
        for(DBMSUserEntry userEntry : UserEntries)
        {
            if(userEntry.userID == uID)
            {
                return userEntry;
            }
        }
        return null;
    }

    public DBMSTournamentEntry getTournamentInfo(int tID)
    {
        for(DBMSTournamentEntry tournamentEntry : TournamentEntries)
        {
            if(tournamentEntry.tID == tID)
            {
                return tournamentEntry;
            }
        }
        return null;
    }

    public DBMSBattleEntry getBattleInfo(int bID)
    {
        for(DBMSBattleEntry battleEntry : BattleEntries)
        {
            if(battleEntry.bID == bID)
            {
                return battleEntry;
            }
        }
        return null;
    }



}
