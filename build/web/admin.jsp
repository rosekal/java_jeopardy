<%-- 
    Document   : admin
    Created on : 4-Dec-2016, 4:01:37 PM
    Author     : azmatzuberi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://unpkg.com/purecss@0.6.0/build/pure-min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <style>
    h1 {
      font-size: 3rem; color: #324BC1;
      -webkit-filter: drop-shadow(5px 5px 5px #222);
      filter:         drop-shadow(5px 5px 5px #222);
      text-align: center;
    }
  </style>
  <body>
    <header>
      <img id="gameLogo" src="images/jeopardy-logo.png" alt="Jeopardy Logo">
      <nav>
        <form method="POST" action="">
          <button id="submit" type="submit">Logout</button>
        </form>	
      </nav>
    </header>
    <section id="formSection">
      <h1>Admin Panel</h1>
      <form class="pure-form pure-form-stacked">
        <fieldset>
          <legend>Edit Form</legend>

          <label for="category">Category</label>
          <input id="category" type="text" name="category" placeholder="Category">

          <label for="round">Round</label>
          <select id="round" name="round">
            <option>Double Jeopardy!</option>
            <option>Final Jeopardy!</option>
            <option>Jeopardy!</option>
            <option>Tie Breaker</option>
          </select>

          <label for="question">Question</label>
          <input id="question" type="text" name="question" placeholder="Question">

          <label for="answer">Answer</label>
          <input id="answer" type="text" name="answer" placeholder="answer">

          <button type="submit" class="pure-button pure-button-primary">Submit</button>
        </fieldset>
      </form>
    </section>
    
  </body>
</html>