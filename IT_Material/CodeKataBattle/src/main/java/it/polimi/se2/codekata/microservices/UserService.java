package it.polimi.se2.codekata.microservices;

import it.polimi.se2.codekata.DBMS.DBMSApplication;
import it.polimi.se2.codekata.DBMS.DBMSUserEntry;
import it.polimi.se2.codekata.GeneralStuff.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService
{
    @Autowired
    DBMSApplication DB;

    public int registerUser(String username, String mail, String password, UserType type)
    {
        int finalID = -1;


        if(username != null && mail != null && password != null && type != null)
        {
            for(int uID : DB.getAllSignedUsers())
            {
                if(username.equals(DB.getUserInfo(uID).UserName))
                {
                    return finalID;
                }
            }
            finalID = switch (type) {
                case STUDENT -> DB.addStudent(username, mail, password);
                case EDUCATOR -> DB.addEducator(username, mail, password);
            };
        }
        return finalID;
    }

    public int login(String user, String password)
    {
        ArrayList<Integer> allStudents = DB.getAllSignedUsers();

        for(int studentID : allStudents)
        {
            if(DB.getUserInfo(studentID).UserName.equals(user) && DB.getUserInfo(studentID).password.equals(password))
            {
                return studentID;
            }
        }

        return -1;
    }
    public boolean isEducator(int userID)
    {
        DBMSUserEntry user = DB.getUserInfo(userID);
        if(user != null)
        {
            return user.userType.equals(UserType.EDUCATOR);
        }
        else
        {
            return false;
        }
    }

}
