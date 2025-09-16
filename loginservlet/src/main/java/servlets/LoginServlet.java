package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
   private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("email");
        String pass = request.getParameter("password");

        String adminEmail = config.getInitParameter("email");
        String adminPass = config.getInitParameter("password");

        System.out.println("adminEmail:"+adminEmail);
        System.out.println("adminPass:"+adminPass);

        if(adminEmail.equals(user) && adminPass.equals(pass)) {
            // Forward to WelcomeServlet
            RequestDispatcher rd = request.getRequestDispatcher("welcome");
            System.out.println("request is here");
           // rd.forward(request, response);
            rd.include(request, response);
        } else {
            // Redirect to error page
            response.sendRedirect("error.html");
        }
    }
}
