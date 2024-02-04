function fillBattleList(battles, burl){
    if(battles==null)
        return
    const bListElement=document.getElementById("blist");
    battles.forEach(b => {
        bListElement.innerHTML+= '<a href="'+burl+'/BattlePageServlet?bid='+b.bid+'&isInvolved='+b.involved+'" class="blist-item"> '+b.bname+'</a';
    });
}

function fillLeaderBoard(leaderB,burl){
    if(leaderB==null || Object.values( leaderB).length===0)
        return
    let team="";
    let score="";
    const lbElment=document.getElementById("lbDiv");


    leaderB.forEach(entry => {
        team="<div class='lb-Name'>"+entry.uname+"</div>"
        score="<div class='lb-Score'>"+entry.pScore+"</div>"
        lbElment.innerHTML+= "<div class='lb-entry'>"+team+score+"</div>";
    });

}