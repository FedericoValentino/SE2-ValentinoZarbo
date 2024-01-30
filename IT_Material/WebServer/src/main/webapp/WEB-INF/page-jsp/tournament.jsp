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
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>
    <script src="${pageContext.request.contextPath}/js/Tournament.js"></script>
    <script>

        function joinTournament() {//todo
            const load={
                tid:"${pageContext.request.session.getAttribute("tid")}",
                uid:"${pageContext.request.session.getAttribute("uid")}"
            }

            restPostRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/join",JSON.stringify(load),reload)
        }
        function reload(){
            location.reload()
        }
    function loadPage(){
        const bUrl="${pageContext.request.contextPath}"
        let batStub =[{bname:"bobbo", bid: 2},{bname:"boso", bid: 3},{bname:"bbo", bid: 4}]
        let leadBstub=[{uname:"askdl", pScore: 3},{uname:"saf", pScore: 2},{uname:"dl", pScore: 2},{uname:"askdasddl", pScore: 1}]
        //rest call for battle list, leaderboard, and if subscribed
        restGetRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/battle",fillBattleList,bUrl)
        restGetRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/lbt",fillLeaderBoard,bUrl)


        //todo rest call getx2
        //
        fillBattleList(batStub,bUrl);
        fillLeaderBoard(leadBstub,bUrl)
    }
    window.onload=function (){loadPage()}

        function showAddCollaborator(){
            document.getElementById("collaboratorDiv").innerHTML="<label for='newCol'>NewCollaboratore username</label><input id='newCol' type=text>" +
                "<button onclick='addCollaborator()'>go</button>";//todo
        }
        function addCollaborator(){
            const newCoUsname=document.getElementById("newCol").value;
            const load={
                newCollab:newCoUsname,
                uid:${pageContext.request.session.getAttribute("uid")},
                tid:${pageContext.request.session.getAttribute("tid")}
            }
            //restcall for addColl
            restPostRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/addCollaborator",JSON.stringify(load),loadPage)


        }
       // function unShowAddColl(){}
        function closeTournament(){//todo
            const load={
                uid:${pageContext.request.session.getAttribute("uid")},
                tid:${pageContext.request.session.getAttribute("tid")}
            }
            restPostRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/closeTournament",JSON.stringify(load),loadPage)

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
                <div class="lb-Name"> Nome</div>
                <div class="lb-Score">Score</div>
            </div>
            <div class="lb-entry">
                <div class="lb-Name"> Nome</div>
                <div class="lb-Score">Score</div>
            </div>
            <div class="lb-entry">
                <div class="lb-Name"> Nome</div>
                <div class="lb-Score">Score</div>
            </div>
            <div class="lb-entry">
                <div class="lb-Name"> Nome</div>
                <div class="lb-Score">Score</div>
            </div>
            <div class="lb-entry">
                <div class="lb-Name"> Nome</div>
                <div class="lb-Score">Score</div>
            </div>

        </div>

    </div>


    <div id="diffCont">
        <%  if( !request.getSession().getAttribute("isEdu").equals("yes") && request.getParameter("isInvolved").equals("false")){ %>
        <div id="subscribe">
            <button onclick="joinTournament()"></button>
        </div>
        <%}if(request.getSession().getAttribute("isEdu").equals("yes") && request.getParameter("isInvolved").equals("true")){ %>
            <div id="addBut">
                <a href="${pageContext.request.contextPath}/CreateBattleServlet"></a>
                <button onclick="showAddCollaborator()">Add COllaborator</button>
                <div id="collaboratorDiv"></div>
            </div>
        <button onclick="closeTournament()">close tournament</button>
        <%} %>

    </div>

</div>

</body>
</html>
