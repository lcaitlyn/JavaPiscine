package edu.school21.app;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class Program {
    private static final String TEXT_DIVIDER = "---------------------";
    private static final String CLASSES_PACKAGE = "edu.school21.classes";
    private static Object object;
    private static Object newObject;
    private static Object [] classes;
    private static String scan;
    

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        List<Class<?>> list = getClassesFromPackage(CLASSES_PACKAGE);
        classes = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            classes[i] = list.get(i).newInstance();
        }

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
        Scanner scanner = new Scanner(System.in);

        switch (raw.toLowerCase()) {
            case "string":
                return scanner.nextLine();
            case "int":
            case "integer":
                return scanner.nextInt();
            case "long":
                return scanner.nextLong();
            case "boolean":
                return scanner.nextBoolean();
            case "double":
                return scanner.nextDouble();
            case "float":
                return scanner.nextFloat();
            case "char":
            case "character":
                return scanner.nextLine().charAt(0);
        }
        return null;
    }

    // вот тут вообще хз можно это использовать или нет? вроде все функции разрешены и остальное тоже
    // случайно нашел это пока искал решение https://github.com/classgraph/classgraph
    // в принципе мог и сам написать, но мне лень)))
    public static List<Class <?>> getClassesFromPackage(String findPackage) {
        try (ScanResult scanResult = new ClassGraph()
                .whitelistPackages(findPackage)
                .scan()) {
            return scanResult.getAllClasses().loadClasses();
        }
    }
}

