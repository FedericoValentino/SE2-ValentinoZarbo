function loadTourn(tournFullList){
    let yourT=[];
    let allT=[];
    let i=0;
    let j=0;
    
    tournFullList.tournaments.forEach(t => {
        allT[i]=t;
        if(t.isInvolved==1){
            yourT[j]=t;
            j++;
        }
        i++;                
    });
    setTList(allT,document.getElementById("pubTo"))
    setTList(yourT, document.getElementById("persTo"))
}

function setTList(list, listElement){
    list.forEach(to => {
        listElement.innerHTML+=tournamentListItem(to);
    });

}
function tournamentListItem( to){
    return '<a class="tlist-item" id="'+to.tid+'-item "href='+goToTournament(to.tid)+'>'+to.tname+'</a>'

}
function goToTournament(tid){
    //calls servlet for new page( tournament page)
    return "servlet call for t"+tid
}


//---------------------------------------------------------battlepage
function fillBattleList(battles, blistElement){
    battles.forEach(b => {
        blistElement.innerHTML+= "<a href='"+goToBattleUrl(b)+"'; class='blist-item'> "+b.bname+"</a";
    });
}
function goToBattleUrl(battle){
    return "servlet to battlePage/"+battle.bid;
}
function fillLeaderBoard(leaderB, lbElment){
    leaderB.forEach(entry => {
        lbElment.innerHTML+= "";
    });

}