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
import pl.polsl.tulczyjew.lukasz.PassengerBean;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

/**
 * Delete passenger servlet.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@WebServlet(name = "DeletePassengerServlet", urlPatterns = {"/DeletePassengerServlet"})
public class DeletePassengerServlet extends HttpServlet {
    
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
            String passengerId = request.getParameter("passenger_id");
            if (!Pattern.compile("^[0-9]{1,}$").matcher(passengerId).matches()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "The type of the id is incorrect.");
                return;
            }
            int intPassengerId = Integer.parseInt(passengerId);
            Passenger passenger = this.passengerBean
                    .readPassenger(intPassengerId);
            if (passenger != null) { 
                this.passengerBean.deletePassenger(intPassengerId);
                out.println("Passenger was deleted.");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "There is no such passenger.");
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