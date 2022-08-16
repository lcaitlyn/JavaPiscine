package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;

public class Converter {
    public static void convertFromBmpToConsole(Color white, Color black) {
        if (white == null || black == null) {
            System.err.println("Цвет не найден. Попробуйте --white=RED --black=GREEN");
            System.exit(-1);
        }

        try {
            BufferedImage image = ImageIO.read(Converter.class.getResource("/resources/it.bmp"));

            for (int x = 0; x < image.getHeight(); x++) {
                for (int y = 0; y < image.getWidth(); y++) {
                    if (image.getRGB(y, x) == Color.BLACK.getRGB())
                        System.out.print(colorize("  ", BACK_COLOR(black.getRed(), black.getGreen(), black.getBlue())));
                    else if (image.getRGB(y, x) == Color.WHITE.getRGB())
                        System.out.print(colorize("  ", BACK_COLOR(white.getRed(), white.getGreen(), white.getBlue())));
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
