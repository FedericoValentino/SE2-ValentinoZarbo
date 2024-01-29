function fillBattleList(battles, burl){
    const bListElement=document.getElementById("leaderboard");
    battles.forEach(b => {
        blistElement.innerHTML+= '<a href="'+burl+'/BattlePageServlet?bid='+b.bid+ '" class="blist-item"> '+b.bname+'</a';
    });
}

function fillLeaderBoard(leaderB,burl){
    let team="";
    let score="";
    const lbElment=document.getElementById("blist");


    leaderB.forEach(entry => {
        team="<div class='lb-Name'>"+entry.uname+"</div>"
        score="<div class='lb-Score'>"+entry.pScore+"</div>"
        lbElment.innerHTML+= "<div class='lb-entry'>"+team+score+"</div>";
    });

}