
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
        const restBaseUrl="${pageContext.request.session.getAttribute("restApiUrl")}"

        const educID=${pageContext.request.session.getAttribute("uid")} ;
        window.onload=function(){
            const burl="${pageContext.request.contextPath}";
            const sourceStub="";
            const idt= ${pageContext.request.session.getAttribute("tid")},idb=${pageContext.request.session.getAttribute("idb")};
            restGetRequest(restBaseUrl+"/tournament/"+idt+"/battle/"+idb+"/evalSource",loadSourceTxt,burl)
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
            restPostRequest(restBaseUrl+"/tournament/{idT}/battle/{idB}/evalSource/score",data,scoreSent)

        }
        function scoreSent(){
            alert("score sent")
        }


    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName"><a href="TournamentsServlet">Main page</a> >> Manual eval</div>
    <div class="logas"><% if ( request.getSession().getAttribute("isEdu").equals("false")){%>Logged as Student <%}else{ %>Logged as Educator<%} %></div>
    <a class="logout" href="LoginServlet">logout</a></div>
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
