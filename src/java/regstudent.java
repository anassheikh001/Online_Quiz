
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class regstudent extends HttpServlet {

    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String c_pass = request.getParameter("c_pass");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement ps = con.prepareStatement("insert into  studentdetails values (?,?,?,?,?)");
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email);
            ps.setString(4, pass);
            ps.setString(5, c_pass);

            int rs = ps.executeUpdate();

            pw.println("<script>alert('registered successfully');</script>");
            RequestDispatcher rd = request.getRequestDispatcher("//login.html");
            rd.include(request, response);

        } catch (Exception e) {
            pw.println(e.getMessage());

        }

    }
}
