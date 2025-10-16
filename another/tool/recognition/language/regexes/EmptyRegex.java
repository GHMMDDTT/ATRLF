package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class EmptyRegex extends Regex {
	public State onRegexExecute(LanguageTokenizerInformation information) {
		information.advanceCursor();
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return "";
	}
}
