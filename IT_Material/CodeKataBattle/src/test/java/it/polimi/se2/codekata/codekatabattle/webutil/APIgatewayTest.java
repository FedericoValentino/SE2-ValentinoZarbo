package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.microservices.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIgatewayTest
{
    @Autowired
    APIgateway AG;

    @Autowired
    DBMSApplication appDB;

    @Autowired
    TournamentService TS;


    @Test
    void getTournaments()
    {
        appDB.addEducator("Feder", "valefeder34@gmail.com", "pwd");
        appDB.addStudent("Feder2", "valefeder34@gmail.com", "pwd");

        TS.createTournament(0, "Ciao");
        TS.createTournament(0, "Bao");
        TS.createTournament(0, "Cao");
        TS.createTournament(0, "XIAO");

        TS.subscribeTournament(1, 0);
        TS.subscribeTournament(1, 2);

        System.out.println(AG.getTournaments(1));

    }
}