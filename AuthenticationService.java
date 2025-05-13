import java.io.*;
import java.util.*;
/**
 * The `AuthenticationService` class handles user authentication and management.
 * It provides methods for user sign-up, login, and data persistence.
 *
 * Features:
 * - Validates user sign-up information.
 * - Checks user credentials during login.
 * - Stores user data in a serialized file for persistence.
 * - Loads user data from the serialized file on initialization.
 */
public class AuthenticationService {
    private static final String DATA_FILE = "users.ser";
    private List<User> users;

    /**
     * Constructor that initializes the user list by loading data from the file.
     */

    public AuthenticationService() {
        users = loadUsers();
    }
    /**
     * Signs up a new user by validating input and saving user data.
     *
     * @param name     The user's full name.
     * @param email    The user's email address.
     * @param username The desired username.
     * @param password The desired password.
     * @return `true` if the sign-up is successful, `false` otherwise.
     */
    public boolean signup(String name, String email, String username, String password) {
        if (!validateSignupInfo(name, email, username, password)) {
            return false;
        }
        if (findUserByUsername(username) != null) {
            return false;
        }
        int id = users.size() + 1;
        User user = new User(id, name, email, username, password);
        users.add(user);
        saveUsers();
        return true;
    }
    /**
     * Logs in a user by validating their credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return `true` if the login is successful, `false` otherwise.
     */
    public boolean login(String username, String password) {
        User user = findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    private boolean validateSignupInfo(String name, String email, String username, String password) {
        return name != null && !name.isEmpty() &&
               email != null && !email.isEmpty() &&
               username != null && !username.isEmpty() &&
               password != null && !password.isEmpty();
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<User> loadUsers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
