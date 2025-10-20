package another.tool.recognition.language.parser;

import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;

public class LanguageParserInformation {
	private int position;

	private final ArrayList<Token> target;

	public LanguageParserInformation(ArrayList<Token> target) { this.target = target; }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setNextPosition() {
		this.position++;
	}

	public void setPreviousPosition() {
		this.position++;
	}

	public boolean hasMoreNextToken() {
		return this.position < this.target.size();
	}

	public ArrayList<Token> getTarget() {
		return target;
	}
}
