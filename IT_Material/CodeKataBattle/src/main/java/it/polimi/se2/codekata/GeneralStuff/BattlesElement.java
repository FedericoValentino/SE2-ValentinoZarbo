package it.polimi.se2.codekata.GeneralStuff;

public class BattlesElement
{
    public int bid;
    public String bname;
    public Boolean isInvolved;

    public BattlesElement(int bid, String bname, Boolean isInvolved) {
        this.bid = bid;
        this.bname = bname;
        this.isInvolved = isInvolved;
    }

    public int getBid() {
        return bid;
    }

    public String getBname() {
        return bname;
    }

    public Boolean getInvolved() {
        return isInvolved;
    }
}
