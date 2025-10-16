package another.tool.recognition.language.tokenizer;

import java.util.ArrayList;

public class LanguageTokenizerInformation {
	private int cursor = 0;
	private int line = 1;
	private int column;

	private final char[] target;

	private final ArrayList<Token> tokens = new ArrayList<>(32);

	public LanguageTokenizerInformation(char[] target) { this.target = target; }

	public LanguageTokenizerInformation(String target) { this.target = target.toCharArray(); }

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	public void advanceCursor() {
		this.cursor++;
	}

	public void backCursor() {
		this.cursor++;
	}

	public int getLine() {
		return line;
	}

	public void advanceLine() {
		this.line++;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) { this.column = column; }

	public char[] getTarget() {
		return target;
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public void addToken(Token t) {
		this.tokens.add(t);
	}
}
