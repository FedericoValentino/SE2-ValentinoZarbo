function setStatus(stat){

    document.getElementById("stTxt").innerText=" "
}
function setAss(astxt){

    document.getElementById("assTxt").innerText=" "+astxt.rules;
}
function setDL(dl){
    document.getElementById("dlTxt").innerText=" "
}
function setGRules(rules){
    document.getElementById("grTxt").innerText=" "
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