
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class stdlogin extends HttpServlet {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();

        String user = request.getParameter("stdemail");
        String pass = request.getParameter("stdpass");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from studentdetails where email=? and password=?");
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pw.println("<script>alert('Login successfully');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("//main.html");
                rd.include(request, response);
            } else {
                pw.println("<script>alert('Failed to Login');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("//login.html");
                rd.include(request, response);
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            pw.println(e.getMessage());
        }

    }
}
