<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28/01/2024
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
        const educID=${pageContext.request.session.getAttribute("uid")} ;
        window.onload=function(){
            const burl="${pageContext.request.contextPath}";
            const sourceStub="";
            const idt= ${pageContext.request.session.getAttribute("tid")},idb=${pageContext.request.session.getAttribute("idb")};
            restGetRequest("/tournament/"+idt+"/battle/"+idb+"/evalSource",loadSourceTxt,burl)
            //get from rest call
            //loadSourceTxt(sourceStub);
        }
        let listOfSource;
        function loadSourceTxt(listOfResp){
            if(listOfResp==null || Object.values(listOfResp).length===0)
                return;
            listOfSource=listOfResp;
            let i=0;
            for (const x in listOfResp) {
                document.getElementById("listOfSo").innerHTML+="<div class='source-item' onclick='showSource("+i+")'>GroupID="+x.gID+"</div>";
                i++;
            }


        }
        function showSource(positionInList){
            document.getElementById("sourceTxt").innerText=listOfSource[positionInList].sources;
        }
        function sendScore(){


            const score=document.getElementById("score").value;
            data={ score: score,
            uid:educID,
            gID:${pageContext.request.getParameter("idb")}}
            restPostRequest("/tournament/{idT}/battle/{idB}/evalSource/score",data,scoreSent)

        }
        function scoreSent(){
            alert("score sent")
        }


    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName">Tournaments</div>
    <div class="logas">ED???</div>
    <a class="logout" href="LoginServlet/">logout</a></div>
</div>

<div >
    <div id="listOfSo"></div>
    <div id="sourceTxt"></div>
    <form id="evalForm" action="uri for rest service" method="post">
        <label for="score">Insert score</label>
        <input type="number" id="score" name="score">
        <button onclick="sendScore()"> send</button>
    </form>
</div>
</body>
</html>
