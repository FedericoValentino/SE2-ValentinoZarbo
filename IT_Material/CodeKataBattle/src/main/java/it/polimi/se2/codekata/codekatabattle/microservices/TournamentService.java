package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Service
public class TournamentService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DBMSApplication DB;

    public ArrayList<Integer> getCurrentTournament(int UserId, String UserType)
    {
        ArrayList<Integer> tList = new ArrayList<Integer>();
    // TODO rest  json return : "{'tournaments':[{'tid': 'idtorneo','tname': 'nometorneo','isInvolved': '0 o 1',--altri tornei{},{},{}---]}
        return tList;
    }

    public void createTournament(int UserId, int UserType, String TournamentName)
    {


    }

    public void addCollaborator(int UserId, String UserType, int CollaboratorID)
    {

    }
    public void closeTournament(int UserId, String UserType, int TournamentID)
    {

    }

    public void subscribeTournament(int UserId,String UserType, int TournamentID)
    {

    }
    public ArrayList<Integer> getTournamentsBattles(String UserId,String UserType, String TournamentID)
    {
        ArrayList<Integer> battleList = new ArrayList<Integer>();
    //todo rest return : list of battles(id,name,is_user_involved[0/1])
        return battleList;
    }


    private void publishTournamentEvent(int tID, Boolean status)
    {
        publisher.publishEvent(new TournamentTopic(tID, status));
    }
}
