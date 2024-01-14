package it.polimi.se2.codekata.codekatabattle.topics;


public class TournamentTopic
{
    private int tournamentID;
    private Boolean tournamentStatus;

    public TournamentTopic(int tournamentID, Boolean tournamentStatus)
    {
        this.tournamentID = tournamentID;
        this.tournamentStatus = tournamentStatus;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public Boolean getTournamentStatus() {
        return tournamentStatus;
    }
}
