package pl.polsl.tulczyjew.lukasz;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.polsl.tulczyjew.lukasz.model.Flight;

/**
 * Class for performing CRUD operation through EJBs.
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */

@Stateless
@LocalBean
public class FlightBean {
    
    /**
     * Manager designed to perform CRUD operations.
     */
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Create entity in database.
     * @param flight New flight.
     */
    public void createFlight(Flight flight) {
        this.entityManager.persist(flight);
    }
    
    /**
     * Read flight from the database.
     * @param id Id of the flight.
     * @return Flight instance.
     */
    public Flight readFlight(int id) {
        Flight flight;
        flight = this.entityManager.find(Flight.class, id);
        return flight;
    }
    
    /**
     * Update entity in database.
     * @param flight Flight to be updated.
     */
    public void updateFlight(Flight flight) {
        this.entityManager.merge(flight);
    }
    
    /**
     * Delete flight by id.
     * @param id Id of the flight to be deleted.
     */
    public void deleteFlight(int id) {
        Flight flight;
        flight = this.entityManager.find(Flight.class, id);
        if (flight !=null ) {
            this.entityManager.remove(flight);
        }
    }
    
    /**
     * Read all flights.
     * @return List of all flights.
     */
    public List<Flight> readAllFlights() {
        return this.entityManager.createNamedQuery(
                "Flight.findAll").getResultList();
    }
}
