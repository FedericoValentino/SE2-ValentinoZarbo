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
        int edu = appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");

        int tID = TS.createTournament(edu, "Ciao");
        int tID2 = TS.createTournament(stud, "Ciao");
        int tID3 = TS.createTournament(-1, "Ciao");


        assert(tID != -1);
        assert(tID2 == -1);
        assert(tID3 == -1);
        assert(appDB.getTournamentInfo(tID).collaborators.contains(edu));
        assert(!appDB.getTournamentInfo(tID).collaborators.contains(stud));
        assert(appDB.getTournamentInfo(tID).creatorID == edu);
    }

    @Test
    void addCollaborator()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        TS.createTournament(0, "Ciao");

        TS.addCollaborator(0, "Feder2", 0);
        TS.addCollaborator(0, "Feder3", 0);

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

        TS.addCollaborator(0, "Feder2", 0);

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