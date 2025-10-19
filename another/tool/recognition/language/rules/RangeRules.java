package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.ArrayList;

public final class RangeRules extends Rules {
	private final Rules rules;
	private final int minimum;
	private final int maximum;

	public RangeRules(Rules rules, int minimum, int maximum) {
		this.rules = rules;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		ArrayList<Result<?>> state = new ArrayList<>();

		if (information.getPosition() >= information.getTarget().length) return new Result<>("End Of Input!!!", false);

		int index = 0;
		int oldCursor = information.getPosition();
		while (index <= maximum) {
			state.add(rules.onRegexExecute(information));
			if (state.get(index).isFailed()) {
				if (index >= minimum) {
					information.setPosition(oldCursor);
					break;
				}
				else return new Result<>(state, false);
			}
			index++;
			oldCursor = information.getPosition();
		}
		return new Result<>(state, true);
	}

	@Override
	public String toString() {
		return this.rules.toString() + '{' + this.minimum + '-' + this.maximum + '}';
	}
}
