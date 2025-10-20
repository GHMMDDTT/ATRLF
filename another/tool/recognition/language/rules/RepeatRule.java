package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RepeatRule extends Rule {
	private final Rule rule;
	private final int repeat;

	public RepeatRule(Rule rule, int repeat) {
		this.rule = rule;
		this.repeat = repeat;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		State state;

		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		int index = 0;
		while (index != repeat) {
			state = rule.onRuleExecute(information);
			if (state != State.SUCCESS) return State.FAILED;
			index++;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.rule.toString() + '{' + this.repeat + '}';
	}
}
