package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;
import java.util.Arrays;

public final class SequenceRules extends Rules {
	private final Rules[] rules;

	public SequenceRules(Rules... rules) { this.rules = rules; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> arrayList = new ArrayList<>();

		for (Rules rules : this.rules) {
			Result<?> state = rules.onRegexExecute(information);

			if (state.isFailed()) return new Result<>(state.getValue(), false);

			arrayList.add(state);
		}
		return new Result<>(arrayList, true);
	}

	@Override
	public String toString() {
		return Arrays.toString(this.rules);
	}
}
