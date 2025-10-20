package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

public final class OptionalGrammatical implements Grammatical {
	private final Grammatical syntaxes;

	public OptionalGrammatical(Grammatical syntaxes) { this.syntaxes = syntaxes; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		Result result;
		int oldCursor = information.getPosition();

		if ((result = this.syntaxes.onGrammaticalExecute(information)) == null) {
			information.setPosition(oldCursor);
			return new Result(null);
		}

		return result;
	}

	@Override
	public String toString() {
		return this.syntaxes.toString() + '?';
	}
}
