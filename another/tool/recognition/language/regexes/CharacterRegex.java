package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRegex extends Regex {
	private final char target;

	public CharacterRegex(char target) { this.target = target; }

	public State onRegexExecute(LanguageTokenizerInformation information) {
		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		if (information.getTarget()[information.getCursor()] == this.target) {
			if (this.target == '\n' || this.target == '\r') {
				information.advanceLine();
				information.setColumn(information.getCursor());
			}
			information.advanceCursor();
			return State.SUCCESS;
		}
		return State.FAILED;
	}

	@Override
	public String toString() {
		return "" + this.target;
	}
}
