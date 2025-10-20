package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class LessOfRule extends Rule {
	private final Rule rules;
	private int minimum;

	public LessOfRule(Rule rules, int minimum) {
		this.rules = rules;
		this.minimum = minimum;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		int oldCursor;
		do {
			oldCursor = information.getPosition();
			this.minimum--;
		} while (this.rules.onRuleExecute(information) == State.SUCCESS);

		information.setPosition(oldCursor);

		if (this.minimum >= 0) {
			return State.SUCCESS;
		} else {
			return State.FAILED;
		}
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]<" + this.minimum;
	}
}
