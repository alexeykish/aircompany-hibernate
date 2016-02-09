package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.enums.Position;

import java.io.Serializable;
import java.util.Map;

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
public class Plane implements Serializable {

    private Long pid;
    private String model;
    private int capacity;
    private int range;
    private Map<Position, Integer> team;
    private PlaneStatus status;

    public Plane() {
    }

    /**
     * @param pid      - plane id
     * @param model    - plane model
     * @param capacity - plane passenger capacity
     * @param range    - plane flight range
     * @param team     - team to service flight
     * @param status   - plane available status
     */

    public Plane(Long pid, String model, int capacity, int range, Map<Position, Integer> team, PlaneStatus status) {
        this.pid = pid;
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.team = team;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (capacity != plane.capacity) return false;
        if (range != plane.range) return false;
        if (pid != null ? !pid.equals(plane.pid) : plane.pid != null) return false;
        if (model != null ? !model.equals(plane.model) : plane.model != null) return false;
        if (team != null ? !team.equals(plane.team) : plane.team != null) return false;
        return status == plane.status;

    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + range;
        result = 31 * result + (team != null ? team.hashCode() : 0);
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
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Map<Position, Integer> getTeam() {
        return team;
    }

    public void setTeam(Map<Position, Integer> team) {
        this.team = team;
    }

    public PlaneStatus getStatus() {
        return status;
    }

    public void setStatus(PlaneStatus status) {
        this.status = status;
    }
}
