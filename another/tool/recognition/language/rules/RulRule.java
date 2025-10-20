package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RulRule extends Rule {
	private final Rule rule;
	private final OnRulRuleListener listener;

	public RulRule(Rule rule, OnRulRuleListener listener) {
		this.rule = rule;
		this.listener = listener;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		int oldCursor = information.getPosition();
		if (information.getPosition() >= information.getTarget().length) return State.FAILED;

		State state;
		if ((state = this.rule.onRuleExecute(information)) == State.FAILED) return state;

		Rule rule = this.listener.onRuleRules(new String(information.getTarget(), oldCursor - 1, information.getPosition() - oldCursor));
		if (rule != null) if ((state = rule.onRuleExecute(information)) != State.FAILED) return state;

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.rule.toString() + " -> {" + this.listener.toString() + '}';
	}

	public static abstract class OnRulRuleListener {
		public abstract Rule onRuleRules(String str);
	}
}
