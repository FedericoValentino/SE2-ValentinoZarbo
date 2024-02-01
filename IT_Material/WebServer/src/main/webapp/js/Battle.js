function setStatus(stat){

    document.getElementById("stTxt").innerText=""+stat.statusTxt;
    if(stat.statusTxt==="CONSOLIDATION_PHASE")
        showManual()
    if(stat.statusTxt==="REGISTRATION_PHASE")
        showJoinDiv()

    status=stat.statusTxt
}

function setAss(astxt){

    document.getElementById("assTxt").innerText=" "+astxt.assignment;
}
function setDL(dl){
    document.getElementById("dlTxt").innerText="subscription deadline: "+dl.subs+" \n submission  deadline: "+dl.subm
}
let grule=[]
let status="";
function setGRules(rules){
    document.getElementById("grTxt").innerText=" mingrop:"+rules.minsize+" maxgroup:"+ rules.maxsize
    grule[0]=rules.minsize
    grule[1]=rules.maxsize
    showJoinButton(rules.minsize,rules.maxsize)


}
function afterAllLoad(){
    if(status==="REGISTRATION_PHASE")
        showJoinButton(grule[0],grule[1]);
}
function fillLeaderBoard(leaderB){
    if(leaderB==null ||Object.values( leaderB).length===0)
         return;
    const div=document.getElementById("grTxt");

    let nameDiv, scoreDiv
    leaderB.forEach(entry => {
        nameDiv="<div class='teamName'>"+ entry.name+"</div>"
        scoreDiv="<div class='TeamScore'>"+ entry.scoreDiv+"</div>"

        div.innerHTML+= nameDiv+scoreDiv;
    });

}