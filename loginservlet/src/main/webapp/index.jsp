<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" errorPage="error.jsp"%>
<html>
  <body>
    <a href="hello">Hello ${param.email}</a>
    <center>
    <div id="mainDiv">
      <form action="main.jsp" method="post">
        <input placeholder="email" name="email" /><br><br>
        <input placeholder="password" name="password" /><br><br>
        <button type="submit">submit</button>
      </form>
    </div>

       <%! int day = 2;%>
       <% if(day>=1 && day<5){%>
       <p>today is weekday</p>
       <% }else {%>
       <p>today is weekend</p>
       <% }%>
       <h3> Switch case example <h3>
       <%
       switch(day){
            case 0 :
                out.println("today is sunday");
                break;
            case 1 :
               out.println("today is Monday");
               break;
             case 2 :
                out.println("today is Tuesday");
                break;
             case 3:
               out.println("Today is Wednesday.");
               break;
             case 4:
                out.println("Today is Thursday.");
                 break;
              case 5:
                out.println("Today is  Friday.");
                 break;
             default:
                out.println("Today is Saturday.");
       }
       %>
    </center>

  </body>
</html>

