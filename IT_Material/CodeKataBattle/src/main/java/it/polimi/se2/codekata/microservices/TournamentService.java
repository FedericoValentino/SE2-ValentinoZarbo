package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.DBMS.DBMSBattleEntry;
import it.polimi.se2.codekata.DBMS.DBMSTournamentEntry;
import it.polimi.se2.codekata.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.GeneralStuff.BattlesElement;
import it.polimi.se2.codekata.GeneralStuff.TournamentsElement;
import it.polimi.se2.codekata.GeneralStuff.UserType;
import it.polimi.se2.codekata.topics.InvitationsTournamentTopic;
import it.polimi.se2.codekata.topics.TournamentTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TournamentService
{
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DBMSApplication DB;



    public int createTournament(int UserId, String TournamentName)
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

        return tID;
    }

    public void addCollaborator(int UserId, String usernameCollab, int tID)
    {
        int CollaboratorID=DB.getUserByName(usernameCollab).getUserID();
        if(DB.getUserInfo(UserId).userType == UserType.EDUCATOR && DB.getTournamentInfo(tID).creatorID == UserId && DB.getUserInfo(CollaboratorID).userType == UserType.EDUCATOR)
        {
            DB.grantBattleCreation(tID, CollaboratorID);
            publisher.publishEvent(new InvitationsTournamentTopic(CollaboratorID, tID));
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
    public ArrayList<BattlesElement> getTournamentsBattles(int UserId, int TournamentID)
    {
        ArrayList<BattlesElement> battleList = new ArrayList<BattlesElement>();
        DBMSTournamentEntry tournamentEntry = DB.getTournamentInfo(TournamentID);

        for(int bID : tournamentEntry.Battles)
        {
            DBMSBattleEntry battleEntry = DB.getBattleInfo(bID);
            battleList.add(new BattlesElement(bID, battleEntry.BattleName, DB.getUserInfo(UserId).UserBattles.contains(bID)));
        }

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
            boolean involved;
            if(userEntry.userType.equals(UserType.EDUCATOR))
                involved=tournamentEntry.collaborators.contains(UserId);
            else involved=tournamentEntry.userID.contains(UserId);
            tList.add(new TournamentsElement(idt, tournamentEntry.TournamentName, involved));
        }

        return tList;
    }


    private void publishTournamentEvent(int tID, Boolean status)
    {
        publisher.publishEvent(new TournamentTopic(tID, status));
    }
}
