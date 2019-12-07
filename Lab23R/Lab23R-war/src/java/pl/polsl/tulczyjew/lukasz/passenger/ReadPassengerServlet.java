package pl.polsl.tulczyjew.lukasz.passenger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.tulczyjew.lukasz.CookieManagerServlet;
import pl.polsl.tulczyjew.lukasz.PassengerBean;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Read passenger servlet.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "ReadPassengerServlet", urlPatterns = {"/ReadPassengerServlet"})
public class ReadPassengerServlet extends HttpServlet {

    @EJB
    PassengerBean passengerBean;

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
            List<Passenger> allPassengers = this.passengerBean.readAllPassengers();
            for (Passenger passenger : allPassengers) {
                out.println(passenger.toString() + "<br/>");
            }
            String key = "ReadPassenger";
            CookieManagerServlet.addCookie(request, response, key);
        }
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
