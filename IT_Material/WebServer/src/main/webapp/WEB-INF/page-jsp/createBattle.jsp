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
            const bname= document.getElementById("bName").value ,
                minsize = document.getElementById("minG").value,
                maxsize = document.getElementById("maxG").value,
                assignment= document.getElementById("assTxt").value,
                subsd = document.getElementById("subsD").value,
                submd = document.getElementById("submD").value;
            const loadData={

                uid: ${pageContext.request.session.getAttribute("uid")} ,
                idT :${pageContext.request.session.getAttribute("tid")},
                bname: bname,
                minsize : minsize,
                maxsize : maxsize,
                assignment: assignment,
                subsd : subsd,
                submd : submd

            }
           // const formdata=new FormData(document.getElementById("maybe"))
            restPostBodyRequest("/tournament/${pageContext.request.session.getAttribute("tid")}/battle/create_b",loadData,goToTournament)
            //restPostRequest("/tournament/${pageContext.request.session.getAttribute("tid")}/battle/create_b",loadData,goToTournament)

         }
         function goToTournament(resp){
            location.href="${pageContext.request.contextPath}/tournament/{${pageContext.request.session.getAttribute("tid")}}";
         }
    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName">CREATE BATTLE</div>
    <div class="logas">ED???</div>
    <a class="logout" href="LoginServlet/">logout</a></div>
</div>

<div id="content">
    <form id="maybe">
        <label for="bName">BATTLE NAME</label><input type="text" id="bName" name="bName">
        <label for="assTxt">ASSIGNMENT TEXT</label><input type="text" id="assTxt" name="assTxt">

        <div id="dlines">
            <label for="subsD"></label><input type="date" id="subsD" name="subsD">
            <label for="submD"></label><input type="date" id="submD" name="submD">
        </div>
        <div id="grules">
            <label for="minG"></label><input type="number" id="minG" name="minG">
            <label for="maxG"></label><input type="number" id="maxG" name="maxG">
        </div>


        <!--<input type="file" id="testC" name="testC">-->
        <button onclick="createBattle()">create</button>



    </form>
</div>
</body>
</html>
