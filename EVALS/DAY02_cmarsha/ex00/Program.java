import java.io.*;
import java.util.*;

public class Program {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		File res = new File("result.txt");
		FileOutputStream fop = new FileOutputStream(res);
		FileInputStream signature = new FileInputStream("signatures.txt");
		HashMap<String, String> mapa = new HashMap<String, String>();
		String line = "";
		File file;
		FileInputStream fis;
		StringBuilder magicBytes = new StringBuilder();
		String magicString = "";
		Scanner scan;

		BufferedReader br = new BufferedReader(new InputStreamReader(signature));
		int i = 0, j;

		while (br.ready()) {
			line = br.readLine();
			i = line.indexOf(",");
			mapa.put(line.substring(i + 2).replaceAll(" ", ""), line.substring(0, i).toUpperCase());
			if (line == null)
				break;
		}

		while (!line.equals("42")) {
			scan = new Scanner(scanner.nextLine());
			if (!scan.hasNext())
				System.exit(-1);
			line = scan.nextLine();
			file = new File(line);
			if (file.exists() && !file.isDirectory()) {
				fis = new FileInputStream(line);
				magicBytes.setLength(0);
				for (int n = 0; n < 40; n++)
					magicBytes.append(String.format("%02X", fis.read()));
				magicString = magicBytes.toString();
				Iterator it = mapa.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					if (magicString.contains(pair.getKey().toString())) {
						if (!res.exists())
							res.createNewFile();
						fop.write((pair.getValue().toString() + "\n").getBytes());
					}
				}
				System.out.println("PROCESSED");
				fis.close();
			}
			else if (line.equals("42"))
				break;
			else
				System.out.println("UNDEFINED!!");
		}
		fop.close();
		scanner.close();
}
}
