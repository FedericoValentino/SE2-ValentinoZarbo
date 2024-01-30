package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.TournamentsElement;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.microservices.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


//TODO Ritornare codice stato operazione per login e registrazione + codice utente
//TODO Fare getTournaments
//TODO Cercare modo per testare

@RestController
public class APIgateway
{
    @Autowired
    TournamentService TS;

    @Autowired
    BattleService BS;

    @Autowired
    UserService US;

    @Autowired
    LeaderBoardService LBS;

    @Autowired
    ManualEvalService MES;



    @PostMapping(value="/user/login")
    @ResponseBody
    public String login(@RequestParam("user")String Username, @RequestParam("pwd")String Password)
    {
        int ID = US.login(Username, Password);
        if(ID >= 0)
            return "{\"status\" : 200 , \"uid\" : "+ID+"}";
        else
            return "{\"status\" : \"418\" , \"uid\" : \"" +ID+ "\"}";
    }

    @PostMapping("/user/register")
    public String register(@RequestParam("user")String Username, @RequestParam("pwd")String Password,
                           @RequestParam("email")String Email, @RequestParam("educator")boolean edu)
    {
        UserType type = (edu) ? UserType.EDUCATOR : UserType.STUDENT;
        int ID = US.registerUser(Username, Email, Password, type);
        if(ID >= 0)
            return "{'status' : '200' , 'uid' : '"+ID+"'}";
        else
            return "{'status' : '418' , 'uid' : '"+ID+"'}";
    }


    @GetMapping("/tournament")
    public String getTournaments(@RequestParam("uid") int UserID)
    {
        //return "{'pino':'insegne'}";
       ArrayList<TournamentsElement> list = TS.getCurrentTournament(UserID);

        JSONArray tournamentlist = new JSONArray();

        for(TournamentsElement element : list)
        {
            JSONObject obj = new JSONObject(element);
            tournamentlist.put(obj);
        }

        return tournamentlist.toString();
    }


}
