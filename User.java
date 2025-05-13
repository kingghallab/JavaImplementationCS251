import java.io.Serializable;
/**
 * The `User` class represents a user in the system.
 * It encapsulates details such as the user's ID, name, email, username, and password.
 *
 * Features:
 * - Stores essential information about a user.
 * - Provides getter methods to access user details.
 * - Ensures encapsulation of sensitive data like passwords.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    /**
     * Constructor that initializes a user with the given details.
     *
     * @param id       The unique ID of the user.
     * @param name     The full name of the user.
     * @param email    The email address of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(int id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    /**
     * Getter methods for all class attributes
     */
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
