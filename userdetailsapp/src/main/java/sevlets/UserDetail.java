package sevlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.System.out;

public class UserDetail extends HttpServlet {

    private ServletConfig config;

    private Connection connection;
    private String tableName;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;

        try {
            // global parameters from ServletContext
            ServletContext context = config.getServletContext();
            String url = context.getInitParameter("dbURL");
            String user = context.getInitParameter("dbUser");
            String password = context.getInitParameter("dbPassword");

            // servlet-specific parameter from ServletConfig
            tableName = config.getInitParameter("tableName");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            out.println("DB Connection Established!");
        } catch (Exception e) {
            throw new ServletException("DB Init Failed: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        //get data
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String gender = req.getParameter("gender");

      //  System.out.println("name: " + name + " email: " + email + " country: " + country + " gender: " + gender);

        if ("delete".equals(action)) {
            this.doDelete(req, resp); // call your delete logic
        } else {
            // handle other post actions (like add/update user)

            try {
                String sql = "INSERT INTO " + tableName + " (name, email, country,gender) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, country);
                ps.setString(4, gender);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    resp.sendRedirect("UserDetail");
                } else {
                    out.println("<h2> Registration Failed!</h2>");
                }

            } catch (Exception e) {
                out.println("<h2>Error: " + e.getMessage() + "</h2>");
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try{
            String querry = "SELECT * FROM " + tableName;
            PreparedStatement ps = connection.prepareStatement(querry);
            ResultSet rs = ps.executeQuery();
            out.println("<html><body>");
            out.println("<h2>Data from " + tableName + "</h2>");
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String gender = rs.getString("gender");
                out.print("<p>" + name + " | " + email + " | " + country + " | " + gender + "</p>" );

             //  out.println("<button onclick=\"deleteUser('" + email + "')\">Delete</button>");
                out.println("<form action='UserDetail' method='post'>");
                out.println("<input type='hidden' name='action' value='delete' />");
                out.println("<input type='hidden' name='email' value='" + email + "' />");
                out.println("<button type='submit'>Delete</button>");
                out.println("</form>");

            }
            out.println("<br><a href='index.html'>Add New User</a>");
            out.println("</body></html>");
        }
        catch (Exception e){
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");

        try {
            String sql = "DELETE FROM " + tableName + " WHERE email = ?";
        PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, email);
            int rowsAffected = ps.executeUpdate();
           // System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected > 0) {
                resp.getWriter().println("<p>User with email " + email + " deleted successfully.</p>");
                resp.sendRedirect(config.getServletContext().getContextPath() + "/UserDetail");
            } else {
                resp.getWriter().println("<p>No user found with email " + email + ".</p>");
            }

        } catch (Exception e) {
            resp.getWriter().println("<p>Error deleting user: " + e.getMessage() + "</p>");
        }

    }

    @Override
    public void destroy() {
        try {
            connection.close();
            out.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
