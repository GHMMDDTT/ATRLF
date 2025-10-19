package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.Results.Result;

import java.util.Arrays;

public class Token {
	private final Result<?> value;
	private final Enum<?>[] type;
	private final int line;

	public Token(Result<?> value, Enum<?>[] type, int line) {
		this.value = value;
		this.type = type;
		this.line = line;
	}

	public Result<?> getValue() {
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
