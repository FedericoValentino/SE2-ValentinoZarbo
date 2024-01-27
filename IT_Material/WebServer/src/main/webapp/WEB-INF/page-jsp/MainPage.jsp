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
    <script src="${pageContext.request.contextPath}/js/scriptckb.js"></script>
    <script>
        const stubTournList='{"tournaments":[{"tid":"1","tname":"tourn12","isInvolved":"0"},' +
            '{"tid":"3","tname":"tr3","isInvolved":"1"},{"tid":"2","tname":"tourn2","isInvolved":"0"}]}'
        window.onload=function(){

            //call to rest api, get content and populate lists

            const tournaments =stubTournList
            // get tournament/ and make list of all and those where user is subscribed
            loadTourn(JSON.parse(tournaments));
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
            <a class="tlist-item" id="t-item111">tourn1</a>
            <div class="tlist-item" id="t-item2">tourn1</div>
            <div class="tlist-item" id="t-item3">tourn1</div>
            <div class="tlist-item" id="t-item4">tourn1</div>
            <div class="tlist-item" id="t-item5">tourn1</div>



        </div>
    </div>

    <div id="alltor">
        <div class="contentHeader"> All Tournaments</div>

        <div class="tournList" id="pubTo"></div>
    </div>
    <% if ((boolean) request.getSession().getAttribute("isStud")){%>
    <div id="diversified">

    <p>studente</p>
        <%}else{%>
        <div id="tournCRForm">
    <p>educatore</p>
        </div>

        <%} %>
    </div>
</div>





</body>
</html>
