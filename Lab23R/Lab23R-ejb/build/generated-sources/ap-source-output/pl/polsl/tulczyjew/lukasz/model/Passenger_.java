package pl.polsl.tulczyjew.lukasz.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.polsl.tulczyjew.lukasz.model.Flight;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T02:06:25")
@StaticMetamodel(Passenger.class)
public class Passenger_ { 

    public static volatile SingularAttribute<Passenger, String> firstName;
    public static volatile SingularAttribute<Passenger, String> lastName;
    public static volatile SingularAttribute<Passenger, Flight> flight;
    public static volatile SingularAttribute<Passenger, String> serviceClass;
    public static volatile SingularAttribute<Passenger, Integer> id;

}