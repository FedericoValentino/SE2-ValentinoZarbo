<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tournament xxx page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>
    <script src="${pageContext.request.contextPath}/js/Tournament.js"></script>
    <script>
        const restBaseUrl="${pageContext.request.session.getAttribute("restApiUrl")}"

        function joinTournament() {//todo
            const load={
                idT:"${pageContext.request.session.getAttribute("tid")}",
                uid:"${pageContext.request.session.getAttribute("uid")}"
            }

            restPostRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/subscribe",(load),goMain)
        }
        function reload(){
            location.reload()
        }
        function goMain(){
            location.href="${pageContext.request.contextPath}/TournamentsServlet";
        }
    function loadPage(){
        const bUrl="${pageContext.request.contextPath}"
        const inv="${pageContext.request.getParameter("isInvolved")}";
        if(inv==="true")
             restGetRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/battle?uid=${pageContext.request.session.getAttribute("uid")}",fillBattleList,bUrl)
        else {
            document.getElementById("battles").innerHTML="<div class='contentHeader'> To see the battles join the tournament</div>"

        }
        restGetRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/lbt",fillLeaderBoard,bUrl)


    }
    window.onload=function (){loadPage()}

        function showAddCollaborator(){
            document.getElementById("buttonAddCol").style.visibility="hidden"
            document.getElementById("collaboratorDiv").innerHTML="<label for='newCol'>NewCollaboratore username</label><input id='newCol' type=text>" +
                "<button onclick='addCollaborator()'>add collaborator to tournament</button>";
        }
        function addCollaborator(){//todo
            const newCoUsname=document.getElementById("newCol").value;
            if(newCoUsname==null){
                alert("missing collaborator username")
                return;
            }
            const load={
                cUsername:newCoUsname,
                uid:${pageContext.request.session.getAttribute("uid")}
            }
            //restcall for addColl
            restPostRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/addCollaborator",(load),reload)


        }
       // function unShowAddColl(){}
        function closeTournament(){//todo
            const load={
                uid:${pageContext.request.session.getAttribute("uid")}

            }
            restPostRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/close",(load),goMain)

            //rest call to close tournament
        }


    </script>


</head>
<body>

<div id="header">

    <img id="logo">
    <div class="PageName"><a href="TournamentsServlet">Main page</a> >> Tournament</div>
    <div class="logas"><% if ( request.getSession().getAttribute("isEdu").equals("false")){%>Logged as Student <%}else{ %>Logged as Educator<%} %></div>
    <a class="logout" href="LoginServlet">logout</a></div></div>
<div id="content">

    <div id="battles">
        <div class="contentHeader"> battles</div>
        <div class="battleList" id="blist">

        </div>
    </div>


    <div id="lboard">
        <div class="contentHeader"> leaderboard</div>
        <div class="leaderboard" id="lbDiv">
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
        <%  if( request.getSession().getAttribute("isEdu").equals("false") && request.getParameter("isInvolved").equals("false")){ %>
        <div id="subscribe">
            <button onclick="joinTournament()">join tournament</button>
        </div>
        <%}if(request.getSession().getAttribute("isEdu").equals("true") && request.getParameter("isInvolved").equals("true")){ %>
            <div id="addBut" class="inputList">
                <a id="createBLink" href="${pageContext.request.contextPath}/CreateBattleServlet">create battle</a>
                <button id="buttonAddCol" onclick="showAddCollaborator()">Add Collaborator</button>
                <div id="collaboratorDiv"></div>
                <button onclick="closeTournament()">close tournament</button>

            </div>
        <%} %>

    </div>

</div>

</body>
</html>
