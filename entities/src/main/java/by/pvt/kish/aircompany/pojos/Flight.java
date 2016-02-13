/**
 *
 */
package by.pvt.kish.aircompany.pojos;

import by.pvt.kish.aircompany.enums.FlightStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the Flight model.
 * Flight is carried out from a place of departure <code>from</code> instead of arrivals <code>to</code> plane <code>plane</code>
 * Flights are operated on a specific date <code>date</code>
 * Each flight team services the particular flight (specified id flight team <code>tid</code>
 * The flight could be in different status of complete
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue
    public Long getFid() {
        return fid;
    }
    public void setFid(Long fid) {
        this.fid = fid;
    }
    private Long fid;

    @Temporal(TemporalType.DATE)
    @Column
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_DEPARTURE_ID")
    public Airport getDeparture() {
        return departure;
    }
    public void setDeparture(Airport departure) {
        this.departure = departure;
    }
    private Airport departure;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_ARRIVAL_ID")
    public Airport getArrival() {
        return arrival;
    }
    public void setArrival(Airport arrival) {
        this.arrival = arrival;
    }
    private Airport arrival;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_PLANE_ID")
    public Plane getPlane() {
        return plane;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    private Plane plane;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('CREATED','READY','CANCELED','DEPARTED','REDIRECTED','ARRIVAL')")
    public FlightStatus getStatus() {
        return status;
    }
    public void setStatus(FlightStatus status) {
        this.status = status;
    }
    private FlightStatus status = FlightStatus.CREATED;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "t_flights_employees",
            joinColumns = @JoinColumn(name = "f_fid"),
            inverseJoinColumns = @JoinColumn(name = "f_eid"))
    public Set<Employee> getCrew() {
        return crew;
    }
    public void setCrew(Set<Employee> crew) {
        this.crew = crew;
    }
    private Set<Employee> crew = new HashSet();

    public Flight() { }

    /**
     * @param date      - flight date (departure date)
     * @param departure - the place where the flight departs
     * @param arrival   - the place where the flight arrives
     * @param plane     - the plane on which the flight is carried out
     * @param crew      - the flight team that serves the flight
     * @param status    - the status of the flight
     */
    public Flight(Date date, Airport departure, Airport arrival, Plane plane, FlightStatus status, Set<Employee> crew) {
        this.date = date;
        this.departure = departure;
        this.arrival = arrival;
        this.plane = plane;
        this.status = status;
        this.crew = crew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (fid != null ? !fid.equals(flight.fid) : flight.fid != null) return false;
        if (date != null ? !date.equals(flight.date) : flight.date != null) return false;
        if (departure != null ? !departure.equals(flight.departure) : flight.departure != null) return false;
        if (arrival != null ? !arrival.equals(flight.arrival) : flight.arrival != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        return status == flight.status;

    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "fid=" + fid +
                ", date=" + date +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", plane=" + plane +
                ", status=" + status +
                '}';
    }
}
