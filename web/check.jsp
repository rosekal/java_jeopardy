<%-- 
    Document   : check
    Created on : 5-Dec-2016, 7:55:18 PM
    Author     : azmatzuberi
--%>

<%@page import="game.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% int price = (Integer) session.getAttribute("price");
   Question question = (Question) session.getAttribute("question");
   boolean isCorrect = question.guessAnswer(request.getParameter("answer"));
   Question[] questions = (Question[]) session.getAttribute("questionArray");
   
   question.setQuestionToVoid();
   int currentAmount = (Integer) session.getAttribute("playerAmount");
   session.setAttribute("playerAmount", (isCorrect ? currentAmount + price : currentAmount - price));
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
        <form class="pure-form pure-form-stacked" action="game.jsp">
          <fieldset>
            <legend></legend>
            <p id="answerIs">You are <%= isCorrect ? "Correct!" : "incorrect."%></p><br>
            <% if(!isCorrect){%>
                <p id="correctAnswer">The correct answer is: <%= question.getAnswer() %> </p>
            <%}%>
            <label id="answerLbl" for="answer">Money:</label>
            <p id="money"><%= session.getAttribute("playerAmount")%></p><br>
            
            <% if((Integer) session.getAttribute("round") != 3){%>
                <button type="submit" class="pure-button pure-button-primary">Go back</button>
            <%}%>
          </fieldset>
        </form>
      </div>
    </section>
    
  </body>
</html>
