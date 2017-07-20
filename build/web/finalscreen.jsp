<%-- 
    Document   : check
    Created on : 5-Dec-2016, 7:55:18 PM
    Author     : azmatzuberi
--%>

<%@page import="db.DB"%>
<%@page import="game.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
   int currentAmount = (Integer) session.getAttribute("playerAmount");
   DB db = (DB) session.getAttribute("database");
   boolean isTop = false;
   
   try{
       db.getUserTopScore((String) session.getAttribute("username"));
       db.results().next();
       
       int topScore = Integer.parseInt(db.getColumn(2));
       isTop = currentAmount > topScore;
   
       if(isTop)
           db.updateTopScore(currentAmount, (String) session.getAttribute("username"));
       
    }catch(Exception e){
        %><%= e %><%
        db.updateTopScore(currentAmount, (String) session.getAttribute("username"));
    }
    
%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.0/build/pure-min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Right or Wrong</title>
    <style>
    h1 {
      font-size: 3rem; color: #324BC1;
      -webkit-filter: drop-shadow(5px 5px 5px #222);
      filter:         drop-shadow(5px 5px 5px #222);
      text-align: center;
    }
  </style>
  </head>
  <body>
    <header>
      <img id="gameLogo" src="images/jeopardy-logo.png" alt="Jeopardy Logo">
      <nav>
        <form method="POST" action="index.jsp">
          <button id="submit" type="submit">Logout</button>
        </form>	
      </nav>
    </header>
    <section id="finalBody">
      <h1>Right or Wrong</h1>
      <div id="finalQuestionBox">
        <form class="pure-form pure-form-stacked" action="reset.jsp">
          <fieldset>
            <legend></legend>
            <p id="answerIs">Your total earnings is: <%= currentAmount %>.
            <% if(isTop){%>
                This is your top game!
            <%}else{%>
                This is not your top game.
            <%}%></p><br>
            
            <button name="goBack" type="submit" class="pure-button pure-button-primary">Go back</button>
            
            <h1 style="center">Leaderboard</h1>
            
            <table class="tbl">
            <tr>
                <th class="tg-e9v8">Username</th>
                <th class="tg-e9v8">Top Score</th>
            </tr>
                <% db.getAllUsersTopScore();
                    while(db.results().next()){%>
                     <tr>
                         <td class="tg-e9v8"><%= db.getColumn(1)%></th>
                         <td class="tg-e9v8"><%= db.getColumn(2)%></th>
                     </tr>
                    <%}%>
          </fieldset>
        </form>
      </div>
    </section>
  </body>
</html>