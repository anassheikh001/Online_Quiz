
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class NextQ extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            HttpSession hs = request.getSession(true);
            ResultSet rs = (ResultSet) hs.getAttribute("resultset");
            int c = Integer.parseInt(hs.getAttribute("correct").toString());
            String a = hs.getAttribute("answer").toString();

            if (request.getParameter("q1").equals(a)) {
                c = c + 1;

            }

            if (rs.next()) {
                pw.println("<html><head><title>Next Question</title>");
                pw.println("<style>");
                pw.println("body { font-family: Arial, sans-serif; background-color: #f0f0f0; margin: 0; padding: 0; }");
                pw.println(".container { max-width: 800px; margin: 50px auto; padding: 20px; }");
                pw.println("h4 { margin-top: 0; }");
                pw.println(".nav { margin-top:20px;}");
                pw.println("nav h1 b { color: #7e57c2; }");
                pw.println("nav { background-color: whitesmoke; display:flex; justify-content: space-between;box-shadow: #fff; padding: 10px; }");
                pw.println(".nav1 a { color: #7e57c2; text-decoration: none; margin-right: 10px; }");
                pw.println("input[type='radio'] { margin-bottom: 10px; }");
                pw.println("input[type='submit'] { margin-top: 20px; padding: 10px 20px; border-radius: 5px; border-style: none; background-color: #7e57c2; color: white; cursor: pointer; }");

                // Media Queries for Responsive Design
                pw.println("@media only screen and (max-width: 600px) {");
                pw.println(".container { width: 90%; }");
                pw.println("nav { flex-direction: column; align-items: center; }");
                pw.println("}");
                pw.println("</style>");
                pw.println("</head><body>");

                pw.println("<nav> <div class='logo'>");
                pw.println("<h1><b>Quizzey</b></h1></div>");
                pw.println("<div class='nav1'>");
                pw.println("<a href='index.html'>Home</a>");
                pw.println("<a href=''>about us</a>");
                pw.println("<a href=''>contact</a>");
                pw.println("</div></nav>");
                pw.println("<div class='container'>");
                pw.println("<form action='NextQ'>");
                pw.println("<div>");
                pw.println("<h2>Question: " + rs.getString(1) + "</h2>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(2) + "'>" + rs.getString(2) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(3) + "'>" + rs.getString(3) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(4) + "'>" + rs.getString(4) + "<br>");
                pw.println("<input type='radio' name='q1' value='" + rs.getString(5) + "'>" + rs.getString(5) + "<br>");
                pw.println("<input type='submit' value='Next Question'>");
                pw.println("</div>");
                pw.println("</form>");
                pw.println("</div>");

                String ans = rs.getString(6);
                hs.setAttribute("answer", ans);
                hs.setAttribute("correct", c);
            } else {
                pw.println("<dialog open>");
                pw.println("<h2>Your total score: " + c + "</h2>");
                pw.println("<form id='redirectForm' action='start'>");
                pw.println("<button type='submit'>OK</button>");
                pw.println("</form>");
                pw.println("</dialog>");
                pw.println("<script>");
                pw.println("document.getElementById('redirectForm').addEventListener('submit', function(event) {");
                pw.println("event.preventDefault();");
                pw.println("window.location.href = 'index.html';");
                pw.println("});");
                pw.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
