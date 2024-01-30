<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
  <script src="${pageContext.request.contextPath}/js/Communication.js"></script>

  <title>LOGIN REGISTRATION</title>


  <script>
    const baseRest="";

    function login(){
      const loginData={
        user:document.getElementById("username").value,
        pwd:document.getElementById("psw").value
      }
      const damnData=new URLSearchParams(loginData);
      restPostRequest("/user/login",damnData,preSession)
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
        edu:edu
      }
      restPostRequest("/user/register",JSON.stringify(regData),preSession)
    }
    function preSession(jsonResp){
      const res=(jsonResp);
      if(res.status!==200){
        alert(res.status)
      }
      let name=res.username, id=res.uid, isEdu=document.getElementById("isEdu").value;
      if(isEdu!=="on")
        isEdu="off"
            //check resp 200 w/ new user Idthen
      setSession({username:name,uid:id+"",isEdu:isEdu})

    }
    function setSession(sessionInfo){


      fetch("LoginServlet", {
        method: "POST",
        body: JSON.stringify(sessionInfo),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        } })
              .then((response) => response.text())
              .then((json) => redirToTournament());
    }

    function setSession1(){//in reality this will be: on positive response from rest, send webB also user id to incorporate in session

      let usname=document.getElementById("username").value;
      let isEDU=document.getElementById("isEdu").value;
      if(isEDU!=="on")
          isEDU="off"
      let load={
        username: usname,
        isEdu: isEDU,
        uid:"1"
      }
      fetch("LoginServlet", {
        method: "POST",
        body: JSON.stringify(load),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        } })
              .then((response) => response.json())
              .then((json) => console.log(json));

    }
    function redirToTournament(){
      location.href="TournamentsServlet";//fetch("", { method: "get"})
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
  <div class="logas">ED???</div>

</div>

<div id="content">
  <div>
    <div class="contentHeader"> Login</div>

    <form  id="logform" method="post" >
      <label for="username">Username</label>
      <input name="username" id="username" type="text">
      <label for="psw">password</label>
      <input name="psw" id="psw" type="password">
      <label for="isEdu">are you a student?</label>
      <input name="isEdu" id="isEdu"  type="checkbox">

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
      <label for="isEdu1"> are you a student ?</label>
      <input name="isEdu1" id="isEdu1" type="checkbox">

      <input type="submit">
    </form>
  </div>

</div>

</body>
</html>