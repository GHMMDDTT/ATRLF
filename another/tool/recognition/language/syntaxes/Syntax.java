package another.tool.recognition.language.syntaxes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;

public interface Syntax {
	public abstract State onSyntaxExecute(LanguageInterpreterInformation information);
}
