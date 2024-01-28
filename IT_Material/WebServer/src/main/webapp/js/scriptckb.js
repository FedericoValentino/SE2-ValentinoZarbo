function loadTourn(tournFullList, baseUrl){
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
    setTList(allT,document.getElementById("pubTo"),baseUrl)
    setTList(yourT, document.getElementById("persTo"),baseUrl)
}

function setTList(list, listElement, baseUrl){
    list.forEach(to => {
        listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item "href='+baseUrl+goToTournament(to.tid)+'>'+to.tname+'</a>';
    });

}
function tournamentListItem( to){
    return '<a class="tlist-item" id="'+to.tid+'-item "href='+goToTournament(to.tid)+'>'+to.tname+'</a>'

}
function goToTournament(tid){
    //calls servlet for new page( tournament page)
    return "/TournamentPageServlet?"+tid;
}


//---------------------------------------------------------
