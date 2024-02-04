package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.Utility;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu1 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu2 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int idT = appDB.addTournament(edu, "Ciao");

        appDB.grantBattleCreation(idT, edu1);

        int bID1 = BS.createBattle(idT, edu, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));
        int bID2 = BS.createBattle(idT, edu1, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));
        int bID3 = BS.createBattle(idT, edu2, "TEST1", new Pair<>(3,1), "Ciao Ciao", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("Ciao", "ciao")));

        assert (appDB.getBattlesOfTournament(idT).size() == 2);
        assert (appDB.getUserInfo(edu).UserBattles.contains(bID1));
        assert (appDB.getUserInfo(edu).UserBattles.contains(bID2));
        assert (appDB.getUserInfo(edu1).UserBattles.contains(bID1));
        assert (appDB.getUserInfo(edu1).UserBattles.contains(bID2));
    }

    @Test
    void joinBattle()
    {
        int stud1 = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud2 = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int idT = appDB.addTournament(edu, "Ciao");
        appDB.subscribeToTournament(idT, stud1);
        appDB.subscribeToTournament(idT, stud2);

        int b1 = appDB.addBattle(idT, edu, "TEST1", "Ciao Ciao", new Date(), new Date(), 2, 3);

        BS.joinBattle(stud1, idT, b1, new ArrayList<>(Arrays.asList(appDB.getUserInfo(stud1).UserName,appDB.getUserInfo(stud2).UserName)));
        BS.joinBattle(stud1, idT, b1, new ArrayList<>(Arrays.asList(appDB.getUserInfo(stud1).UserName,appDB.getUserInfo(stud2).UserName)));
        BS.joinBattle(stud2, idT, b1, new ArrayList<>(Arrays.asList(appDB.getUserInfo(stud2).UserName)));

        assert(appDB.getGroups(b1).size() == 1);
        assert(appDB.getBattleInfo(b1).participatingGroups.get(0).getStudentsID().contains(stud1));

    }

}