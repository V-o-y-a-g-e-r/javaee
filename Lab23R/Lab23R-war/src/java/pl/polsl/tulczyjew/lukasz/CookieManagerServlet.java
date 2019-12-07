package pl.polsl.tulczyjew.lukasz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create flight servlet.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "CookieManagerServlet", urlPatterns = {"/CookieManagerServlet"})
public class CookieManagerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request Servlet request.
     * @param response Servlet response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.getRequestDispatcher("/WEB-INF/cookie.html")
                    .include(request, response);
            Cookie[] cookies = request.getCookies();
            out.println("Cookies:");
            for (Cookie cookie : cookies) {
                out.println(cookie.getName() + "\t" + cookie.getValue() + "\n");
            }
        }
    }

    
    public static void addCookie(HttpServletRequest request,
            HttpServletResponse response, String key) {
        Cookie cookie;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            cookie = new Cookie(key, "0");
        } else {
            cookie = Arrays.stream(cookies)
                    .filter(obj -> key.equalsIgnoreCase(obj.getName()))
                    .findFirst().orElse(new Cookie(key, "0"));
        }
        cookie.setValue(Integer.toString(Integer.parseInt(cookie.getValue()) + 1));
        response.addCookie(cookie);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request Servlet request.
     * @param response Servlet response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request Servlet request.
     * @param response Servlet response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return A String containing servlet description.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
