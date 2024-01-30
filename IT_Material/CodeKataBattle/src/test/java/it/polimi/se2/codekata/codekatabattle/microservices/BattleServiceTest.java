package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BattleServiceTest
{
    @Autowired
    BattleService BS;

    @Autowired
    DBMSApplication appDB;

    @Test
    void addBattle()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder1", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(0, "Ciao");

        appDB.grantBattleCreation(0, 1);

        BS.createBattle(0, 0, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));
        BS.createBattle(0, 1, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));
        BS.createBattle(0, 2, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        assert (appDB.getBattlesOfTournament(0).size() == 2);

    }

    @Test
    void joinBattle()
    {
        appDB.addStudent("Feder1", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder0", "valefeder34@gmail.com", "pwd");
        appDB.addTournament(2, "Ciao");
        appDB.subscribeToTournament(0, 0);
        appDB.subscribeToTournament(0, 1);

        int b1 = appDB.addBattle(0, 2, "TEST1", "Ciao Ciao", new Date(), new Date(), 3, 2);

        BS.joinBattle(0, 0, b1, new ArrayList<>(Arrays.asList(0,1)));
        BS.joinBattle(0, 0, b1, new ArrayList<>(Arrays.asList(0,1)));
        BS.joinBattle(1, 0, b1, new ArrayList<>(Arrays.asList(1)));

        assert(appDB.getGroups(b1).size() == 1);

    }

}