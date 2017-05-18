package project.entities;

import javax.persistence.*;
import java.util.List;

/**
 * The class implements a set of standard methods for working
 * with entity of the Users.
 *
 * @author Aleksey
 */
@Entity
@Table(name = "users")
public class Users {

    /**
     * The unique identifier for each user.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The access role of user.
     */
    @Column(name = "ROLE")
    private String role;

    /**
     * The login of user.
     */
    @Column(name = "LOGIN")
    private String login;

    /**
     * The password of user.
     */
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Orders> ordersList;

    /**
     * The default constructor of entities user.
     */
    public Users() {
    }

    /**
     * Constructor
     *
     * @param id       a unique identifier for components.
     * @param role     an access role of user.
     * @param login    of user.
     * @param password of user.
     */
    public Users(int id, String role, String login, String password) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    /**
     * Getters and setters methods by all fields of Users.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this user is the same as the object
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Users)) return false;

        Users user = (Users) object;

        if (id != user.id) return false;
        if (!role.equals(user.role)) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);
    }

    /**
     * Check a hash code value for the user.
     *
     * @return A hash code value for this user.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + role.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the user.
     */
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
