<%-- 
    Document   : index
    Created on : 1-Dec-2016, 3:33:56 PM
    Author     : azmatzuberi
--%>

<%@page import="game.Game"%>
<%@page import="db.DB"%>
<% 
  DB db = new DB();
  session.setAttribute("database", db);
  session.setAttribute("game", new Game());
  session.setAttribute("round", new Integer(1));
  session.setAttribute("playerAmount", new Integer(0));
  session.setAttribute("questionsSelected", new Integer(0));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <title>Jeopardy</title>
</head>
<style>
  .loginWrong {
    color: red;
    margin-top: 50px;
  }
</style>
<body>
  <header>
    <nav>
      <form method="POST">
        <label>Username</label><input id="username" type="text" name="username" required="">
        <label id="passwordLabel">Password</label><input id="password" type="password" name="password" required="">
        <button id="submit" name="submit" type="submit" value="login">Login</button>
        <button id="signUp" name="submit" type="submit" value="signUp">Sign Up</button>   
      </form>
    </nav>
</header> 

<!-- Login credentials -->  
<%  String username = request.getParameter("username");
    String password = request.getParameter("password");
    db.connect();
    if (null != request.getParameter("username") && null != request.getParameter("password")  && null != request.getParameter("submit")) {
        if (request.getParameter("submit").equals("login")) {
          db.getUserInfo();
        do  {
          db.results().next();  
          String userRetrieve = db.getColumn(2);
          String passwordRetrieve = db.getColumn(3);
         
          if (userRetrieve.equals("admin") && username.equals("admin") && (passwordRetrieve.equals("p@ssw0rd") && password.equals("p@ssw0rd"))) {
             
        %> 
        <jsp:forward page="/admin.jsp"/> 
    <% 
        } else if (username.equals(userRetrieve) && (password.equals(passwordRetrieve))) {
        session.setAttribute("username", userRetrieve);
    %>
        <jsp:forward page="/game.jsp"/>
    <%
      }
       
        } while (!db.results().isLast());
        out.println(loginWrong());
    }
  }   
%>    
<!-- Sign Up -->
<%  String user_Name = request.getParameter("username");
    String pass_Word = request.getParameter("password");
    
    if(request.getParameter("submit") != null) {
    if(request.getParameter("submit").equals("signUp")) {
    
        db.connect(); 
        
        System.out.println(user_Name);
        int returnValue = db.signUp(user_Name, pass_Word);
  
        if (returnValue == 1) {
          out.println(signUpRight());
        } else {
          out.println(signUpBad());
      }
        System.out.println(returnValue);
    }
}
%>    
    <section id="logo-section">
    <img id="logo" src="images/jeopardy-logo.png" alt="Jeopardy Logo">	
  </section>
</body>
</html>

<%! public String loginWrong() {
      String loginBad = "<section id=\"finalBody\" class=\"loginWrong\"><h1>Invalid User Login Credentials</h1></section>";
      return loginBad;
    }
%>
<%! public String signUpRight() {
      String signUpGood =  "<section id=\"finalBody\" class=\"loginRight\"><h1>Sign Up Sueccessful!</h1></section>";
      return signUpGood;
    }
%>
<%! public String signUpBad() {
      String signUpBad =  "<section id=\"finalBody\" class=\"loginWrong\"><h1>Sign Up Unsuccessful!</h1></section>";
      return signUpBad;
    }
%>