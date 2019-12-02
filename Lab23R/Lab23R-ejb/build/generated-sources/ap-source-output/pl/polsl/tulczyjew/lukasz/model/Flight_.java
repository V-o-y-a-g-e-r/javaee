package pl.polsl.tulczyjew.lukasz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.polsl.tulczyjew.lukasz.model.Passenger;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T02:06:25")
@StaticMetamodel(Flight.class)
public class Flight_ { 

    public static volatile ListAttribute<Flight, Passenger> passengers;
    public static volatile SingularAttribute<Flight, String> aircraftType;
    public static volatile SingularAttribute<Flight, String> departureLocation;
    public static volatile SingularAttribute<Flight, Integer> id;
    public static volatile SingularAttribute<Flight, String> arrivalLocation;

}