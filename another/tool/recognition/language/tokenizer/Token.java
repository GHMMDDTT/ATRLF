package another.tool.recognition.language.tokenizer;

import java.util.Arrays;

public class Token {
	private final String value;
	private final Enum<?>[] type;
	private final int line;

	public Token(String value, Enum<?>[] type, int line) {
		this.value = value;
		this.type = type;
		this.line = line;
	}

	public String getValue() {
		return this.value;
	}

	public Enum<?>[] getType() {
		return this.type;
	}

	public int getLine() {
		return this.line;
	}

	@Override
	public String toString() {
		return "Token{" +
				"value='" + value + '\'' +
				", type=" + Arrays.toString(type) +
				", line=" + line +
				'}';
	}
}
