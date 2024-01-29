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
    <script>

            window.onload=function(){
            //REST GET battle-leaderboard, assignment, group-rules, deadlines, is user in this battle
            }
            function showJoinButton(){//todo

            }
            function joinBattle(){//todo

            }
    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName">Battle</div>
    <div class="logas"><%if(request.getSession().getAttribute("isStud").equals("yes")){ %>Student<%}else{ %> Educator<% }%></div>

    <a class="logout" href="https://www.google.com/">logout</a></div>
</div>
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

    <%if(request.getSession().getAttribute("isStud").equals("yes") &&  request.getParameter("isInvolved").equals("true")) {%>

    <div id="joinButton"> <button onclick="joinBattle()">join battle</button></div>
    <% }%>
</div>
</body>
</html>