package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class AlternativesRegex extends Regex {
	private final Regex[] regexes;

	public AlternativesRegex(Regex... regexes) { this.regexes = regexes; }

	public State onRegexExecute(LanguageTokenizerInformation information) {
		State state = State.FAILED;
		if (this.regexes.length == 0) return State.SUCCESS;
		for (Regex regex : this.regexes) {
			if ((state = regex.onRegexExecute(information)) == State.SUCCESS) return state;
		}
		return state;
	}

	@Override
	public String toString() {
		return toString(this.regexes);
	}

	private String toString(Regex[] regexes) {
		if (regexes == null) {
			return "null";
		} else {
			int iMax = regexes.length - 1;
			if (iMax == -1) {
				return "[]";
			} else {
				StringBuilder b = new StringBuilder();
				b.append('[');
				int i = 0;

				while(true) {
					b.append(regexes[i]);
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
