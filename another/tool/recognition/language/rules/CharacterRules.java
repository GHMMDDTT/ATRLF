package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class CharacterRules extends Rules {
	private final char target;

	public CharacterRules(char target) { this.target = target; }

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		if (information.getPosition() >= information.getTarget().length) return new Result<>("End Of Input!!!", false);

		if (information.getTarget()[information.getPosition()] == this.target) {
			if (information.getTarget()[information.getPosition()] == '\n' || information.getTarget()[information.getPosition()] == '\r') {
				information.setNextLine();
				information.setColumn(information.getPosition());
			}
			information.setNextPosition();
			return new Result<>(information.getTarget()[information.getPosition() - 1], true);
		}
		return new Result<>(information.getTarget()[information.getPosition()], false);
	}

	@Override
	public String toString() {
		return "" + this.target;
	}
}
