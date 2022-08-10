import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Converter {
    public static void convertFromBmpToConsole(char white, char black, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));

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
