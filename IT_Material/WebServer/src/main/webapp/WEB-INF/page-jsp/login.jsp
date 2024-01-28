<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
  <title>JSP - Hello World</title>


  <script>
    const baseRest="";

    function login(){
      const loginData={
        username:document.getElementById("username").value,
        psw:document.getElementById("psw").value,
        isStud:document.getElementById("isStud").value
      }

      fetch(baseRest+"/user/login", {
        method:"post",
        body:JSON.stringify(loginData),
        headers:{
          "Content-type": "application/json; charset=UTF-8"
        }
      }).then((response) => response.json())
              .then((json) => preSession(json));
    }

    function register(){
      let psw1=document.getElementById("psw1").value,psw2=document.getElementById("psw2").value;
      if(psw1!=psw2){
        alert("passwords dont correspond")
        return;
      }
      const regData={
        username:document.getElementById("userns").value,
        psw:psw1,
        email:document.getElementById("email").value,
        isStud:document.getElementById("isStud1").value
      }

      fetch(baseRest+"/user/register", {
        method:"post",
        body:JSON.stringify(regData),
        headers:{
          "Content-type": "application/json; charset=UTF-8"
        }
      }).then((response) => response.json())
              .then((json) => preSession(json));
    }
    function preSession(jsonResp){
      //check resp 200 then
      setSession()

    }

    function setSession(){//in reality this will be: on positive response from rest, send webB also user id to incorporate in session

      let usname=document.getElementById("username").value;
      let isStud=document.getElementById("isStud").value;
      if(isStud!=="on")
          isStud="off"
      let load={
        username: usname,
        isStud: isStud
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
      fetch("TournamentsServlet", { method: "get"})
    }


  </script>
</head>
<body>
<h1><%= "stub for session testing" %></h1>
<br/>
<a href="TournamentsServlet">Hello Servlet</a>
<div id="header">

  <img id="logo">
  <div class="PageName">Tournaments</div>
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
      <label for="isStud">are you a student?</label>
      <input name="isStud" id="isStud"  type="checkbox">

<input type="submit">
    </form>
    <button onclick="setSession()">prova</button>
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
      <label for="isStud1"> are you a student ?</label>
      <input name="isStud1" id="isStud1" type="checkbox">

      <input type="submit">
    </form>
  </div>

</div>

</body>
</html>