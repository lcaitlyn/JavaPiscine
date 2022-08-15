package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter {
    public static void convertFromBmpToConsole(char white, char black) {
        try {
            BufferedImage image = ImageIO.read(Converter.class.getResource("/resources/it.bmp"));

            for (int x = 0; x < image.getHeight(); x++) {
                for (int y = 0; y < image.getWidth(); y++) {
                    if (image.getRGB(y, x) == Color.BLACK.getRGB())
                        System.out.print(black);
                    else if (image.getRGB(y, x) == Color.WHITE.getRGB())
                        System.out.print(white);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
