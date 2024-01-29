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
        window.onload=function(){
            const sourceStub="";
            //get from rest call
            loadSourceTxt(sourceStub);
        }
        function loadSourceTxt(source){
            document.getElementById("sourceTxt").innerText=source;

        }
        function sendScore(){
            //todo rest call to send score

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

<div >
    <div id="sourceTxt"></div>
    <form id="evalForm" action="uri for rest service" method="post">
        <label for="score">Insert score</label>
        <input type="number" id="score" name="score">
        <label for="subscore">Save score</label>
        <input type="submit" id="subscore">
        <button onclick="sendScore()"> send</button>
    </form>
</div>
</body>
</html>
