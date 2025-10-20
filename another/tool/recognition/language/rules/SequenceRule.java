package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.Arrays;

public final class SequenceRule extends Rule {
	private final Rule[] rules;

	public SequenceRule(Rule... rules) { this.rules = rules; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		for (Rule rule : this.rules) {
			State state = rule.onRuleExecute(information);
			if (state != State.SUCCESS) return state;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.rules);
	}
}
