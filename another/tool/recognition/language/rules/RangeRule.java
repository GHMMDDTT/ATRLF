package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RangeRule extends Rule {
	private final Rule rule;
	private final int minimum;
	private final int maximum;

	public RangeRule(Rule rule, int minimum, int maximum) {
		this.rule = rule;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		State state;

		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		int index = 0;
		int oldCursor = information.getPosition();
		while (index <= maximum) {
			state = rule.onRuleExecute(information);
			if (state != State.SUCCESS) {
				if (index >= minimum) {
					information.setPosition(oldCursor);
					break;
				}
				else return State.FAILED;
			}
			index++;
			oldCursor = information.getPosition();
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.rule.toString() + '{' + this.minimum + '-' + this.maximum + '}';
	}
}
