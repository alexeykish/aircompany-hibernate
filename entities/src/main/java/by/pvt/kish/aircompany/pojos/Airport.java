package by.pvt.kish.aircompany.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This class represents the Airport model.
 * The airport is used as a flights place of departure and place of arrival.
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
@Entity
public class Airport implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Long aid;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "departure")
    private Set<Flight> derartureFlights;

    @OneToMany(mappedBy = "arrival")
    private Set<Flight> arrivalFlights;

    public Airport() {
    }

    /**
     * @param city - the place where the airport is located
     */
    public Airport(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (aid != null ? !aid.equals(airport.aid) : airport.aid != null) return false;
        return city != null ? city.equals(airport.city) : airport.city == null;

    }

    @Override
    public int hashCode() {
        int result = aid != null ? aid.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "aid=" + aid +
                ", city='" + city + '\'' +
                '}';
    }

    public Set<Flight> getDerartureFlights() {
        return derartureFlights;
    }

    public void setDerartureFlights(Set<Flight> derartureFlights) {
        this.derartureFlights = derartureFlights;
    }

    public Set<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(Set<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }
}
