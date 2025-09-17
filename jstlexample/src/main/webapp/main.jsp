<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" errorPage="error.jsp"%>

<%@ taglib prefix="c"
       uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
    <center>
     <h2>Fill the from</h2>
         <form action="student.jsp" method="post">
                <input placeholder="email" name="email" /><br><br>
                <input placeholder="password" name="password" /><br><br>
                <button type="submit">submit</button>
         </form>
     </center>
    </body>
</html>