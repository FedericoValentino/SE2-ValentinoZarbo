package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.TournamentsElement;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
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



    public void createTournament(int UserId, String TournamentName)
    {
        int tID = -1;
        DBMSUserEntry user = DB.getUserInfo(UserId);
        if(user != null)
        {
            if(DB.getUserInfo(UserId).userType == UserType.EDUCATOR)
            {
                tID = DB.addTournament(UserId, TournamentName);
            }
        }


        if(tID != -1)
        {
            publishTournamentEvent(tID, true);
        }
    }

    public void addCollaborator(int UserId, int CollaboratorID, int tID)
    {
        if(DB.getUserInfo(UserId).userType == UserType.EDUCATOR && DB.getTournamentInfo(tID).creatorID == UserId && DB.getUserInfo(CollaboratorID).userType == UserType.EDUCATOR)
        {
            DB.grantBattleCreation(tID, CollaboratorID);
        }
    }
    public void closeTournament(int UserId, int TournamentID)
    {
        if(DB.getTournamentInfo(TournamentID).creatorID == UserId && DB.getTournamentInfo(TournamentID).status)
        {
            DB.closeTournament(TournamentID);
        }
    }

    public void subscribeTournament(int UserId, int TournamentID)
    {
        if(DB.getUserInfo(UserId).userType == UserType.STUDENT && DB.getTournamentInfo(TournamentID).status)
        {
            DB.subscribeToTournament(TournamentID, UserId);
        }

    }
    public ArrayList<Integer> getTournamentsBattles(String UserId, String TournamentID)
    {
        ArrayList<Integer> battleList = new ArrayList<Integer>();
    //todo rest return : list of battles(id,name,is_user_involved[0/1])
        return battleList;
    }

    public ArrayList<TournamentsElement> getCurrentTournament(int UserId)
    {
        DBMSUserEntry userEntry = DB.getUserInfo(UserId);
        ArrayList<TournamentsElement> tList = new ArrayList<TournamentsElement>();

        ArrayList<Integer> currentT = DB.getCurrentTournament();

        for(int idt : currentT)
        {
            DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(idt);
            tList.add(new TournamentsElement(idt, tournamentEntry.TournamentName, userEntry.UserTournaments.contains(idt)));
        }

        return tList;
    }


    private void publishTournamentEvent(int tID, Boolean status)
    {
        publisher.publishEvent(new TournamentTopic(tID, status));
    }
}
