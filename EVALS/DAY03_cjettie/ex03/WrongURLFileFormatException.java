public class WrongURLFileFormatException extends Throwable {
    @Override
    public String toString() {
        return new String("Wrong URL format in input file");
    }
}
