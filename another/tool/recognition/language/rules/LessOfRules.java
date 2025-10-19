package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class LessOfRules extends Rules {
	private final Rules rules;
	private int minimum;

	public LessOfRules(Rules rules, int minimum) {
		this.rules = rules;
		this.minimum = minimum;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> arrayList = new ArrayList<>(this.minimum);

		int oldCursor;
		do {
			arrayList.add(this.rules.onRegexExecute(information));
			oldCursor = information.getPosition();
			this.minimum--;
		} while (arrayList.get(-(this.minimum - arrayList.size())).isSuccess());

		if (arrayList.getLast().isFailed()) arrayList.removeLast();

		information.setPosition(oldCursor);

		if (this.minimum >= 0) {
			return new Result<>(arrayList, true);
		} else {
			return new Result<>(arrayList, false);
		}
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]<" + this.minimum;
	}
}
