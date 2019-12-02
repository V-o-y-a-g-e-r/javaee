package pl.polsl.tulczyjew.lukasz.model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity representing a Flight.
 *
 * @author Lukasz Tulczyjew
 * @version 1.0.0
 */
@Entity
@Table(name = "flight")
@NamedQueries({
    @NamedQuery(name = Flight.FIND_ALL, query = "SELECT f FROM Flight f")
})
public class Flight implements Serializable {

    /**
     * Key for the query that returns all flights.
     */
    public static final String FIND_ALL = "Flight.findAll";
    
    /**
     * Default class constructor.
     */
    public Flight() {
    }
    
    /**
     * Named constructor.
     * @param aircraftType Type of the aircraft.
     * @param departureLocation Location of departure the flight.
     * @param arrivalLocation Location of arrival the flight.
     */
    public Flight(String aircraftType, String departureLocation,
            String arrivalLocation) {
        this.aircraftType = aircraftType;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * Id of the flight.
     */
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Type of the aircraft that supports the flight.
     */
    @Column(name = "aircraft_type")
    private String aircraftType;

    /**
     * Location of the departure of the flight.
     */
    @Column(name = "departure_location")
    private String departureLocation;

    /**
     * Location of the arrival of the flight.
     */
    @Column(name = "arrival_location")
    private String arrivalLocation;

    /**
     * One to many relation of the flight to its passengers.
     */
    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();

    /**
     * Get id of flight.
     *
     * @return Id of the flight.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the type of aircraft.
     *
     * @return Type of the aircraft.
     */
    public String getAircraftType() {
        return aircraftType;
    }

    /**
     * Get the location of the departure.
     *
     * @return Location of departure.
     */
    public String getDepatruteLocation() {
        return departureLocation;
    }

    /**
     * Get the location of arrival.
     *
     * @return Location of arrival.
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * Get the list of passengers of the flight.
     *
     * @return List of passengers.
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Set id of the flight.
     *
     * @param id Id of the flight.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set type of the aircraft.
     *
     * @param aircraftType Type of the aircraft.
     */
    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    /**
     * Set departure location.
     *
     * @param depatruteLocation Location of the departure.
     */
    public void setDepatruteLocation(String depatruteLocation) {
        this.departureLocation = depatruteLocation;
    }

    /**
     * Set arrival location.
     *
     * @param arrivalLocation Location of the arrival.
     */
    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * Set the list of passengers.
     *
     * @param passengers List of passengers.
     */
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * Create hash code of the flight based on its id.
     *
     * @return Hash code of the flight.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Compare two flight instances and check if they are equal.
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
        final Flight other = (Flight) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Represents object as string.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("Id: ").append(this.id)
                .append(", aircraft type: ").append(this.aircraftType)
                .append(", departure location: ").append(this.departureLocation)
                .append(", arrival location: ").append(this.arrivalLocation)
                .append(".").append("\n").append("Passengers:").append("\n");
        if (!this.passengers.isEmpty()) {
            for (Passenger p : this.passengers) {
                stringBuilder.append(p.toString()).append("\n");
            }
        } else {
            stringBuilder.append("None");
        }
        return stringBuilder.toString();
    }

    /**
     * Check if class fields are specified correctly.
     *
     * @throws Exception Exception containing error information.
     */
    public void checkEntity() throws Exception {
        final Pattern locationLabel = Pattern.compile("^[a-zA-Z]{1,100}");
        if (!Pattern.compile("^[a-zA-Z]{1,100}\\d{1}")
                .matcher(this.aircraftType).matches()) {
            throw new Exception("Illegal type of the aircraft.");
        }
        if (!locationLabel.matcher(this.departureLocation).matches()) {
            throw new Exception("Illegal location of departure.");

        }
        if (!locationLabel.matcher(this.arrivalLocation).matches()) {
            throw new Exception("Illegal location of arrival.");

        }

    }
}
