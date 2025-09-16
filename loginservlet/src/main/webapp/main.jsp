<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <header>
    <center>
     <%@ include file="header.jsp" %>
     <center>
    </header>
<body>
    <%
        session.setAttribute("user","akshay");
    %>
    <center>
        <h2>In main.jsp we get Form Data</h2>
        <ul>
            <p>Email :-</p>
            <li><%= request.getParameter("email") %></li>

            <p>Password :-</p>
            <li><%= request.getParameter("password") %></li>
        </ul>

    </center>
    <p>Accessing the param with EL Email :- ${param["email"]}</p>
    <p>Session Data: ${user}</p>
    <p>5 + 5 = ${5+5}</p>
</body>
 <%@ include file="footer.jsp" %>
</html>
