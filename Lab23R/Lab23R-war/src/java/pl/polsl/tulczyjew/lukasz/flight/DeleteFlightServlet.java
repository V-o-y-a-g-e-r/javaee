package pl.polsl.tulczyjew.lukasz.flight;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Delete flight servlet.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "DeleteFlightServlet", urlPatterns = {"/DeleteFlightServlet"})
public class DeleteFlightServlet extends HttpServlet {
    @EJB
    FlightBean flightBean;
    
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
            String flightId = request.getParameter("flight_id");
            if (!Pattern.compile("^[0-9]{1,}$").matcher(flightId).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the id is incorrect.");
                return;
            }
            int intFlightId = Integer.parseInt(flightId);
            Flight flight = this.flightBean.readFlight(intFlightId);
            if (flight != null) { 
                this.flightBean.deleteFlight(intFlightId);
                out.println("Flight was deleted.");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "There is no such flight.");
            } 
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