public interface UserList {
    void addUser(User newUser);
    User getById(int id) throws UserNotFoundException;
    User getByIndex(int index) throws UserNotFoundException;
    Integer getUsersNumber();
}
