package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.Utility;
import it.polimi.se2.codekata.codekatabattle.microservices.TournamentService;
import it.polimi.se2.codekata.codekatabattle.topics.CommitsTopic;
import it.polimi.se2.codekata.codekatabattle.topics.GithubPingTopic;
import it.polimi.se2.codekata.codekatabattle.topics.ScoresTopic;
import org.javatuples.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIgatewayTest
{
    @Autowired
    APIgateway AG;

    @Autowired
    DBMSApplication appDB;

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    void register()
    {
        String result = AG.register("testRegisterUser0", "test", "test", false);
        final Pattern pattern = Pattern.compile("\\{\"uid\":\"[0-9]+\"\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        String result2 = AG.register("testRegisterUser0", "test", "test", false);



        assert(matcher.matches());
        assert(result2.equals("{\"error\":\"no such user found\"}"));
    }

    @Test
    void login()
    {
        String resultRegister = AG.register("testRegisterUser2", "test", "test", false);
        final Pattern pattern = Pattern.compile("\\{\"isEdu\":\"(true|false)\",\"uid\":\"[0-9]+\"\\}", Pattern.CASE_INSENSITIVE);
        String resultLogin = AG.login("testRegisterUser2","test");
        final Matcher matcher = pattern.matcher(resultLogin);

        assert(matcher.matches());
    }

    @Test
    void getTournament()
    {
        int stud = appDB.getAllSignedUsers().getFirst();

        String result = AG.getTournaments(stud);

        String regex = "\\[(\\{\"involved\":(true|false),\"tname\":\"([^\"]+)\",\"tid\":(\\d+)\\}(?:,\\{\"involved\":(true|false),\"tname\":\"([^\"]+)\",\"tid\":(\\d+)\\})*?)?\\]";


        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }


    @Test
    void getTournamentBattles()
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

        String result = AG.getTournamentBattles(tID, studentID1);


        final Pattern pattern = Pattern.compile("\\[(\\{\"involved\":(true|false),\"bname\":\"([^\"]+)\",\"bid\":(\\d+)\\}(?:,\\{\"involved\":(true|false),\"bname\":\"([^\"]+)\",\"bid\":(\\d+)\\})*?)?\\]", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getBattleStatus()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);

        String result = AG.getBattleStatus(studentID1, tID, bID);

        final Pattern pattern = Pattern.compile("\\{\"statusTxt\":\"(CANT_SHOW|REGISTRATION_PHASE|BATTLE_PHASE|CONSOLIDATION_PHASE|FINISHED)\"\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getGroupRules()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);
        appDB.addGroup(new Group(List.of(studentID1)), bID);

        String result = AG.getGroupRules(studentID1, tID, bID);

        final Pattern pattern = Pattern.compile("\\{\"minsize\":(-?\\d+),\"maxsize\":(-?\\d+)\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getAssignment()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);
        appDB.addGroup(new Group(List.of(studentID1)), bID);

        String result = AG.getAssignement(studentID1, tID, bID);

        final Pattern pattern = Pattern.compile("\\{\"assignment\":\"([^\"]*)\"\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getDeadlines()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");
        int bID = appDB.addBattle(tID, educatorID, "TestB", "Assignment", new Date(), new Date(), 4, 1);
        appDB.addGroup(new Group(List.of(studentID1)), bID);

        String result = AG.getDeadlines(studentID1, tID, bID);

        String regex = "\\{\"subs\":(\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [A-Za-z]+ \\d{4}),\"subm\":(\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [A-Za-z]+ \\d{4})\\}";


        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getLeaderBoardTournament()
    {
        int educatorID = appDB.addEducator(Utility.getRandomUsername(), "email", "password");
        int studentID1 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");
        int studentID2 = appDB.addStudent(Utility.getRandomUsername(), "email", "password");

        int tID = appDB.addTournament(educatorID, "Test");

        appDB.subscribeToTournament(tID, studentID1);
        appDB.subscribeToTournament(tID, studentID2);

        appDB.setScoresUserTournament(studentID2, tID,  10);
        appDB.setScoresUserTournament(studentID1, tID,  9);


        String result = AG.getLeaderBoardTournament(tID);

        String regex = "\\[\\{\"score\":(\\d+),\"user\":\"([^\"]+)\"\\}(?:,\\{\"score\":(\\d+),\"user\":\"([^\"]+)\"\\})*\\]";



        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }


    @Test
    void getLeaderBoardBattle()
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

        assert(appDB.getBattleInfo(bID).participatingGroups.size() == 3);

        publisher.publishEvent(new ScoresTopic(0, 9, bID));
        publisher.publishEvent(new ScoresTopic(1, 8, bID));
        publisher.publishEvent(new ScoresTopic(2, 10, bID));

        String result = AG.getLeaderBoardBattle(tID, bID);

        String regex = "\\[\\{\"score\":(\\d+),\"group\":(\\d+)\\}(?:,\\{\"score\":(\\d+),\"group\":(\\d+)\\})*\\]";



        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());
    }

    @Test
    void getSourcesForEval()
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

        assert(appDB.getBattleInfo(bID).participatingGroups.size() == 3);

        publisher.publishEvent(new GithubPingTopic(0, bID, "CIAO"));
        publisher.publishEvent(new GithubPingTopic(1, bID, "CIAO"));
        publisher.publishEvent(new GithubPingTopic(2, bID, "CIAO"));


        String result = AG.getSourcesForEval(tID, bID, educatorID);

        String regex = "\\[\\{\"sources\":\"[^\"]+\",\"group\":\\d+\\}(?:,\\{\"sources\":\"[^\"]+\",\"group\":\\d+\\})*\\]";



        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        assert(matcher.matches());

    }






}