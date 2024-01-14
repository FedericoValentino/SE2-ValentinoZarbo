package it.polimi.se2.codekata.codekatabattle.topics;

public class InvitationsTournamentTopic
{
    private int userID;
    private int TournamentID;

    public InvitationsTournamentTopic(int userID, int tournamentID) {
        this.userID = userID;
        TournamentID = tournamentID;
    }

    public int getUserID() {
        return userID;
    }

    public int getTournamentID() {
        return TournamentID;
    }
}
