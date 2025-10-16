package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

import java.util.Arrays;

public final class SequenceSyntax implements Syntax {
	private final Syntax[] syntaxes;

	public SequenceSyntax(Syntax... syntaxes) { this.syntaxes = syntaxes; }

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		for (Syntax syntax : this.syntaxes) {
			State state = syntax.onSyntaxExecute(information);
			if (state != State.SUCCESS) return state;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.syntaxes);
	}
}
