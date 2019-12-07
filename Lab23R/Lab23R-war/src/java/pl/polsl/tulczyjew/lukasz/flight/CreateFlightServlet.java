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
import pl.polsl.tulczyjew.lukasz.CookieManagerServlet;
import pl.polsl.tulczyjew.lukasz.FlightBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Create flight servlet.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "CreateFlightServlet", urlPatterns = {"/CreateFlightServlet"})
public class CreateFlightServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String aircraftType, departureLocation, arrivalLocation;
            aircraftType = request.getParameter("aircraft_type");
            departureLocation = request.getParameter("departure_location");
            arrivalLocation = request.getParameter("arrival_location");

            final Pattern label = Pattern.compile("^[a-zA-Z]{3,}$");
            if (!Pattern.compile("^[a-zA-Z0-9]{2,}$")
                    .matcher(aircraftType).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the aircraft is incorrect.");
                return;
            }
            if (!label.matcher(departureLocation).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the depature location is incorrect.");
                return;
            }
            if (!label.matcher(arrivalLocation).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the arrival location is incorrect.");
                return;
            }
            this.flightBean.createFlight(new Flight(aircraftType,
                    departureLocation, arrivalLocation));
            out.println("Flight was added.");

            String key = "CreateFlight";
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
