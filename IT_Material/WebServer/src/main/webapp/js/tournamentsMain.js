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
    setTList(allT,document.getElementById("pubTo"),baseUrl,0)
    setTList(yourT, document.getElementById("persTo"),baseUrl,1)
}

function setTList(list, listElement, baseUrl, involved){
    list.forEach(to => {
        listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,involved)+'>'+to.tname+'</a>';
    });

}
function goToTournament(tid, involved){
    //calls servlet for new page( tournament page)
    return "/TournamentPageServlet?tid="+tid+"&isInvolved="+involved;
}


//---------------------------------------------------------
