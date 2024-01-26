package it.polimi.se2.codekata.codekatabattle.DBMS;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
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

        appDB.addStudent("Feder", "valefeder34@gmail.com", "pwd");
        assert(appDB.UserEntries.get(0).UserName.equals("Feder"));
        assert(appDB.UserEntries.get(0).email.equals("valefeder34@gmail.com"));
        assert(appDB.UserEntries.get(0).password.equals("pwd"));
        assert(appDB.UserEntries.get(0).userType == UserType.STUDENT);
    }

    @Test
    void addEducatorTest()
    {

        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        assert(appDB.UserEntries.get(0).UserName.equals("Feder"));
        assert(appDB.UserEntries.get(0).email.equals("valefeder34@gmail.com"));
        assert(appDB.UserEntries.get(0).password.equals("pwd"));
        assert(appDB.UserEntries.get(0).userType == UserType.EDUCATOR);
    }

    @Test
    void addTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);
        appDB.addTournament(1);
        appDB.addTournament(3);

        assert(appDB.TournamentEntries.size() == 1);
        assert (appDB.TournamentEntries.get(0).collaborators.contains(0));
        assert (!appDB.TournamentEntries.get(0).collaborators.contains(1));
        assert (appDB.TournamentEntries.get(0).creatorID == 0);
    }

    @Test
    void grantBattleCreation()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);

        appDB.grantBattleCreation(0, 1);
        appDB.grantBattleCreation(0, 2);

        assert (appDB.TournamentEntries.get(0).collaborators.size() == 2);
        assert (appDB.TournamentEntries.get(0).collaborators.contains(1));
    }

    @Test
    void subscribeToTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");

        appDB.subscribeToTournament(0, 1);

        assert (appDB.UserEntries.get(1).UserTournaments.contains(0));
        assert (appDB.TournamentEntries.get(0).userID.contains(1));

    }

    @Test
    void setScoresUserTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");

        appDB.subscribeToTournament(0, 1);

        appDB.setScoresUserTournament(1, 0, 2000);

        assert (appDB.TournamentEntries.get(0).scores.containsKey(1));
        assert (appDB.TournamentEntries.get(0).scores.get(1) == 2000);
    }

    @Test
    void checkEducatorPermission()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder1", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);

        appDB.grantBattleCreation(0, 1);



        assert(appDB.checkEducatorPermission(0, 1));
        assert(!appDB.checkEducatorPermission(0, 2));
    }


    @Test
    void addBattle()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder1", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0);

        appDB.grantBattleCreation(0, 1);

        int b1 = appDB.addBattle(0, 0, "TEST1", "Ciao Ciao", new Date(), new Date(), 3, 1, new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        int b2 = appDB.addBattle(0, 1, "TEST2", "Ciao Ciao", new Date(), new Date(), 3, 1, new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        int b3 = appDB.addBattle(0, 2, "TEST2", "Ciao Ciao", new Date(), new Date(), 3, 1, new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        assert (appDB.TournamentEntries.get(0).Battles.size() == 2);
        assert (b2 == b1 + 1);
        assert (b3 == -1);
    }

    @Test
    void addGroup()
    {
        appDB.addStudent("Feder1", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder0", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(2);

        int b1 = appDB.addBattle(0, 2, "TEST1", "Ciao Ciao", new Date(), new Date(), 3, 2, new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        appDB.addGroup(new Group(Arrays.asList(0,1)), b1);
        appDB.addGroup(new Group(Arrays.asList(0,1)), b1);
        appDB.addGroup(new Group(Arrays.asList(1)), b1);

        appDB.addGroup(new Group(Arrays.asList(0,2)), b1);

        assert(appDB.BattleEntries.get(b1).participatingGroups.size() == 1);

    }


    @Test
    void getScoresOfTournament() {
    }

    @Test
    void setScoresGroupBattle() {
    }

    @Test
    void getScoresOfBattle() {
    }

    @Test
    void getAllSignedStudent() {
    }

    @Test
    void getSubscribedStudents() {
    }

    @Test
    void getGroups() {
    }

    @Test
    void getInvolvedEDUBattle() {
    }

    @Test
    void getCurrentTournament() {
    }

    @Test
    void getBattlesOfTournament() {
    }

    @Test
    void getBattleGroupRules() {
    }

    @Test
    void getBattleAssignement() {
    }

    @Test
    void getBattleDeadlines() {
    }
}