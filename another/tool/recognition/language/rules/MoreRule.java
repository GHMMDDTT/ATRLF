package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class MoreRule extends Rule {
	private final Rule rules;

	public MoreRule(Rule rules) { this.rules = rules; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		if (this.rules.onRuleExecute(information) == State.FAILED) {
			return State.FAILED;
		}

		if (this.rules.onRuleExecute(information) == State.FAILED) {
			return State.FAILED;
		}

		int oldCursor;
		do {
			oldCursor = information.getPosition();
		} while (this.rules.onRuleExecute(information) == State.SUCCESS);

		information.setPosition(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]++";
	}
}
