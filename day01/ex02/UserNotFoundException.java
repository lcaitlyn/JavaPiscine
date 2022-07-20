public class UserNotFoundException extends RuntimeException {
    UserNotFoundException() {
        System.err.println("Error! User not found.");
    }

    UserNotFoundException(int id) {
        System.err.println("Error! User {identifier = " + id + "} not found.");
    }
}
