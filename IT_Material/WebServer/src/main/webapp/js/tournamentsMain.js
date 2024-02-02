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
            setSingleT(t,document.getElementById("persTo"),baseUrl,t.involved)
            yourT[j]=t;
            j++;
        }
        setSingleT(t,document.getElementById("pubTo"),baseUrl,t.involved)
        i++;                
    });
   // setTListNotInv(allT,document.getElementById("pubTo"),baseUrl)
    //setTListInvolved(yourT, document.getElementById("persTo"),baseUrl)
}//todo check close and add collab, subscribe, join battle and collaborator stuff

function setTListInvolved(list, listElement, baseUrl, involved){
    list.forEach(to => {
        listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,""+involved)+'>'+to.tname+'</a>';
    });

}
function setSingleT(to,listElement, baseUrl , involved){
    listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,""+involved)+'>'+to.tname+'</a>';

}
function setSingleTNotInvolved(to,listElement, baseUrl){
    listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,"false")+'>'+to.tname+'</a>';

}

function setTListNotInv(list, listElement, baseUrl){
    list.forEach(to => {
        listElement.innerHTML+='<a class="tlist-item" id="'+to.tid+'-item " href='+baseUrl+goToTournament(to.tid,"false")+'>'+to.tname+'</a>';
    });

}
function goToTournament(tid, involved){
    //calls servlet for new page( tournament page)
    return "/TournamentPageServlet?tid="+tid+"&isInvolved="+involved;
}


//---------------------------------------------------------
