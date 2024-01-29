package it.polimi.se2.codekata.codekatabattle.DBMS;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class DBMSSource
{
    private ArrayList<DBMSBattleSourceEntry> Sources;

    private Map<Integer, ArrayList<String>> testCases;

    public void addBattleTestCases(int idB, ArrayList<String> testCases)
    {
        this.testCases.put(idB, testCases);
    }

    public void saveGroupSource(int groupID, int bID, String SourceCode)
    {
        Sources.add(new DBMSBattleSourceEntry(bID, groupID, SourceCode));
    }

}
