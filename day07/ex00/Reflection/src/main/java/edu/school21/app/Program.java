package edu.school21.app;

import edu.school21.classes.Car;
import edu.school21.classes.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Program {
    private static final String TEXT_DIVIDER = "---------------------";
    private static Scanner scanner = new Scanner(System.in);
    private static Object object;
    private static Object newObject;
    private static Object [] classes = {new User(), new Car()};
    private static String scan;
    

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("Classes:");
            for (Object o : classes) {
                System.out.println(o.getClass().getSimpleName());
            }
            System.out.println(TEXT_DIVIDER);

            System.out.println("Enter class name:");
            scan = scanner.nextLine();

            for (Object o : classes) {
                if (o.getClass().getSimpleName().equals(scan))
                    object = o;
            }

            System.out.println(TEXT_DIVIDER);

            if (object == null) {
                System.out.println("Class not found! Try again");
                System.out.println(TEXT_DIVIDER);
            }
            else
                break;
        }

        System.out.println("fields :");
        for (Field f : object.getClass().getDeclaredFields())
            System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName());

        System.out.println("methods:");
        for (Method m : object.getClass().getDeclaredMethods()) {
            System.out.print("\t" + m.getReturnType().getSimpleName() + " " + m.getName() + "(");

            Parameter [] parameters = m.getParameters();

            for (int i = 0; i < parameters.length - 1; i++) {
                System.out.print(parameters[i].getType().getSimpleName() + ", ");
            }

            if (parameters.length != 0)
                System.out.print(parameters[(parameters.length == 0) ? 0 : parameters.length - 1].getType().getSimpleName());

            System.out.println(")");
        }


        System.out.println(TEXT_DIVIDER);

        System.out.println("Let's create an object.");

        newObject = object;
        for (Field f : object.getClass().getDeclaredFields()) {
            System.out.println(f.getName() + ":");
            f.setAccessible(true);
            f.set(newObject, getInfoFromString(f.getType().getSimpleName()));
            f.setAccessible(false);
        }

        System.out.println(newObject);


        if (object.getClass().getMethods() == null)
            System.exit(0);


//        scanner.nextLine();
        Method method = null;

        while (true) {
            System.out.println(TEXT_DIVIDER);
            System.out.println("Enter name of the method for call:");
            scan = scanner.nextLine();

            for (Method m : object.getClass().getDeclaredMethods()) {
                if (m.getName().equals(scan)) {
                    method = m;
                    break;
                }
            }

            if (method == null) {
                System.out.println("Method not found! Try again!");
            } else
                break;
        }

//        method.setAccessible(true);

        List<Object> parameters = new LinkedList<>();

        Parameter [] parameters1 = method.getParameters();

        for (Parameter p : parameters1) {
            System.out.println("Enter " + p.getType().getSimpleName() + " value:");
            parameters.add(getInfoFromString(p.getType().getSimpleName()));
        }

        Object returnValue =  method.invoke(newObject, parameters.toArray());

        if (!method.getReturnType().getSimpleName().equals("void")) {
            System.out.println("Method returned:");
            System.out.println(returnValue);
        }

        System.out.println(newObject);
    }

    public static Object getInfoFromString(String raw) {
        switch (raw.toLowerCase()) {
            case "string":
                return scanner.nextLine();
            case "int":
            case "integer":
                return scanner.nextInt();
            case "long":
                return scanner.nextLong();
            case "boolean":
                return scanner.nextLong();
            case "double":
                return scanner.nextDouble();
            case "float":
                return scanner.nextFloat();
            case "char":
            case "character":
                return scanner.next();
        }
        return null;
    }
}

