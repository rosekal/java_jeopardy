<%-- 
    Document   : login.jsp
    Author     : Kalen Rose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Logged In</title>
    </head>
    <body>
        <% if(request.getParameter("username").equals("admin")){ %>

        <h2>Admin page</h2>
        <form name="admintools" action="admin.jsp">
            <input type="submit" name="create" value="Create New Question" />
            <input type="submit" name="update" value="Update Question" />
        </form>
        <%}else{%>
        
        <%}%>
    </body>
</html>
