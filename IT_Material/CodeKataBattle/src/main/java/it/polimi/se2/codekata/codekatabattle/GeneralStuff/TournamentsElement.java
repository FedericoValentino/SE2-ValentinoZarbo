package it.polimi.se2.codekata.codekatabattle.GeneralStuff;

public class TournamentsElement
{
    public int tid;
    public String tname;
    public Boolean isInvolved;

    public TournamentsElement(int tid, String tname, Boolean isInvolved) {
        this.tid = tid;
        this.tname = tname;
        this.isInvolved = isInvolved;
    }

    public int getTid() {
        return tid;
    }

    public String getTname() {
        return tname;
    }

    public Boolean getInvolved() {
        return isInvolved;
    }
}
