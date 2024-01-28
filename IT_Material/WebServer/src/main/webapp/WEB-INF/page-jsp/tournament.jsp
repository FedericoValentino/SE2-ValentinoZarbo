<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27/01/2024
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tournament xxx page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">

    <script src="${pageContext.request.contextPath}/js/Tournament.js"></script>
    <script>

        function joinTournament() {//todo

    }

    window.onload=function (){//todo
            let batStub =[{bname:"bobbo", bid: 2},{bname:"boso", bid: 3},{bname:"bbo", bid: 4}]
            let leadBstub=[{uname:"askdl", teamScore: 3},{uname:"saf", teamScore: 2},{uname:"dl", teamScore: 2},{uname:"askdasddl", teamScore: 1}]
            //rest call for battle list, leaderboard, and if subscribed
            fillBattleList(batStub,document.getElementById("blist"));
            fillLeaderBoard(leadBstub,document.getElementById("leaderboard"))
        }
        function showAddCollaborator(){
            document.getElementById("collaboratorDiv").innerHTML="";//todo
        }
        function closeTournament(){//todo
            //rest call to close tournament
        }

    </script>


</head>
<body>

<div id="header">

    <img id="logo">
    <div class="PageName">Tournaments</div>
    <div class="logas">ED???</div>
    <a class="logout" href="https://www.google.com/">logout</a></div>
</div>
<div id="content">

    <div id="battles">
        <div class="contentHeader"> battles</div>
        <div class="battleList" id="blist">

        </div>
    </div>


    <div id="lboard">
        <div class="contentHeader"> leaderboard</div>
        <div class="leaderboard">
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>

        </div>

    </div>


    <div id="diffCont">
        <%  if( request.getSession().getAttribute("isStud").equals("yes") && (boolean)request.getSession().getAttribute("subscribed")){ %>
        <div id="subscribe">
            <a href="tournament.jsp"></a>
            <button onclick="joinTournament()"></button>
        </div>
        <%}else{ %>
            <div id="addBut">
                <a href="/CreateBattleServlet"></a>
                <button onclick="showAddCollaborator()">Add COllaborator</button>
                <div id="collaboratorDiv"></div>
            </div>
        <button onclick="closeTournament()">close tournament</button>
        <%} %>

    </div>

</div>

</body>
</html>
