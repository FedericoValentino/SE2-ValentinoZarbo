package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.GeneralStuff.Group;
import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.Utility;
import it.polimi.se2.codekata.topics.ScoresTopic;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.*;

@SpringBootTest
class LeaderBoardServiceTest
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LeaderBoardService leaderBoardService;

    @Autowired
    private DBMSApplication appDB;

    @Test
    void getLeaderBoardTournament()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID2 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        appDB.addTournament(educatorID, "Test");

        appDB.subscribeToTournament(0, studentID1);
        appDB.subscribeToTournament(0, studentID2);

        appDB.setScoresUserTournament(studentID2, 0,  10);
        appDB.setScoresUserTournament(studentID1, 0,  9);



        List<Pair<String, Integer>> leaderboard = leaderBoardService.getLeaderBoardTournament(0);

        assert(leaderboard.get(0).getValue1() >= leaderboard.get(1).getValue1());
    }

    @Test
    void ScoringTopicListener()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID2 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID3 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(0, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);

        appDB.subscribeToTournament(0, studentID1);
        appDB.subscribeToTournament(0, studentID2);
        appDB.subscribeToTournament(0, studentID3);

        appDB.addGroup(new Group(List.of(studentID1)), bID);
        appDB.addGroup(new Group(List.of(studentID2)), bID);
        appDB.addGroup(new Group(List.of(studentID3)), bID);

        publisher.publishEvent(new ScoresTopic(0, 9, bID));

        assert(appDB.getBattleInfo(bID).scores.size() == 1);
        assert(appDB.getBattleInfo(bID).scores.get(0) == 9);
    }

    @Test
    void getLeaderBoardBattle()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID2 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID3 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(0, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);

        appDB.subscribeToTournament(0, studentID1);
        appDB.subscribeToTournament(0, studentID2);
        appDB.subscribeToTournament(0, studentID3);

        appDB.addGroup(new Group(List.of(studentID1)), bID);
        appDB.addGroup(new Group(List.of(studentID2)), bID);
        appDB.addGroup(new Group(List.of(studentID3)), bID);

        assert(appDB.getBattleInfo(bID).participatingGroups.size() == 3);

        publisher.publishEvent(new ScoresTopic(0, 9, bID));
        publisher.publishEvent(new ScoresTopic(1, 8, bID));
        publisher.publishEvent(new ScoresTopic(2, 10, bID));

        List<Pair<Integer, Integer>> leaderboard = leaderBoardService.getLeaderBoardBattle(bID);

        assert(leaderboard.size() == 3);
        assert(leaderboard.get(0).getValue1() >= leaderboard.get(1).getValue1());
        assert(leaderboard.get(1).getValue1() >= leaderboard.get(2).getValue1());

    }

}