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

    public DBMSUserEntry(int userID, String Username, String email, String password, UserType type)
    {
        this.userID = userID;
        this.UserName = Username;
        this.email = email;
        this.password = password;
        this.userType = type;
        this.UserTournaments = new ArrayList<>();
        this.UserBattles = new ArrayList<>();
        this.Groups = new ArrayList<>();
    }
}
