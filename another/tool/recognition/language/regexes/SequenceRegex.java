package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

import java.util.Arrays;

public final class SequenceRegex extends Regex {
	private final Regex[] regexes;

	public SequenceRegex(Regex... regexes) { this.regexes = regexes; }

	public State onRegexExecute(LanguageTokenizerInformation information) {
		for (Regex regex : this.regexes) {
			State state = regex.onRegexExecute(information);
			if (state != State.SUCCESS) return state;
		}
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return Arrays.toString(this.regexes);
	}
}
