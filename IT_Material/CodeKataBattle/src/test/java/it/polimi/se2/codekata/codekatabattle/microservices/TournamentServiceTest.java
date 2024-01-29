package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSUserEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TournamentServiceTest
{
    @Autowired
    TournamentService TS;

    @Autowired
    DBMSApplication appDB;
    @Test
    void addTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");

        TS.createTournament(0, "Ciao");
        TS.createTournament(1, "Ciao");
        TS.createTournament(2, "Ciao");


        assert(appDB.getTournamentInfo(0).collaborators.contains(0));
        assert(!appDB.getTournamentInfo(0).collaborators.contains(1));
        assert(appDB.getTournamentInfo(0).creatorID == 0);
    }

    @Test
    void addCollaborator()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        TS.createTournament(0, "Ciao");

        TS.addCollaborator(0, 1, 0);
        TS.addCollaborator(0, 2, 0);

        DBMSTournamentEntry t = appDB.getTournamentInfo(0);

        assert (t.collaborators.size() == 2);
        assert (t.collaborators.contains(1));
    }

    @Test
    void closeTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");

        TS.createTournament(0, "Ciao");

        TS.addCollaborator(0, 1, 0);

        TS.closeTournament(1, 0);

        DBMSTournamentEntry t = appDB.getTournamentInfo(0);

        assert (t.status);

        TS.closeTournament(0, 0);

        t = appDB.getTournamentInfo(0);

        assert (!t.status);


    }

    @Test
    void subscribeToTournament()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        TS.createTournament(0, "Ciao");

        TS.subscribeTournament(2, 0);

        DBMSTournamentEntry t = appDB.getTournamentInfo(0);
        DBMSUserEntry u = appDB.getUserInfo(2);

        assert (u.UserTournaments.contains(0));
        assert (t.userID.contains(2));
    }

}