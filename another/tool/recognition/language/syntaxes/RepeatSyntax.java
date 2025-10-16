package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class RepeatSyntax implements Syntax {
	private final Syntax syntax;
	private final int repeat;

	public RepeatSyntax(Syntax syntax, int repeat) {
		this.syntax = syntax;
		this.repeat = repeat;
	}

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		State state;

		if (information.getCursor() >= information.getTarget().size()) return State.FAILED;

		int index = 0;
		while (index != repeat) {
			state = syntax.onSyntaxExecute(information);
			if (state != State.SUCCESS) return State.FAILED;
			index++;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.syntax.toString() + '{' + this.repeat + '}';
	}
}
