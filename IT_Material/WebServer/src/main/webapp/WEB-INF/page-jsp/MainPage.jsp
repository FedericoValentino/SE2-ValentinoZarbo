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
        trg.innerHTML="<label for='newName'>New tournament Name</label><input id='newName' type='text'><button onclick='createTournamentCall()'>create</button>"
        document.getElementById("showCreateT").style.visibility="hidden";
    }
    function createTournamentCall(){
        const load= {
           tname: document.getElementById("newName").value,
            uid: ${pageContext.request.session.getAttribute("uid")}
        }
        if(load.tname===""){
            alert("please insert a tournament name")
           // document.getElementById("showCreateT").style.visibility="hidden";
            return;
        }

        restPostRequest("/tournament/create_t",load,afterCreateTournament)//todo once rest operative
    }
    function afterCreateTournament(){
        location.reload()
        //get new tourn id from response, go to tournament/tid page

    }



    const servletBURL="${pageContext.request.contextPath}";

        window.onload=function(){
            restGetRequest("/tournament?uid=${pageContext.request.session.getAttribute("uid")}",loadTourn, servletBURL)

        }
    </script>
</head>


<body>


<div id="header">

    <img id="logo">
    <div class="PageName">Tournaments</div>
    <div class="logas">    <% if ( request.getSession().getAttribute("isEdu").equals("false")){%>Logged as Student <%}else{ %>Logged as Educator<%} %>
    </div>
    <a class="logout" href="LoginServlet">logout</a></div>
</div>



<div id="content">
    <div id="ptor">
        <div class="contentHeader"> Your Tournaments</div>
        <div class="tournList" id="persTo">


        </div>
    </div>

    <div id="alltor">
        <div class="contentHeader"> All Tournaments</div>

        <div class="tournList" id="pubTo"></div>
    </div>
    <% if ( request.getSession().getAttribute("isEdu").equals("false")){%>



        <%}else{%>
        <div id="tournCRForm">
            <div class="inputList">
                <button id="showCreateT" onclick="showCreateTournament()">create tournament</button>
                <div id="crtTrgt"></div>
            </div>


        </div>

        <%} %>
</div>
</body>
</html>
