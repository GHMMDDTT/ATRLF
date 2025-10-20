package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.Arrays;

public final class NoneOfRule extends Rule {
	private final char[] target;

	public NoneOfRule(char[] target) { this.target = target; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		for (char Char : this.target) {
			if (information.getTarget()[information.getPosition()] != Char) {
				information.setNextPosition();
				return State.SUCCESS;
			}
		}
		return State.FAILED;
	}

	@Override
	public String toString() {
		return '!' + Arrays.toString(this.target);
	}
}
