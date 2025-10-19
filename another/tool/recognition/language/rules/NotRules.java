package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class NotRules extends Rules {
	private final Rules rules;

	public NotRules(Rules rules) {
		this.rules = rules;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {

		if (information.getPosition() >= information.getTarget().length) return new Result<>("End Of Input!!!", false);

		Result<?> state = rules.onRegexExecute(information);

		if (state.isFailed()) {
			information.setNextPosition();
			return new Result<>(information.getTarget()[information.getPosition()], true);
		}

		return new Result<>(state, false);
	}

	@Override
	public String toString() {
		return '!' + this.rules.toString();
	}
}
