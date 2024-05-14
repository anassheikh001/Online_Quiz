
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class admin extends HttpServlet {
    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            String user = request.getParameter("u1");
            String pass = request.getParameter("p1");
            if (user.equals("admin") && pass.equals("admin")) {
                pw.println("<script>alert('Login successfully');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("//qadd.html");
                rd.include(request, response);
            } else {
                pw.println("<script>alert('incorrect details');</script>");
                RequestDispatcher rd = request.getRequestDispatcher("//alogin.html");
                rd.include(request, response);
            }
        } catch (Exception e) {
            
        }
    }
}
