function fillBattleList(battles, blistElement){
    battles.forEach(b => {
        blistElement.innerHTML+= '<a href="'+goToBattleUrl(b.bid)+ '" class="blist-item"> '+b.bname+'</a';
    });
}
function goToBattleUrl(bid){
    return "/BattlePageServlet?"+bid;
}
function fillLeaderBoard(leaderB, lbElment){
    let team="";
    let score="";

    leaderB.forEach(entry => {
        team="<div class='teamName'>"+entry.uname+"</div>"
        score="<div class='TeamScore'>"+entry.teamScore+"</div>"
        lbElment.innerHTML+= "<div class='lb-entry'>"+team+score+"</div>";
    });

}