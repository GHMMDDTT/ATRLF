package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.regexes.Regex;

import java.util.ArrayList;

public class LanguageTokenizer {
	private final LanguageTokenizerInformation information;
	private final RegexLanguageTokenizer syntax;

	public LanguageTokenizer(char[] text, RegexLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public LanguageTokenizer(String text, RegexLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public ArrayList<Token> onTokenizer() {
		State state;
		Regex syntaxesSkipped = this.syntax.onRegexSkipped();
		Regex syntaxes = this.syntax.onRegex();

		while (this.information.getCursor() < this.information.getTarget().length) {
			state = syntaxesSkipped.onRegexExecute(this.information);

			int oldCursor = this.information.getCursor();
			if (state == State.FAILED) {
				this.information.setCursor(oldCursor);
			}

			state = syntaxes.onRegexExecute(this.information);

			if (state == State.FAILED) {
				throw new RuntimeException("Contains error the syntax: " + syntaxes + " in " + this.information.getLine() + ':' + (this.information.getCursor() - this.information.getColumn()) + " of the input: '" + this.information.getTarget()[this.information.getCursor()] + '\'');
			}
		}
		return this.information.getTokens();
	}
}
