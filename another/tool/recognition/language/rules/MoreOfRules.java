package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class MoreOfRules extends Rules {
	private final Rules rules;
	private int minimum;

	public MoreOfRules(Rules rules, int minimum) {
		this.rules = rules;
		this.minimum = minimum;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> results = new ArrayList<>();
		int oldCursor;
		do {
			results.add(this.rules.onRegexExecute(information));
			oldCursor = information.getPosition();
			this.minimum--;
		} while (results.get(-(this.minimum - results.size())).isSuccess());

		if (results.getLast().isFailed()) results.removeLast();

		information.setPosition(oldCursor);

		if (this.minimum < 0) {
			return new Result<>(results, true);
		} else {
			return new Result<>(results, false);
		}
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]>" + this.minimum;
	}
}
