package it.polimi.se2.codekata.codekatabattle.topics;

public class GenericTopic
{
    int gID;
    int bID;

    String sources;

    public GenericTopic(int gID, int bID, String sources) {
        this.gID = gID;
        this.bID = bID;
        this.sources = sources;
    }

    public int getgID() {
        return gID;
    }

    public int getbID() {
        return bID;
    }

    public String getSources() {
        return sources;
    }
}
