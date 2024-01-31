function setStatus(stat){

    document.getElementById("stTxt").innerText=""+stat.statusTxt;
    if(stat.value==="CONSOLIDATION_PHASE")
        showManual()
}

function setAss(astxt){

    document.getElementById("assTxt").innerText=" "+astxt.assignment;
}
function setDL(dl){
    document.getElementById("dlTxt").innerText="subscription deadline: "+dl.value1+"submission  deadline: "+dl.value0
}
function setGRules(rules){
    document.getElementById("grTxt").innerText=" mingrop:"+rules.value1+" maxgroup:"+ rules.value0
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