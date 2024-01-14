package it.polimi.se2.codekata.codekatabattle.topics;

public class BattleTopic
{
    private int battleID;
    private int BattleStatus;

    public BattleTopic(int battleID, int battleStatus) {
        this.battleID = battleID;
        BattleStatus = battleStatus;
    }

    public int getBattleID() {
        return battleID;
    }

    public int getBattleStatus() {
        return BattleStatus;
    }
}
