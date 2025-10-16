package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class RangeSyntax implements Syntax {
	private final Syntax syntax;
	private final int minimum;
	private final int maximum;

	public RangeSyntax(Syntax syntax, int minimum, int maximum) {
		this.syntax = syntax;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		State state;

		if (information.getCursor() >= information.getTarget().size()) return State.FAILED;

		int index = 0;
		int oldCursor = information.getCursor();
		while (index <= maximum) {
			state = syntax.onSyntaxExecute(information);
			if (state != State.SUCCESS) {
				if (index >= minimum) {
					information.setCursor(oldCursor);
					break;
				}
				else return State.FAILED;
			}
			index++;
			oldCursor = information.getCursor();
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.syntax.toString() + '{' + this.minimum + '-' + this.maximum + '}';
	}
}
