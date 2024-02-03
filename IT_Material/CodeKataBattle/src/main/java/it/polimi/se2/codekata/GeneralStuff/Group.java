package it.polimi.se2.codekata.GeneralStuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group
{
    ArrayList<Integer> StudentsID;

    public Group(List<Integer> studentsID)
    {
        StudentsID = new ArrayList<>(studentsID);
    }

    public ArrayList<Integer> getStudentsID() {
        return StudentsID;
    }
}
