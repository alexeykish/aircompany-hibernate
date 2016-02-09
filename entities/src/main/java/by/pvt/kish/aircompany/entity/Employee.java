package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.EmployeeStatus;
import by.pvt.kish.aircompany.enums.Position;

import java.io.Serializable;

/**
 * This class represents the Employee model.
 * The employee is in the flight team (can belong to several teams).
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
public class Employee implements Serializable {
    private Long eid;
    private String firstName;
    private String lastName;
    private Position position;
    private EmployeeStatus status;

    public Employee() {
    }

    /**
     * @param eid       - employee id
     * @param firstName - employee firstname
     * @param lastName  - employee lastname
     * @param position  - employee position in a flight team
     * @param status    - employee available status
     */
    public Employee(Long eid, String firstName, String lastName, Position position, EmployeeStatus status) {
        super();
        this.eid = eid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (eid != null ? !eid.equals(employee.eid) : employee.eid != null) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (position != employee.position) return false;
        return status == employee.status;

    }

    @Override
    public int hashCode() {
        int result = eid != null ? eid.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
