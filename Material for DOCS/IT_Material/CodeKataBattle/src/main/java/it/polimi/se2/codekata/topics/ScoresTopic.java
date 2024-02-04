package it.polimi.se2.codekata.topics;


public class ScoresTopic
{
    int groupID;
    int score;

    int bID;

    public ScoresTopic(int groupID, int score, int bID) {
        this.groupID = groupID;
        this.score = score;
        this.bID = bID;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getScore() {
        return score;
    }

    public int getbID() {
        return bID;
    }
}
