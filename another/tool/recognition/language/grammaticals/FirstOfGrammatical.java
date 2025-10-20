package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

public final class FirstOfGrammatical implements Grammatical {
	private final Grammatical[] grammaticals;

	public FirstOfGrammatical(Grammatical... grammaticals) { this.grammaticals = grammaticals; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		Result state = null;
		for (Grammatical grammatical : this.grammaticals) {
			if ((state = grammatical.onGrammaticalExecute(information)) == null) return state;
		}
		return state;
	}

	@Override
	public String toString() {
		return toString(this.grammaticals);
	}

	private String toString(Grammatical[] grammaticals) {
		if (grammaticals == null) {
			return "null";
		} else {
			int iMax = grammaticals.length - 1;
			if (iMax == -1) {
				return "[]";
			} else {
				StringBuilder b = new StringBuilder();
				b.append('[');
				int i = 0;

				while(true) {
					b.append(grammaticals[i]);
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
