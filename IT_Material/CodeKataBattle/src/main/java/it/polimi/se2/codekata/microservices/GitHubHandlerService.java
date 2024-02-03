package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSSource;
import it.polimi.se2.codekata.topics.CommitsTopic;
import it.polimi.se2.codekata.topics.GithubPingTopic;
import it.polimi.se2.codekata.topics.RepoLinksTopic;
import it.polimi.se2.codekata.topics.RepositoryTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GitHubHandlerService
{
    @Autowired
    private ApplicationEventPublisher publisher;



    @Autowired
    private DBMSSource DBS;


    @EventListener
    private void githubPingListener(GithubPingTopic event)
    {
        String GitHubSources = event.getSources();//theoretical download of sources from github
        int gID = event.getgID(); //theoretical search for githubRepo - gID in DB
        int bID = event.getbID(); //theoretical search for githubRepo - bID in DB


        DBS.saveGroupSource(gID, bID, GitHubSources);

        publisher.publishEvent(new CommitsTopic(gID, bID));
    }

    @EventListener
    private void RepositoryListener(RepositoryTopic event)
    {
        //contact github and create repo, get back a link
        String link = "https://github.com/"+DBS.getLinksToBattles().size();
        DBS.getLinksToBattles().put(event.getBattleID(), link);

        publisher.publishEvent(new RepoLinksTopic(event.getBattleID(), link));
    }


}
