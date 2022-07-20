public class UserNotFoundException extends RuntimeException{
    UserNotFoundException() {
        System.err.println("Error! User not found.");
    }
}
