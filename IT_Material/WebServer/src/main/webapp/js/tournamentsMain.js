function loadTourn(tournFullListJson, baseUrl){
    if(tournFullListJson==null || Object.values(tournFullListJson).length===0)
        return
    let yourT=[];
    let allT=[];
    let i=0;
    let j=0;

    tournFullListJson.forEach(t => {
        allT[i]=t;
        if(t.involved){
            yourT[j]=t;
            j++;
        }
        i++;                
    });
    setTListNotInv(allT,document.getElementById("pubTo"),baseUrl)
    setTListInvolved(yourT, document.getElementById("persTo"),baseUrl)
}

function setTListInvolved(list, listElement, baseUrl){
    list.forEach(to => {
        listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,"true")+'>'+to.tname+'</a>';
    });

}

function setTListNotInv(list, listElement, baseUrl){
    list.forEach(to => {
        listElement.innerHTML+='<p class="tlist-item" id="'+to.tid+'-item " >'+to.tname+'</p>';
    });

}
function goToTournament(tid, involved){
    //calls servlet for new page( tournament page)
    return "/TournamentPageServlet?tid="+tid+"&isInvolved="+involved;
}


//---------------------------------------------------------
