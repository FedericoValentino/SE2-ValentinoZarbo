<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27/01/2024
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tournaments main page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/tournamentsMain.js"></script>
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
        function showCreateTournament() {
        const trg=document.getElementById("crtTrgt");
        trg.innerHTML="<label for='newName'>New tournament Name</label><input id='newName' type='text'><button onclick='createTournamentCall()'></button>"

    }
    function createTournamentCall(){
        const load= {
           newName: document.getElementById("newName").value,
            uid : "${pageContext.request.session.getAttribute("uid")}"
        }

        restPostRequest("uri",(load),afterCreateTournament)//todo once rest operative
    }
    function afterCreateTournament(objresp){
            location.href="/TournamentPage?tid="+objresp.tid;
        //get new tourn id from response, go to tournament/tid page

    }



    const servletBURL="${pageContext.request.contextPath}";

        window.onload=function(){
            restGetRequest("/tournament?uid=${pageContext.request.session.getAttribute("uid")}",loadTourn, servletBURL)



            //const tournaments =stubTournList
            // get tournament/ and make list of all and those where user is subscribed
            //loadTourn(tournaments, restBase);
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
    <div id="ptor">
        <div class="contentHeader"> Your Tournaments</div>
        <div class="tournList" id="persTo">
            <!-- <a class="tlist-item" href="TournamentPageServlet?tid=1&isInvolved=1" id="t-item111">tourn1</a>
           - <div class="tlist-item" id="t-item2">tourn1</div>
             <div class="tlist-item" id="t-item3">tourn1</div>
             <div class="tlist-item" id="t-item4">tourn1</div>
             <div class="tlist-item" id="t-item5">tourn1</div>-->



        </div>
    </div>

    <div id="alltor">
        <div class="contentHeader"> All Tournaments</div>

        <div class="tournList" id="pubTo"></div>
    </div>
    <% if ( !request.getSession().getAttribute("isEdu").equals("true")){%>
    <div id="diversified">

    <p>studente</p>
        <%}else{%>
        <div id="tournCRForm">
            <button onclick="showCreateTournament()">create tournament</button>
            <div id="crtTrgt"></div>
    <p>educatore</p>
        </div>

        <%} %>
    </div>
</div>





</body>
</html>
