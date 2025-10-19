package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;
import java.util.Arrays;

public final class SkipRules extends Rules {
	private final Rules rules;

	public SkipRules(Rules rules) { this.rules = rules; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		Result<?> state = this.rules.onRegexExecute(information);

		if (state.isFailed()) return new Result<>(state.getValue(), false);

		return new Result<>("", true);
	}

	@Override
	public String toString() {
		return this.rules.toString();
	}
}
