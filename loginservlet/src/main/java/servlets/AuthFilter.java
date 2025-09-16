package servlets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        System.out.println("request in auth filter");
        HttpSession session = req.getSession(false);
      session.setAttribute("email", req.getParameter("email"));

        if ( session!=null) {
            System.out.println("session exists");
            chain.doFilter(req, res);
        }
        else {
            System.out.println("session is null");
            req.getRequestDispatcher("/login").forward(req, res);
        }
    }
}
