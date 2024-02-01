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
        int edu = appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        int edu1 = appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        int idT = TS.createTournament(edu, "Ciao");

        TS.addCollaborator(edu, edu1, idT);
        TS.addCollaborator(edu, stud, idT);

        DBMSTournamentEntry t = appDB.getTournamentInfo(idT);

        assert (t.collaborators.size() == 2);
        assert (t.collaborators.contains(edu1));
    }

    @Test
    void closeTournament()
    {
        int edu = appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        int edu2 = appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");

        int tID = TS.createTournament(edu, "Ciao");

        TS.addCollaborator(edu, edu2, tID);

        TS.closeTournament(edu2, tID);

        DBMSTournamentEntry t = appDB.getTournamentInfo(tID);

        assert (t.status);

        TS.closeTournament(edu, tID);

        t = appDB.getTournamentInfo(tID);

        assert (!t.status);


    }

    @Test
    void subscribeToTournament()
    {
        int edu = appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        int edu2 = appDB.addEducator("Feder2", "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent("Feder3", "valefeder34@gmail.com", "pwd");
        int tID = TS.createTournament(edu, "Ciao");

        TS.subscribeTournament(stud, tID);
        TS.subscribeTournament(edu2, tID);

        DBMSTournamentEntry t = appDB.getTournamentInfo(tID);
        DBMSUserEntry u = appDB.getUserInfo(stud);

        assert (u.UserTournaments.contains(tID));
        assert (t.userID.contains(stud));
        assert (!t.userID.contains(edu2));
    }

}