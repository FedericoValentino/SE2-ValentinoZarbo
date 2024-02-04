
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createBattle</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
    <script src="${pageContext.request.contextPath}/js/Battle.js"></script>
    <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

    <script>
        const restBaseUrl="${pageContext.request.session.getAttribute("restApiUrl")}"

        function createBattle(){
            const bname= document.getElementById("bName").value ,
                minsize = document.getElementById("minG").value,
                maxsize = document.getElementById("maxG").value,
                assignment= document.getElementById("assTxt").value,
                subsd = new Date(document.getElementById("subsD").value),
                submd =  new Date(document.getElementById("submD").value);
            const loadData={

                uid: ${pageContext.request.session.getAttribute("uid")} ,
                idT :${pageContext.request.session.getAttribute("tid")},
                bname: bname,
                minsize : minsize,
                maxsize : maxsize,
                assignment: assignment,
                subsd : subsd.getTime(),
                submd : submd.getTime()

            }
            if(minsize>maxsize || minsize<0 || maxsize<0){
                alert("Please insert acceptable values for minsize and maxsize")
                return;
            }
            for (const key in loadData) {
                if(loadData[key]==null || loadData[key]===""){
                    alert("please fill every input before creating a battle")
                    return;
                }
            }
           // const formdata=new FormData(document.getElementById("maybe"))
            restPostBodyRequest(restBaseUrl+"/tournament/${pageContext.request.session.getAttribute("tid")}/battle/create_b",loadData,goToTournament)


         }
         function goToTournament(resp){
            location.href="${pageContext.request.contextPath}/TournamentsServlet?tid?${pageContext.request.session.getAttribute("tid")}";
         }
    </script>
</head>
<body>
<div id="header">

    <img id="logo">
    <div class="PageName"><a href="TournamentsServlet">Main page</a> >> CREATE BATTLE</div>
    <div class="logas"><% if ( request.getSession().getAttribute("isEdu").equals("false")){%>Logged as Student <%}else{ %>Logged as Educator<%} %></div>
    <a class="logout" href="LoginServlet">logout</a>
</div>

<div id="content">
    <div>
    <div class="inputList" id="maybe">
        <label for="bName">BATTLE NAME</label><input placeholder="asdafasfa" type="text" id="bName" name="bName">
        <label for="assTxt">ASSIGNMENT TEXT</label><input placeholder=" asdadffffff" type="text" id="assTxt" name="assTxt">

        <div id="dlines">
            <label for="subsD"></label><input type="date" placeholder="2000-21-2" id="subsD" name="subsD">
            <label for="submD"></label><input type="date" placeholder="2000-21-2" id="submD" name="submD">
        </div>
        <div id="grules">
            <label for="minG"></label><input type="number" placeholder="2" id="minG" name="minG">
            <label for="maxG"></label><input type="number" placeholder="3" id="maxG" name="maxG">
        </div>
    </div>

        <!--<input type="file" id="testC" name="testC">-->
        <button onclick="createBattle()">create</button>
    </div>




</div>
</body>
</html>
