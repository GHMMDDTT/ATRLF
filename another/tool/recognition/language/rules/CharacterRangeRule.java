package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRangeRule extends Rule {
	private final char minimum;
	private final char maximum;

	public CharacterRangeRule(char minimum, char maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		if (information.getTarget()[information.getPosition()] >= this.minimum && information.getTarget()[information.getPosition()] <= this.maximum) {
			information.setNextPosition();
			return State.SUCCESS;
		}
		return State.FAILED;
	}

	@Override
	public String toString() {
		return this.minimum + "-" + this.maximum;
	}
}
