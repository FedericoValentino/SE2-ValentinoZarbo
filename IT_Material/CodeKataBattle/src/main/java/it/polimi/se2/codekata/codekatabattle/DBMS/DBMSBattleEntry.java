package it.polimi.se2.codekata.codekatabattle.DBMS;

import ch.qos.logback.core.joran.sanity.Pair;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DBMSBattleEntry
{
    public int bID;
    public String BattleName;
    public Pair<Integer, Integer> groupRule;

    public String Assignment;

    public Pair<Date, Date> deadline;
    public ArrayList<String> testcases;

    public ArrayList<Group> participatingGroups;

    public Map<Integer, Integer> scores;


    public DBMSBattleEntry(String battleName, Pair<Integer, Integer> groupRule, String assignment, Pair<Date, Date> deadline, ArrayList<String> testcases)
    {
        this.BattleName = battleName;
        this.groupRule = groupRule;
        this.Assignment = assignment;
        this.deadline = deadline;
        this.testcases = testcases;
        this.participatingGroups = new ArrayList<>();
    }
}
