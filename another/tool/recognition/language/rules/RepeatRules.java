package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class RepeatRules extends Rules {
	private final Rules rules;
	private final int repeat;

	public RepeatRules(Rules rules, int repeat) {
		this.rules = rules;
		this.repeat = repeat;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> arrayList = new ArrayList<>();

		if (information.getPosition() >= information.getTarget().length) return new Result<>("End Of Input!!!", false);

		int index = 0;
		while (index != repeat) {
			arrayList.add(rules.onRegexExecute(information));
			if (arrayList.get(index).isFailed()) return new Result<>(arrayList, false);
			index++;
		}
		return new Result<>(arrayList, true);
	}

	@Override
	public String toString() {
		return this.rules.toString() + '{' + this.repeat + '}';
	}
}
