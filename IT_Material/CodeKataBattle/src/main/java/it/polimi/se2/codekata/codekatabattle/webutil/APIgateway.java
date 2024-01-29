package it.polimi.se2.codekata.codekatabattle.webutil;

import it.polimi.se2.codekata.codekatabattle.GeneralStuff.TournamentsElement;
import it.polimi.se2.codekata.codekatabattle.microservices.TournamentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


//TODO Ritornare codice stato operazione per login e registrazione + codice utente
//TODO Fare getTournaments
//TODO Cercare modo per testare

@RestController
public class APIgateway
{
    @Autowired
    TournamentService TS;


    @GetMapping("/tournament/")
    public String getTournaments(@RequestParam("uid") int UserID)
    {
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
