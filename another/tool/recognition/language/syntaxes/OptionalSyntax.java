package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class OptionalSyntax implements Syntax {
	private final Syntax syntax;

	public OptionalSyntax(Syntax syntax) { this.syntax = syntax; }

	public State onSyntaxExecute(LanguageInterpreterInformation information) {

		int oldCursor = information.getCursor();
		if (this.syntax.onSyntaxExecute(information) != State.SUCCESS) {
			information.setCursor(oldCursor);
			return State.SUCCESS;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.syntax.toString() + '?';
	}
}
