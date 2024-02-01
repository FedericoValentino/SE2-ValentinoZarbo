package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.Utility;
import it.polimi.se2.codekata.codekatabattle.topics.GithubPingTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManualEvalServiceTest
{
    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    DBMSApplication appDB;

    @Autowired
    ManualEvalService MES;


    @Test
    void addManualScore()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID2 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID3 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);

        appDB.subscribeToTournament(tID, studentID1);
        appDB.subscribeToTournament(tID, studentID2);
        appDB.subscribeToTournament(tID, studentID3);

        appDB.addGroup(new Group(List.of(studentID1)), bID);
        appDB.addGroup(new Group(List.of(studentID2)), bID);
        appDB.addGroup(new Group(List.of(studentID3)), bID);

        publisher.publishEvent(new GithubPingTopic(0, bID, "Test Source"));

        MES.addManualScore(educatorID, 10, 0, bID);


        assert (appDB.getBattleInfo(bID).scores.get(0) == 10);

    }
}