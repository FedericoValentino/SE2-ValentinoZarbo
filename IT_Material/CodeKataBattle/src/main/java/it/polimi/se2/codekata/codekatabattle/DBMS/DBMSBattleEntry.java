package it.polimi.se2.codekata.codekatabattle.DBMS;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattleStatus;
import org.javatuples.Pair;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DBMSBattleEntry
{
    public int tID;
    public int bID;
    public String BattleName;
    public Pair<Integer, Integer> groupRule;

    public String Assignment;

    public Pair<Date, Date> deadline;

    public ArrayList<Group> participatingGroups;

    public Map<Integer, Integer> scores;

    public BattleStatus status;


    public DBMSBattleEntry(int tID, int bID, String battleName, Pair<Integer, Integer> groupRule, String assignment, Pair<Date, Date> deadline)
    {
        this.tID = tID;
        this.bID = bID;
        this.BattleName = battleName;
        this.groupRule = groupRule;
        this.Assignment = assignment;
        this.deadline = deadline;
        this.participatingGroups = new ArrayList<>();
    }

    public int gettID() {
        return tID;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }

    public String getBattleName() {
        return BattleName;
    }

    public void setBattleName(String battleName) {
        BattleName = battleName;
    }

    public Pair<Integer, Integer> getGroupRule() {
        return groupRule;
    }

    public void setGroupRule(Pair<Integer, Integer> groupRule) {
        this.groupRule = groupRule;
    }

    public String getAssignment() {
        return Assignment;
    }

    public void setAssignment(String assignment) {
        Assignment = assignment;
    }

    public Pair<Date, Date> getDeadline() {
        return deadline;
    }

    public void setDeadline(Pair<Date, Date> deadline) {
        this.deadline = deadline;
    }

    public ArrayList<Group> getParticipatingGroups() {
        return participatingGroups;
    }

    public void setParticipatingGroups(ArrayList<Group> participatingGroups) {
        this.participatingGroups = participatingGroups;
    }

    public Map<Integer, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<Integer, Integer> scores) {
        this.scores = scores;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }
}
