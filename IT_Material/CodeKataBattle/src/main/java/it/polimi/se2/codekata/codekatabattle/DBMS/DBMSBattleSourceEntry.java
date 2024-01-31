package it.polimi.se2.codekata.codekatabattle.DBMS;

public class DBMSBattleSourceEntry
{
    public int idB;
    public int groupID;
    public String SourceCode;

    public DBMSBattleSourceEntry(int idB, int groupID, String sourceCode)
    {
        this.idB = idB;
        this.groupID = groupID;
        SourceCode = sourceCode;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getSourceCode() {
        return SourceCode;
    }

    public void setSourceCode(String sourceCode) {
        SourceCode = sourceCode;
    }
}
