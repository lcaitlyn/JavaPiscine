public interface UserList {
    void addUser(User newUser);
    User getById(int id);
    User getByIndex(int index);
    Integer getUsersNumber();
}
