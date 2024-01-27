<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ckbstyle.css">
  <title>JSP - Hello World</title>
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

    <form  id="logform">
      <label for="username">Username</label>
      <input name="username" id="username" type="text">
      <label for="username">password</label>
      <input name="psw" id="psw" type="password">
      <label for="username">are you an educator?</label>
      <input name="isEdu" id="isEdu" type="checkbox">

      <input type="submit">
    </form>
  </div>

  <div >
    <div class="contentHeader"> SignUp</div>

    <form id="signform">
      <label for="userns">Insert a username</label>
      <input name="username" id="userns" type="text">
      <label for="psw1"> type a password</label>
      <input name="psw1" id="psw1" type="password">
      <label for="psw2"> type the password again</label>
      <input name="psw2" id="psw2"  type="password">
      <label for="mail"> insert email</label>
      <input name="mail" id="mail" type="email">
      <label for="isEdS"> are you an educator?</label>
      <input name="isEdu" id="isEdS" type="checkbox">

      <input type="submit">
    </form>
  </div>

</div>

</body>
</html>