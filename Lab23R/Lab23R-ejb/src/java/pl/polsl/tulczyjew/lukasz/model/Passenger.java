package pl.polsl.tulczyjew.lukasz.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity representing a Passenger.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@Entity
@Table(name = "passenger")
@NamedQueries({
    @NamedQuery(name = Passenger.FIND_ALL, query = "SELECT p FROM Passenger p")
})
public class Passenger implements Serializable {

    /**
     * Key for the query that returns all flights.
     */
    public static final String FIND_ALL = "Passenger.findAll";
    
    /**
     * Default constructor for class.
     */
    public Passenger() {
    }
    
    public Passenger(String firstName, String lastName, String serviceClass,
            Flight flight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.serviceClass = serviceClass;
        this.flight = flight;
    }
    
    /**
     * Id of the passenger.
     */
    @Id
    @Column(name = "passenger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * First name of the passenger.
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Last name of the passenger.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Type of service class of the passenger.
     */
    @Column(name = "service_class")
    private String serviceClass;

    /**
     * Many to one relation of the passenger to his/her flight.
     */
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    /**
     * Get the id of passenger.
     *  
     * @return Id of the passenger.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the first name of passenger.
     *
     * @return First name of the passenger.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of passenger.
     *
     * @return Last name of the passenger.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the type of service class for passenger.
     *
     * @return Type of service class.
     */
    public String getServiceClass() {
        return serviceClass;
    }

    /**
     * Get the flight of passenger.
     *
     * @return Flight of passenger.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Set the id of passenger.
     *
     * @param id Id of the passenger.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set first name of passenger.
     *
     * @param firstName First name of the passenger.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the last name of passenger.
     *
     * @param lastName Last name of the passenger.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the service class of the passenger.
     *
     * @param serviceClass Type of service class.
     */
    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    /**
     * Set the passenger's flight.
     *
     * @param flight Flight of the passenger.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Create hash code of the passenger based on its id.
     *
     * @return Hash code of the passenger.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compare two passengers instances and check if they are equal.
     *
     * @param obj Passed object.
     * @return Boolean indicating whether the object is equal to this instance.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Passenger other = (Passenger) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * To string method.
     *
     * @return String representation of the class.
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("Id: ").append(this.id.toString())
                .append(", first name: ").append(this.firstName)
                .append(", last name: ").append(this.lastName)
                .append(", service class: ").append(this.serviceClass);
        if (this.flight != null) {
            stringBuilder.append(", flight id: ")
                    .append(this.flight.getId().toString()).append(".");

        }
        return stringBuilder.toString();
    }

    /**
     * Check if class fields are specified correctly.
     *
     * @throws Exception Exception containing error information.
     */
    public void checkEntity() throws Exception {
        final Pattern label = Pattern.compile("^[a-zA-Z]{1,100}");
        if (!label.matcher(this.firstName).matches()) {
            throw new Exception("Illegal type of the aircraft.");
        }
        if (!label.matcher(this.lastName).matches()) {
            throw new Exception("Illegal location of departure.");

        }
        if (!label.matcher(this.serviceClass).matches()) {
            throw new Exception("Illegal location of arrival.");

        }

    }

}
