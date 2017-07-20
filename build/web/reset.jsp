<%@page import="game.Game"%>
<%@page import="db.DB"%>
<% 
  DB db = new DB();
  session.setAttribute("database", db);
  session.setAttribute("game", new Game());
  session.setAttribute("round", new Integer(1));
  session.setAttribute("playerAmount", new Integer(0));
  session.setAttribute("questionsSelected", new Integer(0));

  response.sendRedirect("http://localhost:8080/Jeopardy/game.jsp");
%>