package it.polimi.se2.codekata.codekatabattle.DBMS;

import java.util.ArrayList;
import java.util.Map;

public class DBMSTournamentEntry
{
    public int tID;

    public int creatorID;
    public ArrayList<Integer> collaborators;
    public ArrayList<Integer> userID;

    public Map<Integer, Integer> scores;


    public DBMSTournamentEntry(int tID, int creatorID) {
        this.tID = tID;
        this.creatorID = creatorID;
        this.collaborators = new ArrayList<>();
        this.userID = new ArrayList<>();
    }
}
