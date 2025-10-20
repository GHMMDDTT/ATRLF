package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class AllRule extends Rule {
	public State onRuleExecute(LanguageTokenizerInformation information) {
		information.setNextPosition();
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return ".";
	}
}
