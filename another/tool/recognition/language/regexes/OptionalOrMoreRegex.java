package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class OptionalOrMoreRegex extends Regex {
	private final Regex syntaxes;

	public OptionalOrMoreRegex(Regex syntaxes) { this.syntaxes = syntaxes; }

	public State onRegexExecute(LanguageTokenizerInformation information) {
		int oldCursor = information.getCursor();
		if (this.syntaxes.onRegexExecute(information) != State.SUCCESS) {
			information.setCursor(oldCursor);
			return State.SUCCESS;
		}

		do {
			oldCursor = information.getCursor();
		} while (this.syntaxes.onRegexExecute(information) == State.SUCCESS);

		information.setCursor(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return "[" + this.syntaxes.toString() + "]?+";
	}
}
