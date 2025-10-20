package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class FirstOfRule extends Rule {
	private final Rule[] rules;

	public FirstOfRule(Rule... rules) { this.rules = rules; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		State state = State.FAILED;
		if (this.rules.length == 0) return State.SUCCESS;
		for (Rule rule : this.rules) {
			if ((state = rule.onRuleExecute(information)) == State.SUCCESS) return state;
		}
		return state;
	}

	@Override
	public String toString() {
		return toString(this.rules);
	}

	private String toString(Rule[] rules) {
		if (rules == null) {
			return "null";
		} else {
			int iMax = rules.length - 1;
			if (iMax == -1) {
				return "[]";
			} else {
				StringBuilder b = new StringBuilder();
				b.append('[');
				int i = 0;

				while(true) {
					b.append(rules[i]);
					if (i == iMax) {
						return b.append(']').toString();
					}

					b.append(" | ");
					++i;
				}
			}
		}
	}


}
