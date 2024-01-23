package it.polimi.se2.codekata.codekatabattle.DBMS;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;

import java.util.ArrayList;

public class DBMSUserEntry
{
    public int userID;

    public String UserName;
    public String email;
    public String password;

    public UserType userType;
    public ArrayList<Integer> UserTournaments;

    public ArrayList<Integer> UserBattles;

    public ArrayList<Integer> Groups;

    public DBMSUserEntry(int userID, String email, String password)
    {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.UserTournaments = new ArrayList<>();
        this.UserBattles = new ArrayList<>();
        this.Groups = new ArrayList<>();
    }
}
