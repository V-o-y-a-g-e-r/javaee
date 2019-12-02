package pl.polsl.tulczyjew.lukasz.passenger;

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
import pl.polsl.tulczyjew.lukasz.PassengerBean;
import pl.polsl.tulczyjew.lukasz.model.Flight;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Create passenger servlet.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "CreatePassengerServlet", urlPatterns = {"/CreatePassengerServlet"})
public class CreatePassengerServlet extends HttpServlet {

    
    @EJB
    FlightBean flightBean;
    
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
            String firstName, lastName, serviceClass, flightId;
            firstName = request.getParameter("first_name");
            lastName = request.getParameter("last_name");
            serviceClass = request.getParameter("service_class");
            flightId = request.getParameter("flight_id");
            
            final Pattern label = Pattern.compile("^[a-zA-Z]{1,100}");
            if (!label.matcher(firstName).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "First name is in incorrect format.");
                return;
            }
            if (!label.matcher(lastName).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Last name is in incorrect format.");
                return;
            }
            if (!label.matcher(serviceClass).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Serivce class is in incorrect format.");
                return;
            }
            if (!Pattern.compile("^[0-9]{1,}$").matcher(flightId).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the id is incorrect.");
                return;
            }
            int intFlightId = Integer.parseInt(flightId);
            Flight flight = this.flightBean.readFlight(intFlightId);
            if (flight != null) {
                this.passengerBean.createPassenger(
                    new Passenger(firstName, lastName,serviceClass, flight));
                out.println("Passenger was added.");
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
     * @param request Servlet Request.
     * @param response Servlet Response.
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
