package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class GitHubHandlerService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    DBMSSource DBS;

}
