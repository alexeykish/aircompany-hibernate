/**
 *
 */
package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.FlightStatus;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
public class Flight implements Serializable {
    private Long fid;
    private Date date;
    private Airport from;
    private Airport to;
    private Plane plane;
    private List<Employee> team;
    private FlightStatus status;

    public Flight() {

    }

    /**
     * @param fid    - flight id
     * @param date   - flight date (departure date)
     * @param from   - the place where the flight departs
     * @param to     - the place where the flight arrives
     * @param plane  - the plane on which the flight is carried out
     * @param team   - the flight team that serves the flight
     * @param status - the status of the flight
     */
    public Flight(Long fid, Date date, Airport from, Airport to, Plane plane, List<Employee> team, FlightStatus status) {
        this.fid = fid;
        this.date = date;
        this.from = from;
        this.to = to;
        this.plane = plane;
        this.team = team;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (fid != null ? !fid.equals(flight.fid) : flight.fid != null) return false;
        if (date != null ? !date.equals(flight.date) : flight.date != null) return false;
        if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
        if (to != null ? !to.equals(flight.to) : flight.to != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        if (team != null ? !team.equals(flight.team) : flight.team != null) return false;
        return status == flight.status;

    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}
