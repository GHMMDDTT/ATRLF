package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class NotSyntax implements Syntax {
	private final Syntax syntax;

	public NotSyntax(Syntax syntax) {
		this.syntax = syntax;
	}

	public State onSyntaxExecute(LanguageInterpreterInformation information) {

		if (information.getCursor() >= information.getTarget().size()) return State.FAILED;

		State state = syntax.onSyntaxExecute(information);

		if (state == State.FAILED) {
			information.advanceCursor();
			return State.SUCCESS;
		}

		return State.FAILED;
	}

	@Override
	public String toString() {
		return this.syntax.toString() + '!';
	}
}
