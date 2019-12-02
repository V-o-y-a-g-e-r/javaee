package pl.polsl.tulczyjew.lukasz.flight;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Read flight servlet.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "ReadFlightServlet", urlPatterns = {"/ReadFlightServlet"})
public class ReadFlightServlet extends HttpServlet {

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
            List<Flight> allFlights = this.flightBean.readAllFlights();
            for(Flight flight: allFlights) {
                out.println(flight.toString() + "<br/>");
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