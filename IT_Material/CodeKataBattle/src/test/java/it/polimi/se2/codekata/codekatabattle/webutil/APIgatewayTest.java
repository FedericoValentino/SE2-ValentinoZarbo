package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.microservices.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

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

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void register() throws URISyntaxException
    {
    }

    @Test
    public void login()
    {
    }
}