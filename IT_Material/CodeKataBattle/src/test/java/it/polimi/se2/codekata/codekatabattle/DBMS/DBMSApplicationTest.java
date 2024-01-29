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

    }

    @Test
    void grantBattleCreation()
    {

    }

    @Test
    void subscribeToTournament()
    {


    }

    @Test
    void setScoresUserTournament()
    {

    }

    @Test
    void checkEducatorPermission()
    {

    }


    @Test
    void addBattle()
    {

    }

    @Test
    void addGroup()
    {

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