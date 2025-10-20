package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.rules.Rule;

import java.io.File;
import java.util.ArrayList;

public class LanguageTokenizer {
	private final LanguageTokenizerInformation information;
	private final RuleLanguageTokenizer syntax;

	public LanguageTokenizer(char[] text, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public LanguageTokenizer(String text, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public LanguageTokenizer(File file, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(file);
		this.syntax = syntax;
	}

	public ArrayList<Token> onTokenizer() {
		State state;
		Rule syntaxesSkipped = this.syntax.onRegexSkipped();
		Rule syntaxes = this.syntax.onRegex();

		while (this.information.getPosition() < this.information.getTarget().length) {
			syntaxesSkipped.onRuleExecute(this.information);

			if (this.information.getPosition() == this.information.getTarget().length) break;

			state = syntaxes.onRuleExecute(this.information);

			if (state == State.FAILED) {
				throw new RuntimeException("Contains error the syntax: " + syntaxes + " in " + this.information.getLine() + ':' + (this.information.getPosition() - this.information.getColumn()) + " of the input: '" + this.information.getTarget()[this.information.getPosition()] + '\'');
			}
		}
		return this.information.getTokens();
	}
}
