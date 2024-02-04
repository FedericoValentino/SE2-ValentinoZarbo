package it.polimi.se2.codekata.DBMS;

import it.polimi.se2.codekata.GeneralStuff.UserType;

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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ArrayList<Integer> getUserTournaments() {
        return UserTournaments;
    }

    public void setUserTournaments(ArrayList<Integer> userTournaments) {
        UserTournaments = userTournaments;
    }

    public ArrayList<Integer> getUserBattles() {
        return UserBattles;
    }

    public void setUserBattles(ArrayList<Integer> userBattles) {
        UserBattles = userBattles;
    }

    public ArrayList<Integer> getGroups() {
        return Groups;
    }

    public void setGroups(ArrayList<Integer> groups) {
        Groups = groups;
    }
}
