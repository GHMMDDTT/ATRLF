package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class MoreRules extends Rules {
	private final Rules rules;

	public MoreRules(Rules rules) { this.rules = rules; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> arrayList = new ArrayList<>();
		int index = 0;

		arrayList.add(this.rules.onRegexExecute(information));
		if (arrayList.get(index++).isFailed()) {
			return new Result<>(arrayList, false);
		}

		arrayList.add(this.rules.onRegexExecute(information));
		if (arrayList.get(index++).isFailed()) {
			return new Result<>(arrayList, false);
		}

		int oldCursor;
		do {
			arrayList.add(this.rules.onRegexExecute(information));
			oldCursor = information.getPosition();
		} while (arrayList.get(index++).isSuccess());

		if (arrayList.getLast().isFailed()) arrayList.removeLast();

		information.setPosition(oldCursor);

		return new Result<>(arrayList, true);
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]++";
	}
}
