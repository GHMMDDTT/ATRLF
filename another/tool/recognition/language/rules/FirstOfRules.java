package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class FirstOfRules extends Rules {
	private final Rules[] rules;

	public FirstOfRules(Rules... rules) { this.rules = rules; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		Result<?> state = null;
		if (this.rules.length == 0) return new Result<>("Empty alternatives", false);
		for (Rules rules : this.rules) {
			state = rules.onRegexExecute(information);
			if (state.isSuccess()) return new Result<>(state, true);
		}
		return new Result<>(state, false);
	}

	@Override
	public String toString() {
		return toString(this.rules);
	}

	private String toString(Rules[] rules) {
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
