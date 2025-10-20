package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class NotRule extends Rule {
	private final Rule rule;

	public NotRule(Rule rule) {
		this.rule = rule;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {

		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		int oldCursor = information.getPosition();
		State state = rule.onRuleExecute(information);

		if (state == State.FAILED) {
			information.setNextPosition();
			return State.SUCCESS;
		}

		information.setPosition(oldCursor);
		return State.FAILED;
	}

	@Override
	public String toString() {
		return this.rule.toString() + '!';
	}
}
