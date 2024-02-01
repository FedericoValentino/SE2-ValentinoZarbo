package it.polimi.se2.codekata.codekatabattle.DBMS;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.Utility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DBMSApplicationTest
{

    DBMSApplication appDB = new DBMSApplication();

    @Test
    void addStudentTest()
    {

        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        assert(appDB.UserEntries.get(stud).email.equals("valefeder34@gmail.com"));
        assert(appDB.UserEntries.get(stud).password.equals("pwd"));
        assert(appDB.UserEntries.get(stud).userType == UserType.STUDENT);
    }

    @Test
    void addEducatorTest()
    {

        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        assert(appDB.UserEntries.get(edu).email.equals("valefeder34@gmail.com"));
        assert(appDB.UserEntries.get(edu).password.equals("pwd"));
        assert(appDB.UserEntries.get(edu).userType == UserType.EDUCATOR);
    }

    @Test
    void addTournament()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Test");
        assert(appDB.getTournamentInfo(tID).creatorID == edu);
        assert(appDB.getTournamentInfo(tID).collaborators.contains(edu));
    }

    @Test
    void grantBattleCreation()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu2 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Test");
        appDB.grantBattleCreation(tID, edu2);
        assert(appDB.getTournamentInfo(tID).creatorID == edu);
        assert(appDB.getTournamentInfo(tID).collaborators.contains(edu));
        assert(appDB.getTournamentInfo(tID).collaborators.contains(edu2));

    }

    @Test
    void subscribeToTournament()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Test");
        appDB.subscribeToTournament(tID, stud);
        assert(appDB.getTournamentInfo(tID).userID.contains(stud));
    }

    @Test
    void setScoresUserTournament()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Test");
        appDB.subscribeToTournament(tID, stud);
        appDB.setScoresUserTournament(stud, tID, 10);
        assert (appDB.getTournamentInfo(tID).scores.get(stud) == 10);

    }

    @Test
    void checkEducatorPermission()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu2 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Test");
        appDB.grantBattleCreation(tID, edu2);
        assert(appDB.checkEducatorPermission(tID, edu));
        assert(appDB.checkEducatorPermission(tID, edu2));
    }


    @Test
    void addBattle()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Ciao");

        int b1 = appDB.addBattle(tID, edu, "TEST1", "Ciao Ciao", new Date(), new Date(), 3, 2);

        assert(appDB.getBattlesOfTournament(tID).size() == 1);
        assert(appDB.getBattlesOfTournament(tID).contains(b1));
    }

    @Test
    void addGroup()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = appDB.addTournament(edu, "Ciao");

        int b1 = appDB.addBattle(tID, edu, "TEST1", "Ciao Ciao", new Date(), new Date(), 3, 1);

        appDB.addGroup(new Group(Arrays.asList(stud)), b1);

        assert(appDB.getBattleInfo(b1).participatingGroups.size() == 1);
        assert(appDB.getBattleInfo(b1).participatingGroups.get(0).getStudentsID().contains(stud));


    }
}