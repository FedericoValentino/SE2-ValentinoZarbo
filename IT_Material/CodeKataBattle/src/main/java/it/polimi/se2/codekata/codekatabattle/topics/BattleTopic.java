package it.polimi.se2.codekata.codekatabattle.topics;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattleStatus;

public class BattleTopic
{
    private int battleID;
    private BattleStatus BattleStatus;

    public BattleTopic(int battleID, BattleStatus battleStatus) {
        this.battleID = battleID;
        BattleStatus = battleStatus;
    }

    public int getBattleID() {
        return battleID;
    }

    public BattleStatus getBattleStatus() {
        return BattleStatus;
    }
}
