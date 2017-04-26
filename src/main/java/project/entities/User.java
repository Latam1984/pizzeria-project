package project.entities;

/**
 * The class implements a set of standard methods for working
 * with entity of the User.
 *
 * @author Aleksey
 */
public class User implements Model{

    /**
     * The unique identifier for each component.
     */
    private int id;

    /**
     * The access roll of user.
     */
    private String roll;

    /**
     * The login of user.
     */
    private String login;

    /**
     * The password of user.
     */
    private String password;

    /**
     * The default constructor of entities user.
     */
    public User() {
    }

    /**
     * Constructor
     *
     * @param id a unique identifier for components.
     * @param roll an access roll of user.
     * @param login of user.
     * @param password of user.
     *
     */
    public User(int id, String roll, String login, String password) {
        this.id = id;
        this.roll = roll;
        this.login = login;
        this.password = password;
    }

    /**
     * Returns a string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roll='" + roll + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
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
        if (!(object instanceof User)) return false;

        User user = (User) object;

        if (id != user.id) return false;
        if (!roll.equals(user.roll)) return false;
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
        result = 31 * result + roll.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    /**
     * Getters and setters methods by all fields of Components.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
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
}
