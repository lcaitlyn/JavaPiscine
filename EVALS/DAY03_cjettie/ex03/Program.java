public class Program {
    public static void main(String [] args) {
        Integer threadsCount = null;
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException();
            }
            if ((threadsCount = readIntegerOption(args[0], "--threadsCount")) == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException numberFormatException) {
            System.err.println("Illegal arguments");
            System.exit(-1);
        }
        new DownloadManager(threadsCount, "files_urls.txt").StartDownloads();
        System.exit(0);
    }
    private static Integer readIntegerOption(String fullOption, String optionOnly) {
        if (fullOption.length() < optionOnly.length() + 2) {
            return null;
        }
        if (fullOption.indexOf(optionOnly) != 0) {
            return null;
        }
        return Integer.parseInt(fullOption.replaceFirst(optionOnly + "=", ""));
    }
}
