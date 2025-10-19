package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class AllRules extends Rules {
	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		information.setNextPosition();
		return new Result<>(information.getTarget()[information.getPosition() - 1], true);
	}

	@Override
	public String toString() {
		return ".";
	}
}
