import java.io.IOException;
import java.io.InputStream;

/**
 * Interpreter
 * @author: av-sharma
 */

public class Interpreter {
	private String code;
	private byte[] memory;
	private int dataPointer;
	private int instructionPointer;
	private InputStream in;

	public Interpreter(String code) {
		this.code = code;
		this.memory = new byte[30000];
		this.dataPointer = 0;
		this.instructionPointer = 0;
		this.in = System.in;
	}

	public void execute() {
		while (instructionPointer < code.length()) {
			char op = code.charAt(instructionPointer);
			switch (op) {
				case '+':
					memory[dataPointer]++;
					break;
				case '-':
					memory[dataPointer]--;
					break;
				case '>':
					dataPointer++;
					break;
				case '<':
					dataPointer--;
					break;
				case ',':
					readChar();
					break;
				case '.':
					writeChar();
					break;
				case '[':
					if (memory[dataPointer] == 0) skipLoop();
					break;
				case ']':
					if (memory[dataPointer] != 0) goBack();
			}
			instructionPointer++;
		}
		System.out.println();
		System.exit(0);
//		debug();
	}

	private void readChar() {
		try {
			int input = in.read();
			if (input != -1)
				memory[dataPointer] = (byte) input;
		} catch (IOException e) {
			System.out.println("[ERROR] IOException Raised!!");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void writeChar() {
		System.out.print((char) memory[dataPointer]);
	}

	private void skipLoop() {
		int depth = 1;
		while (depth != 0) {
			instructionPointer++;
			char ch = code.charAt(instructionPointer);
			if (ch == '[') depth++;
			else if (ch == ']') depth--;
		}
	}

	private void goBack() {
		int depth = 1;
		while (depth != 0) {
			instructionPointer--;
			char ch = code.charAt(instructionPointer);
			if (ch == ']') depth++;
			else if (ch == '[') depth--;
		}
	}

	private void debug() {
		System.out.println("##### DEBUG INFO #####");
		System.out.println("code: " + code);
		System.out.println("dataPointer: " + dataPointer);
		System.out.println("instructionPointer: " + instructionPointer);
		System.out.print("memory: [ ");
		for (int i = 0; i < memory.length; i++) System.out.print(memory[i] + " ");
		System.out.println("]");
		System.out.println("##### END DEBUG #####");
	}
}