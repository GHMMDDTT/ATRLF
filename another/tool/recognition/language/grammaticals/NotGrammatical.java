package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

public final class NotGrammatical implements Grammatical {
	private final Grammatical grammatical;

	public NotGrammatical(Grammatical grammatical) {
		this.grammatical = grammatical;
	}

	public Result onGrammaticalExecute(LanguageParserInformation information) {

		if (information.getPosition() >= information.getTarget().size()) return null;

		Result state = grammatical.onGrammaticalExecute(information);

		if (state == null) {
			information.setNextPosition();
			return new Result(null);
		}

		return null;
	}

	@Override
	public String toString() {
		return this.grammatical.toString() + '!';
	}
}
