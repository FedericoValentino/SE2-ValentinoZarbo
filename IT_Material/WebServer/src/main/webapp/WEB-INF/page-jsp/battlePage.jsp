<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28/01/2024
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Battle</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Battle.js"></script>
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
            const servletBURL="${pageContext.request.contextPath}";
            window.onload=function(){
                const uid="${pageContext.request.session.getAttribute("uid")}"
                const uriBattle="/tournament/${pageContext.request.session.getAttribute("tid")}/battle/${pageContext.request.session.getAttribute("bid")}"
                restGetRequest(uriBattle+"/lbb?uid="+uid,fillLeaderBoard, servletBURL)
                restGetRequest(uriBattle+"/assignment?uid="+uid,setAss, servletBURL)
                restGetRequest(uriBattle+"/deadlines?uid="+uid,setDL, servletBURL)
                restGetRequest(uriBattle+"/rules?uid="+uid,setGRules, servletBURL)
                restGetRequest(uriBattle+"/status?uid="+uid,setStatus, servletBURL)

                afterAllLoad();


                //REST GET battle-leaderboard, assignment, group-rules, deadlines, is user in this battle
            }
            function showJoinButton(minsize, maxsize){//todo
                let divJoin="<div class='inputlist'>"
                for (let i = 0; i < maxsize-1; i++) {
                    divJoin+="<lable for='otherStud"+(i+1)+"' >Add student to group</lable><input class='joinStudents' type='text' id='otherStud"+(i+1)+"' >"

                }
                divJoin+="<button onclick='joinBattle("+minsize+","+maxsize+")'>join battle</button></div>"
                document.getElementById("joinButton").innerHTML=divJoin
            }
            function joinBattle(minsize, maxsize){//todo
                let memmbers=[];
                let v;
                for (let i = 0; i < maxsize-1; i++) {
                    v=document.getElementById("otherStud"+(i+1)+"").value;
                    if(v!=null && v!=="")
                         memmbers.push(v);
                }
                if(memmbers.length<minsize-1 || memmbers.length>maxsize-1)
                    {alert("doesnt respect group rules, max:"+maxsize+", min:"+minsize+", current group:"+(memmbers.length+1))
                    return
                    }
                const data={
                    members:memmbers,
                    uid:"${pageContext.request.session.getAttribute("uid")}"
                }
                restPostRequest("/tournament/${pageContext.request.session.getAttribute("tid")}/battle/${pageContext.request.session.getAttribute("bid")}/join",data,goToTournament())
            }
            function goToTournament(){
                location.href="${pageContext.request.contextPath}/TournamentPageServlet?tid=${pageContext.request.session.getAttribute("tid")}&isInvolved=true"
            }
            function showManual(){
                document.getElementById("manualLink").innerHTML="<a href='${pageContext.request.contextPath}/ManualEvalServlet' >go to manual evaluation</a>"
            }
            function showJoinDiv(){
                document.getElementById("joinButton").style.visibility="visible";
            }
    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName"><a href="TournamentsServlet">Main page</a> >>Battle</div>
    <div class="logas"><% if ( request.getSession().getAttribute("isEdu").equals("false")){%>Logged as Student <%}else{ %>Logged as Educator<%} %>
    </div>

    <a class="logout" href="LoginServlet">logout</a></div></div>
<div id="content">
    <div id="assignment">
        <div class="contentHeader"> Assignment</div>
        <div id="assTxt">Lorem ipsum swag</div>
    </div>
    <div id="statrules">
        <div id="status">
            <div class="contentHeader"> Status</div>
            <div id="stTxt">Lorem ipsum swag</div>

        </div>
        <div id="deadlines">
            <div class="contentHeader"> Deadlines</div>
            <div id="dlTxt">Lorem ipsum swag</div>

        </div>
        <div id="groupRules">
            <div class="contentHeader"> Group Rules</div>
            <div id="grTxt">Lorem ipsum swag</div>

        </div>
    </div>
    <div id="lboard">
        <div class="contentHeader"> leaderboard</div>
        <div class="leaderboard" id="blbDIV">
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>
            <div class="lb-entry">
                <div class="teamName"> NomeSq</div>
                <div class="TeamScore">ScoreSq</div>
            </div>

        </div>
        <div id="manualLink"></div>

    </div>

    <%if(!request.getSession().getAttribute("isEdu").equals("true") &&  request.getParameter("isInvolved").equals("false")) {%>

    <div id="joinButton" style="visibility: hidden"> </div>
    <% } if (!request.getSession().getAttribute("isEdu").equals("true") && request.getParameter("isInvolved").equals("true")) { %>
        <div>Already joined this battle</div>
    <% } %>
</div>
</body>
</html>
