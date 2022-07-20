public class UsersArrayList implements UserList {
    private int userNumber = 0;
    private User [] arr = new User[10];

    @Override
    public void addUser(User newUser) {
        for (int i = 0; i < userNumber; i++) {
            if (arr[i].getIdentifier() == newUser.getIdentifier())
                return;
        }
        if (userNumber >= arr.length) {
            User [] temp = new User[arr.length * 2];

            for (int i = 0; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[userNumber] = newUser;
        userNumber++;
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        for (int i = 0; i < userNumber; i++) {
            if (arr[i].getIdentifier() == id) {
                return arr[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByIndex(int index) throws UserNotFoundException {
        if (index <= userNumber)
            return arr[index];
        throw new UserNotFoundException();
    }

    @Override
    public Integer getUsersNumber() {
        return userNumber;
    }
}
