import java.io.File;
import java.util.Scanner;

/**
 * Main
 * @author: av-sharma
 */

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("[ERROR] No input file detected!!");
			System.out.println("[INFO] Usage instruction: java -jar target/brainfuck-interpreter-1.0.java <path to file containing brainfuck code>");
			System.exit(-1);
		}

		StringBuffer code = new StringBuffer();
		try {
			File file = new File(args[0]);
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) code.append(sc.nextLine().replaceAll("\n", ""));
		} catch (Exception e) {
			System.out.println("[ERROR] File not found!!");
			e.printStackTrace();
			System.exit(-1);
		}

		long startTime = System.nanoTime();
		Interpreter bf = new Interpreter(code.toString());
		bf.execute();
		System.out.println("execution time: " + ((double)(System.nanoTime() - startTime) / 1000000) + "ms");
	}
}
