<%@ page import="db.*" %>
<%@ page import="game.*" %>

<% int round = (Integer) session.getAttribute("round"),
       numOfSelected = (Integer) session.getAttribute("questionsSelected");
   Game game = (Game) session.getAttribute("game");
%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Jeopardy - Game</title>
    </head>
    <body>
        <header>
            <img id="gameLogo" src="images/jeopardy-logo.png" alt="Jeopardy Logo">
            <nav>
                <form method="POST" action="index.jsp">
                    <button id="submit" type="submit">Logout</button>
                    <p id="roundNumber">Round <%= session.getAttribute("round") %> </p>
                    <p id="playerAmount">Current amount: <%= session.getAttribute("playerAmount") %></p>
                </form>	
            </nav>
        </header>
        <div class="tg-wrap"><table class="tg">
                <tr>
                    <% int colNum = 1;
                       for(Category c : game.loadRound(round)){%>
                        <th class="tg-baqh tbl-header col <%= colNum%>" name="col <%= colNum++ %>"> <%= c.getCategory() %></th>
                    <%}%>
                </tr>
                <form action="answer.jsp">
                <%  
                    int price = 200;
                    int i = 0;
                    numOfSelected = 0;

                    for(int j = 0 ; j < 5 ; j++){
                        %>
                <tr>
                    <%
                        for(Category c : game.loadRound(round)){
                            Question[] q = c.loadQuestions();%>
                                <td class="tg-baqh col<%= i %>" name="col<%= i%>">
                                    <% if(!q[j].getText().equals("")){ %>
                                    <button class="priceButton" type="submit" name="<%= j%><%= i%>" value="<%= 
                                        (round * price) %>">$<%= round*price%></button>
                                    <%}else session.setAttribute("questionsSelected", new Integer(++numOfSelected));%>
                                </td>
                    <%i++;}
                    price += 200;%>
                </tr>
                <%i = 0;}
                    if(numOfSelected == 30){
                            session.setAttribute("round", new Integer(++round));
                    if(round == 3){
                        response.sendRedirect("http://localhost:8080/Jeopardy/finalscreen.jsp");
                        %>
                    <%}else
                        response.sendRedirect("http://localhost:8080/Jeopardy/game.jsp");
                    }
                %>
                </form>
        </table></div>
    </body>
</html>