package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.rules.Rules;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LanguageTokenizer {
	private final LanguageTokenizerInformation information;
	private final RuleLanguageTokenizer syntax;
	private final ArrayList<Token> tokens = new ArrayList<>(32);

	public LanguageTokenizer(char[] text, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public LanguageTokenizer(String text, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public LanguageTokenizer(File text, RuleLanguageTokenizer syntax) {
		this.information = new LanguageTokenizerInformation(text);
		this.syntax = syntax;
	}

	public ArrayList<Token> onTokenizer() {
		Result<?> state;
		Rules syntaxesSkipped = this.syntax.onRegexSkipped();
		Rules syntaxes = this.syntax.onRegex();

		while (this.information.getPosition() < this.information.getTarget().length) {
			syntaxesSkipped.onRegexExecute(this.information);

			state = syntaxes.onRegexExecute(this.information);

			if (state.isFailed()) {
				throw new RuntimeException("Contains error the syntax: " + syntaxes + " in " + this.information.getLine() + ':' + (this.information.getPosition() - this.information.getColumn()) + " of the input: '" + this.information.getTarget()[this.information.getPosition() - 1] + '\'');
			}

			if (state.getValue() instanceof Token) this.tokens.add((Token) state.getValue());
			else if (state.getValue() instanceof Result<?> result) {
				if (result.getValue() instanceof Token) this.tokens.add((Token) result.getValue());
				else if (result.getValue() instanceof ArrayList<?>) this.tokens.addAll((Collection<? extends Token>) result);
			}
		}
		return this.tokens;
	}
}
