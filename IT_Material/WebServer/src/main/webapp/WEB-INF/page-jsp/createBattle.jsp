<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27/01/2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createBattle</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Battle.js"></script>
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
        function createBattle(){
            const loadData={
                uid: ${pageContext.request.session.getAttribute("uid")} ,
                idT :${pageContext.request.session.getAttribute("tid")},
                bname: document.getElementById("bName").value ,
                minsize : document.getElementById("minG").value,
                maxsize : document.getElementById("maxG").value,
                assignment: document.getElementById("assTxt").value,
                subsd : document.getElementById("subsD").value,
                submd : document.getElementById("submD").value

            }
            restPostRequest("/tournament/{${pageContext.request.session.getAttribute("tid")}}/battle/b_create",loadData,goToBattle)

         }
         function goToTournament(resp){
            location.href="${pageContext.request.contextPath}/tournament/{${pageContext.request.session.getAttribute("tid")}}";
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
    <form>
        <label for="bName"></label><input type="text" id="bName" name="bName">
        <label for="assTxt"></label><input type="text" id="assTxt" name="assTxt">

        <div id="dlines">
            <label for="subsD"></label><input type="date" id="subsD" name="subsD">
            <label for="submD"></label><input type="date" id="submD" name="submD">
        </div>
        <div id="grules">
            <label for="minG"></label><input type="number" id="minG" name="minG">
            <label for="maxG"></label><input type="number" id="maxG" name="maxG">
        </div>


        <input type="file" id="testC" name="testC">
        <button onclick="createBattle()">create</button>



    </form>
</div>
</body>
</html>
