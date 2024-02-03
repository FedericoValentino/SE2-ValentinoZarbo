package it.polimi.se2.codekata.DBMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBMSTournamentEntry
{
    public int tID;

    public String TournamentName;

    public Boolean status;

    public int creatorID;
    public ArrayList<Integer> collaborators;
    public ArrayList<Integer> userID;

    public Map<Integer, Integer> scores;

    public ArrayList<Integer> Battles;


    public DBMSTournamentEntry(int tID, int creatorID, String tournamentName) {
        this.tID = tID;
        this.creatorID = creatorID;
        this.TournamentName = tournamentName;
        this.collaborators = new ArrayList<>();
        this.collaborators.add(creatorID);
        this.userID = new ArrayList<>();
        this.status = true;
        this.scores = new HashMap<>();
        this.Battles = new ArrayList<>();
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public void setTournamentName(String tournamentName) {
        TournamentName = tournamentName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public ArrayList<Integer> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<Integer> collaborators) {
        this.collaborators = collaborators;
    }

    public ArrayList<Integer> getUserID() {
        return userID;
    }

    public void setUserID(ArrayList<Integer> userID) {
        this.userID = userID;
    }

    public Map<Integer, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Integer, Integer> scores) {
        this.scores = scores;
    }

    public ArrayList<Integer> getBattles() {
        return Battles;
    }

    public void setBattles(ArrayList<Integer> battles) {
        Battles = battles;
    }
}
