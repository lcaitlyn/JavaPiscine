package edu.school21.printer.app;

import edu.school21.printer.logic.Converter;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Добавьте аргументы: белый символ, чёрный символ, путь к файлу.bmp");
            System.exit(-1);
        }

        Converter.convertFromBmpToConsole(args[0].charAt(0), args[1].charAt(0), args[2]);
    }
}
