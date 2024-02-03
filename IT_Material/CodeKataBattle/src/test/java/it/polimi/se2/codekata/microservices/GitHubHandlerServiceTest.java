package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.GeneralStuff.Group;
import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.DBMS.DBMSSource;
import it.polimi.se2.codekata.Utility;
import it.polimi.se2.codekata.topics.GithubPingTopic;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class GitHubHandlerServiceTest
{
    @Autowired
    ApplicationEventPublisher publisher;

    @Autowired
    DBMSApplication appDB;

    @Autowired
    DBMSSource DBS;

    @Autowired
    BattleService BS;

    @Autowired
    TournamentService TS;

    @Autowired
    GitHubHandlerService GHS;

    @Test
    void gitHubPingListener()
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

        publisher.publishEvent(new GithubPingTopic(0, bID, "Test Source"));

        assert(DBS.getSources(bID).get(0).idB == bID);
        assert(DBS.getSources(bID).get(0).groupID == 0);
        assert(DBS.getSources(bID).get(0).SourceCode.equals("Test Source"));
        assert (appDB.getBattleInfo(bID).scores.size() == 1);
    }

    @Test
    void RepositoryListener()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");

        int tID = TS.createTournament(educatorID, "Test");
        int bID = BS.createBattle(tID, educatorID, "TestB", new Pair<>(1, 4), "Assignment", new Pair<>(new Date(), new Date()), new ArrayList<>(Arrays.asList("ciao")));


        assert(DBS.getLinksToBattles().get(bID).equals("https://github.com/"+(DBS.getLinksToBattles().size()-1)));
    }
}