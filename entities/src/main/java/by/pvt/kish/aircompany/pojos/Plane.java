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
    private Long pid;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int flightRange;

    @OneToMany(mappedBy = "plane")
    private Set<Flight> flights;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('AVAILABLE','MAINTENANCE','BLOCKED')", insertable = false)
    private PlaneStatus status = PlaneStatus.AVAILABLE;

    @OneToOne(mappedBy = "plane", cascade=CascadeType.ALL)
    private PlaneCrew planeCrew;

    public Plane() {
    }

    /**
     * @param model     - plane model
     * @param capacity  - plane passenger capacity
     * @param range     - plane flight range
     */

    public Plane(String model, int capacity, int range) {
        this.model = model;
        this.capacity = capacity;
        this.flightRange = range;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRange() {
        return flightRange;
    }

    public void setRange(int range) {
        this.flightRange = range;
    }

    public PlaneCrew getPlaneCrew() {
        return planeCrew;
    }

    public void setPlaneCrew(PlaneCrew team) {
        this.planeCrew = team;
    }

    public PlaneStatus getStatus() {
        return status;
    }

    public void setStatus(PlaneStatus status) {
        this.status = status;
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

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
