package it.polimi.se2.codekata.codekatabattle.microservices;

import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSApplication;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest
{

    @Autowired
    UserService US;

    @Autowired
    DBMSApplication appDB;



    @Test
    void registerUserTest()
    {
        //Test if the method registerUser returns the correct id of the user
        //Test if the method registerUser returns -1 if the username is null
        //Test if the method registerUser returns -1 if the email is null
        //Test if the method registerUser returns -1 if the password is null
        //Test if the method registerUser returns -1 if the type is null

        //Test if the method registerUser returns the correct id of the user
        int id = US.registerUser("test", "test", "test", UserType.STUDENT);
        assertTrue(id >= 0);


        //Test if the method registerUser returns -1 if the username is null
        int id4 = US.registerUser(null, "test", "test", UserType.STUDENT);
        assertEquals(-1, id4);

        //Test if the method registerUser returns -1 if the email is null
        int id5 = US.registerUser("test", null, "test", UserType.STUDENT);
        assertEquals(-1, id5);

        //Test if the method registerUser returns -1 if the password is null
        int id6 = US.registerUser("test", "test", null, UserType.STUDENT);
        assertEquals(-1, id6);

        //Test if the method registerUser returns -1 if the type is null
        int id7 = US.registerUser("test", "test", "test", null);
        assertEquals(-1, id7);
    }


    @Test
    void loginTest()
    {

        int id = US.registerUser("test", "test", "test", UserType.STUDENT);
        int id2 = US.login("test", "test");
        assertEquals(id, id2);

    }

}