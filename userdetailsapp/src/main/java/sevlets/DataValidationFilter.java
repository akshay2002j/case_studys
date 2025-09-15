package sevlets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class DataValidationFilter extends HttpFilter{


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String gender = req.getParameter("gender");


        if (email == null ) {
            res.getWriter().println("<p>Invalid email format</p>");
            return;
        }
        if (name == null ) {
            res.getWriter().println("<p>Name should not be null</p>");
            return;
        }
        if (country == null ) {
            res.getWriter().println("<p>Country should not be null</p>");
            return;
        }
        if (gender == null ) {
            res.getWriter().println("<p>Gender should not be null</p>");
            return;
        }
        chain.doFilter(req, res);

    }
}
