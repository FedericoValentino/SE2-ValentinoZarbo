package it.polimi.se2.codekata.topics;
import java.util.ArrayList;

public class InvitationsBattleTopic
{
    private int groupID;

    private ArrayList<Integer> userID;

    public InvitationsBattleTopic(int groupID, ArrayList<Integer> userID) {
        this.groupID = groupID;
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public ArrayList<Integer> getUserID() {
        return userID;
    }
}
