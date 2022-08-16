package edu.school21.printer.app;

import edu.school21.printer.logic.Colors;
import edu.school21.printer.logic.Converter;
import edu.school21.printer.logic.Parser;

import com.beust.jcommander.JCommander;

public class Program {
    public static void main(String[] args) {
        Parser parser = new Parser();
        JCommander.newBuilder().addObject(parser).build().parse(args);

        if (args.length != 2) {
            System.err.println("Добавьте аргументы: белый символ, чёрный символ, путь к файлу.bmp");
            System.exit(-1);
        }

        Converter.convertFromBmpToConsole(Colors.getColor(parser.getWhite()), Colors.getColor(parser.getBlack()));
    }
}
