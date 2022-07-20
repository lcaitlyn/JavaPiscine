public class Program {

    public static void main(String[] args) {
        String[] names = {"Андрей","Антон","Денис","Вася","Петя","Коля","Саша","Маша","Наташа","Дима","Оля","Юля","Яна","Олег","Руслан","Людмила"};

        UsersArrayList array = new UsersArrayList();

        for (int i = 0; i < 8; i++) {
            array.addUser(new User(names[i]));
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(array.getByIndex(i).toString());
        }

        System.out.println("Количество пользователей: " + array.getUsersNumber());

        for (int i = 8; i < 15; i++) {
            array.addUser(new User(names[i]));
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(array.getByIndex(i).toString());
        }

        System.out.println("Количество пользователей: " + array.getUsersNumber());

        System.out.println("Пользователь с index 5 = " + array.getByIndex(5));
        System.out.println("Пользователь с ID 5 = " + array.getById(5));
        System.out.println("Пользователь с ID 6 = " + array.getById(6));

//        System.out.println("Пользователь с index 25 = " + array.getByIndex(25));
        System.out.println("Пользователь с ID 25 = " + array.getById(25));

    }
}
