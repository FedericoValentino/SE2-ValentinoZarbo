package it.polimi.se2.codekata.codekatabattle;

public class Utility
{
    static int x = 0;

    public static String getRandomUsername()
    {
        x++;
        return "Feder"+x;
    }
}
