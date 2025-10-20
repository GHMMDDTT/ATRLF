package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class OptionalOrMoreRule extends Rule {
	private final Rule syntaxes;

	public OptionalOrMoreRule(Rule syntaxes) { this.syntaxes = syntaxes; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		int oldCursor = information.getPosition();
		if (this.syntaxes.onRuleExecute(information) != State.SUCCESS) {
			information.setPosition(oldCursor);
			return State.SUCCESS;
		}

		do {
			oldCursor = information.getPosition();
		} while (this.syntaxes.onRuleExecute(information) == State.SUCCESS);

		information.setPosition(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return "[" + this.syntaxes.toString() + "]?+";
	}
}
