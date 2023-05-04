import java.io.*;
import java.util.*;

public class LoginSystem {


    static ArrayList<User> userList = new ArrayList<>();
    static boolean loggedIn = false;

    public static void main(String[] args) throws IOException {
        readUserFile(); // Load existing user data from file
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            if (loggedIn) {
                System.out.println("Enter 1 to sign out.");
                System.out.println("Enter 2 to secure your account!.");
            } else {
                System.out.println("Enter 1 to register a new account.");
                System.out.println("Enter 2 to log in to an existing account.");
            }
            System.out.println("Enter 3 to exit.");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (loggedIn) {
                            loggedIn = false;
                            System.out.println("You have been signed out.");
                        } else {
                            register(scanner);
                        }
                        break;
                        case 2:
                        if (loggedIn) {
                            Random rand = new Random();
                            int num1 = rand.nextInt(10) + 1; // Generate a random number between 1 and 10
                            int num2 = rand.nextInt(10) + 1; // Generate another random number between 1 and 10
                            int answer = num1 * num2 + 7; // Calculate the answer to the math problem
                            System.out.println("What is " + num1 + "*" + num2 + "+7?");
                            int userAnswer = scanner.nextInt(); 
                            scanner.nextLine(); 
                            if (userAnswer == answer) {
                                System.out.println("Your account is secured!.");
                                break;
                            } else {
                                System.out.println("Incorrect answer. Try again.");
                            }
                        } else {
                            login(scanner);
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        writeUserFile(); // Save user data to file
    }


    static void register(Scanner scanner) throws IOException {
        System.out.println("Enter a username:");
        String username = scanner.nextLine();
        if (isUserRegistered(username)) {
            System.out.println("This username is already taken. Please choose another one.");
            return;
        }
        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        userList.add(newUser);
        System.out.println("Account created successfully.");
        writeUserToFile(newUser); // Add the new user to the file
    }
    
    static void writeUserToFile(User u) throws IOException {
        File file = new File("user_data.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // append flag set to true
        writer.write(u.getUsername() + "," + u.getPassword() + "\n");
        writer.close();
    }
    

    static void login(Scanner scanner) {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        User loginUser = getUser(username);
        if (loginUser == null) {
            System.out.println("This username is not registered. Please register first.");
            return;
        }
        if (!loginUser.getPassword().equals(password)) {
            System.out.println("Incorrect password for this username.");
            return;
        }
        loggedIn = true;
        System.out.println("Welcome back, " + username + "!");
    }

    static boolean isUserRegistered(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    static User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


    static void readUserFile() throws IOException {
        File file = new File("user_data.txt");
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = (parts.length > 1) ? parts[1] : "";
                User user = new User(username, password);
                userList.add(user);
            }
            reader.close();
        }
        
    }

    static void writeUserFile() throws IOException {
        File file = new File("user_data.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (User user : userList) {
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
        }
        writer.close();
    }

    public static class User {
        private String username;
        private String password;
    
        public User(String u, String p) {
            username = u;
            password = p;
        }
    
        public String getUsername() {
            return username;
        }
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String pa) {
            password = pa;
        }
    }

}