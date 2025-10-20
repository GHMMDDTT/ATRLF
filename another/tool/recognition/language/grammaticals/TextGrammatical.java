package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

public final class TextGrammatical implements Grammatical {
	private final String syntax;

	public TextGrammatical(String syntax) { this.syntax = syntax; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		if (!information.hasMoreNextToken()) return null;

		if (information.getTarget().get(information.getPosition()).getValue().equals(this.syntax)) {
			information.setNextPosition();
			return new Result(null);
		}
		return null;
	}

	@Override
	public String toString() {
		return this.syntax;
	}
}
