package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RangeRegex extends Regex {
	private final Regex regex;
	private final int minimum;
	private final int maximum;

	public RangeRegex(Regex regex, int minimum, int maximum) {
		this.regex = regex;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {
		State state;

		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		int index = 0;
		int oldCursor = information.getCursor();
		while (index <= maximum) {
			state = regex.onRegexExecute(information);
			if (state != State.SUCCESS) {
				if (index >= minimum) {
					information.setCursor(oldCursor);
					break;
				}
				else return State.FAILED;
			}
			index++;
			oldCursor = information.getCursor();
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.regex.toString() + '{' + this.minimum + '-' + this.maximum + '}';
	}
}
