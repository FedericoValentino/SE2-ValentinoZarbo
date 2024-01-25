package it.polimi.se2.codekata.codekatabattle.DBMS;

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
    public ArrayList<String> testcases;

    public ArrayList<Group> participatingGroups;

    public Map<Integer, Integer> scores;


    public DBMSBattleEntry(int tID, int bID, String battleName, Pair<Integer, Integer> groupRule, String assignment, Pair<Date, Date> deadline, ArrayList<String> testcases)
    {
        this.tID = tID;
        this.bID = bID;
        this.BattleName = battleName;
        this.groupRule = groupRule;
        this.Assignment = assignment;
        this.deadline = deadline;
        this.testcases = testcases;
        this.participatingGroups = new ArrayList<>();
    }
}
