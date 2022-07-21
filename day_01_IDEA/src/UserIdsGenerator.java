public class UserIdsGenerator {
    private Integer id;

    UserIdsGenerator() {
        this.id = 0;
    }

    private static class UserIdsGeneratorHolder {
        public static final UserIdsGenerator HOLDER_SERVICE = new UserIdsGenerator();
    }

    public static UserIdsGenerator getInstance() {
        return UserIdsGeneratorHolder.HOLDER_SERVICE;
    }

    public Integer generateId() {
        return ++this.id;
    }
}
