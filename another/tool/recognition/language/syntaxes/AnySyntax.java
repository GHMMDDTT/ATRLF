package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class AnySyntax implements Syntax {
	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		information.advanceCursor();
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return ".";
	}
}
