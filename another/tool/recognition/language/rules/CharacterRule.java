package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRule extends Rule {
	private final char target;

	public CharacterRule(char target) { this.target = target; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		if (information.getTarget()[information.getPosition()] == this.target) {
			if (this.target == '\n' || this.target == '\r') {
				information.setNextLine();
				information.setColumn(information.getPosition());
			}
			information.setNextPosition();
			return State.SUCCESS;
		}
		return State.FAILED;
	}

	@Override
	public String toString() {
		return "" + this.target;
	}
}
