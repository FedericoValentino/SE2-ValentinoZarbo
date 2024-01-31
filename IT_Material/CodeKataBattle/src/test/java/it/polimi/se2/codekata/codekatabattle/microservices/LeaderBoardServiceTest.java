package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSBattleEntry;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaderBoardServiceTest
{
    @Autowired
    private LeaderBoardService leaderBoardService;

    @Autowired
    private DBMSApplication appDB;

    @Test
    void getLeaderBoardTournament()
    {
        int educatorID = appDB.addEducator("educator", "email", "password");
        int studentID1 = appDB.addEducator("student1", "email", "password");
        int studentID2 = appDB.addEducator("student2", "email", "password");

        appDB.addTournament(educatorID, "Test");

        appDB.subscribeToTournament(0, studentID1);
        appDB.subscribeToTournament(0, studentID2);

        appDB.setScoresUserTournament(studentID2, 0,  10);
        appDB.setScoresUserTournament(studentID1, 0,  9);



        List<Pair<String, Integer>> leaderboard = leaderBoardService.getLeaderBoardTournament(0);

        assert(leaderboard.get(0).getValue1() >= leaderboard.get(1).getValue1());
    }

}