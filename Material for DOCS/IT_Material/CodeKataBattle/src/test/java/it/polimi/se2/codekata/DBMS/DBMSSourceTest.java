package it.polimi.se2.codekata.DBMS;

import it.polimi.se2.codekata.GeneralStuff.Group;
import it.polimi.se2.codekata.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DBMSSourceTest
{

    @Autowired
    DBMSApplication appDB;

    @Autowired
    DBMSSource DBS;


    @Test
    void addBattleTestCases()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);

        DBS.addBattleTestCases(bID, new ArrayList<>(Arrays.asList("Ciao", "Ciao")));

        assert(DBS.getTestCases().get(bID).size() == 2);
    }

    @Test
    void saveGroupSources()
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

        DBS.saveGroupSource(0, bID, "CIAO");

        assert(DBS.getSources(bID).size() == 1);
    }
}