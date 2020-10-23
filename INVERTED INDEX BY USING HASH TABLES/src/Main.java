
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public String DELIMITERS;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		HashTable<String, String> ht = new HashTable<String, String>();
		String DELIMITERS = "[-+=" + " " + // space
				"\r\n " + // carriage return line fit
				"1234567890" + // numbers
				"’'\"" + // apostrophe
				"(){}<>\\[\\]" + // brackets
				":" + // colon
				"," + // comma
				"‒–—―" + // dashes
				"…" + // ellipsis
				"!" + // exclamation mark
				"." + // full stop/period
				"«»" + // guillemets
				"-‐" + // hyphen
				"?" + // question mark
				"‘’“”" + // quotation marks
				";" + // semicolon
				"/" + // slash/stroke
				"⁄" + // solidus
				"␠" + // space?
				"·" + // interpunct
				"&" + // ampersand
				"@" + // at sign
				"*" + // asterisk
				"\\" + // backslash
				"•" + // bullet
				"^" + // caret
				"¤¢$€£¥₩₪" + // currency
				"†‡" + // dagger
				"°" + // degree
				"¡" + // inverted exclamation point
				"¿" + // inverted question mark
				"¬" + // negation
				"#" + // number sign (hashtag)
				"№" + // numero sign ()
				"%‰‱" + // percent and related signs
				"¶" + // pilcrow
				"′" + // prime
				"§" + // section sign
				"~" + // tilde/swung dash
				"¨" + // umlaut/diaeresis
				"_" + // underscore/understrike
				"|¦" + // vertical/pipe/broken bar
				"⁂" + // asterism
				"☞" + // index/fist
				"∴" + // therefore sign
				"‽" + // interrobang
				"※" + // reference mark
				"]";

		File folder = new File("bbc");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
//		for (int i = 0; i < 1; i++) {

			String f = listOfFiles[i].getName();
			if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
				File[] listAllFiles = listOfFiles[i].listFiles();
				for (int j = 0; j < listAllFiles.length; j++) {
//				for (int j = 0; j < 1; j++) {

					String f2 = f + "\n" + listAllFiles[j].getName();
					System.out.println("File " + listAllFiles[j].getName());
					try (BufferedReader br = new BufferedReader(new FileReader(listAllFiles[j]))) {
						String strLine;
						while ((strLine = br.readLine()) != null) {
//							if (!strLine.equals("")) {

							String[] splitted = strLine.toLowerCase().split(DELIMITERS);
							for (int k = 0; k < splitted.length; k++) {
								File file = new File("stop_words_en.txt");
								ArrayList<String> arr = readContent(file);
								if (!arr.contains(splitted[k]) && !splitted[k].equals("")) {
									ht.put(splitted[k], f2);
								}
//							}
							}

						}
					}
				}
			}
			System.out.println(ht.getCollision());
		}
		Scanner sc = new Scanner(System.in);
		File file = new File("1000.txt");
		System.out.println("If you want to search 1000.txt , please write 'YES'");
		String input = sc.next().toLowerCase();
		if (input.equals("yes")) {

			ArrayList<String> arr = readContent(file);
			boolean flag = false;
			for (int i = 0; i < ht.getTABLE_SIZE(); i++) {
				long startTime   = System.nanoTime();
				if (arr.contains(ht.table[i])) {
					long endTime   = System.nanoTime();
					long totalTime = endTime - startTime;
					System.out.println(ht.table[i] +"---- "+ totalTime);
					System.out.println(i + " " + ht.table[i].getKey());
					ht.table[i].getValue().display();
				}
			
			}
		}
		System.out.println(ht.getTABLE_SIZE());
		System.out.println("If you want to remove, please write remove. \n If you want to search, pelase write search");
		input = sc.next().toLowerCase();
		System.out.println("Please enter the key !!");
		String key = sc.next().toLowerCase();
		if (input.equals("remove") || input.equals("search")) {
			while (input.equals("remove")) {
				ht.remove(key);
				System.out.println(
						"If you want to remove, please write remove. \n If you want to search, pelase write search");
				input = sc.next();
				System.out.println("Please enter the key !!");
				key = sc.next().toLowerCase();
				while (input.equals("search")) {
					ht.search(key);
					System.out.println(
							"If you want to remove, please write remove. \n If you want to search, pelase write search");
					input = sc.next();
					System.out.println("Please enter the key !!");
					key = sc.next().toLowerCase();
				}

			}
			while (input.equals("search")) {
				ht.search(key);
				System.out.println(
						"If you want to remove, please write remove. \n If you want to search, pelase write search");
				input = sc.next();
				System.out.println("Please enter the key !!");
				key = sc.next().toLowerCase();
				while (input.equals("remove")) {
					ht.remove(key);
					System.out.println(
							"If you want to remove, please write remove. \n If you want to search, pelase write search");
					input = sc.next();
					System.out.println("Please enter the key !!");
					key = sc.next().toLowerCase();
				}
			}
		}

	}
//		 ht.put("car", "daşdlad");
//		 ht.put("family", "jdrjkjfl");
//		 ht.put("bike", "oeıfoır");
//		 ht.put("mouse", "njrjsk");
//		 ht.put("vallet", "daşdlad");
//		 ht.put("cra", "bla");
//		 ht.put("cra", "bla");
//		 ht.show();

//	 }
	public static ArrayList readContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		String line;

		BufferedReader br = new BufferedReader(fileReader);
		ArrayList<String> stops = new ArrayList<String>();

		while ((line = br.readLine()) != null) {

			stops.add(line);

		}

		br.close();
		return stops;

	}

	public List readContents(Path filePath) throws IOException {
		System.out.println("read file " + filePath);
		List<String> fileList = Files.readAllLines(filePath);
		return fileList;

	}

}