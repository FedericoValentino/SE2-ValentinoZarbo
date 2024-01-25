package it.polimi.se2.codekata.codekatabattle.DBMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBMSTournamentEntry
{
    public int tID;

    public Boolean status;

    public int creatorID;
    public ArrayList<Integer> collaborators;
    public ArrayList<Integer> userID;

    public Map<Integer, Integer> scores;

    public ArrayList<Integer> Battles;


    public DBMSTournamentEntry(int tID, int creatorID) {
        this.tID = tID;
        this.creatorID = creatorID;
        this.collaborators = new ArrayList<>();
        this.collaborators.add(creatorID);
        this.userID = new ArrayList<>();
        this.status = true;
        this.scores = new HashMap<>();
        this.Battles = new ArrayList<>();
    }
}
