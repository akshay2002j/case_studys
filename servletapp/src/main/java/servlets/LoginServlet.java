package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("email");
        String pass = request.getParameter("password");

        if("admin".equals(user) && "1234".equals(pass)) {
            // Forward to WelcomeServlet
            RequestDispatcher rd = request.getRequestDispatcher("welcome");
            rd.forward(request, response);
        } else {
            // Redirect to error page
            response.sendRedirect("error.html");
        }
    }
}
