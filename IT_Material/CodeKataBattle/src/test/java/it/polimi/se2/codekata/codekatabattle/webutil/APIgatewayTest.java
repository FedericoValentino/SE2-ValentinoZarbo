package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIgatewayTest
{
    @Autowired
    APIgateway AG;

    @Test
    void register()
    {
        String result = AG.register("testRegisterUser", "test", "test", false);
        final Pattern pattern = Pattern.compile("\\{\"uid\":\"[0-9]+\"\\}", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(result);

        String result2 = AG.register("testRegisterUser", "test", "test", false);



        assert(matcher.matches());
        assert(result2.equals("{\"error\":\"no such user found\"}"));
    }

    void login()
    {
    }
}