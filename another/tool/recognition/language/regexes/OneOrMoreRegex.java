package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class OneOrMoreRegex extends Regex {
	private final Regex syntaxes;

	public OneOrMoreRegex(Regex syntaxes) { this.syntaxes = syntaxes; }

	public State onRegexExecute(LanguageTokenizerInformation information) {
		State state;
		if ((state = this.syntaxes.onRegexExecute(information)) != State.SUCCESS) {
			return state;
		}

		int oldCursor;
		do {
			oldCursor = information.getCursor();
		} while (this.syntaxes.onRegexExecute(information) == State.SUCCESS);

		information.setCursor(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return '[' + this.syntaxes.toString() + "]+";
	}
}
