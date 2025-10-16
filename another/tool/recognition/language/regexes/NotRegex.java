package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class NotRegex extends Regex {
	private final Regex regex;

	public NotRegex(Regex regex) {
		this.regex = regex;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {

		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		int oldCursor = information.getCursor();
		State state = regex.onRegexExecute(information);

		if (state == State.FAILED) {
			information.advanceCursor();
			return State.SUCCESS;
		}

		information.setCursor(oldCursor);
		return State.FAILED;
	}

	@Override
	public String toString() {
		return this.regex.toString() + '!';
	}
}
