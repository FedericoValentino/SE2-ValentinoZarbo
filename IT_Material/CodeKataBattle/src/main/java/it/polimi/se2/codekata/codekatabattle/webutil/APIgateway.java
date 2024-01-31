package it.polimi.se2.codekata.codekatabattle.webutil;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import it.polimi.se2.codekata.codekatabattle.DBMS.DBMSBattleSourceEntry;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.BattlesElement;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.SourcesElement;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.TournamentsElement;
import it.polimi.se2.codekata.codekatabattle.GeneralStuff.UserType;
import it.polimi.se2.codekata.codekatabattle.microservices.*;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


/*
=================================================================================================================================================
   USER API
=================================================================================================================================================
*/
    @PostMapping("/user/login")
    public String login(@RequestParam("user")String Username, @RequestParam("pwd")String Password)
    {
        int ID = US.login(Username, Password);
        JSONObject res=new JSONObject();
        if(ID >= 0)
             res.accumulate("uid",ID+"") ;
        else
             res.accumulate("error","no such user found");
        return res.toString();
    }

    @PostMapping("/user/register")
    public String register(@RequestParam("user")String Username, @RequestParam("pwd")String Password,
                           @RequestParam("email")String Email, @RequestParam("educator")boolean edu)
    {
        UserType type = (edu) ? UserType.EDUCATOR : UserType.STUDENT;
        int ID = US.registerUser(Username, Email, Password, type);
        JSONObject res=new JSONObject();
        if(ID >= 0)
            res.accumulate("uid",ID+"") ;
        else
            res.accumulate("error","no such user found");
        return res.toString();
    }
/*
=================================================================================================================================================
    TOURNAMENT API
=================================================================================================================================================
*/

    @GetMapping("/tournament")
    public String getTournaments(@RequestParam("uid") int UserID)
    {
        //todo doesnt returna true for involved for educators
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

    @PostMapping("/tournament/create_t")
    public void createTournament(@RequestParam("uid") int UserID, @RequestParam("tname") String TournamentName)
    {
        TS.createTournament(UserID, TournamentName);
    }

    @PostMapping("/tournament/{idT}/addCollaborator")
    public void addCollaborator(@PathVariable("idT") int idT, @RequestParam("uid") int UserID, @RequestParam("cid") int collaboratorID)
    {
        TS.addCollaborator(UserID, collaboratorID, idT);//todo reqeust collaborator username instead of id
    }

    @PostMapping("/tournament/{idT}/close")
    public void closeTournament(@PathVariable("idT") int idT, @RequestParam("uid") int UserID)
    {
        TS.closeTournament(UserID, idT);
    }

    @PostMapping("/tournament/{idT}/subscribe")
    public void subscribeTournament(@PathVariable("idT") int idT, @RequestParam("uid") int UserID)
    {
        TS.subscribeTournament(UserID, idT);
    }

    @GetMapping("/tournament/{idT}/battle")
    public String getTournamentBattles(@PathVariable("idT") int idT, @RequestParam("uid") int UserID)
    {
        ArrayList<BattlesElement> battles = TS.getTournamentsBattles(UserID, idT);

        JSONArray Battles = new JSONArray();

        for(BattlesElement element : battles)
        {
            JSONObject obj = new JSONObject(element);
            Battles.put(obj);
        }

        return Battles.toString();
    }

/*
=================================================================================================================================================
    BATTLE API
=================================================================================================================================================
*/
    @PostMapping(value="/tournament/{idT}/battle/create_b",consumes="application/json", produces ="application/json")
    public void createBattle(@RequestBody Map<String,String > input,@PathVariable("idT")int idt)
            /*@RequestParam("uid")int uID,
                             @PathVariable("idT") int idT,
                             @RequestParam("bname")String battleName,
                             @RequestParam("minsize") int minSize,
                             @RequestParam("maxSize")int maxsize,
                             @RequestParam("assignment")String assignment,
                             @RequestParam("subsd")String subscriptionDeadline,
                             @RequestParam("submd")String submissionDeadline)//,
                            // @RequestBody List<String> testCases)*/
    {
        int g1=Integer.parseInt(input.get("minsize"));
        int g2=Integer.parseInt(input.get("maxsize"));
        Pair<Integer,Integer> gl=new Pair<>(g1,g2);
        Pair<Date,Date>dl=new Pair<>(new Date(),new Date());
        ArrayList<String > fakeCase=new ArrayList<>();
        fakeCase.add("boh");
        BS.createBattle(idt,Integer.parseInt(input.get("uid")),input.get("bname"),gl,input.get("assignment"),dl,fakeCase);
        //BS.createBattle(idT, uID, battleName, new Pair<>(minSize, maxsize), assignment, new Pair<>(subscriptionDeadline, submissionDeadline), new ArrayList<>());
    }//todo move post paramaeter to body, eventually create class for every type where usefull

    @GetMapping("tournament/{idT}/battle/{idB}/status")
    public String getBattleStatus(@RequestParam("uid")int uID, @PathVariable("idT") int idT, @PathVariable("idB") int idB)
    {
        return  "{\"statusTxt\":\"" +BS.getBattleStatus(uID, idB).toString()+ "\"}";
    }

    @GetMapping("/tournament/{idT}/battle/{idB}/rules")
    public String getGroupRules(@RequestParam("uid")int uID, @PathVariable("idT") int idT, @PathVariable("idB") int idB)
    {
        Pair<Integer, Integer> rules = BS.getGroupRules(uID, idB);

        return new JSONObject(rules).toString();
    }

    @GetMapping("/tournament/{idT}/battle/{idB}/assignment")
    public String getAssignement(@RequestParam("uid")int uID, @PathVariable("idT") int idT, @PathVariable("idB") int idB)
    {
        String assignmentText = BS.getAssignmentText(uID, idB);

        return "{\"assignment\":\"" +assignmentText+ "\"}";
    }

    @GetMapping("/tournament/{idT}/battle/{idB}/deadlines")
    public String getDeadlines(@RequestParam("uid")int uID, @PathVariable("idT") int idT, @PathVariable("idB") int idB)
    {
        Pair<Date, Date> rules = BS.getDeadlines(uID, idB);

        return new JSONObject(rules).toString();
    }

    @PostMapping("/tournament/{idT}/battle/{idB}/join")
    public void joinBattle(@RequestParam("uid")int uID, @PathVariable("idT") int idT, @PathVariable("idB") int idB,
                             @RequestParam(required = false) List<Integer> members)
    {
        BS.joinBattle(uID, idT, idB, new ArrayList<>(members));
    }

/*
=================================================================================================================================================
    LEADERBOARD API
=================================================================================================================================================
*/
    @GetMapping("/tournament/{idT}/lbt")
    public String getLeaderBoardTournament(@PathVariable("idT") int idT)
    {
        JSONObject leaderboard = new JSONObject(LBS.getLeaderBoardTournament(idT));

        return leaderboard.toString();
    }

    @GetMapping("/tournament/{idT}/battle/{idB}/lbb")
    public String getLeaderBoardBattle(@PathVariable("idT") int idT, @PathVariable("idB") int idB)
    {
        JSONObject leaderboard = new JSONObject(LBS.getLeadBoardBattle(idB));

        return leaderboard.toString();
    }

/*
=================================================================================================================================================
    MANUALEVALUATION API
=================================================================================================================================================
*/

    @GetMapping("/tournament/{idT}/battle/{idB}/evalSource")
    public String getSourcesForEval(@PathVariable("idT") int idT, @PathVariable("idB") int idB, @RequestParam("uid")int uID)
    {
        ArrayList<DBMSBattleSourceEntry> sources = MES.getSourcesForEval(uID, idB);

        JSONArray sourcesJson = new JSONArray();

        for(DBMSBattleSourceEntry entry : sources)
        {
            JSONObject obj = new JSONObject(new SourcesElement(entry.groupID, entry.SourceCode));
            sourcesJson.put(obj);
        }
        return sourcesJson.toString();
    }

    @PostMapping("/tournament/{idT}/battle/{idB}/evalSource/score")
    public void addManualScore(@PathVariable("idT") int idT, @PathVariable("idB") int idB,
                               @RequestParam("uid")int uID,
                               @RequestParam("gID")int gID, @RequestParam("score")int score)
    {
        MES.addManualScore(uID, score, gID, idB);
    }




}
