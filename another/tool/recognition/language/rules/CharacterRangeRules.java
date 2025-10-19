package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRangeRules extends Rules {
	private final char minimum;
	private final char maximum;

	public CharacterRangeRules(char minimum, char maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		if (information.getPosition() >= information.getTarget().length) return new Result<>("End Of Input!!!", false);

		if (information.getTarget()[information.getPosition()] >= this.minimum && information.getTarget()[information.getPosition()] <= this.maximum) {
			information.setNextPosition();
			return new Result<>(information.getTarget()[information.getPosition() - 1], true);
		}
		return new Result<>(information.getTarget()[information.getPosition()], false);
	}

	@Override
	public String toString() {
		return this.minimum + "-" + this.maximum;
	}
}
