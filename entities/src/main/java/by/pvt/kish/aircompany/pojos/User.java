/**
 *
 */
package by.pvt.kish.aircompany.pojos;

import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class represents the User model.
 * User could be two types: administrator and dispatcher
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long uid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ADMINISTRATOR','DISPATCHER')")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('ONLINE','OFFLINE')", insertable = false)
    private UserStatus status = UserStatus.OFFLINE;

    public User() {
    }

    /**
     * @param uid       - user id
     * @param firstName - user firstname
     * @param lastName  - user lastname
     * @param login     - user login
     * @param password  - user password
     * @param userType  - user type (Administrator or dispatcher)
     * @param status    - user status (Online or offline)
     */
    public User(Long uid, String firstName, String lastName, String login, String password, UserType userType, UserStatus status) {
        super();
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != null ? !uid.equals(user.uid) : user.uid != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (userType != user.userType) return false;
        return status == user.status;

    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", status=" + status +
                '}';
    }
}
