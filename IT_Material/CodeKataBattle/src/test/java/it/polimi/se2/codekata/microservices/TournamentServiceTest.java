package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");

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
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu1 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = TS.createTournament(edu, "Ciao");

        TS.addCollaborator(edu, appDB.getUserInfo(edu1).UserName, tID);
        TS.addCollaborator(edu, appDB.getUserInfo(stud).UserName, tID);

        DBMSTournamentEntry t = appDB.getTournamentInfo(tID);

        assert (t.collaborators.size() == 2);
        assert (t.collaborators.contains(edu1));
    }

    @Test
    void closeTournament()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu1 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");

        int tID = TS.createTournament(edu, "Ciao");

        TS.addCollaborator(edu, appDB.getUserInfo(edu1).UserName, tID);

        TS.closeTournament(edu1, tID);

        DBMSTournamentEntry t = appDB.getTournamentInfo(tID);

        assert (t.status);

        TS.closeTournament(edu, tID);

        t = appDB.getTournamentInfo(tID);

        assert (!t.status);


    }

    @Test
    void subscribeToTournament()
    {
        int edu = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int edu1 = appDB.addEducator(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int stud = appDB.addStudent(Utility.getRandomUsername(), "valefeder34@gmail.com", "pwd");
        int tID = TS.createTournament(edu, "Ciao");

        TS.subscribeTournament(stud, tID);

        DBMSTournamentEntry t = appDB.getTournamentInfo(tID);
        DBMSUserEntry u = appDB.getUserInfo(stud);

        assert (u.UserTournaments.contains(tID));
        assert (t.userID.contains(stud));
    }

}