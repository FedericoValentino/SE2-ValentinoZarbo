<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
  <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

  <title>LOGIN REGISTRATION</title>


  <script>
    const baseServlet="${pageContext.request.contextPath}";
    let educ;
    function login(){
      const loginData={
        user:document.getElementById("username").value,
        pwd:document.getElementById("psw").value
       // educator:document.getElementById("isEdu").checked
      }
      //educ=document.getElementById("isEdu").checked
      //const damnData=new URLSearchParams(loginData);
      restPostRequest("/user/login",loginData,preSession)
    }

    function register(){
      let psw1=document.getElementById("psw1").value,psw2=document.getElementById("psw2").value;
      if(psw1!=psw2){
        alert("passwords dont correspond")
        return;
      }
      let edu=false
      if(document.getElementById("isEdu1").value!=null)
        if(document.getElementById("isEdu1").value==="on")
          edu=true
      const regData={
        user:document.getElementById("userns").value,
        pwd:psw1,
        email:document.getElementById("email").value,
        educator:edu
      }
      educ=document.getElementById("isEdu1").checked
      restPostRequest("/user/register",JSON.stringify(regData),preSession)
    }
    function preSession(jsonResp){
      const res=(jsonResp);
      let  id=res.uid, isEdu=res.isEdu;
      if(res.hasOwnProperty("error")){
        alert("error logging in")
        return
      }

            //check resp 200 w/ new user Idthen
      setSession({username:name,uid:id+"",isEdu:isEdu})

    }
    function setSession(sessionInfo){


      fetch(baseServlet+"/LoginServlet", {
        method: "POST",
        body: JSON.stringify(sessionInfo),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        } })
              .then((response) => response.text())
              .then((json) => redirToTournament());
    }

    function redirToTournament(){
      location.href=baseServlet+"/TournamentsServlet";//fetch("", { method: "get"})
    }


  </script>
</head>
<body>
<h1><%= "stub for session testing" %></h1>
<br/>
<a href="TournamentsServlet">Hello Servlet</a>
<div id="header">

  <img id="logo">
  <div class="PageName">Login</div>
  <div class="logas"></div>

</div>

<div id="content">
  <div>
    <div class="contentHeader"> Login</div>

    <form  id="logform" method="post" >
      <label for="username">Username</label>
      <input name="username" id="username" type="text">
      <label for="psw">password</label>
      <input name="psw" id="psw" type="password">
     <!-- <label for="isEdu">are you an educator?</label>
      <input name="isEdu" id="isEdu"  type="checkbox">-->

<input type="submit">
    </form>
    <button onclick="login()">prova</button>
  </div>

  <div >
    <div class="contentHeader"> SignUp</div>

    <form id="signform" method="post">
      <label for="userns">Insert a username</label>
      <input name="username" id="userns" type="text">
      <label for="psw1"> type a password</label>
      <input name="psw1" id="psw1" type="password">
      <label for="psw2"> type the password again</label>
      <input name="psw2" id="psw2"  type="password">
      <label for="mail"> insert email</label>
      <input name="mail" id="mail" type="email">
      <label for="isEdu1"> are you an educator ?</label>
      <input name="isEdu1" id="isEdu1" type="checkbox">

      <input type="submit">
    </form>
  </div>

</div>

</body>
</html>