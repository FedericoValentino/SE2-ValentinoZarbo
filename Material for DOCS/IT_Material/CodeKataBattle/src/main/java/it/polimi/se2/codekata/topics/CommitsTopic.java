package it.polimi.se2.codekata.topics;

public class CommitsTopic
{
    private int groupID;
    private int battleID;

    public CommitsTopic(int groupID, int battleID) {
        this.groupID = groupID;
        this.battleID = battleID;
    }

    public int getBattleID() {
        return battleID;
    }

    public int getGroupID() {
        return groupID;
    }
}
