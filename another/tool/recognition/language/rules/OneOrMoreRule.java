package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class OneOrMoreRule extends Rule {
	private final Rule syntaxes;

	public OneOrMoreRule(Rule syntaxes) { this.syntaxes = syntaxes; }

	public State onRuleExecute(LanguageTokenizerInformation information) {
		State state;
		if ((state = this.syntaxes.onRuleExecute(information)) != State.SUCCESS) {
			return state;
		}

		int oldCursor;
		do {
			oldCursor = information.getPosition();
		} while (this.syntaxes.onRuleExecute(information) == State.SUCCESS);

		information.setPosition(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return '[' + this.syntaxes.toString() + "]+";
	}
}
