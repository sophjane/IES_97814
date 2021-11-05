import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UsernameServlet", value = "/UsernameServlet")
public class UsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>Hello");

        String userName = request.getParameter("username");

        if(userName != null) {
            out.println("" + HTMLFilter.filter(userName) + "</h3>");
        } else {
            out.println("World</h3>");
        }
        out.println("<P>");
        out.print("<form action=\"");
        out.print("UsernameServlet\" ");
        out.println("method=POST>");
        out.println("Username:");
        out.println("<input type=text size=20 name=username>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
        doGet(request, response);
    }
}
