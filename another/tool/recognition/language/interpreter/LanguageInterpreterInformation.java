package another.tool.recognition.language.interpreter;

import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;

public class LanguageInterpreterInformation {
	private int cursor;

	private final ArrayList<Token> target;

	public LanguageInterpreterInformation(ArrayList<Token> target) { this.target = target; }

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

	public ArrayList<Token> getTarget() {
		return target;
	}
}
