package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.Arrays;

public final class AnyOfRules extends Rules {
	private final char[] target;

	public AnyOfRules(char[] target) { this.target = target; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		for (char Char : this.target) {
			if (information.getTarget()[information.getPosition()] == Char) {
				information.setNextPosition();
				return new Result<>(information.getTarget()[information.getPosition() - 1], true);
			}
		}
		return new Result<>(information.getTarget()[information.getPosition() - 1], false);
	}

	@Override
	public String toString() {
		return Arrays.toString(this.target);
	}
}
