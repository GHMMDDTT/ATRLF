package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class OneOrMoreSyntax implements Syntax {
	private final Syntax syntax;

	public OneOrMoreSyntax(Syntax syntax) { this.syntax = syntax; }

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		State state;
		if ((state = this.syntax.onSyntaxExecute(information)) != State.SUCCESS) {
			return state;
		}

		int oldCursor;
		do {
			oldCursor = information.getCursor();
		} while (information.getCursor() < information.getTarget().size() && this.syntax.onSyntaxExecute(information) == State.SUCCESS);

		information.setCursor(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return '[' + this.syntax.toString() + "]+";
	}
}
