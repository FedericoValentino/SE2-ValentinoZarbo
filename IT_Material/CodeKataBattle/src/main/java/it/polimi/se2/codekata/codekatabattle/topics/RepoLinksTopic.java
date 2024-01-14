package it.polimi.se2.codekata.codekatabattle.topics;

public class RepoLinksTopic
{
    private int battleID;

    private String LinkToRepository;

    public RepoLinksTopic(int battleID, String linkToRepository) {
        this.battleID = battleID;
        LinkToRepository = linkToRepository;
    }

    public int getBattleID() {
        return battleID;
    }

    public String getLinkToRepository() {
        return LinkToRepository;
    }
}
