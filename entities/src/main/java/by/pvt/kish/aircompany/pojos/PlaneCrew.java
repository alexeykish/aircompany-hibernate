package by.pvt.kish.aircompany.pojos;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * This class represents the Plane crew model
 * @author Kish Alexey
 */
@Entity
public class PlaneCrew {

    @Id
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "plane"))
    @GeneratedValue(generator = "generator")
    public Long getPid() {
        return pid;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }
    private Long pid;

    @Column(nullable = false)
    public int getNumberOfPilots() {
        return numberOfPilots;
    }
    public void setNumberOfPilots(int numberOfPilots) {
        this.numberOfPilots = numberOfPilots;
    }
    private int numberOfPilots;

    @Column
    public int getNumberOfNavigators() {
        return numberOfNavigators;
    }
    public void setNumberOfNavigators(int numberOfNavigators) {
        this.numberOfNavigators = numberOfNavigators;
    }
    private int numberOfNavigators;

    @Column
    public int getNumberOfRadiooperators() {
        return numberOfRadiooperators;
    }
    public void setNumberOfRadiooperators(int numberOfRadiooperators) {
        this.numberOfRadiooperators = numberOfRadiooperators;
    }
    private int numberOfRadiooperators;

    @Column
    public int getNumberOfStewardesses() {
        return numberOfStewardesses;
    }
    public void setNumberOfStewardesses(int numberOfStewardesses) {
        this.numberOfStewardesses = numberOfStewardesses;
    }
    private int numberOfStewardesses;

    @OneToOne
    @PrimaryKeyJoinColumn
    public Plane getPlane() {
        return plane;
    }
    public void setPlane(Plane plane) {
        this.plane = plane;
    }
    private Plane plane;

    public PlaneCrew() {
    }

    public PlaneCrew(int numberOfPilots, int numberOfNavigators, int numberOfRadiooperators, int numberOfStewardesses) {
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadiooperators = numberOfRadiooperators;
        this.numberOfStewardesses = numberOfStewardesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaneCrew planeCrew = (PlaneCrew) o;

        if (numberOfPilots != planeCrew.numberOfPilots) return false;
        if (numberOfNavigators != planeCrew.numberOfNavigators) return false;
        if (numberOfRadiooperators != planeCrew.numberOfRadiooperators) return false;
        if (numberOfStewardesses != planeCrew.numberOfStewardesses) return false;
        return pid != null ? pid.equals(planeCrew.pid) : planeCrew.pid == null;

    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + numberOfPilots;
        result = 31 * result + numberOfNavigators;
        result = 31 * result + numberOfRadiooperators;
        result = 31 * result + numberOfStewardesses;
        return result;
    }

    @Override
    public String toString() {
        return "PlaneCrew{" +
                "pid=" + pid +
                ", numberOfPilots=" + numberOfPilots +
                ", numberOfNavigators=" + numberOfNavigators +
                ", numberOfRadiooperators=" + numberOfRadiooperators +
                ", numberOfStewardesses=" + numberOfStewardesses +
                '}';
    }
}
