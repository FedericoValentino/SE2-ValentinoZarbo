package it.polimi.se2.codekata.codekatabattle.DBMS;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class DBMSSource
{
    private ArrayList<DBMSBattleSourceEntry> Sources;

    private Map<Integer, ArrayList<String>> testCases;

    DBMSSource(){
        this.Sources=new ArrayList<>();
        this.testCases= new HashMap<>();
    }
    public void addBattleTestCases(int idB, ArrayList<String> testCases)
    {
        this.testCases.put(idB, testCases);
    }

    public void saveGroupSource(int groupID, int bID, String SourceCode)
    {
        Sources.add(new DBMSBattleSourceEntry(bID, groupID, SourceCode));
    }

    public ArrayList<DBMSBattleSourceEntry> getSources(int bID)
    {
        ArrayList<DBMSBattleSourceEntry> sources = new ArrayList<>();
        for(DBMSBattleSourceEntry entry : Sources)
        {
             if(entry.idB == bID)
             {
                 sources.add(entry);
             }
        }

        return sources;
    }

    public ArrayList<DBMSBattleSourceEntry> getSources() {
        return Sources;
    }

    public void setSources(ArrayList<DBMSBattleSourceEntry> sources) {
        Sources = sources;
    }

    public Map<Integer, ArrayList<String>> getTestCases() {
        return testCases;
    }

    public void setTestCases(Map<Integer, ArrayList<String>> testCases) {
        this.testCases = testCases;
    }
}
