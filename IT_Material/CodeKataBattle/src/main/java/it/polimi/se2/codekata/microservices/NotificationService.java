package it.polimi.se2.codekata.microservices;
import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.topics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService
{

    @Autowired
    DBMSApplication DB;

    private void sendEmail(String text, int uID)
    {
        System.out.println("Sent email to " + DB.getUserInfo(uID).email + " containing message:\n" + text);
    }
    @EventListener
    public void tournamentTopicListener(TournamentTopic event)
    {
        ArrayList<Integer> subscribedStudents = DB.getSubscribedStudents(event.getTournamentID());
        for(int uID : subscribedStudents)
        {
            if (event.getTournamentStatus()) {
                sendEmail("Tournament " + event.getTournamentID() + " is starting!", uID);
            } else {
                sendEmail("Tournament " + event.getTournamentID() + " has ended!", uID);
            }
        }
    }


    @EventListener
    public void BattleTopicListener(BattleTopic event)
    {
        ArrayList<Integer> subscribedStudents = DB.getSubscribedStudents(DB.getBattleInfo(event.getBattleID()).gettID());
        for(int uID : subscribedStudents)
        {
            switch(event.getBattleStatus())
            {
                case REGISTRATION_PHASE:
                    sendEmail("Battle "+event.getBattleID()+" opened the registration!", uID);
                    break;
                case BATTLE_PHASE:
                    sendEmail("Battle "+event.getBattleID()+" has started!", uID);
                    break;
                case CONSOLIDATION_PHASE:
                    sendEmail("Battle "+event.getBattleID()+" is in consolidation!", uID);
                    break;
                case FINISHED:
                    sendEmail("Battle "+event.getBattleID()+" has ended!", uID);
                    break;
            }
        }

    }

    @EventListener
    public void InvitationsBattleListener(InvitationsBattleTopic event)
    {
        for(int uID : event.getUserID())
        {
            sendEmail("User " + Integer.toString(uID) + " has been invited to group " + Integer.toString(event.getGroupID()), uID);
        }
    }

    @EventListener
    public void InvitationsTournamentListener(InvitationsTournamentTopic event)
    {
        sendEmail("User " + Integer.toString(event.getUserID()) + " has been invited to moderate tournamet" + Integer.toString(event.getTournamentID()), event.getUserID());;
    }

    @EventListener
    public void RepoLinksListener(RepoLinksTopic event)
    {
        ArrayList<Integer> subscribedStudents = DB.getSubscribedStudents(DB.getBattleInfo(event.getBattleID()).gettID());
        for(int uID : subscribedStudents)
        {
            sendEmail("Clone this repository and setup workflow to start the battle!\n" + event.getLinkToRepository(), uID);
        }
    }


}
