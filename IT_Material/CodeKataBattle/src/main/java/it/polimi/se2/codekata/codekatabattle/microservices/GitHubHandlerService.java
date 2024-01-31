package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSSource;
import it.polimi.se2.codekata.codekatabattle.topics.CommitsTopic;
import it.polimi.se2.codekata.codekatabattle.topics.RepoLinksTopic;
import it.polimi.se2.codekata.codekatabattle.topics.RepositoryTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GitHubHandlerService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    private Map<Integer, String> linksToBattles;

    @Autowired
    DBMSSource DBS;

    GitHubHandlerService()
    {
        linksToBattles = new HashMap<>();
    }

    @EventListener
    private void githubPingListener()
    {
        String GitHubSources = "";//theoretical download of sources from github
        int gID = 0; //theoretical search for githubRepo - gID in DB
        int bID = 0; //theoretical search for githubRepo - bID in DB


        DBS.saveGroupSource(gID, bID, GitHubSources);

        publisher.publishEvent(new CommitsTopic(gID, bID));
    }

    @EventListener
    private void RepositoryListener(RepositoryTopic event)
    {
        //contact github and create repo, get back a link
        String link = "https://github.com/"+linksToBattles.size();
        linksToBattles.put(event.getBattleID(), link);

        publisher.publishEvent(new RepoLinksTopic(event.getBattleID(), link));
    }


}
