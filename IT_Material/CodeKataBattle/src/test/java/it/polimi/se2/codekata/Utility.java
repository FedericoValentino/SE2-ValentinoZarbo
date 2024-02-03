package it.polimi.se2.codekata;

public class Utility
{
    static int x = 0;

    public static String getRandomUsername()
    {
        x++;
        return "Feder"+x;
    }
}
