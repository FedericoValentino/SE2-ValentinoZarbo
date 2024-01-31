function setStatus(stat){

    document.getElementById("stTxt").innerText=""+stat.value;
    if(stat.value=="CONSOLIDATION_PHASE")
        showManual()
}

function setAss(astxt){

    document.getElementById("assTxt").innerText=" "+astxt.rules;
}
function setDL(dl){
    document.getElementById("dlTxt").innerText="subscription deadline: "+dl[0]+"submission  deadline: "+dl[1]
}
function setGRules(rules){
    document.getElementById("grTxt").innerText=" mingrop:"+rules[0]+" maxgroup:"+ rules[1]
}
function fillLeaderBoard(leaderB){
    const div=document.getElementById("grTxt");

    let nameDiv, scoreDiv
    leaderB.forEach(entry => {
        nameDiv="<div class='teamName'>"+ entry.name+"</div>"
        scoreDiv="<div class='TeamScore'>"+ entry.scoreDiv+"</div>"

        div.innerHTML+= nameDiv+scoreDiv;
    });

}