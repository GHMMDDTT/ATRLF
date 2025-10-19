package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class EmptyRules extends Rules {
	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		return new Result<>(information.getTarget()[information.getPosition()], true);
	}

	@Override
	public String toString() {
		return "";
	}
}
