package by.pvt.kish.aircompany.pojos;

import by.pvt.kish.aircompany.enums.PlaneStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * This class represents the Plane model.
 * The plane is used for Flight.
 * The plane is characterized by passenger capacity of <code>capacity</code> and a flight range of <code>range</code>
 * For service flights on a particular aircraft,
 * flight crew should consist of particular number of professionals (pilots, navigators, radiooperators, stewardesses).
 * These amounts are described by Map <code>team</code>
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
@Entity
public class Plane implements Serializable {

    @Id
    @GeneratedValue
    public Long getPid() {
        return pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    private Long pid;

    @Column(nullable = false)
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    private String model;

    @Column(nullable = false)
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    private int capacity;

    @Column(nullable = false)
    public int getFlightRange() {
        return flightRange;
    }
    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }
    private int flightRange;

    @OneToMany(mappedBy = "plane")
    public Set<Flight> getFlights() {
        return flights;
    }
    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
    private Set<Flight> flights;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('AVAILABLE','MAINTENANCE','BLOCKED')")
    public PlaneStatus getStatus() {
        return status;
    }
    public void setStatus(PlaneStatus status) {
        this.status = status;
    }
    private PlaneStatus status = PlaneStatus.AVAILABLE;

    @OneToOne(mappedBy = "plane", cascade=CascadeType.ALL)
    public PlaneCrew getPlaneCrew() {
        return planeCrew;
    }
    public void setPlaneCrew(PlaneCrew crew) {
        this.planeCrew = crew;
    }
    private PlaneCrew planeCrew;

    public Plane() {
    }

    /**
     * @param model     - plane model
     * @param capacity  - plane passenger capacity
     * @param flightRange     - plane flight range
     */

    public Plane(String model, int capacity, int flightRange) {
        this.model = model;
        this.capacity = capacity;
        this.flightRange = flightRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (capacity != plane.capacity) return false;
        if (flightRange != plane.flightRange) return false;
        if (pid != null ? !pid.equals(plane.pid) : plane.pid != null) return false;
        if (model != null ? !model.equals(plane.model) : plane.model != null) return false;
        return status == plane.status;

    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + flightRange;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "pid=" + pid +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", flightRange=" + flightRange +
                ", status=" + status +
                ", planeCrew=" + planeCrew +
                '}';
    }
}
