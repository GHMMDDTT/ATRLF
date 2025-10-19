package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class OptionalRules extends Rules {
	private final Rules rules;

	public OptionalRules(Rules rules) { this.rules = rules; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		int oldCursor = information.getPosition();

		Result<?> result;
		if ((result = this.rules.onRegexExecute(information)).isFailed()) {
			information.setPosition(oldCursor);
			return new Result<>("", true);
		}
		return new Result<>(result, true);
	}

	@Override
	public String toString() {
		return this.rules.toString() + '?';
	}
}
