function setStatus(stat){
    document.getElementById("stTxt").innerText=" "
}
function setAss(astxt){
    document.getElementById("assTxt").innerText=" "
}
function setDL(dl){
    document.getElementById("dlTxt").innerText=" "
}
function setGRules(rules){
    document.getElementById("grTxt").innerText=" "
}
function fillLeaderBoard(leaderB, lbElment){
    let nameDiv, scoreDiv
    leaderB.forEach(entry => {
        nameDiv="<div class='teamName'>"+ entry.name+"</div>"
        scoreDiv="<div class='TeamScore'>"+ entry.scoreDiv+"</div>"

        lbElment.innerHTML+= nameDiv+scoreDiv;
    });

}