package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class OptionalOrMoreSyntax implements Syntax {
	private final Syntax syntax;

	public OptionalOrMoreSyntax(Syntax syntax) { this.syntax = syntax; }

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		int oldCursor = information.getCursor();
		if (this.syntax.onSyntaxExecute(information) != State.SUCCESS) {
			information.setCursor(oldCursor);
			return State.SUCCESS;
		}

		do {
			oldCursor = information.getCursor();
		} while (this.syntax.onSyntaxExecute(information) == State.SUCCESS);

		information.setCursor(oldCursor);

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return "[" + this.syntax.toString() + "]?+";
	}
}
