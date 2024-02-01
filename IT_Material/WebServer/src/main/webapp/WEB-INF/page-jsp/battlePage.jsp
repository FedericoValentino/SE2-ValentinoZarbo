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



                //REST GET battle-leaderboard, assignment, group-rules, deadlines, is user in this battle
            }
            function showJoinButton(){//todo

                document.getElementById("joinButton").innerHTML="<div class='inputlist'></div>"
            }
            function joinBattle(){//todo
                const data={
                    members:[],
                    uid:"${pageContext.request.session.getAttribute("uid")}"
                }
                restPostRequest("/tournament/{idT}/battle/{idB}/join",data,location.reload)
            }
            function showManual(){
                document.getElementById("manualLink").innerHTML="<a href='${pageContext.request.contextPath}/ManualEvalServlet' >go to manual evaluation</a>"
            }
    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName">Battle</div>
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

    <div id="joinButton"> <button onclick="showJoinButton()">join battle</button></div>
    <% }%>
</div>
</body>
</html>
