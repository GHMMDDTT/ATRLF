package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RepeatRegex extends Regex {
	private final Regex regex;
	private final int repeat;

	public RepeatRegex(Regex regex, int repeat) {
		this.regex = regex;
		this.repeat = repeat;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {
		State state;

		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		int index = 0;
		while (index != repeat) {
			state = regex.onRegexExecute(information);
			if (state != State.SUCCESS) return State.FAILED;
			index++;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.regex.toString() + '{' + this.repeat + '}';
	}
}
