package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public final class AlternativesSyntax implements Syntax {
	private final Syntax[] syntaxes;

	public AlternativesSyntax(Syntax... syntaxes) { this.syntaxes = syntaxes; }

	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		State state = State.FAILED;
		if (this.syntaxes.length == 0) return State.SUCCESS;
		for (Syntax syntax : this.syntaxes) {
			if ((state = syntax.onSyntaxExecute(information)) == State.SUCCESS) return state;
		}
		return state;
	}

	@Override
	public String toString() {
		return toString(this.syntaxes);
	}

	private String toString(Syntax[] syntaxes) {
		if (syntaxes == null) {
			return "null";
		} else {
			int iMax = syntaxes.length - 1;
			if (iMax == -1) {
				return "[]";
			} else {
				StringBuilder b = new StringBuilder();
				b.append('[');
				int i = 0;

				while(true) {
					b.append(syntaxes[i]);
					if (i == iMax) {
						return b.append(']').toString();
					}

					b.append(" | ");
					++i;
				}
			}
		}
	}


}
