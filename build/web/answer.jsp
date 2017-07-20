<%-- 
    Document   : answer
    Created on : 4-Dec-2016, 6:50:26 PM
    Author     : Kalen
--%>

<%@page import="java.io.IOException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="db.*" %>
<%@ page import="game.*" %>


<%! private int[] getQuestion(HttpServletRequest request, HttpSession session) throws IOException{
        
        int[] buttonPressed = new int[2];
        //Loops through the columns (6 categories)
        for(int i = 0; i < 6; i++){
            //Loops through the rows (5 questions)
            for(int j = 0; j < 5; j++){
                //This checks which cell was selected via column and row
                if(request.getParameter(j + "" + i) != null){
                    session.setAttribute("price", Integer.parseInt(request.getParameter(j + "" + i)));

                    //Stores the position in an int array (first element: row; second element: column)
                    buttonPressed[0] = j;
                    buttonPressed[1] = i;
                }
            }
        }
        return buttonPressed;
}%>


<% 
   //Get the specific cell picked
   int[] buttonPressed = getQuestion(request, session);
   Game g = (Game) session.getAttribute("game");
   
   //Gets the categories based on the round
   Category[] cat = g.loadRound((Integer) session.getAttribute("round"));
   
   //Gets the questions based on the category (specified by the column retrieved by getQuestion method)
   Question[] questions = cat[buttonPressed[1]].loadQuestions();
   session.setAttribute("questionArray", questions);
   
   //Gets the specific question (specified by the row retrieved by getQuestion method)
   String question = questions[buttonPressed[0]].getText();
   session.setAttribute("question", questions[buttonPressed[0]]);
%>


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
        <form method="POST" action="index.jsp">
          <button id="submit" type="submit">Logout</button>
        </form>	
      </nav>
    </header>
    <section id="finalBody">
      <h1>Answer</h1>
      <div id="finalQuestionBox">
        <form class="pure-form pure-form-stacked" action="check.jsp">
          <fieldset>
            <legend></legend>
            <label id="questionLbl" for="question">Question</label>
            <p id="questionPlaceHolder"><%= question%> </p><br>

            <label id="answerLbl" for="answer">Answer</label>
            <input type="text" name="answer" id="answerText" placeholder="Type Answer Here" autofocus>

            <button type="submit" class="pure-button pure-button-primary">Submit</button>
          </fieldset>
        </form>
      </div>
    </section>
  </body>
</html>