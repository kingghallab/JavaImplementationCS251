import java.io.*;
import java.util.*;

public class AuthenticationService {
    private static final String DATA_FILE = "users.ser";
    private List<User> users;

    public AuthenticationService() {
        users = loadUsers();
    }

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
