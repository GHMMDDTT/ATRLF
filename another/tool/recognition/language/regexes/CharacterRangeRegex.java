package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRangeRegex extends Regex {
	private final char minimum;
	private final char maximum;

	public CharacterRangeRegex(char minimum, char maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {
		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		if (information.getTarget()[information.getCursor()] >= this.minimum && information.getTarget()[information.getCursor()] <= this.maximum) {
			information.advanceCursor();
			return State.SUCCESS;
		}
		return State.FAILED;
	}

	@Override
	public String toString() {
		return this.minimum + "-" + this.maximum;
	}
}
