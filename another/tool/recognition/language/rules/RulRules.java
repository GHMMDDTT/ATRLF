package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class RulRules extends Rules {
	private final Rules rules;
	private final OnRulRulesListener listener;

	public RulRules(Rules rules, OnRulRulesListener listener) {
		this.rules = rules;
		this.listener = listener;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		if (information.getPosition() >= information.getTarget().length) new Result<>("End Of Input!!!", true);

		ArrayList<Result<?> > state = new ArrayList<>();
		state.add(this.rules.onRegexExecute(information));
		if (state.getFirst().isFailed()) return state.getFirst();

		Rules rules = this.listener.onRulRules(state.getFirst());
		if (rules != null) {
			state.add(this.rules.onRegexExecute(information));
			if (state.getLast().isSuccess()) return state.getLast();
		}

		return new Result<>(state, true);
	}

	@Override
	public String toString() {
		return this.rules.toString() + " -> {" + this.listener.toString() + '}';
	}

	public static abstract class OnRulRulesListener {
		public abstract Rules onRulRules(Result<?> str);
	}
}
